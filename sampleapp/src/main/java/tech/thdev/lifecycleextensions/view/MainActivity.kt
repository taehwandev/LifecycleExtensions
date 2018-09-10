package tech.thdev.lifecycleextensions.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import tech.thdev.lifecycle.extensions.observer.injectAutoLifecycle
import tech.thdev.lifecycle.extensions.viewmodel.lazyInjectViewModel
import tech.thdev.lifecycleextensions.R
import tech.thdev.lifecycleextensions.observer.MainLifecycleObserver
import tech.thdev.lifecycleextensions.view.home.HomeFragment
import tech.thdev.lifecycleextensions.view.time.TimeFragment
import tech.thdev.lifecycleextensions.view.time.viewmodel.TimeViewModel

class MainActivity : AppCompatActivity() {

    private val homeFragment: HomeFragment by lazy {
        HomeFragment()
    }

    private val timeFragment: TimeFragment by lazy {
        TimeFragment()
    }

    private val timeViewModel: TimeViewModel by lazyInjectViewModel {
        TimeViewModel()
    }

    private val mainLifecycleObserver by injectAutoLifecycle {
        MainLifecycleObserver()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                homeFragment.updateFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_time -> {
                timeFragment.updateFragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        timeViewModel
        homeFragment.updateFragment()
    }

    private fun Fragment.updateFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, this)
                .commitNow()
    }
}
