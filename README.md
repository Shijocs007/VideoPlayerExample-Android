# VideoPlayerExample-Android
This is a video player library for android app development.

## ADD THE FOLLOWING GRADLE CODES IN YOUR PROJECT

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	
		dependencies {
	        compile 'com.github.Shijocs007:VideoPlayerExample-Android:0.1.0' // get INTERNET permission in the app manifest

	}
	
## Add the following code in the xml view

	<FrameLayout
        android:id="@+id/content_frame"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
 
 ## Add the below code in the java class
 
 	 VideoManager.getInstance().setmActivity(this); // this line is very important, pass the activity here
        Bundle bundle = new Bundle();
        bundle.putString("url","http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");  //pass the url here
        Fragment videoView = new VideoViewFragment();
        videoView.setArguments(bundle);
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
        fragmentTransaction1.replace(R.id.content_frame, videoView, "video_view_example");
        fragmentTransaction1.commit();
 
 


