plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.oli.mywearremote"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.oli.mywearremote"
        minSdk = 30
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }

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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Koin
    implementation("io.insert-koin:koin-androidx-compose:3.3.0")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    implementation("com.google.android.gms:play-services-wearable:18.0.0")

    implementation("androidx.percentlayout:percentlayout:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.0")

    implementation("androidx.wear:wear:1.2.0")

    implementation("androidx.activity:activity-compose:1.5.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    val composeVersion = "1.2.1"
    implementation("androidx.compose.ui:ui:$composeVersion")
    // implementation("androidx.compose.ui:ui-tooling-preview$composeVersion")
    // val material3Version = "1.2.0-alpha10"
    // implementation("androidx.compose.material3:material3-desktop:$material3Version")

    val wearComposeVersion = "1.2.1"
    implementation("androidx.wear.compose:compose-navigation:$wearComposeVersion")
    implementation("androidx.wear.compose:compose-material:$wearComposeVersion")
    implementation("androidx.wear.compose:compose-foundation:$wearComposeVersion")

    /*
    val horologistVersion = "0.1.8"
    implementation("com.google.android.horologist:horologist-compose-tools:$horologistVersion")
    implementation("com.google.android.horologist:horologist-tiles:$horologistVersion")

    val tilesVersion = "1.1.0"
    implementation("androidx.wear.tiles:tiles:$tilesVersion")
    implementation("androidx.wear.tiles:tiles-material:$tilesVersion")
    */

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))

    wearApp(project(":wear"))
}