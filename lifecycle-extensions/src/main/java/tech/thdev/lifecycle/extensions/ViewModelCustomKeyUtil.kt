package tech.thdev.lifecycle.extensions

/**
 * Custom key default value
 */
const val DEFAULT_KEY = "tech.thdev.lifecycle.extensions.ViewModelProvider.DefaultKey"

fun <T> Class<T>.getCustomKey(): String = "$DEFAULT_KEY:${this.canonicalName}"