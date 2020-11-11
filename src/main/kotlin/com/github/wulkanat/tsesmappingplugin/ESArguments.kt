package com.github.wulkanat.tsesmappingplugin

enum class ESArguments(val argumentName: String, val forTypes: List<ESTypeTags>? = null, val forTag: String? = null, val description: String? = null) {
    DUCET("ducet", ESTypeTags.jsString, "sortable", "Ducet: Sort by Default Unicode Collation Element Table, the default sorting used in most places."),
    PRICE("price", ESTypeTags.jsNumbers, "sortable", "Pricing: Sort by pricing."),
    DISTANCE("distance", null, "sortable", "Distance: Sort by distance.")
}
