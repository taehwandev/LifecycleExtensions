package tech.thdev.lifecycle.extensions

import android.arch.lifecycle.ViewModel
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

inline fun <reified VIEW_MODEL : ViewModel> FragmentActivity.lazyInject(
        customKey: String = VIEW_MODEL::class.java.getCustomKey(),
        noinline onCreateViewModel: () -> VIEW_MODEL): ActivityViewModelInject<VIEW_MODEL> =
        ActivityViewModelInject(this, customKey, onCreateViewModel)

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