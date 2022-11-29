apply<AndroidLibraryPlugin>()

dependencies {
    implementation(projects.domain)

    implementation(gradleDependencies.libs.kotlin.coroutines.core)
    implementation(gradleDependencies.libs.di.koin.core)

    implementation(gradleDependencies.libs.bundles.networking.retrofit)
    api(gradleDependencies.libs.networking.retrofit2.retrofit)
}