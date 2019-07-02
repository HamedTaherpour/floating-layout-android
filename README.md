# floating-layout-android
[![](https://jitpack.io/v/HamedTaherpour/floating-layout-android.svg)](https://jitpack.io/#HamedTaherpour/floating-layout-android)

### Build
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
	implementation 'com.github.HamedTaherpour:floating-layout-android:1.0.0'
}
```

## Usage
#### Layout.xml

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
```java
FloatingLayout floatingLayout = new FloatingLayout(this, R.layout.floating_layout, this);
	if (!floatingLayout.isShow())
		floatingLayout.create();
```


