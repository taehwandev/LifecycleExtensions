package tech.thdev.lifecycle.sample.photo.common.adapter.viewmodel

import android.arch.lifecycle.ViewModel
import tech.thdev.lifecycle.sample.photo.data.Photo

/**
 * Created by tae-hwan on 7/27/17.
 */
class PhotoListViewModel : ViewModel() {

    val list = mutableListOf<Photo>()

    val size: Int
        get() = list.size

    lateinit var onClickListener: (Int) -> Unit

    lateinit var notifyItemChanged: (Int) -> Unit

    lateinit var notifyDataSetChanged: () -> Unit

    fun getItem(position: Int) = list[position]

    fun addItem(item: Photo) = list.add(item)
}