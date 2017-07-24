# Android Architecture Components Extensions
[![License](https://img.shields.io/hexpm/l/plug.svg)]()


This is the Android Architecture Components extension library available for kotlin.

I used kotlin infix notation for the following code.

```
ViewModelProviders.of(this, object : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
        return SearchViewModel(..., ..., ...) as T
    }
}).get(ViewModel::class.java)
```


## Library version

It can be used via jcenter(), as follows

[ ![Download](https://api.bintray.com/packages/taehwandev/thdev.tech/lifecycle-extensions/images/download.svg) ](https://bintray.com/taehwandev/thdev.tech/lifecycle-extensions/_latestVersion)

```
compile 'tech.thdev.lifecycle.extensions:android-lifecycle-extensions:1.0.0-alpha1
```

## Blog

[Android Architecture Components ViewModel을 간단하게 초기화 하려면?](http://thdev.tech/androiddev/2017/07/25/Android-Architecture-Components-ViewModel-Inject.html)

### Use with kotlin - Activity

```
import android.arch.lifecycle.LifecycleActivity

class MainActivity : LifecycleActivity() {
    // ...
    val viewModel = ViewModel(...).inject(this)
    // ...
}
```

### Use with kotlin - Fragment

```
import android.arch.lifecycle.LifecycleFragment

class MainActivity : Fragment() {
    // ...
    val viewModel = ViewModel(...).inject(this)
    // ...
}
```

## Use Library version

- Kotlin version : 1.1.3-2
- Android Architecture Components : 1.0.0-alpha5
- support libraryVersion : 26.0.0-beta2


## License

```
Copyright 2017 Tae-hwan

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
