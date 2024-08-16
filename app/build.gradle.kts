plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.serialization)

    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")

//    id("kotlin-kapt")
//    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.alaishat.mohammad.mealzapp"
    compileSdk = 34

//    repositories {
//        google()
//        mavenCentral()
//    }
    defaultConfig {
        applicationId = "com.alaishat.mohammad.mealzapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // Ktor and Serialization:
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.json)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$1.6.3")


    implementation(libs.androidx.core.ktx)

    implementation(libs.gson)

    implementation(libs.coil.compose)

    implementation(libs.androidx.navigation.compose)

//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")


//    implementation("com.google.dagger:hilt-android:2.44")
//    kapt("com.google.dagger:hilt-android-compiler:2.44")


    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    implementation(project(path = ":domain"))
    implementation(project(path = ":data"))

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")


    implementation("androidx.core:core-splashscreen:1.0.1")

    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    ksp(libs.androidx.room.compiler)





}

//// Allow references to generated code
kapt {
    correctErrorTypes = true
}

