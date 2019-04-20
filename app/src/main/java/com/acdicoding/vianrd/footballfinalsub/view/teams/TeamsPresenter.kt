package com.acdicoding.vianrd.footballfinalsub.view.teams

import com.acdicoding.vianrd.footballfinalsub.api.ApiRepository
import com.acdicoding.vianrd.footballfinalsub.api.TheSportDbApi
import com.acdicoding.vianrd.footballfinalsub.model.TeamResp
import com.acdicoding.vianrd.footballfinalsub.utils.CoroutineProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter(private val view: TeamView,
                         private val apiRepo: ApiRepository,
                         private val gson: Gson,
                         private val ctx: CoroutineProvider = CoroutineProvider()
){

    fun getEventList(league: String?) {
        view.showLoading()

        async(ctx.main){
            val data = bg {
                gson.fromJson(apiRepo
                    .doRequest(TheSportDbApi.getAllTeam(league)),
                    TeamResp::class.java)
            }
            view.showTeamList(data.await().teams)
            view.hideLoading()
        }
    }

}