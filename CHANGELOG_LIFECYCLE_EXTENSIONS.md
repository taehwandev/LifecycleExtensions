## Change Log

### Version 2.4.0 (2018-11-13)
- Update kotlin version(1.3.0)
- Update build version(3.2.1)

### Version 2.3.0 (2018-09-10)
    - Add new api : LifecycleObserver simple inject

    ex) only java sample

    ```
    mainObserver = LifecycleObserverExtensions.inject(this, new LifecycleObserverCreate<MainObserver>() {
        @NotNull
        @Override
        public MainObserver onCreateLifecycleObserver() {
            return new MainObserver();
        }
    });
    ```

    - Java create interface change : ViewModelHelper<> -> ViewModelCreate<>


### Version 2.2.0 (2018-08-10)

- Split Java method.
- Clarification of the java method

  ex) only java method.

  ```
  viewModel = ViewModelExtensions.inject(this, MyViewModel.class, new ViewModelHelper<MyViewModel>() {
      @NotNull
      @Override
      public MainViewModel onCreateViewModel() {
          return new MyViewModel(..., ..., ...);
      }
  });
  ```


### Version 2.1.1 (2018-08-04)

- Just version change.


### Version 2.1.0-beta01 (2018-08-03)

- Add lazyInject

    ```
    private val viewModel: YourClassViewModel by /* activity or fragment default this */.lazyInject(/* customKey */) {
        YourClassViewModel().apply {
            // More ViewModel init.
        }
    }
    ```

- Change initialized

    ```
    val viewModel = /* activity or fragment default this */.inject {
        YourClassViewModel().apply {
            // More ViewModel init.
        }
    }
    ```


### Version 2.0.0-beta01 (2018-08-03)

- Version update.
- Change initialized in lifecycle extensions.

    ```
    YourClassViewModel::class.java.inject(this) {
        // Your class inject.

        YourClassViewModel().apply {
            // ViewModel init
        }
    }
    ```

### Version 1.1.1 (2018-06-07)

- Support Android 28.(Android P)


### Version 1.1.0 (2018-06-05)

- Update android new package. androidx
- Update lifecycle library.
- Android appcompat version 1.0.0-alpha1


### Version 1.0.0-alpha3 (2017-07-27)

- Fixed syntax.


### Version 1.0.0-alpha2 (2017-07-26)

- Update buildToolsVersion(26.0.1)
- Code refactoring


### Version 1.0.0-alpha1 (2017-07-24)

- It provides an Inject that simplifies the use of the ViewModel.Factory of Android Architecture Components.