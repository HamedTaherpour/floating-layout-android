# floating-layout-android
[![](https://jitpack.io/v/HamedTaherpour/floating-layout-android.svg)](https://jitpack.io/#HamedTaherpour/floating-layout-android)


## Download
```gradle
	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}  
	dependencies {
    implementation 'com.github.HamedTaherpour:floating-layout-android:1.0.0'
	}
```

## Usage
```java
  FloatingLayout floatingLayout = new FloatingLayout(this, R.layout.floating_layout, this);
  if (!floatingLayout.isShow())
    floatingLayout.create();
```


