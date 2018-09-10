package tech.thdev.lifecycleextensions

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import tech.thdev.lifecycle.extensions.viewmodel.inject
import tech.thdev.lifecycle.extensions.viewmodel.lazyInject
import tech.thdev.lifecycleextensions.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val lazyViewModel: MainViewModel by lazyInject {
        MainViewModel()
    }

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * ViewModel use.
         */
        mainViewModel = inject {
            MainViewModel().apply {
                updateButton = { count ->
                    tv_message.text = getString(R.string.message_text, count)
                }
            }
        }

        btn_test.setOnClickListener {
            mainViewModel.clickButton()
        }
    }
}