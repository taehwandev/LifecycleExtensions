@file:Suppress("UNCHECKED_CAST")
@file:JvmName("Extensions")

package tech.thdev.lifecycle.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


/**
 * Android lifecycle ViewModel Inject.
 */
fun <T : ViewModel> Class<T>.inject(fragment: Fragment, customKey: String = "", onCreateViewModel: () -> T): T =
        ViewModelProviders.of(fragment, createViewModel(onCreateViewModel)).run {
            if (customKey.isNotEmpty()) {
                this.get(customKey, this@inject)
            } else {
                this.get(this@inject)
            }
        }

fun <T : ViewModel> Class<T>.inject(fragmentActivity: FragmentActivity, customKey: String = "", onCreateViewModel: () -> T): T =
        ViewModelProviders.of(fragmentActivity, createViewModel(onCreateViewModel)).run {
            if (customKey.isNotEmpty()) {
                this.get(customKey, this@inject)
            } else {
                get(this@inject)
            }
        }

private fun <T : ViewModel> createViewModel(onCreateViewModel: () -> T) = object : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (ViewModel::class.java.isAssignableFrom(modelClass)) {
            try {
                return onCreateViewModel() as T
            } catch (e: Exception) {
                when (e) {
                    is NoSuchMethodException, is IllegalAccessException, is InstantiationException ->
                        throw RuntimeException("Cannot create an instance of $modelClass", e)
                }
            }
        }

        throw RuntimeException("Cannot create an instance of $modelClass")
    }
}