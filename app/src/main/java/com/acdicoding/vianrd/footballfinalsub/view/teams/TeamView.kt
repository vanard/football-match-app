package com.acdicoding.vianrd.footballfinalsub.view.teams

import com.acdicoding.vianrd.footballfinalsub.model.Team

interface TeamView {

    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)

}