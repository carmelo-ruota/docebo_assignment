package com.docebo.assignment.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.synthetic.main.item_loading.view.progressBar1

class LoadingViewHolder(itemView: View) : ViewHolder(itemView) {
    fun bind() = with(itemView) {
        progressBar1.isIndeterminate = true
    }
}

