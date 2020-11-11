package com.github.wulkanat.tsesmappingplugin.extensions

import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement

val PsiElement.comment: PsiComment?
    get() = children.filterIsInstance<PsiComment>().firstOrNull()
