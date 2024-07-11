plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
    id ("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.placevisited"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.placevisited"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // ViewModel and LiveData
    implementation (libs.lifecycle.viewmodel)
    implementation (libs.androidx.lifecycle.livedata)

    // Room
    implementation (libs.androidx.room.runtime)
    annotationProcessor (libs.androidx.room.compiler)

    // Firebase Authentication
    implementation(libs.firebase.auth)

    // firestore for storing data in pages
    implementation(libs.firebase.firestore)

    // navigation complement
    implementation (libs.androidx.navigation.fragment)
    implementation (libs.androidx.navigation.ui)
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)

    // hilt dependency injection
    annotationProcessor (libs.androidx.hilt.compiler)
    implementation (libs.hilt.android)
    annotationProcessor (libs.hilt.android.compiler)
}