package com.docebo.assignment.data.model

data class Data(
        val items: List<Catalog>,
        val count: Int,
        val has_more_data: Boolean,
        val cursor: Any,
        val current_page: Int,
        val current_page_size: Int,
        val total_page_count: Int,
        val total_count: Int
)
