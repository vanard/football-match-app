package com.acdicoding.vianrd.footballfinalsub.view.matches

import com.acdicoding.vianrd.footballfinalsub.model.Event

interface MatchesView {

    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Event>)

}