import org.gradle.api.Project
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object ProjectConfiguration {
    val applicationId = "com.nous.example"
    val versionCode = 1
    val versionName = "1.0.0"

    object Android {
        const val minSdk = 26
        const val targetSdk = 33
        const val compileSdk = targetSdk
    }
}