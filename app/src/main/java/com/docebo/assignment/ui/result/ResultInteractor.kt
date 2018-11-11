package com.docebo.assignment.ui.result

import com.docebo.assignment.base.BaseView
import com.docebo.assignment.data.model.Catalog

interface ResultInteractor {
    interface View : BaseView {
        fun setCount(count : Int)
        fun setData(data : List<Catalog>)
        fun appendData(data : List<Catalog>)
    }
    interface Presenter {
        fun search()
        fun hasMore() : Boolean
    }
}