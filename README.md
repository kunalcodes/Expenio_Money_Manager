# Expenio

Expenio is an expense tracking, money management app for better financial planning.



 ![GitHub Cards Preview](https://github.com/kunalcodes/Expenio_Money_Manager/blob/main/Expenio/app/src/main/res/drawable/github_project_image.jpg)



# Features ✨
* User authentication
* List of most recent transactions.
* Detailed list of all the previous transactions.
* Balance info.
* Review and manage transactions.
<br/>


# Get it on playstore:


<a href="https://play.google.com/store/apps/details?id=kunal.project.expenio" title="Playstore" target="_blank"><img height="120" width="240" src="https://data.ibtimes.sg/en/full/12247/google-play-store-8-1-73-apk.png"></a>

<!-- 
# Links 
* Blog-Post :-  -->

# Tech Stack ✨

* Kotlin
* Android studio
* REST-API
* Anko toast library 
* Figma
* Retrofit
* Room DB
* Live-data
* Coroutines
* Mvvm
* Hilt dependecy injection


# Clone this Repo To Your System Using Android Studio✨

* Step 1: Copy the project url from this repository as shown below.


  ![GitHub Cards Preview](https://media.geeksforgeeks.org/wp-content/uploads/20201103234355/Clone1.png)
  
  
* Step 2: Open your Android Studio then go to the File > New > Project from Version Control.


  ![GitHub Cards Preview](https://media.geeksforgeeks.org/wp-content/uploads/20201103235112/Clone2.png)
  
  
* Step 3: After clicking on the Project from Version Control a pop-up screen will arise. In the Version control choose Git from the drop-down menu.


  ![GitHub Cards Preview](https://media.geeksforgeeks.org/wp-content/uploads/20201103235114/Clone3.png)
  
  
* Step 4: Then at last paste the link in the URL and choose your Directory. Click on the Clone button and you are done.


  ![GitHub Cards Preview](https://media.geeksforgeeks.org/wp-content/uploads/20201103235115/Clone4.png)
  

# Clone this Repo To Your System Using GitBash✨

* Open Git Bash

* If Git is not already installed, it is super simple. Just go to the Git Download Folder and follow the instructions.

* Go to the current directory where you want the cloned directory to be added.

* To do this, input cd and add your folder location. You can add the folder location by dragging the folder to Git bash.

* Go to the page of the repository that you want to clone

* Click on “Clone or download” and copy the URL.

* Use the git clone command along with the copied URL from earlier. $ git clone https://github.com/kunalcodes/Expenio_Money_Manager.git

* Press Enter. $ git clone https://github.com/kunalcodes/Expenio_Money_Manager.git Cloning into Git … remote: Counting objects: 13, done. remote: Compressing objects: 100% (13/13), done. remove: Total 13 (delta 1), reused 0 (delta 1) Unpacking objects: 100% (13/13), done.

Congratulations, you have created your first local clone from your remote Github repository.

Open Android Studio. Go to File > New > Project From Version Control. Copy the link of this repositary. Paste the link in Url Box of Android Studio window and click on "Clone" button.


# Dependencies 

    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.4.0'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.2'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    //Anko
    def anko_version = '0.10.0'
    implementation "org.jetbrains.anko:anko-commons:$anko_version"

    //Room
    implementation 'androidx.room:room-ktx:2.1.0'
    kapt 'androidx.room:room-compiler:2.1.0'

    // ViewModel and LiveData
    def arch_version = '2.2.0-alpha01'
    implementation "androidx.lifecycle:lifecycle-extensions:$arch_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$arch_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$arch_version"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$arch_version"

    // Retrofit & OkHttp
    implementation 'com.squareup.retrofit2:retrofit:2.8.1'
    // This library is used by retrofit internally to convert json-pojo and pojo-json
    implementation 'com.squareup.retrofit2:converter-gson:2.6.1'
    //This library is used to observe the API logs, Http status code and the API response
    implementation 'com.squareup.okhttp3:logging-interceptor:4.7.2'
    implementation 'com.github.bumptech.glide:glide:4.12.0'

    //hilt
    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")

    //ViewModels delegation extentensions for activity
    implementation 'androidx.activity:activity-ktx:1.3.1'
<!-- 
# Lessons Learnt📚 -->

# 🔗Links to resources and Open-Source Libraries


* [Android Studio](https://developer.android.com/studio?gclsrc=aw.ds&gclid=EAIaIQobChMI3MPrr7bC9AIVEA4rCh1cBA5PEAAYASAAEgJR7_D_BwE)
* [API](https://www.getpostman.com/collections/5e8e73d832975377546a)
* [Kotlin](https://kotlinlang.org/)
* [Retrofit](https://github.com/square/retrofit)
* [Anko Toast](https://github.com/Kotlin/anko)
* [UI Design Reference](https://www.figma.com/file/aYX52ysnR7xLkNxKL2jf6x/Expenio---Personal-Finance-UI-Kit-(Community))
