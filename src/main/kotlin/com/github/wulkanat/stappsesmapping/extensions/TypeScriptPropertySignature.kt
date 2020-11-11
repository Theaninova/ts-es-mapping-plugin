package com.github.wulkanat.stappsesmapping.extensions

import com.intellij.lang.javascript.psi.JSReferenceExpression
import com.intellij.lang.javascript.psi.ecma6.TypeScriptArrayType
import com.intellij.lang.javascript.psi.ecma6.TypeScriptPropertySignature
import com.intellij.lang.javascript.psi.ecma6.TypeScriptType

fun TypeScriptPropertySignature.typeElementWithoutArray(): TypeScriptType? {
    val element = typeElement
    if (element !is TypeScriptType) return null
    return element.typeWithoutArray()
}

tailrec fun TypeScriptType.typeWithoutArray(): TypeScriptType? {
    return when (this) {
        is TypeScriptArrayType -> {
            this.firstChild.letIfIs<JSReferenceExpression> { reference ->
                return reference.deepResolve()?.nullIfNot<TypeScriptType>()
            }
            this.type?.typeWithoutArray()
        }
        else -> this
    }
}
