package com.vhenri.mypy.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExecResponse(
    val result: String,
)

data class ExecRequest(
    val language: String = "python",
    val command: String
)
