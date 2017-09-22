# VideoPlayerExample-Android
This is a video player library for android app development.

# ADD THE FOLLOWING GRADLE CODES IN YOUR PROJECT

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	
		dependencies {
	        compile 'com.github.Shijocs007:VideoPlayerExample-Android:0.1.0'
	}

 **add following line in the app manifest.xml

  <uses-permission android:name="android.permission.INTERNET" />
