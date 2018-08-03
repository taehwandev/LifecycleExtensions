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



## Use api - by lazy patten

Use when using lazy initialization

### Activity - Kotlin

```kotlin
class MainActivity : AppCompatActivity() {

    private val yourViewModel: YourViewModel by lazyInject(/* @Option customKey = "custom key" */) {
        // create Your ViewModel
        YourViewModel(..., ..., ...).apply {
            // Maybe init your lateinit?
        }
    }
}
```

### Fragment - Kotlin

```kotlin
class MainFragment : Fragment() {

    private val yourViewModel: YourViewModel by lazyInject(/* @Option customKey = "custom key" */) {
        // create Your ViewModel
        YourViewModel(..., ..., ...).apply {
            // Maybe init your lateinit?
        }
    }
}

// or Use Activity inject.
class MainFragment : Fragment() {

    private val yourViewModel: YourViewModel by requireActivity().lazyInject(/* @Option customKey = "custom key" */) {
        // create Your ViewModel
        YourViewModel(..., ..., ...).apply {
            // Maybe init your lateinit?
        }
    }
}
```


## Use api - by inject

Used when initializing lateinit.

### Activity - kotlin

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var yourViewModel: YourViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // ...
        yourViewModel = inject(/* @option customKey = "custom key" */) {
            // create Your ViewModel
            YourViewModel(..., ..., ...).apply {
                // Maybe init view model
            }
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

        yourViewModel = inject(/* @option customKey = "custom key" */) {
            // create Your ViewModel
            YourViewModel(..., ..., ...).apply {
                // Maybe init view model
            }
        }
    }
}

// or Use Activity inject.
class MainFragment : Fragment() {

    private lateinit var yourViewModel: YourViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        yourViewModel = requireActivity().inject(/* @option customKey = "custom key" */) {
            // create Your ViewModel
            YourViewModel(..., ..., ...).apply {
                // Maybe init view model
            }
        }
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
