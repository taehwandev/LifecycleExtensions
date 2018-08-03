package tech.thdev.lifecycleextensions.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import tech.thdev.lifecycle.extensions.inject
import tech.thdev.lifecycleextensions.R
import tech.thdev.lifecycleextensions.view.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_home, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewModel = requireActivity().inject {
            HomeViewModel().apply {
                // ...
            }
        }


        homeViewModel.updateButton = { count ->
            Snackbar.make(fab_plus_one, "Plus one $count", Snackbar.LENGTH_SHORT).show()
        }

        fab_plus_one.setOnClickListener {
            homeViewModel.clickButton()
        }
    }
}