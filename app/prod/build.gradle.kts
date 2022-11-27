apply<AndroidAppPlugin>()

dependencies {
    implementation(projects.domain)
    implementation(projects.data)

    implementation(gradleDependencies.libs.androidx.core)
    implementation(gradleDependencies.libs.androidx.appcompat)
    implementation(gradleDependencies.libs.androidx.splashScreen)

    implementation(gradleDependencies.libs.di.koin.core)
    implementation(gradleDependencies.libs.di.koin.android)
    implementation(gradleDependencies.libs.di.koin.compose)

    implementation(gradleDependencies.libs.kotlin.reflect)

    implementation(gradleDependencies.libs.ui.coil)
    implementation(gradleDependencies.libs.ui.compose.activity)
    implementation(gradleDependencies.libs.ui.compose.navigation)
    implementation(gradleDependencies.libs.bundles.ui.compose)
    implementation(gradleDependencies.libs.licences.aboutLibrariesCore)
    implementation(gradleDependencies.libs.licences.aboutLibrariesCompose)
    implementation(gradleDependencies.libs.theme.googleFonts)
    implementation(gradleDependencies.libs.ui.accompanist.systemUiController)

    debugImplementation(gradleDependencies.libs.debug.leakCanary)

    testImplementation(gradleDependencies.libs.bundles.test.unitTest)
    testImplementation(gradleDependencies.libs.test.kotlinx.metadataJvm)
    testImplementation(gradleDependencies.libs.bundles.test.koin)
}