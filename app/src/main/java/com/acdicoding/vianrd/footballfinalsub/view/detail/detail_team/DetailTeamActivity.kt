package com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.acdicoding.vianrd.footballfinalsub.R
import com.acdicoding.vianrd.footballfinalsub.R.layout.activity_detail_tim
import com.acdicoding.vianrd.footballfinalsub.api.ApiRepository
import com.acdicoding.vianrd.footballfinalsub.db.FavoriteTeam
import com.acdicoding.vianrd.footballfinalsub.db.dab
import com.acdicoding.vianrd.footballfinalsub.model.Team
import com.acdicoding.vianrd.footballfinalsub.utils.SectionsPagerAdapter
import com.acdicoding.vianrd.footballfinalsub.utils.gone
import com.acdicoding.vianrd.footballfinalsub.utils.visible
import com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team.overview.OverviewFragment
import com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team.players.PlayersFragment
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_tim.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {

    private lateinit var id: String
    private lateinit var teamPresenter: DetailTeamPresenter
    private lateinit var teams: Team
    private val datas = Bundle()
    private lateinit var mSectionsPagerAdapter: SectionsPagerAdapter

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_detail_tim)

        setSupportActionBar(toolbar)

        val req = ApiRepository()
        val gson = Gson()
        id = intent.getStringExtra("team")

        favoriteState()

        teamPresenter = DetailTeamPresenter(this, req, gson)
        teamPresenter.getDataTeam(id)
        datas.putString("id", id)

        if (supportActionBar != null) {
            toolbar.inflateMenu(R.menu.menu_search)

            app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener{app_bar, verticalOffset-> when
            {
                Math.abs(verticalOffset) >= app_bar.totalScrollRange -> supportActionBar?.title = teams.teamName
                else -> supportActionBar?.title = ""
            }})

        }

        val vPager = findViewById<ViewPager>(R.id.viewpager)
        val mTabs = findViewById<TabLayout>(R.id.tabs)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        val ovFragment = OverviewFragment()
        ovFragment.arguments = datas
        val plFragment = PlayersFragment()
        plFragment.arguments = datas

        mSectionsPagerAdapter.populateFragment(ovFragment, "Overview")
        mSectionsPagerAdapter.populateFragment(plFragment, "Player")
        vPager.adapter = mSectionsPagerAdapter
        mTabs.setupWithViewPager(vPager)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.match_detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFav() else addToFav()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState(){
        dab.use{
            val res = select(FavoriteTeam.TABLE_FAV_TEAM)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to id)
            val favv = res.parseList(classParser<FavoriteTeam>())
            if (!favv.isEmpty()) isFavorite = true
        }
    }

    private fun addToFav(){
        try{
            dab.use{
                insert(
                    FavoriteTeam.TABLE_FAV_TEAM,
                    FavoriteTeam.ID to id,
                    FavoriteTeam.TEAM_ID to teams.teamId,
                    FavoriteTeam.TEAM_NAME to teams.teamName,
                    FavoriteTeam.TEAM_BADGE to teams.teamBadge)
            }
            snackbar(coordinator_layout, "Added to favorite").show()
        }catch (e: SQLiteConstraintException){
            snackbar(coordinator_layout, e.localizedMessage).show()
        }
    }

    private fun removeFromFav(){
        try{
            dab.use{
                delete(
                    FavoriteTeam.TABLE_FAV_TEAM,
                    "(TEAM_ID = {id})",
                    "id" to id)
            }
            snackbar(coordinator_layout, "Removed from favorite").show()
        }catch (e: SQLiteConstraintException){
            snackbar(coordinator_layout, e.localizedMessage).show()
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun setDataTeam(data: Team) {
        teams = data
        Picasso.get().load(data.teamBadge).into(img_team)
        tv_team_name.text = data.teamName
        tv_team_year.text = data.teamFormedYear
        tv_team_stadium.text = data.teamStadium
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

}
