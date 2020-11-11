package com.github.wulkanat.stappsesmapping

enum class ESTags(val tagString: String) {
    AGGREGATABLE("aggregatable"),
    FLOAT("float"),
    INDEXABLE("indexable"),
    INTEGER("integer"),
    KEYWORD("keyword"),
    SORTABLE("sortable"),
    TEXT("text"),
    FILTERABLE("filterable"),
    INHERIT_TAGS("inheritTags"),
}