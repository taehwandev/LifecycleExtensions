# Android Architecture Components Extensions
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

[ ![Download](https://api.bintray.com/packages/taehwandev/thdev.tech/lifecycle-extensions/images/download.svg) ](https://bintray.com/taehwandev/thdev.tech/lifecycle-extensions/_latestVersion)

```
implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.2.60'
implementation 'androidx.appcompat:appcompat:1.0.0-beta01'
implementation 'androidx.lifecycle:lifecycle-extensions:2.0.0-beta01'
implementation "tech.thdev.lifecycle:lifecycle-extensions:$latestVersion"
```


## Blog

한글 Post - [Android Architecture Components ViewModel을 간단하게 초기화 하려면?](http://thdev.tech/androiddev/2017/07/25/Android-Architecture-Components-ViewModel-Inject.html)



## Use api

### How to use with kotlin - Activity

```kotlin
class MainActivity : AppCompatActivity() {
    // ...
    val yourViewModel = YourViewModel::class.java.inject(this /* @option , customKey = "custom key" */) {
        // create Your ViewModel
        YourViewModel(..., ..., ...).apply {
            // Maybe init view model
        }
    }
    // ...
}
```

### How to use with kotlin - Fragment

```kotlin
class MainFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = YourViewModel::class.java.inject(this /* @option , customKey = "custom key" */) {
            // create Your ViewModel
            YourViewModel().apply {
                // Maybe init view model
            }
        }
    }
}

// or Use Activity inject.
class MainFragment : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = MainViewModel::class.java.inject(requireActivity() /* @option , customKey = "custom key" */) {
            // create Your ViewModel
            MainViewModel(..., ..., ...).apply {
                // Maybe init view model
            }
        }
    }
}
```

### How to use with java - Activity

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        YourViewModel yourViewModel = Extensions.inject(YourViewModel.class, this /* @option , customKey = "custom key" */, new Function0<YourViewModel>() {
            @Override
            public YourViewModel invoke() {
                return new YourViewModel();
            }
        });
    }
}
```

### Use with java - Fragment

```java
public class MainFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        YourViewModel yourViewModel = Extensions.inject(YourViewModel.class, requireActivity() /* @option , customKey = "custom key" */, new Function0<YourViewModel>() {
            @Override
            public YourViewModel invoke() {
                return new YourViewModel();
            }
        });
    }
}
```


## Use Library version

- Kotlin version : 1.2.60
- AndroidX Architecture Components : 2.0.0-beta01
- AndroidX support libraryVersion : 1.0.0-beta01


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
