import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.withType

/**
 * Configures JVM heap size for JUnit tests.
 *
 * Note: `org.gradle.jvmargs` from gradle.properties configures JVM for Gradle but not for the unit tests.
 * From documentation: "Certain tasks, like the test task, also fork additional JVM processes. You can configure these through the tasks
 * themselves. They all use -Xmx512m by default."
 * Source: https://docs.gradle.org/current/userguide/build_environment.html#sec:configuring_jvm_memory
 */
internal class TestConfigPlugin : Plugin<Project> by local plugin {
    tasks.withType<Test> {
        maxHeapSize = "1024m"
    }
}
