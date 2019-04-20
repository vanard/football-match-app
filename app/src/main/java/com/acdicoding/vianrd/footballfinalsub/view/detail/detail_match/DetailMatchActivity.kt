package com.acdicoding.vianrd.footballfinalsub.view.detail.detail_match

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.acdicoding.vianrd.footballfinalsub.R
import com.acdicoding.vianrd.footballfinalsub.R.drawable.ic_add_to_favorites
import com.acdicoding.vianrd.footballfinalsub.R.drawable.ic_added_to_favorites
import com.acdicoding.vianrd.footballfinalsub.R.id.add_to_favorite
import com.acdicoding.vianrd.footballfinalsub.R.menu.match_detail_menu
import com.acdicoding.vianrd.footballfinalsub.api.ApiRepository
import com.acdicoding.vianrd.footballfinalsub.db.FavoriteMatch
import com.acdicoding.vianrd.footballfinalsub.db.dab
import com.acdicoding.vianrd.footballfinalsub.model.Event
import com.acdicoding.vianrd.footballfinalsub.model.Team
import com.acdicoding.vianrd.footballfinalsub.utils.DateStringFormat
import com.acdicoding.vianrd.footballfinalsub.utils.gone
import com.acdicoding.vianrd.footballfinalsub.utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.*
import org.jetbrains.anko.design.snackbar

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {

    private lateinit var id: String
    private lateinit var matchPresenter: DetailMatchPresenter
    private lateinit var events: Event

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        val req = ApiRepository()
        val gson = Gson()
        id = intent.getStringExtra("event")

        favoriteState()

        supportActionBar?.title = getString(R.string.match_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        matchPresenter = DetailMatchPresenter(this, req, gson)
        matchPresenter.getEventDetail(id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(match_detail_menu, menu)
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
            add_to_favorite -> {
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
            val res = select(FavoriteMatch.TABLE_FAV_MATCH)
                .whereArgs("(EVENT_ID = {id})",
                    "id" to id)
            val favv = res.parseList(classParser<FavoriteMatch>())
            if (!favv.isEmpty()) isFavorite = true
        }
    }

    private fun addToFav(){
        try{
            dab.use{
                insert(FavoriteMatch.TABLE_FAV_MATCH,
                    FavoriteMatch.ID to id,
                    FavoriteMatch.EVENT_ID to events.idEvent,
                    FavoriteMatch.EVENT_DATE to events.dateEvent,
                    FavoriteMatch.EVENT_TIME to events.timeEvent,
                    FavoriteMatch.HOME_TEAM_NAME to events.homeTeam,
                    FavoriteMatch.AWAY_TEAM_NAME to events.awayTeam,
                    FavoriteMatch.HOME_SCORE to events.homeScore,
                    FavoriteMatch.AWAY_SCORE to events.awayScore)
            }
            snackbar(scrollView, "Added to favorite").show()
        }catch (e: SQLiteConstraintException){
            snackbar(scrollView, e.localizedMessage).show()
        }
    }

    private fun removeFromFav(){
        try{
            dab.use{
                delete(FavoriteMatch.TABLE_FAV_MATCH,
                    "(EVENT_ID = {id})",
                    "id" to id)
            }
            snackbar(scrollView, "Removed from favorite").show()
        }catch (e: SQLiteConstraintException){
            snackbar(scrollView, e.localizedMessage).show()
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun setDataEvent(mEvent: Event) {
        events = mEvent

        tv_tanggal.text = DateStringFormat.reformatStringDate(mEvent.dateEvent, DateStringFormat.DATE_FORMAT_YEAR_FIRST, DateStringFormat.DATE_FORMAT_FULL_DATE)
        tv_hour.text = DateStringFormat.reformatStringDate(mEvent.timeEvent, DateStringFormat.DATE_FORMAT_TIME, DateStringFormat.DATE_FORMAT_TIME_LOCAL)
        tv_home_team.text = mEvent.homeTeam
        tv_away_team.text = mEvent.awayTeam
        score_team_home.text = mEvent.homeScore
        score_team_away.text = mEvent.awayScore
        tv_home_goals.text = mEvent.homeGoalDetails ?: "Coming Soon"
        tv_away_goals.text = mEvent.awayGoalDetails ?: "Coming Soon"
        tv_home_shot.text = mEvent.homeShots ?: "Coming Soon"
        tv_away_shot.text = mEvent.awayShots ?: "Coming Soon"
        tv_home_goal_keeper.text = mEvent.homeLineupGoalkeeper ?: "Coming Soon"
        tv_away_goal_keeper.text = mEvent.awayLineupGoalkeeper ?: "Coming Soon"
        tv_home_def.text = mEvent.homeLineupDefense ?: "Coming Soon"
        tv_away_def.text = mEvent.awayLineupDefense ?: "Coming Soon"
        tv_home_mid.text = mEvent.homeLineupMidfield ?: "Coming Soon"
        tv_away_mid.text = mEvent.awayLineupMidfield ?: "Coming Soon"
        tv_home_fwd.text = mEvent.homeLineupForward ?: "Coming Soon"
        tv_away_fwd.text = mEvent.awayLineupForward ?: "Coming Soon"
        tv_home_subs.text = mEvent.homeLineUpSubstitutes ?: "Coming Soon"
        tv_away_subs.text = mEvent.awayLineUpSubstitutes ?: "Coming Soon"

        matchPresenter.getLogoAwayTeam(mEvent.idAwayTeam)
        matchPresenter.getLogoHomeTeam(mEvent.idHomeTeam)
    }

    override fun setLogoHomeTeam(team: Team) {
        Picasso.get().load(team.teamBadge).into(img_home)
    }

    override fun setLogoAwayTeam(team: Team) {
        Picasso.get().load(team.teamBadge).into(img_away)
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

}
