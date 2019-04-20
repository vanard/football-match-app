package com.acdicoding.vianrd.footballfinalsub.view.team

import com.acdicoding.vianrd.footballfinalsub.TestCoroutineContextProvider
import com.acdicoding.vianrd.footballfinalsub.api.ApiRepository
import com.acdicoding.vianrd.footballfinalsub.api.TheSportDbApi
import com.acdicoding.vianrd.footballfinalsub.model.Team
import com.acdicoding.vianrd.footballfinalsub.model.TeamResp
import com.acdicoding.vianrd.footballfinalsub.view.teams.TeamView
import com.acdicoding.vianrd.footballfinalsub.view.teams.TeamsPresenter
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamPresenterTest {
    @Mock
    private
    lateinit var view: TeamView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepo: ApiRepository

    private lateinit var presenter: TeamsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamsPresenter(view, apiRepo, gson, TestCoroutineContextProvider())
    }

    @Test
    fun testGetMatchList() {
        val events: MutableList<Team> = mutableListOf()
        val response = TeamResp(events)
        val leagueName = "Spanish La Liga"

        Mockito.`when`(
            gson.fromJson(
                apiRepo
                    .doRequest(TheSportDbApi.getAllTeam(leagueName)),
                TeamResp::class.java
            )
        ).thenReturn(response)

        presenter.getEventList(leagueName)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(events)
        Mockito.verify(view).hideLoading()
    }

}