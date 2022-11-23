import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import java.io.File
import java.io.FileInputStream
import java.util.Properties

class AndroidAppPlugin : Plugin<Project> by local plugin {
    apply(plugin = "com.android.application")
    apply(plugin = "kotlin-android")
    apply(plugin = "com.mikepenz.aboutlibraries.plugin")
    apply<CompilerConfigPlugin>()
    apply<TestConfigPlugin>()

    androidApplication {
        compileSdk = ProjectConfiguration.Android.compileSdk
        namespace = namespace()

        defaultConfig {
            applicationId = ProjectConfiguration.applicationId
            minSdk = ProjectConfiguration.Android.minSdk
            targetSdk = ProjectConfiguration.Android.targetSdk
            versionCode = ProjectConfiguration.versionCode
            versionName = ProjectConfiguration.versionName

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            release {
                with(ProjectConfiguration) {
                    isDebuggable = false
                    isMinifyEnabled = true
                    isShrinkResources = true
                }

                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }

            debug {
                with(ProjectConfiguration) {
                    isDebuggable = true
                    isMinifyEnabled = false
                    isShrinkResources = false
                }

                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }

            create("benchmark") {
                initWith(getByName("release"))
                signingConfig = signingConfigs.getByName("debug")
                matchingFallbacks += listOf("release")
            }
        }

        buildFeatures.apply {
            compose = true
            aidl = false
            renderScript = false
            shaders = false
        }

        composeOptions {
            kotlinCompilerExtensionVersion = gradleDependencies.versions.composeCompiler
        }

        lint {
            lintConfig = File(rootDir, "lint.xml")
            checkDependencies = true
            ignoreTestSources = true
            warningsAsErrors = true
            checkGeneratedSources = false
        }
    }
}