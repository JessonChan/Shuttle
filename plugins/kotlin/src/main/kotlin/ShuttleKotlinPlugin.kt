import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

abstract class ShuttleKotlinPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.setupKotlinPlugin()
    }
}

private fun Project.setupKotlinPlugin() {
    apply(plugin = "com.google.devtools.ksp")

    if (hasKotlinAndroidPlugin().not()) {
        apply(plugin = "org.jetbrains.kotlin.jvm")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlin.RequiresOptIn"
            )
        }
        setSource("build/generated/ksp/main/kotlin")
        setSource("build/generated/ksp/test/kotlin")
    }
}

private fun Project.hasKotlinAndroidPlugin() =
    plugins.hasPlugin("org.jetbrains.kotlin.android")
