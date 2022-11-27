apply<AndroidLibraryPlugin>()

dependencies {
    implementation(projects.domain)

    implementation(gradleDependencies.libs.kotlin.coroutines.core)
    implementation(gradleDependencies.libs.di.koin.core)
}