plugins {
    `kotlin-dsl`
}
kotlinDslPluginOptions {
    jvmTarget.set(JavaVersion.VERSION_11.toString())
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.3.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    implementation("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.7.20-1.0.6")
    implementation("com.mikepenz.aboutlibraries.plugin:aboutlibraries-plugin:10.5.1")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.7.20")
}