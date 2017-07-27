package tech.thdev.lifecycle.extensions

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

/**
 * Created by taehwankwon on 7/24/17.
 *
 * Android lifecycle ViewModel Inject.
 */
@Suppress("UNCHECKED_CAST")
fun <T : ViewModel> T.inject(fragment: Fragment): T
        = ViewModelProviders.of(fragment, createViewModel(this)).get(this.javaClass)

@Suppress("UNCHECKED_CAST")
fun <T : ViewModel> T.inject(fragmentActivity: FragmentActivity): T
        = ViewModelProviders.of(fragmentActivity, createViewModel(this)).get(this.javaClass)

@Suppress("UNCHECKED_CAST")
private fun <T : ViewModel> createViewModel(model: T) = object : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>?): T = model as T
}