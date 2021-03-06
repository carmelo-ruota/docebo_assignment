package com.docebo.assignment.data.model

data class Catalog(
        val item_id: String,
        val item_type: String,
        val item_code: String,
        val item_name: String,
        val item_description: String,
        val item_category: String,
        val item_language: String,
        val number_of_courses: Any,
        val item_price: String,
        val item_rating_option: String,
        val item_rating: Int,
        val is_new: Boolean,
        val is_user_enrolled: Any,
        val item_visibility: String,
        val item_policy: String,
        val item_can_enroll: String,
        val is_opened: String,
        val item_type_int: String,
        val course_type: String,
        val duration: String,
        val waiting: Any,
        val item_language_label: String,
        val item_thumbnail: String,
        val item_slug: String,
        val access_status: Int,
        val price_status: Int,
        val is_user_subscribed: Int,
        val is_affiliate: Boolean,
        val partner_fields: Any
)
