package com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team

import com.acdicoding.vianrd.footballfinalsub.model.Team

interface DetailTeamView {

    fun showLoading()
    fun hideLoading()
    fun setDataTeam(data: Team)

}