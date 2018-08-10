package tech.thdev.lifecycleextensions.java.view;

import android.os.Bundle;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import tech.thdev.lifecycle.extensions.ViewModelExtensions;
import tech.thdev.lifecycle.extensions.ViewModelHelper;
import tech.thdev.lifecycleextensions.R;
import tech.thdev.lifecycleextensions.view.home.viewmodel.HomeViewModel;

public class JavaMainActivity extends AppCompatActivity {

    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeViewModel = ViewModelExtensions.inject(this, HomeViewModel.class, new ViewModelHelper<HomeViewModel>() {
            @NotNull
            @Override
            public HomeViewModel onCreateViewModel() {
                return new HomeViewModel();
            }
        });

        homeViewModel.setUpdateButton(new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer integer) {
                // ...
                return null;
            }
        });

        homeViewModel.clickButton();
    }
}
