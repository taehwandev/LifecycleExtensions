package tech.thdev.lifecycleextensions.view.time.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.cancelAndJoin
import kotlinx.coroutines.experimental.launch
import java.text.SimpleDateFormat
import java.util.*

class TimeViewModel : ViewModel() {

    private val tag = this::class.java.simpleName

    lateinit var updateLoginTime: (time: String) -> Unit

    private var prevTime: Long = 0

    init {
        Log.d(tag, "create ViewModel")
    }

    private val simpleDateFormat: SimpleDateFormat by lazy {
        SimpleDateFormat("mm:ss.S", Locale.getDefault())
    }

    private lateinit var timer: Job

    private fun updateTimeThread() = launch {
        while (isActive) {
            launch(UI) {
                if (::updateLoginTime.isInitialized) {
                    updateLoginTime(simpleDateFormat.format(System.currentTimeMillis() - prevTime))
                }
            }
            Thread.sleep(10)
        }
    }

    fun startTime() {
        prevTime = System.currentTimeMillis()
        timer = updateTimeThread()
    }

    fun stopTime() {
        Log.e(tag, "stop")
        launch {
            timer.cancelAndJoin()
        }
        Log.e(tag, "stop cancelAndJoin")
    }
}