package com.docebo.assignment.base

open class BasePresenter(view: BaseView) {

    private var isAttached = false

    fun onAttach() {
        isAttached = true
    }

    fun onDetach() {
        isAttached = false
    }
}