package com.github.wulkanat.stappsesmapping

enum class ESArguments(val argumentName: String, val forTypes: List<ESTypeTags>? = null, val forTag: ESTags? = null, val description: String? = null) {
    DUCET("ducet", ESTypeTags.jsString, ESTags.SORTABLE, "Ducet: Sort by Default Unicode Collation Element Table, the default sorting used in most places"),
    PRICE("price", ESTypeTags.jsNumbers, ESTags.SORTABLE, "Pricing: Sort by pricing"),
    DISTANCE("distance", null, ESTags.SORTABLE, "Distance: Sort by distance"),
    GLOBAL("global", null, ESTags.AGGREGATABLE, "Global aggregation"),
}
