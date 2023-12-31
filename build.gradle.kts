// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed

plugins {
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.sqldelight) apply false
    alias(libs.plugins.detekt) apply true
}

detekt {
    toolVersion = libs.versions.detekt.asProvider().get()
    config.setFrom(file("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
}

dependencies {
    detektPlugins(libs.detekt.compose)
}

true // Needed to make the Suppress annotation work for the plugins block
