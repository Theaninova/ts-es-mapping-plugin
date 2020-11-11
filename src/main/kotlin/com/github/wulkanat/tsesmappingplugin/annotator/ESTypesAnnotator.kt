package com.github.wulkanat.tsesmappingplugin.annotator

import com.github.wulkanat.tsesmappingplugin.ESArguments
import com.github.wulkanat.tsesmappingplugin.ESTypeTags
import com.github.wulkanat.tsesmappingplugin.ElasticsearchHighlighter
import com.github.wulkanat.tsesmappingplugin.extensions.arguments
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.javascript.ecmascript6.TypeScriptAnnotatorCheckerProvider
import com.intellij.lang.javascript.psi.ecma6.TypeScriptPropertySignature
import com.intellij.lang.javascript.psi.ecma6.TypeScriptType
import com.intellij.lang.javascript.psi.jsdoc.JSDocTag
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement

class ESTypesAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is JSDocTag -> visitDocTag(element, holder)
            is TypeScriptPropertySignature -> visitTypeScriptPropertySignature(element, holder)
        }
    }

    private fun visitTypeScriptPropertySignature(element: TypeScriptPropertySignature, holder: AnnotationHolder) {
        val tags = element.children.filterIsInstance<PsiComment>().firstOrNull()?.children?.filterIsInstance<JSDocTag>() ?: return
        val type = element.children.filterIsInstance<TypeScriptType>().firstOrNull() ?: return

        if (tags.find { it.name == "integer" } != null) {
            holder.createInfoAnnotation(type, "Elasticsearch integer type").apply {
            }
        }
    }

    private fun visitDocTag(element: JSDocTag, holder: AnnotationHolder) {
        when (element.name) {
            null -> return

            "indexable" -> {
                val annotation = holder.createAnnotation(TypeScriptAnnotatorCheckerProvider.TS_INFORMATION, element.textRange, "")
                annotation.textAttributes = ElasticsearchHighlighter.ES_KEYWORD
                annotation.tooltip = "Marks this interface to be processed by the Elasticsearch mapping generator"
            }
            "filterable" -> {
                val annotation = holder.createAnnotation(TypeScriptAnnotatorCheckerProvider.TS_INFORMATION, element.textRange, "")
                annotation.textAttributes = ElasticsearchHighlighter.ES_KEYWORD
                annotation.tooltip = "Make the field filterable"
            }
            "sortable" -> {
                val annotation = holder.createAnnotation(TypeScriptAnnotatorCheckerProvider.TS_INFORMATION, element.textRange, "")
                annotation.textAttributes = ElasticsearchHighlighter.ES_KEYWORD
                annotation.tooltip = "Allow for sorting of the field"
            }
            "inheritTags" -> {
                val annotation = holder.createAnnotation(TypeScriptAnnotatorCheckerProvider.TS_INFORMATION, element.textRange, "")
                annotation.textAttributes = ElasticsearchHighlighter.ES_KEYWORD
                annotation.tooltip = "Inherit tags from another field"
            }
            "aggregatable" -> {
                val annotation = holder.createAnnotation(TypeScriptAnnotatorCheckerProvider.TS_INFORMATION, element.textRange, "")
                annotation.textAttributes = ElasticsearchHighlighter.ES_KEYWORD
                annotation.tooltip = "Make the field appear in aggregations/facets"
            }
            else -> {
                ESTypeTags.values().firstOrNull { it.tagName == element.name }?.let { tag ->
                    val annotation = holder.createAnnotation(TypeScriptAnnotatorCheckerProvider.TS_INFORMATION, element.textRange, "")
                    annotation.textAttributes = ElasticsearchHighlighter.ES_KEYWORD
                    annotation.tooltip = "Store as ${tag.esName} in Elasticsearch${tag.description?.let {".\n\n$it"} ?: ""}"
                }
            }
        }

        element.arguments.forEach { argument ->
            val annotation = holder.createAnnotation(TypeScriptAnnotatorCheckerProvider.TS_INFORMATION, argument.textRange, "")
            annotation.textAttributes = ElasticsearchHighlighter.ES_ARGUMENT

            ESArguments.values().firstOrNull { it.argumentName == argument.text }?.let {
                annotation.tooltip = it.description
            }
        }
    }
}
