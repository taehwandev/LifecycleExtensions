## Change Log

### Version 1.3.0 (2018-08-04)

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

### Version 1.2.0 (2018-08-03)

- New legacy branch
    - Support library 27.1.1
    - lifecycle 1.1.1
- Change initialized in lifecycle extensions.

    ```
    MainViewModel::class.java.inject(this) {
        // Your class inject.

        MainViewModel().apply {
            // ViewModel init
        }
    }
    ```

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
