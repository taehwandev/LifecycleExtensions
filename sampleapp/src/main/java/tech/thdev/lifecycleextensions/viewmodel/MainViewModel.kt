package tech.thdev.lifecycleextensions.viewmodel

import android.arch.lifecycle.ViewModel
import android.util.Log

class MainViewModel : ViewModel() {

    lateinit var updateButton: (count: Int) -> Unit

    private var count = 0

    private val tag = MainViewModel::class.java.simpleName

    init {
        Log.i(tag, "ViewModel init")
    }

    fun clickButton() {
        if (::updateButton.isInitialized) {
            updateButton(count++)
        }
    }
}