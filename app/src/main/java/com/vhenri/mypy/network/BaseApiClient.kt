package com.vhenri.mypy.network

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.squareup.moshi.JsonEncodingException
import com.vhenri.mypy.models.AppApiException
import com.vhenri.mypy.models.AppApiResponseException
import com.vhenri.mypy.models.AppMalformedJsonException
import retrofit2.Response

abstract class BaseApiClient() {

    protected suspend fun <T> execute(block: suspend () -> Response<T>): Result<T?, AppApiException> {
        return kotlin.runCatching {
            block.invoke()
        }.fold(
            onSuccess = {
                if (it.isSuccessful) {
                    Ok(it.body())
                } else {
                    Err(AppApiResponseException(it.errorBody().toString()))
                }
            },
            onFailure = {
                // TODO - add other errors as needed
                when (it){
                    is JsonEncodingException -> Err(AppMalformedJsonException(it.toString()))
                    else -> Err(AppApiException(it))
                }
            }
        )
    }
}