package tech.thdev.lifecycleextensions.java;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import tech.thdev.lifecycle.extensions.observer.LifecycleObserverCreate;
import tech.thdev.lifecycle.extensions.observer.LifecycleObserverExtensions;
import tech.thdev.lifecycle.extensions.viewmodel.ViewModelCreate;
import tech.thdev.lifecycle.extensions.viewmodel.ViewModelExtensions;
import tech.thdev.lifecycleextensions.R;
import tech.thdev.lifecycleextensions.observer.MainObserver;
import tech.thdev.lifecycleextensions.viewmodel.MainViewModel;

public class SampleJava extends AppCompatActivity {

    private MainViewModel viewModel;
    private MainObserver mainObserver;

    private TextView tvMessage;
    private Button btnTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelExtensions.injectViewModel(
                this,
                MainViewModel.class,
                new ViewModelCreate<MainViewModel>() {
                    @NotNull
                    @Override
                    public MainViewModel onCreateViewModel() {
                        return new MainViewModel();
                    }
                });

        viewModel.setUpdateButton(new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer integer) {
                tvMessage.setText("" + integer);
                return null;
            }
        });

        mainObserver = LifecycleObserverExtensions.injectLifecycle(this, new LifecycleObserverCreate<MainObserver>() {
            @NotNull
            @Override
            public MainObserver onCreateLifecycleObserver() {
                return new MainObserver();
            }
        });

        tvMessage = findViewById(R.id.tv_message);
        btnTest = findViewById(R.id.btn_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.clickButton();
            }
        });
    }
}
