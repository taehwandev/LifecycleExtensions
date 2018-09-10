package tech.thdev.lifecycleextensions.java.view;

import android.os.Bundle;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import tech.thdev.lifecycle.extensions.observer.LifecycleObserverCreate;
import tech.thdev.lifecycle.extensions.observer.LifecycleObserverExtensions;
import tech.thdev.lifecycle.extensions.viewmodel.ViewModelCreate;
import tech.thdev.lifecycle.extensions.viewmodel.ViewModelExtensions;
import tech.thdev.lifecycleextensions.R;
import tech.thdev.lifecycleextensions.observer.MainLifecycleObserver;
import tech.thdev.lifecycleextensions.view.home.viewmodel.HomeViewModel;

public class JavaMainActivity extends AppCompatActivity {

    private HomeViewModel homeViewModel;
    private MainLifecycleObserver mainLifecycleObserver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeViewModel = ViewModelExtensions.inject(this, HomeViewModel.class, new ViewModelCreate<HomeViewModel>() {
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

        mainLifecycleObserver = LifecycleObserverExtensions.inject(this, new LifecycleObserverCreate<MainLifecycleObserver>() {
            @NotNull
            @Override
            public MainLifecycleObserver onCreateLifecycleObserver() {
                return new MainLifecycleObserver();
            }
        });

        homeViewModel.clickButton();
    }
}
