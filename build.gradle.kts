import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.ktor)
  alias(libs.plugins.versions)
}

val mainClassName = "com.myapp.ApplicationKt"
val fatJarName = "vapi4k-template.jar"
val jvmVersion = libs.versions.jvm.get().toInt()

val buildFatJarTask = "buildFatJar"
val cleanTask = "clean"
val stageTask = "stage"

val preReleaseSuffixes = listOf("-RC", "-BETA", "-ALPHA", "-M")

application {
  mainClass.set(mainClassName)
}

ktor {
  fatJar {
    // Change this to whatever name you want
    // It also has to be changed in the Dockerfile
    archiveFileName.set(fatJarName)
  }
}

// This must match the version defined in system.properties
kotlin {
  jvmToolchain(jvmVersion)
}

dependencies {
  implementation(libs.vapi4k.core)
  implementation(libs.vapi4k.dbms)
}

// Required for heroku deployments
tasks.register(stageTask) {
  dependsOn(buildFatJarTask, cleanTask)
  doLast {
    println("Stage task completed")
  }
}

// Required for heroku deployments
tasks.named(buildFatJarTask) {
  mustRunAfter(cleanTask)
}

tasks.withType<DependencyUpdatesTask> {
  rejectVersionIf {
    preReleaseSuffixes.any { candidate.version.uppercase().contains(it) }
  }
}
