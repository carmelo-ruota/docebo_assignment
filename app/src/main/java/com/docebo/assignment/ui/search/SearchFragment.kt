package com.docebo.assignment.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.docebo.assignment.R
import com.docebo.assignment.R.layout
import com.docebo.assignment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_search.button_search
import kotlinx.android.synthetic.main.fragment_search.spinner_type
import java.util.Collections


class SearchFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTypeAdapter()
        button_search.setOnClickListener {
            //TODO launch search
        }
    }

    private fun setTypeAdapter() {
        val adapter = ArrayAdapter.createFromResource(
                activity,
                R.array.type_array,
                android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_type.adapter = adapter
    }
}
