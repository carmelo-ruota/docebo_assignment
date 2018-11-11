package com.docebo.assignment.base

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment(), BaseView {

    override fun showLoader() {
    }

    override fun dismissLoader() {
    }

    override fun showError(message: String) {
    }
}