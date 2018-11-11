package com.docebo.assignment.ui.result

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.docebo.assignment.R
import com.docebo.assignment.R.layout
import com.docebo.assignment.base.BaseFragment
import com.docebo.assignment.data.NetworkCatalogRepository
import com.docebo.assignment.data.model.Catalog
import com.docebo.assignment.ui.adapters.CatalogAdapter
import com.docebo.assignment.ui.adapters.OnLoadMoreListener
import kotlinx.android.synthetic.main.fragment_result.recycler_results
import kotlinx.android.synthetic.main.fragment_result.text_total


class ResultFragment : BaseFragment(), ResultInteractor.View {

    private var presenter: ResultPresenter = ResultPresenter(this, NetworkCatalogRepository.instance)
    private lateinit var adapter: CatalogAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CatalogAdapter(arrayListOf(), null)
        val layoutManager = LinearLayoutManager(activity)
        recycler_results.setLayoutManager(layoutManager)
        recycler_results.setAdapter(adapter)
        recycler_results.setItemAnimator(DefaultItemAnimator())
        adapter.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                if (presenter.hasMore() && adapter.items.last() != null) {
                    adapter.items.add(null)
                    adapter.notifyItemInserted(adapter.itemCount - 1)
                    Handler().postDelayed(object : Runnable {
                        override fun run() {
                            presenter.getNext()
                        }
                    }, 500)
                }
            }
        })
        presenter.search()
    }

    override fun setCount(count: Int) {
        text_total.setText(String.format("%s: %d",getString(R.string.total),count))
    }

    override fun setData(data: List<Catalog>) {
        adapter.items.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun appendData(data: List<Catalog>) {
        adapter.items.removeAt(adapter.itemCount - 1)
        adapter.notifyItemRemoved(adapter.itemCount)
        adapter.items.addAll(data)
        adapter.notifyDataSetChanged()
        adapter.setLoaded()
    }


}
