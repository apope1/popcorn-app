package com.example.app.core


sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Error<out T>(val exception: Throwable) : Result<T>()

    companion object {
        suspend operator fun <T> invoke(function: suspend () -> T): Result<T> = try {
            Success(function())
        } catch (exception: Exception) {
            Error(exception)
        }
    }
}
