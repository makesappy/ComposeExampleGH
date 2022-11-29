apply<AndroidLibraryPlugin>()

dependencies {
    implementation(projects.domain)

    implementation(gradleDependencies.libs.kotlin.coroutines.core)

    implementation(gradleDependencies.libs.di.koin.core)

    implementation(gradleDependencies.libs.androidx.securityCrypto)

    implementation(gradleDependencies.libs.bundles.networking.retrofit)
    api(gradleDependencies.libs.networking.retrofit2.retrofit)

    implementation(gradleDependencies.libs.database.room.ktx)
    implementation(gradleDependencies.libs.database.room.runtime)
    implementation(gradleDependencies.libs.database.sqlCipher)

    ksp(gradleDependencies.libs.database.room.compiler)
}