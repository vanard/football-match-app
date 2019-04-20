package com.acdicoding.vianrd.footballfinalsub.view.match

import com.acdicoding.vianrd.footballfinalsub.TestCoroutineContextProvider
import com.acdicoding.vianrd.footballfinalsub.api.ApiRepository
import com.acdicoding.vianrd.footballfinalsub.api.TheSportDbApi
import com.acdicoding.vianrd.footballfinalsub.model.Event
import com.acdicoding.vianrd.footballfinalsub.model.EventResp
import com.acdicoding.vianrd.footballfinalsub.view.matches.MatchesView
import com.acdicoding.vianrd.footballfinalsub.view.matches.next_match.NextMatchPresenter
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {
    @Mock
    private
    lateinit var view: MatchesView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepo: ApiRepository

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, apiRepo, gson, TestCoroutineContextProvider())
    }

    @Test
    fun testGetMatchList() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResp(events)
        val leagueId = "4328"

        Mockito.`when`(
            gson.fromJson(
                apiRepo
                    .doRequest(TheSportDbApi.getNextMatch(leagueId)),
                EventResp::class.java
            )
        ).thenReturn(response)

        presenter.getEventList(leagueId)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showMatchList(events)
        Mockito.verify(view).hideLoading()
    }

}