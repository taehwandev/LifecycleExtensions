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
@file:JvmName("ViewModelExtensions")
@file:JvmMultifileClass

package tech.thdev.lifecycle.extensions

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

interface ViewModelHelper<VIEW_MODEL : ViewModel> {

    fun onCreateViewModel(): VIEW_MODEL
}

/**
 * Only Java method.
 * @param receiver  Fragment
 * @param viewModel : ViewModel Class<T>
 * @param customKey : @option your custom key
 * @param viewModelHelper : new ViewModelHelper<>
 */
@JvmOverloads
fun <VIEW_MODEL : ViewModel> Fragment.injectViewModel(viewModel: Class<VIEW_MODEL>,
                                             customKey: String = "",
                                             viewModelHelper: ViewModelHelper<VIEW_MODEL>): VIEW_MODEL =
        ViewModelProviders.of(this, createViewModel(viewModelHelper::onCreateViewModel)).create(customKey, viewModel)

/**
 * Only Java method.
 * @param receiver : FragmentActivity
 * @param viewModel : ViewModel Class<T>
 * @param customKey : @option your custom key
 * @param viewModelHelper : new ViewModelHelper<>
 */
@JvmOverloads
fun <VIEW_MODEL : ViewModel> FragmentActivity.injectViewModel(viewModel: Class<VIEW_MODEL>,
                                             customKey: String = "",
                                             viewModelHelper: ViewModelHelper<VIEW_MODEL>): VIEW_MODEL =
        ViewModelProviders.of(this, createViewModel(viewModelHelper::onCreateViewModel)).create(customKey, viewModel)
