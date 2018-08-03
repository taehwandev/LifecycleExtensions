## Change Log

### Version 2.0.0-beta01 (2018-08-03)

- Version update.
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