package tech.thdev.lifecycleextensions.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import kotlin.jvm.functions.Function0;
import tech.thdev.lifecycle.extensions.ViewModelExtensions;
import tech.thdev.lifecycleextensions.view.home.viewmodel.HomeViewModel;

public class Sample extends Fragment {

    private HomeViewModel viewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelExtensions.inject(HomeViewModel.class, requireActivity(), new Function0<HomeViewModel>() {
            @Override
            public HomeViewModel invoke() {
                return new HomeViewModel();
            }
        });
    }
}
