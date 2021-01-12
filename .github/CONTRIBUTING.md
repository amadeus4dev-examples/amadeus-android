## Development and Testing

## How to run the project locally

* Clone the repository:
```sh
git https://github.com/amadeus4dev/amadeus-hotel-booking-android.git
```

* Install latest [Android Studio version](https://developer.android.com/studio)
* Open project's root folder `build.gradle` with Android Studio `File > Open`
* For authentication: Open the `.gradle` folder (root user) and edit/create the `gradle.properties` file. Add your [API Key and API Secret](https://developers.amadeus.com/get-started/get-started-with-self-service-apis-335) in the `gradle.properties`:
```gradle
amadeus.api.key="REPLACE_BY_YOUR_API_KEY"
amadeus.api.secret="REPLACE_BY_YOUR_API_SECRET"
```

### Running tests

To run the tests using a terminal use you can use gradle.

```sh
cd amadeus-android
./gradlew testDebugUnitTest --tests 'com.amadeus.android.unit*'
```

### Using library locally

To use the library locally, you should increment the number inside `gradle.properties` file.

```properties
# publication properties

VERSION=X.X.X
...
```

Then publish your new version locally.

```sh
./gradlew publishToMavenLocal
```

 And use it with the [hotel demo](https://github.com/amadeus4dev/amadeus-hotel-booking-android), by changing the version used with your own inside `build.gradle` file.

```gradle
    implementation 'com.amadeus:amadeus-android:X.X.X'
```


### Releasing

[TBD]

## How to contribute to the Amadeus Python SDK

#### **Did you find a bug?**

* **Ensure the bug was not already reported** by searching on GitHub under [Issues](https://github.com/amadeus4dev/amadeus-python/issues).

* If you're unable to find an open issue addressing the problem, [open a new one](https://github.com/amadeus4dev/amadeus-python/issues/new). Be sure to include a **title and clear description**, as much relevant information as possible, and a **code sample** or an **executable test case** demonstrating the expected behavior that is not occurring.

#### **Did you write a patch that fixes a bug?**

* Open a new GitHub pull request with the patch.

* Ensure the PR description clearly describes the problem and solution. Include the relevant issue number if applicable.

#### **Do you intend to add a new feature or change an existing one?**

* Suggest your change [in a new issue](https://github.com/amadeus4dev/amadeus-python/issues/new) and start writing code.

* Make sure your new code does not break any tests and include new tests.

* With good code comes good documentation. Try to copy the existing documentation and adapt it to your needs.

* Close the issue or mark it as inactive if you decide to discontinue working on the code.

#### **Do you have questions about the source code?**

* Ask any question about how to use the library by [raising a new issue](https://github.com/amadeus4dev/amadeus-python/issues/new).