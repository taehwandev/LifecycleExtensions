package tech.thdev.lifecycleextensions

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import tech.thdev.lifecycle.extensions.viewmodel.injectViewModel
import tech.thdev.lifecycle.extensions.viewmodel.lazyInjectViewModel
import tech.thdev.lifecycleextensions.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val lazyViewModel: MainViewModel by lazyInjectViewModel {
        MainViewModel()
    }

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * ViewModel use.
         */
        mainViewModel = injectViewModel {
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