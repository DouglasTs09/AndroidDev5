package com.example.proy5.entidad

sealed class Result<out T> {
    data class Exito <out T: Any>(val data: T): Result<T>()
    data class Error (val mensaje: SimpleMensaje): Result<Nothing>()
}