# Kotlin WebExtensions API Declarations

This project contains Kotlin JS declarations for the Firefox WebExtensions API generated from the offical schema files.

The schema is taken from https://github.com/mozilla/gecko-dev/tree/master/browser/components/extensions/schemas and https://github.com/mozilla/gecko-dev/tree/master/toolkit/components/extensions/schemas.

## How to use in a Gradle project

You can include the declarations in your project by using jitpack.io.

In your `build.gradle` add

```groovy
repositories {
    // ...
    maven { url 'https://jitpack.io' }
}
```

and

```groovy 
dependencies {
    // ...
    compile 'com.github.cypressious.kotlin-webextensions-declarations:webextensions-declarations:v0.1'
}
```

## Example project

See https://github.com/cypressious/second-firefox-extension-kotlin



