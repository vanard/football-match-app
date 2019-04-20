package com.acdicoding.vianrd.footballfinalsub

import com.acdicoding.vianrd.footballfinalsub.utils.CoroutineProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestCoroutineContextProvider : CoroutineProvider() {
    override val main: CoroutineContext = Unconfined
}