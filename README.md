# floating-layout-android
Floating Layout for android platform

[![](https://jitpack.io/v/HamedTaherpour/floating-layout-android.svg)](https://jitpack.io/#HamedTaherpour/floating-layout-android)
[![Download](https://img.shields.io/badge/Android%20Arsenal-Floating%20Layout-red.svg)](https://android-arsenal.com/details/1/8202)

## Demo
<div style="dispaly:flex">
    <img src="/sample1.gif" width="24%">
    <img src="/sample2.gif" width="24%">
</div>

##### Example project [floating-video-player-android](https://github.com/HamedTaherpour/floating-video-player-android)

## Build
##### Step 1. Add the JitPack repository to your build file
```build
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
##### Step 2. Add the dependency
```build
dependencies {
    implementation 'com.github.HamedTaherpour:floating-layout-android:1.1.5'
}
```

## Usage
### Manifest
##### Step 1. Add permission and service to your manifest file
```xml
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />

<application>
    <service android:name="io.hamed.floatinglayout.service.FloatingService"/>
</application>
```
### Layout.xml
##### Step 2. Create layout and don't forget add id (root_container) like this
```layout
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <FrameLayout
        android:id="@+id/root_container"
        android:layout_width="250dp"
        android:layout_height="200dp">

        ....

    </FrameLayout>

</FrameLayout>
```
### Java
##### Step 3. Create FloatingLayout
```java
private FloatingListener floatingListener = new FloatingListener() {
    @Override
    public void onCreateListener(View view) {

    }

    @Override
    public void onCloseListener() {

    }
};

floatingLayout = new FloatingLayout(this, R.layout.sample_layout);
floatingLayout.setFloatingListener(floatingListener);
floatingLayout.create();
```
