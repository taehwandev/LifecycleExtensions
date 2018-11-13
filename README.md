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
implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.0'
implementation 'androidx.appcompat:appcompat:1.0.1'

implementation 'androidx.lifecycle:lifecycle-extensions:2.0.0'
implementation "tech.thdev.lifecycle:lifecycle-extensions:$latestVersion"
```

### Legacy package

[ ![Download](https://api.bintray.com/packages/taehwandev/thdev.tech/lifecycle-extensions-legacy/images/download.svg) ](https://bintray.com/taehwandev/thdev.tech/lifecycle-extensions-legacy/_latestVersion)

```
implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.0'
implementation 'com.android.support:appcompat-v7:27.1.1'

implementation 'android.arch.lifecycle:extensions:1.1.1'
implementation "tech.thdev.lifecycle:lifecycle-extensions-legacy:$latestLegacyVersion"
```

## Diagram

![](./images/AAC-ViewModel.png)


## Blog

한글 Post - [Android Architecture Components ViewModel을 간단하게 초기화 하려면?](https://thdev.tech/androiddev/2018/08/05/Android-Architecture-Components-ViewModel-Inject/)


## Use api

### [Guide AAC - ViewModel](README_VIEW_MODEL.md)

```kotlin
class MainActivity : AppCompatActivity() {

    private val viewModel: MyViewModel
            by lazyInjectViewModel(/* @Option customKey = "custom key" */) {
        // create Your ViewModel
        MyViewModel(..., ..., ...)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Maybe init view model
        viewModel ...
    }
}
```

### [Guide AAC - LifecycleObserver](README_LIFECYCLE_OBSERVER.md)

```kotlin
class MainActivity : AppCompatActivity() {

    private val observer: MyLifecycleObserver by injectAutoLifecycle {
        // create Your Observer
        MyLifecycleObserver(..., ..., ...)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // use lifecycle
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
