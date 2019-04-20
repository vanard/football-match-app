package com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team.players.detail_player

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.acdicoding.vianrd.footballfinalsub.R.layout.activity_detail_player
import com.acdicoding.vianrd.footballfinalsub.api.ApiRepository
import com.acdicoding.vianrd.footballfinalsub.model.Player
import com.acdicoding.vianrd.footballfinalsub.utils.gone
import com.acdicoding.vianrd.footballfinalsub.utils.visible
import com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team.DetailTeamActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity(), DetailPlayerView {

    private lateinit var id: String
    private lateinit var player: Player
    private lateinit var presenter: DetailPlayerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_detail_player)

        val req = ApiRepository()
        val gson = Gson()
        id = intent.getStringExtra("id")
        presenter = DetailPlayerPresenter(this, req, gson)
        presenter.getPlayer(id)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val i = Intent(this, DetailTeamActivity::class.java)
                i.putExtra("team", player.teamId)
                startActivity(i)
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun setDataPlayer(data: Player) {
        player = data

        Picasso.get().load(data.playerThumb).into(img_thumbnail)
        tv_berat.text = data.playerWeight?: "-"
        tv_tinggi.text = data.playerHeight?: "-"
        tv_post.text = data.playerPost
        tv_description.text = data.playerDesc

        supportActionBar?.title = player.playerName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val i = Intent(this, DetailTeamActivity::class.java)
        i.putExtra("team", player.teamId)
        startActivity(i)
        finish()
    }

}
