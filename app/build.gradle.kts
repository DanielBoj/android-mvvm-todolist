plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // KSP Compatibility
    id("com.google.devtools.ksp")
    // Dagger Hilt
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "dev.dboj.mvvmtodoapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.dboj.mvvmtodoapp"
        minSdk = 27
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Jetpack Compose Lifecycle ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    // Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Room
    implementation("androidx.room:room-runtime:${Versions.ANDROID_ROOM}")
    ksp("androidx.room:room-compiler:${Versions.ANDROID_ROOM}")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    // Room Coroutines
    implementation("androidx.room:room-ktx:${Versions.ANDROID_ROOM}")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:${Versions.GOOGLE_ANDROID_HILT}")
    ksp("com.google.dagger:hilt-android-compiler:${Versions.GOOGLE_ANDROID_HILT}")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    ksp("androidx.hilt:hilt-compiler:${Versions.ANDROIDX_HILT}")
    implementation("androidx.hilt:hilt-navigation-compose:${Versions.ANDROIDX_HILT}")
}

object Versions {
    const val ANDROID_ROOM = "2.6.1"
    const val GOOGLE_ANDROID_HILT = "2.48"
    const val ANDROIDX_HILT = "1.1.0"
}