package com.example.nodemcuv3

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("responseStatus")
    val responseStatus: String,
    @SerializedName("ledStatus")
    val ledStatus: String
)
