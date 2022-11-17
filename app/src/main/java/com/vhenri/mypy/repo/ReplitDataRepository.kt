package com.vhenri.mypy.repo

import com.github.michaelbull.result.Result
import com.vhenri.mypy.models.AppApiException
import com.vhenri.mypy.models.ExecResponse
import com.vhenri.mypy.network.ReplitApiClient
import javax.inject.Inject

interface ReplitDataRepositoryInterface {
    suspend fun executeCode(language: String?, command: String): Result<ExecResponse?, AppApiException>
}

class ReplitDataRepository @Inject constructor (
    private val replitApiClient: ReplitApiClient
    ): ReplitDataRepositoryInterface {

    override suspend fun executeCode(
        language: String?,
        command: String
    ): Result<ExecResponse?, AppApiException> {
        return replitApiClient.executeCode( language, command )
    }

}