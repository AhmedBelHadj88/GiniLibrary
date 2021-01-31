package com.tda.ginilibrary.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tda.ginilibrary.R
import com.tda.ginilibrary.core.loadUrl
import com.tda.ginilibrary.data.mapper.CatImage

internal class CatsListAdapter(
    private var click: (CatImage) -> Unit
) :
    PagedListAdapter<CatImage, RecyclerView.ViewHolder>(DiffCallBack) {

    private var loadingState = LoadingState.LOADING

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageThumb: ImageView = itemView.findViewById(R.id.image_thumb)

        fun bindItem(catImage: CatImage) {
            imageThumb.loadUrl(catImage.url)
            itemView.setOnClickListener { click(catImage) }
        }
    }

    inner class LoadMoreHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_ITEM) {
            ViewHolder(inflater.inflate(R.layout.item_image, parent, false))
        } else {
            LoadMoreHolder(inflater.inflate(R.layout.item_load_more, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (position < super.getItemCount()) VIEW_TYPE_ITEM else VIEW_TYPE_LOAD

    override fun getItemCount(): Int = super.getItemCount() + if (hasFooter()) 1 else 0

    private fun hasFooter() = super.getItemCount() != 0 && loadingState == LoadingState.LOADING

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val cat = getItem(holder.adapterPosition)
            if (cat is CatImage) {
                holder.bindItem(cat)
            }
        }
    }

    object DiffCallBack : DiffUtil.ItemCallback<CatImage>() {
        override fun areItemsTheSame(oldItem: CatImage, newItem: CatImage): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: CatImage, newItem: CatImage): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        const val VIEW_TYPE_ITEM = 1
        const val VIEW_TYPE_LOAD = 2
    }
}
