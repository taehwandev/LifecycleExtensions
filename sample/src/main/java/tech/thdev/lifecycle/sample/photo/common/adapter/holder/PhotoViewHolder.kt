package tech.thdev.lifecycle.sample.photo.common.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_photo_view.view.*
import tech.thdev.lifecycle.sample.photo.R
import tech.thdev.lifecycle.sample.photo.common.adapter.PhotoListAdapter
import tech.thdev.lifecycle.sample.photo.data.Photo

/**
 * Created by tae-hwan on 7/27/17.
 */
class PhotoViewHolder(val adapter: PhotoListAdapter,
                      parent: ViewGroup?) :
        RecyclerView.ViewHolder(LayoutInflater.from(adapter.context).inflate(R.layout.item_photo_view, parent, false)) {

    init {
        itemView.setOnClickListener {
            adapter.viewModel.onClickListener(adapterPosition)
        }
    }

    fun onBindViewHolder(item: Photo) {
        itemView.onViewCreated(item)
    }

    fun View.onViewCreated(item: Photo) {
        Glide.with(adapter.context)
                .load(item.getImageUrl())
                .into(circle_iv_user)
    }
}