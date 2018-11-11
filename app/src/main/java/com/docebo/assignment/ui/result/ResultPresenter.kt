package com.docebo.assignment.ui.result

import com.docebo.assignment.base.BasePresenter
import com.docebo.assignment.data.CatalogRepository

class ResultPresenter(val view: ResultInteractor.View, private val repository: CatalogRepository?) : BasePresenter(view), ResultInteractor.Presenter {

    override fun search() {
        repository?.search(
                {
                    view.setCount(it.total_count)
                    view.setData(it.items)
                },
                {
                    view.showError(it)
                })
    }

    override fun hasMore(): Boolean {
        return repository?.hasNext()!!
    }

    fun getNext(){
        repository?.getNext(
                {
                    view.appendData(it.items)
                },
                {
                    view.showError(it)
                })
    }

}