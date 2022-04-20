# Brief Introduction
This is a simple Android application that aims at downloading data about sites of interest using
API REST calls and show them to the user as markers in a map with embedded information that can be
displayed when clicking this markers.

### Getting started
This code needs to be executed on an Android device or an Android emulator.

### Installation Requirements
The following requirements are needed:
- `gradle` tool for compiling



### Installation
First ensure `gradle` is installed. The following works in macOS using `Homebrew`:
```Bash
if (! gradle --version 2> /dev/null); then brew install gradle; fi
```

Once gradle is installed, do one of the following:
* If you haven't cloned this repository already:
```Bash
git clone git@github.com:piloudu/map-resource-locator.git
cd map-resource-locator
gradle build
```

* If you have cloned this repository in `<path_to_repository>`:
```Bash
cd <path_to_repository>
gradle build
```

Once the `gradle build` finishes, an Android installable `.apk` file will be generated in `<path_to_repository>/app/build/outputs/apk/release/` folder.
