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
package tech.thdev.lifecycle.extensions.viewmodel

import android.arch.lifecycle.ViewModel
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

/**
 * Fragment lazy inject viewModel
 *
 * ex) only fragment
 * val viewModel by lazyInject {
 *      MyViewModel()
 * }
 *
 * ex) Create activity ViewModel from Fragment
 * val viewModel by lazyInject(isActivity = true) {
 *      MyViewModel()
 * }
 */
inline fun <reified VIEW_MODEL : ViewModel> Fragment.lazyInject(
        isActivity: Boolean = false,
        customKey: String = VIEW_MODEL::class.java.getCustomKey(),
        noinline onCreateViewModel: () -> VIEW_MODEL): FragmentViewModelInject<VIEW_MODEL> =
        FragmentViewModelInject(isActivity, this, customKey, onCreateViewModel)

class FragmentViewModelInject<VIEW_MODEL : ViewModel>(private val isActivity: Boolean,
                                                      private val fragment: Fragment,
                                                      private val customKey: String,
                                                      initializer: () -> VIEW_MODEL) : Lazy<VIEW_MODEL> {

    private var initializer: (() -> ViewModel)? = initializer
    private var _value: ViewModel? = null

    override val value: VIEW_MODEL
        get() {
            if (_value == null) {
                _value = if (isActivity) {
                    fragment.requireActivity().inject(customKey) {
                        initializer!!()
                    }
                } else {
                    fragment.inject(customKey) {
                        initializer!!()
                    }
                }
                initializer = null
            }
            @Suppress("UNCHECKED_CAST")
            return _value as VIEW_MODEL
        }

    override fun isInitialized(): Boolean = _value != null
}

/**
 * FragmentActivity lazy inject viewModel
 *
 * ex)
 * val viewModel by lazyInject {
 *      MyViewModel()
 * }
 */
inline fun <reified VIEW_MODEL : ViewModel> FragmentActivity.lazyInject(
        customKey: String = VIEW_MODEL::class.java.getCustomKey(),
        noinline onCreateViewModel: () -> VIEW_MODEL): ActivityViewModelInject<VIEW_MODEL> =
        ActivityViewModelInject(this, customKey, onCreateViewModel)

class ActivityViewModelInject<VIEW_MODEL : ViewModel>(private val activity: FragmentActivity,
                                                      private val customKey: String,
                                                      initializer: () -> ViewModel) : Lazy<VIEW_MODEL> {
    private var initializer: (() -> ViewModel)? = initializer
    private var _value: ViewModel? = null

    override val value: VIEW_MODEL
        get() {
            if (_value == null) {
                _value = activity.inject(customKey) {
                    initializer!!()
                }
                initializer = null
            }
            @Suppress("UNCHECKED_CAST")
            return _value as VIEW_MODEL
        }

    override fun isInitialized(): Boolean =
            _value != null
}