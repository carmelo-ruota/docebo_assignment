package com.docebo.assignment.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.docebo.assignment.R
import com.docebo.assignment.R.layout
import com.docebo.assignment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_search.button_search
import kotlinx.android.synthetic.main.fragment_search.edit_name
import kotlinx.android.synthetic.main.fragment_search.spinner_type


class SearchFragment : BaseFragment(), SearchInteractor.View {

    var presenter: SearchPresenter = SearchPresenter(this, null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTypeAdapter()
        button_search.setOnClickListener {
            presenter.setPreferences(
                    edit_name.text.toString().trim(),
                    spinner_type.selectedView.findViewById<TextView>(android.R.id.text1).text.toString())
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

    override fun goToResult() {
        activity?.let { Navigation.findNavController(it,R.id.fragment).navigate(R.id.action_searchFragment_to_resultFragment) }
    }

    override fun onAttach(context: Context?) {
        presenter.onAttach()
        super.onAttach(context)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
