package com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team.players

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acdicoding.vianrd.footballfinalsub.R.layout.fragment_players
import com.acdicoding.vianrd.footballfinalsub.api.ApiRepository
import com.acdicoding.vianrd.footballfinalsub.model.Player
import com.acdicoding.vianrd.footballfinalsub.utils.gone
import com.acdicoding.vianrd.footballfinalsub.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_players.*
import org.jetbrains.anko.support.v4.ctx

class PlayersFragment : Fragment(), PlayerView {

    private var playerList: MutableList<Player> = mutableListOf()
    private lateinit var presenter: PlayerPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_players, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rcv_player.layoutManager = LinearLayoutManager(context)
        rcv_player.setHasFixedSize(true)
        rcv_player.adapter = PlayerAdapter(ctx, playerList)

        val req = ApiRepository()
        val gson = Gson()
        val datas = arguments?.getString("id")

        presenter = PlayerPresenter(this, req, gson)
        presenter.getPlayerList(datas!!)

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun setPlayerList(players: List<Player>) {
        playerList.clear()
        playerList.addAll(players)
        rcv_player.adapter?.notifyDataSetChanged()
    }

}