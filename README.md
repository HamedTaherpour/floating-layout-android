# floating-layout-android
Floating Layout for android platform

[![](https://jitpack.io/v/HamedTaherpour/floating-layout-android.svg)](https://jitpack.io/#HamedTaherpour/floating-layout-android)


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
    implementation 'com.github.HamedTaherpour:floating-layout-android:1.0.1'
}
```

## Usage
### Manifest
##### Step 1. Add permission and service to your manifest file
```xml
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />

<application>
    <service android:name="com.hamedtaherpour.floatinglayout.FloatingLayoutService" />
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
FloatingLayout floatingLayout = new FloatingLayout(this, R.layout.floating_layout, new FloatingLayout.CallBack() {
    @Override
    public void onClickListener(int resourceId) {
    
    }

    @Override
    public void onCreateListener(View view) {
    }
    
    @Override
    public void onCloseListener() {
    
    }
});
floatingLayout.create();
```
For handle on click you need add tag (Click)
```xml
android:tag="Click"
```
Somting like this
```xml
<Button
    android:id="@+id/btn_close"
    android:layout_width="40dp"
    android:layout_height="40dp"
    android:tag="Click" />
```

## License
    MIT License

    Copyright (c) 2019 Hamed

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
