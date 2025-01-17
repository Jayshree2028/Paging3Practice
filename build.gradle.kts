buildscript {
    dependencies {
        val nav_version = "2.7.7"
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id ("com.android.application") version "8.0.2" apply false
    id ("com.android.library") version "8.0.2" apply false
    id ("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id ("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
}
tasks.register("cleanRootBuildDir", Delete::class) {
    delete(rootProject.buildDir)
}