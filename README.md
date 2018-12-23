# Kotlin WebExtensions API Declarations

A generator for Kotlin JS declarations for the Firefox WebExtensions API from the offical schema files. This repo also contains the ready-made generated declarations.

The schema is taken from https://github.com/mozilla/gecko-dev/tree/master/browser/components/extensions/schemas and https://github.com/mozilla/gecko-dev/tree/master/toolkit/components/extensions/schemas.

See KotlinConf 2018 talk: [Building a Browser Extension with Kotlin](https://kotlinconf.com/talks/#session=21914)

See https://developer.mozilla.org/en-US/docs/Mozilla/Add-ons/WebExtensions

## Example projects

- https://github.com/cypressious/webextension-search-kotlin-docs
- https://github.com/cypressious/second-firefox-extension-kotlin


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
    compile 'com.github.cypressious.kotlin-webextensions-declarations:webextensions-declarations:v0.4'
}
```
