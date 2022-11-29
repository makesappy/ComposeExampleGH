apply<JvmLibraryPlugin>()

dependencies{
    implementation(gradleDependencies.libs.kotlin.coroutines.core)
    implementation(gradleDependencies.libs.di.koin.core)

    implementation(gradleDependencies.libs.bundles.networking.retrofit)
    implementation(gradleDependencies.libs.networking.okHttp.loggingInterceptor)
    api(gradleDependencies.libs.networking.retrofit2.retrofit)
}