package tech.thdev.lifecycleextensions.view.time.viewmodel

import android.util.Log
import kotlinx.coroutines.*
import tech.thdev.coroutines.provider.DispatchersProvider
import tech.thdev.support.base.coroutines.viewmodel.CoroutineScopeViewModel
import java.text.SimpleDateFormat
import java.util.*

class TimeViewModel : CoroutineScopeViewModel() {

    private val tag = this::class.java.simpleName

    lateinit var updateLoginTime: (time: String) -> Unit

    private var prevTime: Long = 0

    init {
        Log.d(tag, "create ViewModel")
    }

    private val simpleDateFormat: SimpleDateFormat by lazy {
        SimpleDateFormat("mm:ss.S", Locale.getDefault())
    }

    private fun updateTimeThread() = launch {
        while (isActive) {
            launch(DispatchersProvider.main) {
                if (::updateLoginTime.isInitialized) {
                    updateLoginTime(simpleDateFormat.format(System.currentTimeMillis() - prevTime))
                }
            }
            Thread.sleep(10)
        }
    }

    fun startTime() {
        prevTime = System.currentTimeMillis()
        updateTimeThread()
    }

    fun stopTime() {
        Log.e(tag, "stop")
        launch {
            job.cancelAndJoin()
        }
        Log.e(tag, "stop cancelAndJoin")
    }

    override fun onCleared() {
        stopTime()
    }
}