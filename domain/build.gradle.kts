apply<JvmLibraryPlugin>()

dependencies{
    implementation(gradleDependencies.libs.kotlin.coroutines.core)
    implementation(gradleDependencies.libs.di.koin.core)
    implementation(gradleDependencies.libs.networking.retrofit2.kotlinSerializerConverter)

    testImplementation(gradleDependencies.libs.bundles.test.unitTest)
}