package com.example.temper.utils

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    NO_NETWORK
}

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }
        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }
        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
        fun <T> noNetwork(data: T?): Resource<T> {
            return Resource(Status.NO_NETWORK, data, null)
        }
    }
}
