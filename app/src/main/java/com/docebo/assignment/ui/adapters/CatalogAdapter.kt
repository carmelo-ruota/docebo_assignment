package com.docebo.assignment.ui.adapters

import android.text.Html
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.docebo.assignment.R
import com.docebo.assignment.data.model.Catalog
import com.docebo.assignment.utils.extensions.inflate
import kotlinx.android.synthetic.main.item_catalog.view.image_catalog
import kotlinx.android.synthetic.main.item_catalog.view.text_description
import kotlinx.android.synthetic.main.item_catalog.view.text_name
import kotlinx.android.synthetic.main.item_catalog.view.text_price
import kotlinx.android.synthetic.main.item_catalog.view.text_type
import java.util.Locale

class CatalogAdapter(items: ArrayList<Catalog?>, listener: ((Catalog?, Int) -> Unit)?) : LoadMoreAdapter<Catalog>(items, listener) {

    override fun getItemViewType(position: Int) = if (items[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        VIEW_TYPE_ITEM -> CatalogViewHolder(parent.inflate(R.layout.item_catalog))
        else -> LoadingViewHolder(parent.inflate(R.layout.item_loading))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = when (holder) {
        is CatalogViewHolder -> holder.bind(items[position]!!, listener)
        is LoadingViewHolder -> holder.bind()
        else -> {}
    }


    class CatalogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Catalog, listener: ((Catalog, Int) -> Unit)?) = with(itemView) {
            Glide.with(itemView.context).load(String.format("%s:%s","https",item.item_thumbnail)).into(image_catalog)
            text_name.text = item.item_name
            text_type.text = item.course_type
            text_price.text = String.format(Locale.getDefault(),"%s €",item.item_price)
            //TODO replace deprecated function
            text_description.text = Html.fromHtml(item.item_description)
        }
    }
}