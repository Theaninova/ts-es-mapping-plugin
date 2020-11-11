package com.github.wulkanat.stappsesmapping.inspections

import com.github.wulkanat.stappsesmapping.ESArguments
import com.github.wulkanat.stappsesmapping.ESTags
import com.github.wulkanat.stappsesmapping.extensions.arguments
import com.github.wulkanat.stappsesmapping.extensions.typeElementWithoutArray
import com.intellij.codeHighlighting.HighlightDisplayLevel
import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.lang.javascript.inspections.JSInspection
import com.intellij.lang.javascript.psi.JSElementVisitor
import com.intellij.lang.javascript.psi.ecma6.TypeScriptPropertySignature
import com.intellij.lang.javascript.psi.jsdoc.JSDocComment
import com.intellij.lang.javascript.psi.jsdoc.JSDocTag
import com.intellij.psi.PsiElementVisitor

class ESArgumentsInspection : JSInspection() {
    override fun isEnabledByDefault() = true
    override fun getShortName() = "TagArgument"
    override fun getDisplayName() = "Check if arguments can apply"
    override fun getGroupPath() = arrayOf("Elasticsearch", "Mapping Generation")
    override fun getDefaultLevel(): HighlightDisplayLevel = HighlightDisplayLevel.ERROR
    override fun getStaticDescription() = "Check if arguments can apply to the tag."

    override fun createVisitor(holder: ProblemsHolder, localInspectionToolSession: LocalInspectionToolSession): PsiElementVisitor {
        return object : JSElementVisitor() {
            override fun visitJSDocComment(docComment: JSDocComment?) {
                val context = if (docComment?.context is TypeScriptPropertySignature) docComment.context as TypeScriptPropertySignature else return
                val esTag = ESTags.values().firstOrNull { it.tagString == docComment.name } ?: return

                docComment.children.filterIsInstance<JSDocTag>().forEach exit@{ tag ->
                    tag.arguments.forEach { element ->
                        val esArgument = ESArguments.values().firstOrNull { it.argumentName == element.text } ?: run {
                            holder.registerProblem(element, "Unknown parameter")
                            return@exit
                        }

                        if (esTag != esArgument.forTag) {
                            holder.registerProblem(element, "Parameter can only be applied to ${esArgument.forTag}")
                            return@exit
                        }

                        esArgument.forTypes?.let { tags ->
                            val type = context.typeElementWithoutArray()?.text ?: return@exit

                            tags.forEach { if (type == it.appliesToJSType) return@exit }
                            holder.registerProblem(element, "Parameter can't be applied to $type")
                        }
                    }
                }
            }
        }
    }
}
