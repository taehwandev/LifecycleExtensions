package tech.thdev.lifecycle.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel


fun <VIEW_MODEL : ViewModel> FragmentActivity.lazyInject(customKey: String = "", onCreateViewModel: () -> VIEW_MODEL): ActivityViewModelInject<VIEW_MODEL> =
        ActivityViewModelInject(this, customKey, onCreateViewModel)

fun <VIEW_MODEL : ViewModel> Fragment.lazyInject(customKey: String = "", onCreateViewModel: () -> VIEW_MODEL): FragmentViewModelInject<VIEW_MODEL> =
        FragmentViewModelInject(this, customKey, onCreateViewModel)

class FragmentViewModelInject<VIEW_MODEL : ViewModel>(private val fragment: Fragment,
                                                      private val customKey: String,
                                                      initializer: () -> ViewModel) : Lazy<VIEW_MODEL> {

    private var initializer: (() -> ViewModel)? = initializer
    private var _value: ViewModel? = null

    override val value: VIEW_MODEL
        get() {
            if (_value == null) {
                _value = fragment.inject(customKey) {
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

class ActivityViewModelInject<VIEW_MODEL : ViewModel>(private val fragmentActivity: FragmentActivity,
                                                      private val customKey: String,
                                                      initializer: () -> ViewModel) : Lazy<VIEW_MODEL> {
    private var initializer: (() -> ViewModel)? = initializer
    private var _value: ViewModel? = null

    override val value: VIEW_MODEL
        get() {
            if (_value == null) {
                _value = fragmentActivity.inject(customKey) {
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