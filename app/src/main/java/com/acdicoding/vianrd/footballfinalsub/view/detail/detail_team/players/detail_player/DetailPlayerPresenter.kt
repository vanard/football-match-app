package com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team.players.detail_player

import com.acdicoding.vianrd.footballfinalsub.api.ApiRepository
import com.acdicoding.vianrd.footballfinalsub.api.TheSportDbApi
import com.acdicoding.vianrd.footballfinalsub.model.PlayerDetailResp
import com.acdicoding.vianrd.footballfinalsub.utils.CoroutineProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPlayerPresenter (private val view: DetailPlayerView,
                       private val apiRepo: ApiRepository,
                       private val gson: Gson,
                       private val context: CoroutineProvider = CoroutineProvider()
) {

    fun getPlayer(id: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepo
                        .doRequest(TheSportDbApi.getPlayerDetail(id)),
                    PlayerDetailResp::class.java
                )
            }
            view.setDataPlayer(data.await().players[0])
            view.hideLoading()
        }

    }

}