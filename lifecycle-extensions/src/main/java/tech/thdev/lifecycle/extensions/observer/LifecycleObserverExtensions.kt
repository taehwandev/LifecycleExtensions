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
import androidx.lifecycle.LifecycleObserver

/**
 * Create OBSERVER from Fragment.
 */
inline fun <reified OBSERVER : LifecycleObserver> Fragment.inject(noinline onCreateLifecycleObserver: () -> OBSERVER): OBSERVER =
        onCreateLifecycleObserver().also {
            this.lifecycle.addObserver(it)
        }

/**
 * Create OBSERVER from FragmentActivity.
 */
inline fun <reified OBSERVER : LifecycleObserver> FragmentActivity.inject(noinline onCreateLifecycleObserver: () -> OBSERVER): OBSERVER =
        onCreateLifecycleObserver().also {
            this.lifecycle.addObserver(it)
        }