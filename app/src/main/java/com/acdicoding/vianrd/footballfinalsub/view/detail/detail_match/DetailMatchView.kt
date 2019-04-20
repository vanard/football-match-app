package com.acdicoding.vianrd.footballfinalsub.view.detail.detail_match

import com.acdicoding.vianrd.footballfinalsub.model.Event
import com.acdicoding.vianrd.footballfinalsub.model.Team

interface DetailMatchView {

    fun showLoading()
    fun hideLoading()
    fun setDataEvent(mEvent: Event)
    fun setLogoHomeTeam(team: Team)
    fun setLogoAwayTeam(team: Team)

}