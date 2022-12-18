package com.example.task_app.data.network

sealed class ApiResultSealed<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : ApiResultSealed<T>(data)
    class Error<T>(message: String?, data: T?) : ApiResultSealed<T>(data, message)
    class Loading<T> : ApiResultSealed<T>()
}