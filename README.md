# CustomSlider-Android
Custom Slider showing a range in a bar and pin-pointing certain value with a thumbnail



# Gradle
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.Rounak122:CustomSlider-Android:0.1.0'
	}
  
# Usage

1.XML:

            <com.example.customslider.CustomSlider
                android:id="@+id/customSlider"
                android:layout_width="300dp"
                android:layout_height="wrap_content"/>
In XML define a fixed width, it will be needed during initialization              

2.JAVA:

        customSlider=findViewById(R.id.customSlider);
        customSlider.setValues(0,40000,4000,22000,9000,300); //float min, float max, float normalMin, float normalMax, float res, int sliderWholeLayoutWidth
