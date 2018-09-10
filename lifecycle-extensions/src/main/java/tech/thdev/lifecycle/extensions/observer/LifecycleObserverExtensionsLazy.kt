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
package tech.thdev.lifecycle.extensions.observer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Fragment lazy inject LifecycleObserver
 *
 * ex) only fragment
 * val lifecycleObserver by autoInjectLifecycle {
 *      MyLifecycleObserver()
 * }
 *
 * ex) Create activity lifecycleObserver from Fragment
 * val lifecycleObserver by autoInjectLifecycle(isActivity = true) {
 *      MyLifecycleObserver()
 * }
 */
inline fun <reified OBSERVER : LifecycleObserver> Fragment.injectAutoLifecycle(
        isActivity: Boolean = false,
        noinline onCreateLifecycleObserver: () -> OBSERVER): FragmentViewModelInject<OBSERVER> =
        FragmentViewModelInject(isActivity, this, onCreateLifecycleObserver)

class FragmentViewModelInject<OBSERVER : LifecycleObserver>(private val isActivity: Boolean,
                                                            private val fragment: Fragment,
                                                            initializer: () -> OBSERVER) : Lazy<OBSERVER> {

    init {
        val observer = object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreate() {
                // auto call and init...
                value
            }
        }

        if (isActivity) {
            fragment.requireActivity().lifecycle.addObserver(observer)
        } else {
            fragment.lifecycle.addObserver(observer)
        }
    }

    private var initializer: (() -> LifecycleObserver)? = initializer
    private var _value: LifecycleObserver? = null

    override val value: OBSERVER
        get() {
            if (_value == null) {
                _value = if (isActivity) {
                    fragment.requireActivity().injectLifecycle {
                        initializer!!()
                    }
                } else {
                    fragment.injectLifecycle {
                        initializer!!()
                    }
                }
                initializer = null
            }
            @Suppress("UNCHECKED_CAST")
            return _value as OBSERVER
        }

    override fun isInitialized(): Boolean = _value != null
}

/**
 * FragmentActivity lazy inject LifecycleObserver
 *
 * ex)
 * val lifecycleObserver by autoInjectLifecycle {
 *      MyLifecycleObserver()
 * }
 */
inline fun <reified OBSERVER : LifecycleObserver> FragmentActivity.injectAutoLifecycle(
        noinline onCreateLifecycleObserver: () -> OBSERVER): ActivityViewModelInject<OBSERVER> =
        ActivityViewModelInject(this, onCreateLifecycleObserver)

class ActivityViewModelInject<OBSERVER : LifecycleObserver>(private val activity: FragmentActivity,
                                                            initializer: () -> LifecycleObserver) : Lazy<OBSERVER> {
    private var initializer: (() -> LifecycleObserver)? = initializer
    private var _value: LifecycleObserver? = null

    init {
        activity.lifecycle.addObserver(object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreate() {
                // auto call and init...
                value
            }
        })
    }

    override val value: OBSERVER
        get() {
            if (_value == null) {
                _value = activity.injectLifecycle {
                    initializer!!()
                }
                initializer = null
            }
            @Suppress("UNCHECKED_CAST")
            return _value as OBSERVER
        }

    override fun isInitialized(): Boolean =
            _value != null
}