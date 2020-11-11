package com.github.wulkanat.stappsesmapping.extensions

inline fun <reified T> Any.letIfIs(executor: (T) -> Unit) {
    if (this is T) {
        return executor(this)
    }

    return
}

inline fun <reified T> Any.nullIfNot(): T? {
    return if (this is T) this else null
}
