package com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team.players

import com.acdicoding.vianrd.footballfinalsub.api.ApiRepository
import com.acdicoding.vianrd.footballfinalsub.api.TheSportDbApi
import com.acdicoding.vianrd.footballfinalsub.model.PlayerResp
import com.acdicoding.vianrd.footballfinalsub.utils.CoroutineProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerPresenter (private val view: PlayerView,
                       private val apiRepo: ApiRepository,
                       private val gson: Gson,
                       private val context: CoroutineProvider = CoroutineProvider()
) {

    fun getPlayerList(id: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepo
                        .doRequest(TheSportDbApi.getAllPlayer(id)),
                    PlayerResp::class.java
                )
            }
            view.setPlayerList(data.await().players)
            view.hideLoading()
        }

    }

}