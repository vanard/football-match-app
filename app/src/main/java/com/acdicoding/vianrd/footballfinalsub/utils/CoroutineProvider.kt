package com.acdicoding.vianrd.footballfinalsub.utils

import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

open class CoroutineProvider {
    open val main: CoroutineContext by lazy { UI }
}