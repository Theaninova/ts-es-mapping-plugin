package com.github.wulkanat.tsesmappingplugin

import com.intellij.lang.javascript.JavaScriptHighlightingLexer
import com.intellij.lang.javascript.highlighting.JSHighlighter
import com.intellij.lang.javascript.highlighting.TypeScriptHighlighter
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.markup.EffectType
import com.intellij.openapi.editor.markup.TextAttributes
import java.awt.Font

object ElasticsearchHighlighter {
    private val ES_COLOR = TypeScriptHighlighter.TS_REGEXP.defaultAttributes.foregroundColor
    private val KEYWORD_COLOR = TypeScriptHighlighter.TS_KEYWORD.defaultAttributes.foregroundColor
    private val ARGUMENT_COLOR = TypeScriptHighlighter.TS_PARAMETER.defaultAttributes.foregroundColor

    val ES_VALIDATABLE = TextAttributesKey.createTextAttributesKey(
        "ES.VALIDATABLE",
        TextAttributes(
            ES_COLOR,
            null, ES_COLOR, EffectType.LINE_UNDERSCORE, Font.BOLD
        )
    )
    val ES_KEYWORD = TextAttributesKey.createTextAttributesKey(
        "ES.KEYWORD",
        TextAttributes(
            KEYWORD_COLOR,
            null, KEYWORD_COLOR, EffectType.LINE_UNDERSCORE, Font.BOLD
        )
    )
    val ES_ARGUMENT = TextAttributesKey.createTextAttributesKey(
        "ES.ARGUMENT",
        TextAttributes(
            ARGUMENT_COLOR,
            null, null, EffectType.STRIKEOUT, Font.PLAIN
        )
    )
}
