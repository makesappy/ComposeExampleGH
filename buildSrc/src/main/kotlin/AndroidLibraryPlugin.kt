import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class AndroidLibraryPlugin : Plugin<Project> by local plugin {
    apply(plugin = "com.android.library")
    apply(plugin = "kotlin-android")
    apply<CompilerConfigPlugin>()
    apply<TestConfigPlugin>()

    androidLibrary {
        compileSdk = ProjectConfiguration.Android.compileSdk
        namespace = namespace()

        defaultConfig {
            minSdk = ProjectConfiguration.Android.minSdk
            targetSdk = ProjectConfiguration.Android.targetSdk

            buildConfigField("String", "VERSION_NAME", "\"${ProjectConfiguration.versionName}\"")
            buildConfigField("int", "VERSION_CODE", "${ProjectConfiguration.versionCode}")

            consumerProguardFiles("consumer-rules.pro")

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }
}