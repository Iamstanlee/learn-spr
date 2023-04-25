package com.learn.spr.util

interface Response<T> {
    val status: Boolean
    val message: String?
    val data: T?
}

data class SuccessResponse<T>(
    override val status: Boolean,
    override val message: String?,
    override val data: T?,
) : Response<T> {
    constructor(data: T) : this(true, null, data)
}

data class ErrorResponse<T>(
    override val status: Boolean,
    override val message: String?,
    override val data: T?,
) : Response<T> {
    constructor(message: String) : this(false, message, null)
}