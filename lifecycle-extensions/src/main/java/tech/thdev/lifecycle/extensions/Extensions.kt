@file:Suppress("UNCHECKED_CAST")

package tech.thdev.lifecycle.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


/**
 * Created by taehwankwon on 7/24/17.
 *
 * Android lifecycle ViewModel Inject.
 */
fun <T : ViewModel> T.inject(fragment: Fragment): T =
        ViewModelProviders.of(fragment, createViewModel(this)).get(this.javaClass)

fun <T : ViewModel> T.inject(fragmentActivity: FragmentActivity): T =
        ViewModelProviders.of(fragmentActivity, createViewModel(this)).get(this.javaClass)

private fun <T : ViewModel> createViewModel(model: T) = object : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            model as T
}