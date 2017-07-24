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
fun <T : ViewModel> T.inject(fragment: Fragment): T {
    val model = this
    ViewModelProviders.of(fragment, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
            return model as T
        }
    }).get(this.javaClass)
    return this
}

@Suppress("UNCHECKED_CAST")
fun <T : ViewModel> T.inject(fragmentActivity: FragmentActivity): T {
    val model = this
    ViewModelProviders.of(fragmentActivity, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
            return model as T
        }
    }).get(this.javaClass)
    return this
}