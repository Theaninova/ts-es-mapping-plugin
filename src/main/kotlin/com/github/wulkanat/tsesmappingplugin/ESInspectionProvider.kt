package com.github.wulkanat.tsesmappingplugin

import com.github.wulkanat.tsesmappingplugin.inspections.ESArgumentsInspection
import com.github.wulkanat.tsesmappingplugin.inspections.TypeCheckInspection
import com.intellij.codeInspection.InspectionToolProvider
import com.intellij.codeInspection.LocalInspectionTool

class ESInspectionProvider : InspectionToolProvider {
    override fun getInspectionClasses(): Array<Class<out LocalInspectionTool>> {
        return arrayOf(
            TypeCheckInspection::class.java,
            ESArgumentsInspection::class.java,
        )
    }
}
