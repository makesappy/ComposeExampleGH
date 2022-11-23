import ProjectConfiguration.applicationId
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.findByType
import java.io.ByteArrayOutputStream

/** Applies block to Project specific part of [T] type **/
internal inline fun <reified T : Any> Project.extension(block: T.() -> Unit) {
    extensions.findByType<T>()?.apply(block)
}

/** Applies block to Project specific part of [ApplicationExtension] type **/
internal fun Project.androidApplication(block: ApplicationExtension.() -> Unit) {
    extension(block)
}

/** Applies block to Project specific part of [LibraryExtension] type **/
fun Project.androidLibrary(block: LibraryExtension.() -> Unit) {
    extension(block)
}

/** Applies block to Project specific part of [JavaPluginExtension] type **/
internal fun Project.java(block: JavaPluginExtension.() -> Unit) {
    extension(block)
}

@Suppress("ClassName")
/** Helper object for sugar extensions **/
internal object local

/** Syntactic sugar for Project specification configuration **/
internal infix fun local.plugin(config: Project.() -> Unit) = Plugin<Project> { config(it) }

/**
 * Module namespace in format [applicationId].{module name without dashes}
 */
fun Project.namespace() = "$applicationId.${name.replace("-", "")}"