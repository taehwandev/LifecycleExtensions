# Android Architecture Components Extensions Legacy
[![License](https://img.shields.io/hexpm/l/plug.svg)]()


This is the Android Architecture Components extension library available for kotlin.

I used kotlin infix notation for the following code.

```
ViewModelProviders.of(this, object : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
        return YourViewModelClass(..., ..., ...) as T
    }
}).get(YourViewModelClass::class.java)
```


## Library version

It can be used via jcenter(), as follows

[ ![Download](https://api.bintray.com/packages/taehwandev/thdev.tech/lifecycle-extensions-legacy/images/download.svg?version=1.3.0) ](https://bintray.com/taehwandev/thdev.tech/lifecycle-extensions-legacy/1.3.0/link)

```
implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.2.60'
implementation 'com.android.support:appcompat-v7:27.1.1'

implementation 'android.arch.lifecycle:extensions:1.1.1'
implementation 'tech.thdev.lifecycle:lifecycle-extensions-legacy:1.3.0'
```


## Blog

한글 Post - [Android Architecture Components ViewModel을 간단하게 초기화 하려면?](https://thdev.tech/androiddev/2018/08/05/Android-Architecture-Components-ViewModel-Inject/)



## Use api - by lazy patten

Use when using lazy initialization

### Activity - Kotlin

```kotlin
class MainActivity : AppCompatActivity() {

    private val yourViewModel: YourViewModel
            by lazyInject(/* @Option customKey = "custom key" */) {
        // create Your ViewModel
        YourViewModel(..., ..., ...)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
       // Maybe init view model
       yourViewModel ...
    }
}
```

### Fragment - Kotlin

```kotlin
class MainFragment : Fragment() {

    private val yourViewModel: YourViewModel
            by lazyInject(/* @Option customKey = "custom key" */) {
        // create Your ViewModel
        YourViewModel(..., ..., ...)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Maybe init view model
        yourViewModel ...
    }
}

// or Use Activity inject.
class MainFragment : Fragment() {

    private val yourViewModel: YourViewModel
            by lazyInject(isActivity = true /* @Option , customKey = "custom key" */) {
        // create Your ViewModel
        YourViewModel(..., ..., ...)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Maybe init view model
        yourViewModel ...
    }
}
```


## Use api - by inject only kotlin.

Used when initializing lateinit.

### Activity - kotlin

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var yourViewModel: YourViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // ...
        yourViewModel = inject(/* @Option customKey = "custom key" */) {
            // create Your ViewModel
            YourViewModel(..., ..., ...)
        }.run {
            // Maybe init view model
        }
    }
}
```

### Fragment - kotlin

```kotlin
class MainFragment : Fragment() {

    private lateinit var yourViewModel: YourViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        yourViewModel = inject(/* @Option customKey = "custom key" */) {
            // create Your ViewModel
            YourViewModel(..., ..., ...)
        }.run {
            // Maybe init view model
        }
    }
}

// or Use Activity inject.
class MainFragment : Fragment() {

    private lateinit var yourViewModel: YourViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        yourViewModel = requireActivity().inject(
                /* @Option customKey = "custom key" */) {
            // create Your ViewModel
            YourViewModel(..., ..., ...)
        }.run {
            // Maybe init view model
        }
    }
}
```

## Use api - by inject only Java.

Used when java

### Activity - Java

```java
public class MainActivity extends AppCompatActivity {

    private YourViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelExtensions.inject(
                YourViewModel.class,
                this /*, @Option "custom key" */,
                new Function0<YourViewModel>() {

            @Override
            public YourViewModel invoke() {
                return new YourViewModel(..., ..., ...);
            }
        });
    }
}
```

### Fragment - Java

```java
public class Sample extends Fragment {

    private YourViewModel viewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelExtensions.inject(
                YourViewModel.class,
                this /*, @Option "custom key" */,
                new Function0<YourViewModel>() {

            @Override
            public YourViewModel invoke() {
                return new YourViewModel(..., ..., ...);
            }
        });
    }
}


// or Use Activity inject.
public class Sample extends Fragment {

    private YourViewModel viewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelExtensions.inject(
                YourViewModel.class,
                requireActivity() /*, @Option "custom key" */,
                new Function0<YourViewModel>() {

            @Override
            public YourViewModel invoke() {
                return new YourViewModel(..., ..., ...);
            }
        });
    }
}
```


## Use Library version

- Kotlin version : 1.2.60
- Android Architecture Components : 1.1.1
- support libraryVersion : 27.1.1


## License

```
Copyright 2018 Tae-hwan

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
