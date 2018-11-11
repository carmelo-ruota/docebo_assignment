package com.docebo.assignment.base

interface BasePresenter<V : BaseView> {
    fun onAttach()
    fun onDetach()
}