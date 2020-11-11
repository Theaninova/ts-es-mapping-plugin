package com.github.wulkanat.tsesmappingplugin.extensions

import com.intellij.lang.javascript.psi.ecma6.TypeScriptInterface

val TypeScriptInterface.isESValidatable: Boolean
    get() = comment?.docTags?.first { it.isESValidatable } != null
