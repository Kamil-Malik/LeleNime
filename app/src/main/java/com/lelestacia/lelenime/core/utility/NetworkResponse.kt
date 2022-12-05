package com.lelestacia.lelenime.core.utility

sealed class NetworkResponse<out T> {
    object None : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()
    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data class Error(val code: Int, val message: String) : NetworkResponse<Nothing>()
}
