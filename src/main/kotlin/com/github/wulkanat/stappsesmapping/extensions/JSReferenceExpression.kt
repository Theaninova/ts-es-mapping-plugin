package com.github.wulkanat.stappsesmapping.extensions

import com.intellij.lang.javascript.psi.JSReferenceExpression
import com.intellij.psi.PsiElement

tailrec fun JSReferenceExpression.deepResolve(): PsiElement? {
    return when (val resolvedType = resolve()) {
        is JSReferenceExpression -> resolvedType.deepResolve()
        else -> resolvedType
    }
}
