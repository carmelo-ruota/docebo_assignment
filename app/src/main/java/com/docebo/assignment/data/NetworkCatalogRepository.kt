package com.docebo.assignment.data

import com.docebo.assignment.data.api.BaseResponse
import com.docebo.assignment.data.api.DoceboService
import com.docebo.assignment.data.model.Data
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class NetworkCatalogRepository : CatalogRepository {


    private object Holder {
        val INSTANCE = NetworkCatalogRepository()
    }

    companion object {
        val instance: NetworkCatalogRepository by lazy { Holder.INSTANCE }

        var mName: String = ""
        var mType: String = "all"
    }


    var totalPageCount = 0
    private var currentPage = 1

    var disposableBad = CompositeDisposable()

    override fun setType(type: String) {
        mType = type
    }

    override fun setName(name: String) {
        mName = name
    }

    override fun hasNext(): Boolean {
        return currentPage < totalPageCount
    }

    override fun getNext(result: (Data) -> Unit, error: (String) -> Unit) {
        if (currentPage < totalPageCount) {
            currentPage++
            search(result, error)
        }
    }

    override fun search(result: (Data) -> Unit, error: (String) -> Unit) {
        val d = DoceboService().serviceAPI.getCatalog(mType, mName, currentPage)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        object : Consumer<BaseResponse<Data>> {
                            override fun accept(t: BaseResponse<Data>?) {
                                t?.let {
                                    totalPageCount = it.data!!.total_page_count
                                    result(it.data!!)
                                }
                            }
                        },
                        object : Consumer<Throwable> {
                            override fun accept(t: Throwable?) {
                                t?.localizedMessage?.let { error(it) }
                            }
                        }
                )
        disposableBad.add(d)
    }

    fun clearRequest() {
        disposableBad.clear()
    }

}