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
	        implementation 'com.github.Rounak122:CustomSlider-Android:0.2.0'
	}
  
# Usage

1.XML:

            <com.example.customslider.CustomSlider
                android:id="@+id/customSlider"
                android:layout_width="300dp"
                android:layout_height="wrap_content"/>
In XML define a fixed width, it will be needed during initialization              

2.JAVA:

        //Slider with 3 regions (low, normal,high) | Use SetValues method for that
        customSlider1 = findViewById(R.id.customSlider1);
        customSlider1.setValues(0, 40000, 20000, 30000, 30000, 300);

        /* Slider with unlimited regions
         result will be set irrespective of weight, so it works only when all the weight are equal
         result = no. of block for res starting from 1 */

        customSlider2 = findViewById(R.id.customSlider2);
        customSlider2.setMultipleValues(4, new String[]{"#7596ff", "#7565ff", "#759685", "#829712", "#759325"}, new float[]{25, 25, 25, 25}, 5, 300);

