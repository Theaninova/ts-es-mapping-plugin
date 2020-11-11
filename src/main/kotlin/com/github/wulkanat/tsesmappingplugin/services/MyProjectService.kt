package com.github.wulkanat.tsesmappingplugin.services

import com.intellij.openapi.project.Project
import com.github.wulkanat.tsesmappingplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
