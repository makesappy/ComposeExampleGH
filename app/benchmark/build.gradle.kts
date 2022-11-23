import gradleDependencies.libs.bundles.test.marcobenchmark

plugins {
    id("com.android.test")
    id("kotlin-android")
}

android {
    compileSdk = ProjectConfiguration.Android.compileSdk
    namespace = namespace()

    defaultConfig {
        minSdk = ProjectConfiguration.Android.minSdk
        targetSdk = ProjectConfiguration.Android.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        // This benchmark buildType is used for benchmarking, and should function like your
        // release build (for example, with minification on). It"s signed with a debug key
        // for easy local/CI testing.
        create("benchmark") {
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
        }
    }

    targetProjectPath = ":app:prod"
    experimentalProperties["android.experimental.self-instrumenting"] = true
}

dependencies {
    implementation(marcobenchmark)
}

androidComponents {
    beforeVariants(selector().all()) {
        it.enableUnitTest = it.buildType == "benchmark"
    }
}