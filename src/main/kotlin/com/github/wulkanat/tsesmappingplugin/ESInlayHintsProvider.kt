package com.github.wulkanat.tsesmappingplugin

import com.github.wulkanat.tsesmappingplugin.extensions.isESValidatable
import com.intellij.codeInsight.hints.HintInfo
import com.intellij.codeInsight.hints.InlayInfo
import com.intellij.codeInsight.hints.InlayParameterHintsProvider
import com.intellij.lang.javascript.JSTokenTypes.INTERFACE_KEYWORD
import com.intellij.lang.javascript.psi.ecma6.TypeScriptInterface
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType

class ESInlayHintsProvider : InlayParameterHintsProvider {
    override fun getParameterHints(element: PsiElement?): MutableList<InlayInfo> {
        if (element !is TypeScriptInterface || !element.isESValidatable) return mutableListOf()

        return mutableListOf(
            InlayInfo(
                "validatable",
                element.children.first { it.elementType == INTERFACE_KEYWORD }.textOffset,
                false, isFilterByBlacklist = false, relatesToPrecedingText = true
            )
        )
    }

    override fun getHintInfo(element: PsiElement?): HintInfo? {
        /*if (element !is TypeScriptInterface || !element.isESValidatable) return null

        return HintInfo()*/
        return null
    }

    override fun getDefaultBlackList(): MutableSet<String> {
        return mutableSetOf()
    }
}
