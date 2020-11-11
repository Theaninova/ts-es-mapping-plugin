package com.github.wulkanat.tsesmappingplugin.extensions

import com.intellij.lang.javascript.psi.jsdoc.JSDocTag
import com.intellij.psi.PsiComment

val PsiComment.docTags: List<JSDocTag>
    get() = children.filterIsInstance<JSDocTag>()
