package tech.thdev.lifecycle.sample.photo.view.main.search

import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.fragment_list.*
import tech.thdev.base.view.BaseFragment
import tech.thdev.lifecycle.extensions.inject
import tech.thdev.lifecycle.sample.photo.R
import tech.thdev.lifecycle.sample.photo.common.adapter.PhotoListAdapter
import tech.thdev.lifecycle.sample.photo.common.adapter.viewmodel.PhotoListViewModel
import tech.thdev.lifecycle.sample.photo.data.source.search.PhotoSearchRepository
import tech.thdev.lifecycle.sample.photo.view.main.search.viewmodel.SearchViewModel

/**
 * Created by tae-hwan on 7/27/17.
 */
class SearchFragment : BaseFragment() {

    companion object {
        val INSTANCE: SearchFragment by lazy {
            Log.w("TEMP", "INSTANCE")
            SearchFragment()
        }
    }

    private val photoListAdapter: PhotoListAdapter by lazy {
        PhotoListAdapter(context, PhotoListViewModel().inject(this))
    }

    private val searchViewModel: SearchViewModel by lazy {
        SearchViewModel(PhotoSearchRepository, photoListAdapter.viewModel).inject(this)
    }

    override fun getLayoutResource() = R.layout.fragment_list

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.w("TEMP", "onViewCreated")

        recycler_view.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = photoListAdapter
        }

        searchViewModel.run {
            showProgress = {

            }
            hideProgress = {

            }
            searchGitHubUser("seoul")
        }
    }
}