package com.acdicoding.vianrd.footballfinalsub.api

import org.junit.Test
import org.mockito.Mockito

class ApiRepositoryTest {
    @Test
    fun doRequestTest() {
        val apiRepo = Mockito.mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventslastleague.php?id=4328"

        apiRepo.doRequest(url)
        Mockito.verify(apiRepo).doRequest(url)
    }
}