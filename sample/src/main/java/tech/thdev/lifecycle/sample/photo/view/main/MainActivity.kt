package tech.thdev.lifecycle.sample.photo.view.main

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import tech.thdev.lifecycle.sample.photo.R
import tech.thdev.lifecycle.sample.photo.view.main.search.LikeFragment
import tech.thdev.lifecycle.sample.photo.view.main.search.SearchFragment

class MainActivity : LifecycleActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_search -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, SearchFragment.INSTANCE).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_like -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, LikeFragment.INSTANCE).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container, SearchFragment.INSTANCE).commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}