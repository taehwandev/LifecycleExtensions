package tech.thdev.lifecycleextensions.view.time

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_time.*
import tech.thdev.lifecycle.extensions.observer.injectAutoLifecycle
import tech.thdev.lifecycle.extensions.viewmodel.lazyInjectViewModel
import tech.thdev.lifecycleextensions.R
import tech.thdev.lifecycleextensions.observer.MainLifecycleObserver
import tech.thdev.lifecycleextensions.view.time.viewmodel.TimeViewModel

class TimeFragment : Fragment() {

    private val timeViewModel: TimeViewModel by lazyInjectViewModel(isActivity = true) {
        TimeViewModel()
    }

    private val mainLifecycleObserver by injectAutoLifecycle {
        MainLifecycleObserver()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_time, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        timeViewModel.updateLoginTime = { time ->
            if (!isDetached) {
                tv_past_time?.text = time
            }
        }

        timeViewModel.startTime()
    }

    override fun onPause() {
        super.onPause()

        timeViewModel.stopTime()
    }
}