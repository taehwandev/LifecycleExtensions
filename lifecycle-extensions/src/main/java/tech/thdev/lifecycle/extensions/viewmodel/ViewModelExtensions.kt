/**
 * Android lifecycle ViewModel Inject.
 *
 * Copyright 2017-2018 Tae-hwan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")

package tech.thdev.lifecycle.extensions.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

/**
 * Create VIEW_MODEL from ViewModelProvider.
 * @param VIEW_MODEL
 * @param customKey
 * @param cls
 */
inline fun <VIEW_MODEL : ViewModel> ViewModelProvider.create(customKey: String, cls: Class<VIEW_MODEL>): VIEW_MODEL =
        if (customKey.isNotEmpty()) {
            get(customKey, cls)
        } else {
            get(cls.getCustomKey(), cls)
        }

inline fun <reified VIEW_MODEL : ViewModel> Fragment.injectViewModel(customKey: String = "",
                                                            noinline onCreateViewModel: () -> VIEW_MODEL): VIEW_MODEL =
        ViewModelProviders.of(this, createViewModel(onCreateViewModel)).create(customKey, VIEW_MODEL::class.java)


/**
 * FragmentActivity inject viewModel
 */
inline fun <reified VIEW_MODEL : ViewModel> FragmentActivity.injectViewModel(customKey: String = "",
                                                                    noinline onCreateViewModel: () -> VIEW_MODEL): VIEW_MODEL =
        ViewModelProviders.of(this, createViewModel(onCreateViewModel)).create(customKey, VIEW_MODEL::class.java)

/**
 * ViewModel Factory function. create viewModel
 * @param onCreateViewModel Higher-Order function.
 */
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