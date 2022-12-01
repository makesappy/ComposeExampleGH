import com.mikepenz.aboutlibraries.plugin.AboutLibrariesExtension
import com.mikepenz.aboutlibraries.plugin.DuplicateMode
import com.mikepenz.aboutlibraries.plugin.DuplicateRule
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import java.io.File
import java.io.FileInputStream
import java.util.Properties

class AndroidAppPlugin : Plugin<Project> by local plugin {
    apply(plugin = "com.android.application")
    apply(plugin = "kotlin-android")
    apply(plugin = "com.google.devtools.ksp")
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
                isDebuggable = false
                isMinifyEnabled = true
                isShrinkResources = true
                manifestPlaceholders["isProfilingEnabled"] = false

                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }

            debug {
                isDebuggable = true
                isMinifyEnabled = false
                isShrinkResources = false
                manifestPlaceholders["isProfilingEnabled"] = true

                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }

            create("benchmark") {
                initWith(getByName("release"))
                signingConfig = signingConfigs.getByName("debug")
                matchingFallbacks += listOf("release")
                manifestPlaceholders["isProfilingEnabled"] = true
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

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
        extension<AboutLibrariesExtension> {
            duplicationMode = DuplicateMode.MERGE
            duplicationRule = DuplicateRule.GROUP
        }
    }
}