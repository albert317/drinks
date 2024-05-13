package com.albert.infinitespirit.presentation

import java.util.Locale

fun String.normalize(): String {
    val original = arrayOf('á', 'é', 'í', 'ó', 'ú', 'ü')
    val replacement = arrayOf('a', 'e', 'i', 'o', 'u', 'u')
    var result = this.lowercase(Locale.ROOT)
    for (i in original.indices) {
        result = result.replace(original[i], replacement[i])
    }
    return result
}