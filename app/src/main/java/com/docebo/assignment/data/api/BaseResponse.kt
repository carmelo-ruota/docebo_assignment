package com.docebo.assignment.data.api

class BaseResponse<T> {
    var status: Int = 200
    var code: Int = 0
    var message: String? = null
    var name: String? = null
    var data: T? = null
}