package com.acdicoding.vianrd.footballfinalsub.view.detail.detail_match

import com.acdicoding.vianrd.footballfinalsub.api.ApiRepository
import com.acdicoding.vianrd.footballfinalsub.api.TheSportDbApi
import com.acdicoding.vianrd.footballfinalsub.model.EventResp
import com.acdicoding.vianrd.footballfinalsub.model.TeamResp
import com.acdicoding.vianrd.footballfinalsub.utils.CoroutineProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailMatchPresenter (private val matchView: DetailMatchView,
                            private val apiRepo: ApiRepository,
                            private val gson: Gson,
                            private val context: CoroutineProvider = CoroutineProvider()
) {

    fun getEventDetail(eventId: String?) {
        matchView.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepo
                        .doRequest(TheSportDbApi.getEventDetails(eventId)),
                    EventResp::class.java
                )
            }
            matchView.setDataEvent(data.await().events[0])
        }
    }

    fun getLogoAwayTeam(teamId: String?) {
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepo
                        .doRequest(TheSportDbApi.getTeamDetail(teamId)),
                    TeamResp::class.java
                )
            }
            matchView.setLogoAwayTeam(data.await().teams[0])
            matchView.hideLoading()
        }
    }

    fun getLogoHomeTeam(teamId: String?) {
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepo
                        .doRequest(TheSportDbApi.getTeamDetail(teamId)),
                    TeamResp::class.java
                )
            }
            matchView.setLogoHomeTeam(data.await().teams[0])
        }
    }

}