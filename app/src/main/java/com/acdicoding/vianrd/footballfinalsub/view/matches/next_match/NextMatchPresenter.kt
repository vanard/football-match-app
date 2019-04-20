package com.acdicoding.vianrd.footballfinalsub.view.matches.next_match

import com.acdicoding.vianrd.footballfinalsub.api.ApiRepository
import com.acdicoding.vianrd.footballfinalsub.api.TheSportDbApi
import com.acdicoding.vianrd.footballfinalsub.model.EventResp
import com.acdicoding.vianrd.footballfinalsub.utils.CoroutineProvider
import com.acdicoding.vianrd.footballfinalsub.view.matches.MatchesView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextMatchPresenter(private val view: MatchesView,
                         private val apiRepo: ApiRepository,
                         private val gson: Gson,
                         private val ctx: CoroutineProvider = CoroutineProvider()
){

    fun getEventList(eventId: String?) {
        view.showLoading()

        async(ctx.main){
            val data = bg {
                gson.fromJson(apiRepo
                    .doRequest(TheSportDbApi.getNextMatch(eventId)),
                    EventResp::class.java)
            }
            view.showMatchList(data.await().events)
            view.hideLoading()
        }
    }

}