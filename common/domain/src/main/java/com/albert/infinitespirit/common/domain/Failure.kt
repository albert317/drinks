package com.albert.infinitespirit.common.domain

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    data class CustomError(val errorMessage: String) : Failure()
    // Puedes agregar más tipos de errores aquí
}