@Suppress("ClassName") // We want to use the same syntax as Gradle Version catalog generated accessor
object gradleDependencies {
    object versions {
        internal const val accompanist = "0.27.1"

        internal const val androidxAppcompat = "1.5.1"
        internal const val androidxLifecycle = "2.5.1"
        const val composeCompiler = "1.3.2"
        const val compose = "1.3.1"
        internal const val coroutines = "1.6.4"
        internal const val koin = "3.2.0"
        internal const val mockk = "1.12.4"
        internal const val retrofit = "2.9.0"
        internal const val room = "2.5.0-beta02"
        internal const val okhttp = "5.0.0-alpha.10"
        internal const val coil = "2.2.2"
    }

    object libs {
        object androidx {
            val annotation = "androidx.annotation:annotation" version "1.3.0"
            val core = "androidx.core:core-ktx" version "1.9.0"
            val appcompat = "androidx.appcompat:appcompat" version versions.androidxAppcompat
            val appcompatResources = "androidx.appcompat:appcompat-resources" version versions.androidxAppcompat
            val activityKtx = "androidx.activity:activity-ktx" version "1.6.1"
            val lifecycleCommon = "androidx.lifecycle:lifecycle-common" version versions.androidxLifecycle
            val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx" version versions.androidxLifecycle
            val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx" version versions.androidxLifecycle
            val splashScreen = "androidx.core:core-splashscreen" version "1.0.0-beta02"
            val browser = "androidx.browser:browser" version "1.4.0"
        }

        object theme {
            val googleFonts = "androidx.compose.ui:ui-text-google-fonts:1.3.1"
        }

        object debug {
            val leakCanary = "com.squareup.leakcanary:leakcanary-android" version "2.10"
        }

        object di {
            object koin {
                val core = "io.insert-koin:koin-core" version versions.koin
                val android = "io.insert-koin:koin-android" version versions.koin
                val compose = "io.insert-koin:koin-androidx-compose" version versions.koin
            }
        }

        object kotlin {
            const val version = "1.7.20"
            object coroutines {
                val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android" version versions.coroutines
                val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core" version versions.coroutines
                val playServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services" version versions.coroutines
                val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test" version versions.coroutines
            }

            val reflect = "org.jetbrains.kotlin:kotlin-reflect" version version
        }

        object networking {
            object retrofit2 {
                val retrofit = "com.squareup.retrofit2:retrofit" version versions.retrofit
                val kotlinSerializerConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter" version "0.8.0"
            }

            object okHttp {
                val client = "com.squareup.okhttp3:okhttp" version versions.okhttp
                val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor" version versions.okhttp
            }

            val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json" version "1.4.1"
        }

        object database {
            object room {
                val runtime = "androidx.room:room-runtime" version versions.room
                val ktx = "androidx.room:room-ktx" version versions.room
                val compiler = "androidx.room:room-compiler" version versions.room
                val testing = "androidx.room:room-testing" version versions.room
            }

            val sqlCipher = "net.zetetic:android-database-sqlcipher" version "4.5.1"
        }

        object security {
            object jwt {
                val decoder = "com.auth0.android:jwtdecode" version "2.0.1"
            }
        }

        object test {
            internal object android {
                val archCore = "android.arch.core:core-testing" version "2.1.0"
                val jUniAndroidXExt = "androidx.test.ext:junit" version "1.1.4-alpha06"
                val runner = "androidx.test:runner" version "1.5.0-alpha03"
                val core = "androidx.test:core" version "1.4.1-alpha06"
                val espresso = "androidx.test.espresso:espresso-core" version "3.5.0-alpha06"
                val uiautomator = "androidx.test.uiautomator:uiautomator" version "2.2.0"
                val benchmark = "androidx.benchmark:benchmark-macro-junit4" version "1.1.0-rc02"
            }

            internal object koin {
                val test = "io.insert-koin:koin-test" version versions.koin
                val testJUni4 = "io.insert-koin:koin-test-junit4" version versions.koin
            }

            internal object kotest {
                val assertions = "io.kotest:kotest-assertions-core-jvm" version "5.3.0"
            }

            object kotlinx {
                val metadataJvm = "org.jetbrains.kotlinx:kotlinx-metadata-jvm" version "0.4.2"
            }

