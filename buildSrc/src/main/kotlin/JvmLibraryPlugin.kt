import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.apply

class JvmLibraryPlugin : Plugin<Project> by local plugin {
    apply(plugin = "kotlin")
    apply(plugin = "com.android.lint")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
    apply<CompilerConfigPlugin>()
    apply<TestConfigPlugin>()
}