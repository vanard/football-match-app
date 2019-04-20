package com.acdicoding.vianrd.footballfinalsub.model

import com.google.gson.annotations.SerializedName

data class EventResp(@SerializedName("events") var events: List<Event>)