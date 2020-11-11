package com.github.wulkanat.tsesmappingplugin.inspections

import com.github.wulkanat.tsesmappingplugin.extensions.typeElementWithoutArray
import com.intellij.codeHighlighting.HighlightDisplayLevel
import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.lang.javascript.inspections.JSInspection
import com.intellij.lang.javascript.psi.JSElementVisitor
import com.intellij.lang.javascript.psi.ecma6.TypeScriptInterface
import com.intellij.lang.javascript.psi.ecma6.TypeScriptPropertySignature
import com.intellij.lang.javascript.psi.jsdoc.JSDocComment
import com.intellij.psi.PsiElementVisitor

class TypeCheckInspection : JSInspection() {
    override fun isEnabledByDefault() = true
    override fun getShortName() = "TypeMatch"
    override fun getDisplayName() = "Check if type tag is compatible"
    override fun getGroupPath() = arrayOf("Elasticsearch", "Mapping Generation")
    override fun getDefaultLevel(): HighlightDisplayLevel = HighlightDisplayLevel.ERROR
    override fun getStaticDescription() = "Check if the type tag is compatible with the JavaScript type."

    override fun createVisitor(holder: ProblemsHolder, localInspectionToolSession: LocalInspectionToolSession): PsiElementVisitor {
        return object : JSElementVisitor() {
            override fun visitJSDocComment(docComment: JSDocComment?) {
                when (docComment?.context) {
                    is TypeScriptPropertySignature -> {
                        docComment.tags.forEach { tag ->
                            when (tag.name) {
                                "integer", "float" -> {
                                    if ((docComment.context as TypeScriptPropertySignature).typeElementWithoutArray()?.text != "number") {
                                        holder.registerProblem(tag, "@${tag.name} is only supported for number type")
                                    }
                                }
                                "keyword", "text" -> {
                                    if ((docComment.context as TypeScriptPropertySignature).typeElementWithoutArray()?.text != "string") {
                                        holder.registerProblem(tag, "@${tag.name} is only supported for string type")
                                    }
                                }
                            }
                        }
                    }
                    is TypeScriptInterface -> {
                        docComment.tags.forEach { tag ->
                            when (tag.name) {
                                "integer", "float", "keyword", "text", "filterable", "aggregatable", "inheritTags" -> {
                                    holder.registerProblem(tag, "Tag not supported for interfaces")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
