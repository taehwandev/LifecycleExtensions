# Android Architecture Components Extensions
[![License](https://img.shields.io/hexpm/l/plug.svg)]()


This is the Android Architecture Components extension library available for kotlin.


## Use api - by auto inject

Use AAC-LifecycleObserver lazy initialization

### Activity inject

Use LifeCycle activity

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

### Fragment inject

Use LifeCycle fragment

```kotlin
class MainFragment : Fragment() {

    private val observer: MyLifecycleObserver by injectAutoLifecycle {
        // create Your Observer
        MyLifecycleObserver(..., ..., ...)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // use lifecycle
    }
}
```

### Fragment - use activity inject

Use cache activity

```kotlin
class MainFragment : Fragment() {

    private val observer: MyLifecycleObserver by injectAutoLifecycle(isActivity = true) {
        // create Your Observer
        MyLifecycleObserver(..., ..., ...)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // use lifecycle
    }
}
```


## Use api - by lateinit only kotlin.

### Activity inject

Use cache activity

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var observer: MyLifecycleObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        // ...
        observer = injectLifecycle {
            // create Your Observer
            MyLifecycleObserver(..., ..., ...)
        }
    }
}
```

### Fragment inject

Use cache fragment

```kotlin
class MainFragment : Fragment() {

    private lateinit var observer: MyLifecycleObserver

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observer = injectLifecycle {
            // create Your Observer
            MyLifecycleObserver(..., ..., ...)
        }
    }
}
```

### Fragment - use activity inject

Use cache activity

```kotlin
class MainFragment : Fragment() {

    private lateinit var observer: MyLifecycleObserver

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observer = requireActivity().injectLifecycle {
            // create Your Observer
            MyLifecycleObserver(..., ..., ...)
        }
    }
}
```

## Use api - by inject only Java.

Use AAC-LifecycleObserver java

### Activity inject

Use cache activity

```java
public class MainActivity extends AppCompatActivity {

    private MyLifecycleObserver observer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        observer = LifecycleObserverExtensions.injectLifecycle(
                this,
                new LifecycleObserverCreate<MyLifecycleObserver>() {

            @NotNull
            @Override
            public MainLifecycleObserver onCreateLifecycleObserver() {
               return new MyLifecycleObserver();
            }
        });
    }
}
```

### Fragment inject

Use cache fragment

```java
public class Sample extends Fragment {

    private MyLifecycleObserver observer;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        observer = LifecycleObserverExtensions.injectLifecycle(
                this,
                new LifecycleObserverCreate<MyLifecycleObserver>() {

            @NotNull
            @Override
            public MainLifecycleObserver onCreateLifecycleObserver() {
               return new MyLifecycleObserver();
            }
        });
    }
}
```


### Fragment - use activity inject

Use cache activity

```java
public class Sample extends Fragment {

    private MyLifecycleObserver observer;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        observer = LifecycleObserverExtensions.injectLifecycle(
                requireActivity(),
                new LifecycleObserverCreate<MyLifecycleObserver>() {

            @NotNull
            @Override
            public MainLifecycleObserver onCreateLifecycleObserver() {
               return new MyLifecycleObserver();
            }
        });
    }
}
```

##

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
