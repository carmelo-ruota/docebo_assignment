package com.docebo.assignment.ui.adapters

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


/**
 * Created by Carmelo on 17/01/2018.
 */

abstract class LoadMoreAdapter<T>(var items: ArrayList<T?>, val listener: ((T?, Int) -> Unit)?) : RecyclerView.Adapter<ViewHolder>() {

    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0
    private var isLoading = false
    private var visibleThreshold = 2
    private lateinit var mRecyclerView: RecyclerView

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView

        val layoutManager = when (recyclerView.layoutManager) {
            is GridLayoutManager -> recyclerView.layoutManager as GridLayoutManager
            else -> {
                recyclerView.layoutManager as LinearLayoutManager
            }
        }
        layoutManager.let {
            mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    totalItemCount = layoutManager!!.getItemCount()
                    lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                    if (!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
                        isLoading = true
                        onLoadMoreListener?.onLoadMore()
                    }
                }
            })

            if (layoutManager is GridLayoutManager) {
                layoutManager.spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        if (getItemViewType(position) == LoadMoreAdapter.VIEW_TYPE_LOADING)
                            return layoutManager.spanCount
                        return 1
                    }
                }
            }
        }
    }

    fun setLoaded() {
        isLoading = false
    }

    companion object {
        val VIEW_TYPE_ITEM: Int = 0
        val VIEW_TYPE_LOADING: Int = 1
    }

    private var onLoadMoreListener: OnLoadMoreListener? = null
    fun setOnLoadMoreListener(mOnLoadMoreListener: OnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener
    }

    override fun getItemCount() = items.size


}