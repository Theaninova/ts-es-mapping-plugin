package com.github.wulkanat.tsesmappingplugin.extensions

import com.intellij.lang.javascript.psi.ecma6.TypeScriptArrayType
import com.intellij.lang.javascript.psi.ecma6.TypeScriptPropertySignature
import com.intellij.lang.javascript.psi.ecma6.TypeScriptType

fun TypeScriptPropertySignature.typeElementWithoutArray(): TypeScriptType? {
    val element = typeElement
    if (element !is TypeScriptType) return null
    return element.typeWithoutArray()
}

fun TypeScriptType.typeWithoutArray(): TypeScriptType? {
    return when (this) {
        is TypeScriptArrayType -> this.type?.typeWithoutArray()
        else -> this
    }
}
