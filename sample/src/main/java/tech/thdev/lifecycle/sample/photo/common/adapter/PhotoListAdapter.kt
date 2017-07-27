package tech.thdev.lifecycle.sample.photo.common.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.thdev.lifecycle.sample.photo.common.adapter.holder.PhotoViewHolder
import tech.thdev.lifecycle.sample.photo.common.adapter.viewmodel.PhotoListViewModel

/**
 * Created by tae-hwan on 7/27/17.
 */
class PhotoListAdapter(val context: Context, val viewModel: PhotoListViewModel) : RecyclerView.Adapter<PhotoViewHolder>() {

    init {
        viewModel.run {
            notifyDataSetChanged = {
                this@PhotoListAdapter.notifyDataSetChanged()
            }
            notifyItemChanged = { position ->
                this@PhotoListAdapter.notifyItemChanged(position)
            }
        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder?, position: Int) {
        holder?.onBindViewHolder(viewModel.getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            PhotoViewHolder(this, parent)

    override fun getItemCount() = viewModel.size
}