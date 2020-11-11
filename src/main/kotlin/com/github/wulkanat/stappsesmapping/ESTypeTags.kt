package com.github.wulkanat.stappsesmapping

enum class ESTypeTags(val esName: String, val tag: ESTags, val appliesToJSType: String, val description: String? = null) {
    INTEGER("integer", ESTags.INTEGER, "number"),
    FLOAT("float", ESTags.FLOAT, "number", "For floating-point types, it is often more efficient to store floating-point data into an integer using a scaling factor, which is what the scaled_float type does under the hood."),
    KEYWORD("keyword", ESTags.KEYWORD, "string", "Keywords are used for structured content such as IDs, email addresses, hostnames, status codes, zip codes, or tags. Avoid using keyword fields for full-text search. Use the text field type instead."),
    TEXT(
        "text", ESTags.TEXT, "string",
        """
        A field to index full-text values, such as the body of an email or the description of a product. These fields are analyzed, that is they are passed through an analyzer to convert the string into a list of individual terms before being indexed. The analysis process allows Elasticsearch to search for individual words within each full text field. Text fields are not used for sorting and seldom used for aggregations (although the significant text aggregation is a notable exception).

        If you need to index structured content such as email addresses, hostnames, status codes, or tags, it is likely that you should rather use a keyword field.
        """.trimIndent()
    );

    companion object {
        val jsNumbers = listOf(INTEGER, FLOAT)
        val jsString = listOf(KEYWORD, TEXT)
    }
}
