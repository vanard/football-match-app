package com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team.players

import com.acdicoding.vianrd.footballfinalsub.model.Player

interface PlayerView {

    fun showLoading()
    fun hideLoading()
    fun setPlayerList(players: List<Player>)

}