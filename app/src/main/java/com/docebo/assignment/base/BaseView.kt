package com.docebo.assignment.base

interface BaseView {
    fun showLoader()
    fun dismissLoader()
    fun showError(message : String)
}