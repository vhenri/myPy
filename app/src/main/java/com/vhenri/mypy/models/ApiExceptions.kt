package com.vhenri.mypy.models

open class AppException(t: Throwable? = null) : Exception()

open class AppApiException(t: Throwable? = null) : AppException(t)

class AppApiResponseException(errorBody: String) : AppApiException()

class AppMalformedJsonException(errorBody: String): AppApiException()

class EmptyResponseBodyException : AppApiException()

class NetworkException(t: Throwable) : AppApiException(t)

class UndefinedException(t: Throwable) : AppApiException(t)

fun AppException.getUserExceptionMsg() : String {
    return when(this){
        is AppApiResponseException -> "Reason: Error in Api Response"
        is AppMalformedJsonException -> "Reason: Malformed Json"
        is EmptyResponseBodyException -> "Reason: Empty Response body"
        is NetworkException -> "Reason: Network Error occurred"
        else -> "Reason: Unknown"
    }
}