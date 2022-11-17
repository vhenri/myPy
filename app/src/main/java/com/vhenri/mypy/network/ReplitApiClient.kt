package com.vhenri.mypy.network

import com.github.michaelbull.result.Result
import com.vhenri.mypy.models.AppApiException
import com.vhenri.mypy.models.ExecResponse
import javax.inject.Inject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT

interface ReplitApi {
    @PUT("exec")
    suspend fun executeCode(
        @Body language: String? = "python",
        @Body command: String
    ): Response<ExecResponse>
}

class ReplitApiClient @Inject constructor(private val api: ReplitApi): BaseApiClient() {

    suspend fun executeCode(
        language: String?,
        command: String
    ): Result<ExecResponse?, AppApiException> {
        return execute { api.executeCode(language, command) }
    }
}