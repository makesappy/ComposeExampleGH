import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(dependency: Any) {
    "implementation"(dependency)
}

fun DependencyHandlerScope.implementation(dependencies: List<Any>) {
    dependencies.forEach { "implementation"(it) }
}

fun DependencyHandlerScope.testImplementation(dependency: Any) {
    "testImplementation"(dependency)
}

fun DependencyHandlerScope.testImplementation(dependencies: List<Any>) {
    dependencies.forEach { "testImplementation"(it) }
}

fun DependencyHandlerScope.debugImplementation(dependency: Any) {
    "debugImplementation"(dependency)
}

fun DependencyHandlerScope.debugImplementation(dependencies: List<Any>) {
    dependencies.forEach { "debugImplementation"(it) }
}

fun DependencyHandlerScope.androidTestImplementation(dependency: Any) {
    "androidTestImplementation"(dependency)
}

fun DependencyHandlerScope.androidTestImplementation(dependencies: List<Any>) {
    dependencies.forEach { "androidTestImplementation"(it) }
}

fun DependencyHandlerScope.api(dependency: Any) {
    "api"(dependency)
}