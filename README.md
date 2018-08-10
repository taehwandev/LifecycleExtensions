# Android Architecture Components Extensions
[![License](https://img.shields.io/hexpm/l/plug.svg)]()


This is the Android Architecture Components extension library available for kotlin.

I used kotlin infix notation for the following code.

```
ViewModelProviders.of(this, object : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
        return MyViewModel(..., ..., ...) as T
    }
}).get(MyViewModel::class.java)
```


## Library version

It can be used jcenter(), as follows

### AndroidX package

[ ![Download](https://api.bintray.com/packages/taehwandev/thdev.tech/lifecycle-extensions/images/download.svg) ](https://bintray.com/taehwandev/thdev.tech/lifecycle-extensions/_latestVersion)

```
implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.2.60'
implementation 'androidx.appcompat:appcompat:1.0.0-rc01'
implementation 'androidx.lifecycle:lifecycle-extensions:2.0.0-rc01'
implementation "tech.thdev.lifecycle:lifecycle-extensions:$latestVersion"
```

### Legacy package

[ ![Download](https://api.bintray.com/packages/taehwandev/thdev.tech/lifecycle-extensions-legacy/images/download.svg?version=1.4.1) ](https://bintray.com/taehwandev/thdev.tech/lifecycle-extensions-legacy/1.4.1/link)

```
implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.2.60'
implementation 'com.android.support:appcompat-v7:27.1.1'

implementation 'android.arch.lifecycle:extensions:1.1.1'
implementation 'tech.thdev.lifecycle:lifecycle-extensions-legacy:1.4.1'
```


## Blog

한글 Post - [Android Architecture Components ViewModel을 간단하게 초기화 하려면?](https://thdev.tech/androiddev/2018/08/05/Android-Architecture-Components-ViewModel-Inject/)



## Use api - by lazy patten

Use lazy initialization

### Activity - Kotlin

```kotlin
class MainActivity : AppCompatActivity() {

    private val viewModel: MyViewModel
            by lazyInject(/* @Option customKey = "custom key" */) {
        // create Your ViewModel
        MyViewModel(..., ..., ...)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Maybe init view model
        viewModel ...
    }
}
```

### Fragment - Kotlin

```kotlin
class MainFragment : Fragment() {

    private val viewModel: MyViewModel
            by lazyInject(/* @Option customKey = "custom key" */) {
        // create Your ViewModel
        MyViewModel(..., ..., ...)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Maybe init view model
        viewModel ...
    }
}
```

Or Activity inject.

```kotlin
class MainFragment : Fragment() {

    private val viewModel: MyViewModel
            by lazyInject(
                    isActivity = true
                    /* @Option , customKey = "custom key" */) {
        // create Your ViewModel
        MyViewModel(..., ..., ...)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Maybe init view model
        viewModel ...
    }
}
```


## Use api - by inject only kotlin.

Use initialize lateinit.

### Activity - kotlin

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // ...
        viewModel = inject(/* @Option customKey = "custom key" */) {
            // create Your ViewModel
            MyViewModel(..., ..., ...)
        }.run {
            // Maybe init view model
        }
    }
}
```

### Fragment - kotlin

```kotlin
class MainFragment : Fragment() {

    private lateinit var viewModel: MyViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = inject(
                /* @Option customKey = "custom key" */) {
            // create Your ViewModel
            MyViewModel(..., ..., ...)
        }.run {
            // Maybe init view model
        }
    }
}
```

Or Activity inject.

```kotlin
class MainFragment : Fragment() {

    private lateinit var viewModel: MyViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = requireActivity().inject(
                /* @Option customKey = "custom key" */) {
            // create Your ViewModel
            MyViewModel(..., ..., ...)
        }.run {
            // Maybe init view model
        }
    }
}
```

## Use api - by inject only Java.

Use java

### Activity - Java

```java
public class MainActivity extends AppCompatActivity {

    private MyViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelExtensions.inject(
                this,
                MyViewModel.class /*, @Option "custom key" */,
                new ViewModelHelper<MyViewModel>() {

            @NotNull
            @Override
            public MyViewModel onCreateViewModel() {
                return new MyViewModel(..., ..., ...);
            }
        });
    }
}
```

### Fragment - Java

```java
public class Sample extends Fragment {

    private MyViewModel viewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelExtensions.inject(
                this,
                MyViewModel.class /*, @Option "custom key" */,
                new ViewModelHelper<MyViewModel>() {

            @NotNull
            @Override
            public MyViewModel onCreateViewModel() {
                return new MyViewModel(..., ..., ...);
            }
        });
    }
}
```


Or Activity inject.

```java
public class Sample extends Fragment {

    private MyViewModel viewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelExtensions.inject(
                requireActivity(),
                MyViewModel.class /*, @Option "custom key" */,
                new ViewModelHelper<MyViewModel>() {

            @NotNull
            @Override
            public MyViewModel onCreateViewModel() {
                return new MyViewModel(..., ..., ...);
            }
        });
    }
}
```

## License

```
Copyright 2017-2018 Tae-hwan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
