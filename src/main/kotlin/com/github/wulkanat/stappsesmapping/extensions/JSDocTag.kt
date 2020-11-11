package com.github.wulkanat.stappsesmapping.extensions

import com.intellij.lang.javascript.JSDocTokenTypes.DOC_COMMENT_DATA
import com.intellij.lang.javascript.JSDocTokenTypes.DOC_COMMENT_END
import com.intellij.lang.javascript.JSDocTokenTypes.DOC_COMMENT_LEADING_ASTERISK
import com.intellij.lang.javascript.psi.jsdoc.JSDocTag
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType

val JSDocTag.isESValidatable
    get() = name == "validatable"

val JSDocTag.arguments: List<PsiElement>
    get() {
        val out = mutableListOf<PsiElement>()
        var currentNode = nextSibling
        while (currentNode != null && currentNode.elementType != DOC_COMMENT_LEADING_ASTERISK && currentNode.elementType != DOC_COMMENT_END) {
            if (currentNode.elementType == DOC_COMMENT_DATA) {
                out.add(currentNode)
            }

            currentNode = currentNode.nextSibling
        }

        return out
    }
