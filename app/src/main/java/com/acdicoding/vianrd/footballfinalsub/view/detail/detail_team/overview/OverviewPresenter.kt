package com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team.overview

import com.acdicoding.vianrd.footballfinalsub.api.ApiRepository
import com.acdicoding.vianrd.footballfinalsub.api.TheSportDbApi
import com.acdicoding.vianrd.footballfinalsub.model.TeamResp
import com.acdicoding.vianrd.footballfinalsub.utils.CoroutineProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class OverviewPresenter (private val v: OverviewView,
                         private val apiRepo: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineProvider = CoroutineProvider()
) {

    fun getDataTeam(id: String) {
        v.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepo
                        .doRequest(TheSportDbApi.getTeamDetail(id)),
                    TeamResp::class.java
                )
            }
            v.setDataTeam(data.await().teams[0])
            v.hideLoading()
        }

    }

}