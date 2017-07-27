package tech.thdev.lifecycle.sample.photo.view.main.search.viewmodel

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers
import tech.thdev.lifecycle.sample.photo.common.adapter.viewmodel.PhotoListViewModel
import tech.thdev.lifecycle.sample.photo.contract.Contract
import tech.thdev.lifecycle.sample.photo.data.source.search.PhotoSearchDataSource
import java.util.concurrent.TimeUnit

/**
 * Created by tae-hwan on 7/27/17.
 */
class SearchViewModel(val dataSource: PhotoSearchDataSource,
                      val adapterViewModel: PhotoListViewModel)
    : ViewModel() {

    lateinit var hideProgress: () -> Unit
    lateinit var showProgress: () -> Unit

    var page = 0

    init {
        adapterViewModel.onClickListener = {
            position ->
            val item = adapterViewModel.getItem(position)
            item.isLike = !item.isLike
            adapterViewModel.notifyItemChanged(position)
        }
    }

    val userSearchProcessor = BehaviorProcessor.createDefault("")!!

    val userSearchDisposable = userSearchProcessor
            .subscribeOn(Schedulers.io())
            .filter {
                it.isNotEmpty()
            }
            .distinctUntilChanged()
            .sample(400, TimeUnit.MILLISECONDS, true)
            .subscribe {
                dataSource.getSearchUser(it, ++page, Contract.DEFAULT_PER_PAGE)
                        .observeOn(Schedulers.io())
                        .flatMapIterable {
                            it
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { showProgress() }
                        .doOnComplete {
                            hideProgress()
                            adapterViewModel.notifyDataSetChanged()
                        }
                        .observeOn(Schedulers.io())
                        .subscribe({
                            adapterViewModel.addItem(it)
                        }, {
                            it.printStackTrace()
                        })
            }!!

    fun searchGitHubUser(name: String) {
        userSearchProcessor.onNext(name)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        userSearchDisposable.takeIf { !it.isDisposed }?.dispose()
    }
}