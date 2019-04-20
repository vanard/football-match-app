package com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team.overview

import com.acdicoding.vianrd.footballfinalsub.model.Team

interface OverviewView {

    fun showLoading()
    fun hideLoading()
    fun setDataTeam(team: Team)

}