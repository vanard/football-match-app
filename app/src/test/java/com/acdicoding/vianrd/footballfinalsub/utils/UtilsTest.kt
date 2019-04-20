package com.acdicoding.vianrd.footballfinalsub.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest{
    @Test
    fun testDateStringFormat() {
        assertEquals("Mon, 05 Nov 2018",DateStringFormat.reformatStringDate("2018-11-05","yyyy-MM-dd", "E, dd MMM yyyy"))
    }
}