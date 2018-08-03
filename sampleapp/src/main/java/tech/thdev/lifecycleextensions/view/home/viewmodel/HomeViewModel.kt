package tech.thdev.lifecycleextensions.view.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    lateinit var updateButton: (count: Int) -> Unit

    private var count = 0

    private val tag = this::class.java.simpleName

    init {
        Log.i(tag, "ViewModel init")
    }

    fun clickButton() {
        if (::updateButton.isInitialized) {
            updateButton(count++)
        }
    }
}