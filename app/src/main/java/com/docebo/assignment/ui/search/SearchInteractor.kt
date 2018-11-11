package com.docebo.assignment.ui.search

import com.docebo.assignment.base.BaseView

interface SearchInteractor {
    interface View : BaseView {
        fun goToResult()
    }
    interface Presenter {
        fun setPreferences(name : String, type : String)
    }
}