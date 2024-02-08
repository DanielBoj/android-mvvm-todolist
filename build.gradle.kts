// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false

    // KSP Compatibility
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    // Dagger Hilt
    id("com.google.dagger.hilt.android") version "2.44" apply false
}