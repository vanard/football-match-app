package com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team.players.detail_player

import com.acdicoding.vianrd.footballfinalsub.model.Player

interface DetailPlayerView {

    fun showLoading()
    fun hideLoading()
    fun setDataPlayer(data: Player)

}