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
@file:JvmName("LifecycleObserverExtensions")
@file:JvmMultifileClass

package tech.thdev.lifecycle.extensions.observer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleObserver

interface LifecycleObserverCreate<OBSERVER : LifecycleObserver> {

    fun onCreateLifecycleObserver(): OBSERVER
}


/**
 * Create OBSERVER from Fragment.
 */
fun <OBSERVER : LifecycleObserver> inject(fragment: Fragment,
                                          onCreateLifecycleObserver: LifecycleObserverCreate<OBSERVER>): OBSERVER =
        onCreateLifecycleObserver.onCreateLifecycleObserver().also {
            fragment.lifecycle.addObserver(it)
        }

/**
 * Create OBSERVER from FragmentActivity.
 */
fun <OBSERVER : LifecycleObserver> inject(activity: FragmentActivity,
                                          onCreateLifecycleObserver: LifecycleObserverCreate<OBSERVER>): OBSERVER =
        onCreateLifecycleObserver.onCreateLifecycleObserver().also {
            activity.lifecycle.addObserver(it)
        }