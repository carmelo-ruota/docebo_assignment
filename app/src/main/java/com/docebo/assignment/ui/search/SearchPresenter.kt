package com.docebo.assignment.ui.search

import com.docebo.assignment.base.BasePresenter
import com.docebo.assignment.data.CatalogRepository

class SearchPresenter(val view : SearchInteractor.View, private val repository : CatalogRepository?) : BasePresenter(view) , SearchInteractor.Presenter {
    override fun setPreferences(name: String, type: String) {
        repository?.setName(name)
        repository?.setType(type)
        view.goToResult()
    }
}