@file:Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
@file:JvmName("ViewModelExtensions")

package tech.thdev.lifecycle.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * Android lifecycle ViewModel Inject.
 */
inline fun <reified VIEW_MODEL : ViewModel> Fragment.inject(customKey: String = "",
                                                            noinline onCreateViewModel: () -> VIEW_MODEL): VIEW_MODEL =
        VIEW_MODEL::class.java.inject(this, customKey, onCreateViewModel)

/**
 * JVM only method...
 */
@JvmOverloads
fun <VIEW_MODEL : ViewModel> Class<VIEW_MODEL>.inject(fragment: Fragment, customKey: String = "", onCreateViewModel: () -> VIEW_MODEL): VIEW_MODEL =
        ViewModelProviders.of(fragment, createViewModel(onCreateViewModel)).run {
            if (customKey.isNotEmpty()) {
                this.get(customKey, this@inject)
            } else {
                this.get(this@inject.getCustomKey(), this@inject)
            }
        }

inline fun <reified VIEW_MODEL : ViewModel> FragmentActivity.inject(customKey: String = "",
                                                                    noinline onCreateViewModel: () -> VIEW_MODEL): VIEW_MODEL =
        VIEW_MODEL::class.java.inject(this, customKey, onCreateViewModel)

@JvmOverloads
fun <VIEW_MODEL : ViewModel> Class<VIEW_MODEL>.inject(fragmentActivity: FragmentActivity, customKey: String = "", onCreateViewModel: () -> VIEW_MODEL): VIEW_MODEL =
        ViewModelProviders.of(fragmentActivity, createViewModel(onCreateViewModel)).run {
            if (customKey.isNotEmpty()) {
                this.get(customKey, this@inject)
            } else {
                get(this@inject.getCustomKey(), this@inject)
            }
        }


fun <VIEW_MODEL : ViewModel> createViewModel(onCreateViewModel: () -> VIEW_MODEL) = object : ViewModelProvider.Factory {

    override fun <VIEW_MODEL : ViewModel?> create(modelClass: Class<VIEW_MODEL>): VIEW_MODEL {
        if (ViewModel::class.java.isAssignableFrom(modelClass)) {
            try {
                return onCreateViewModel() as VIEW_MODEL
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