            object jUnit {
                val jUnit4 = "junit:junit" version "4.13.2"
                val params = "pl.pragmatists:JUnitParams" version "1.1.1"
            }

            object mockk {
                val mockk = "io.mockk:mockk" version versions.mockk
                internal val mockkAndroid = "io.mockk:mockk-android" version versions.mockk
            }

            val turbine = "app.cash.turbine:turbine" version "0.8.0"
        }

        object ui {
            val coilCompose = "io.coil-kt:coil-compose" version versions.coil
            val coil = "io.coil-kt:coil" version versions.coil
            val composeReordable = "org.burnoutcrew.composereorderable:reorderable" version "0.9.2"
            val material = "com.google.android.material:material" version "1.7.0"

            object compose {
                internal val runtime = "androidx.compose.runtime:runtime" version versions.compose
                internal val compiler = "androidx.compose.compiler:compiler" version versions.composeCompiler
                internal val ui = "androidx.compose.ui:ui" version versions.compose
                internal val extended = "androidx.compose.material:material-icons-extended" version versions.compose
                internal val uiTooling = "androidx.compose.ui:ui-tooling" version versions.compose
                internal val foundation = "androidx.compose.foundation:foundation" version versions.compose
                internal val animation = "androidx.compose.animation:animation" version versions.compose
                internal val material3 = "androidx.compose.material3:material3" version "1.0.1"
                internal val constraintLayout = "androidx.constraintlayout:constraintlayout-compose" version "1.0.0"

                val activity = "androidx.activity:activity-compose" version "1.6.1"
                val navigation = "androidx.navigation:navigation-compose" version "2.5.0"
            }

            object accompanist {
                val pager = "com.google.accompanist:accompanist-pager" version versions.accompanist
                val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh" version versions.accompanist
                val placeholder = "com.google.accompanist:accompanist-placeholder-material" version versions.accompanist
                val systemUiController = "com.google.accompanist:accompanist-systemuicontroller" version versions.accompanist
            }

            object customView {
                val customView = "androidx.customview:customview" version "1.2.0-alpha01"
                val customViewPoolingContainer = "androidx.customview:customview-poolingcontainer" version "1.0.0-beta01"
            }
        }

        object licences {
            val aboutLibrariesCore = "com.mikepenz:aboutlibraries-core" version "10.5.1"
            val aboutLibrariesCompose = "com.mikepenz:aboutlibraries-compose" version "10.5.1"
        }

        object bundles {
            object networking {
                val retrofit = listOf(
                    libs.networking.retrofit2.retrofit,
                    libs.networking.retrofit2.kotlinSerializerConverter,
                    libs.networking.serialization,
                    libs.networking.okHttp.client
                )
            }

            object test {
                val android = listOf(
                    libs.test.kotest.assertions,
                    libs.test.android.core,
                    libs.test.android.runner,
                    libs.test.mockk.mockkAndroid,
                    libs.test.android.jUniAndroidXExt
                )

                val koin = listOf(
                    libs.test.koin.test,
                    libs.test.koin.testJUni4
                )

                val marcobenchmark = listOf(
                    libs.test.android.jUniAndroidXExt,
                    libs.test.android.espresso,
                    libs.test.android.uiautomator,
                    libs.test.android.benchmark
                )

                val unitTest = listOf(
                    libs.test.jUnit.jUnit4,
                    libs.test.jUnit.params,
                    libs.test.kotest.assertions,
                    libs.test.mockk.mockk,
                    libs.test.turbine,
                    libs.kotlin.coroutines.test
                )
            }

            object ui {
                val compose = listOf(
                    libs.ui.compose.extended,
                    libs.ui.compose.runtime,
                    libs.ui.compose.compiler,
                    libs.ui.compose.ui,
                    libs.ui.compose.uiTooling,
                    libs.ui.compose.foundation,
                    libs.ui.compose.animation,
                    libs.ui.material,
                    libs.ui.compose.material3,
                    libs.ui.compose.constraintLayout,
                    libs.ui.accompanist.swipeRefresh,
                    libs.ui.customView.customView,
                    libs.ui.customView.customViewPoolingContainer,
                )
            }
        }
    }
}

/** Appends version name in Gradle dependency coordinate format. */
private infix fun String.version(version: String) = "$this:$version"