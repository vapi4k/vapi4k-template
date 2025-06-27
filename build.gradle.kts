import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
  java
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.ktor)
  alias(libs.plugins.versions)
}

group = "com.myapp"
version = "1.1.0"

application {
  mainClass.set("com.myapp.ApplicationKt")
}

ktor {
  fatJar {
    // Change this to whatever name you want
    // It also has to be changed in the Dockerfile
    archiveFileName.set("vapi4k-template.jar")
  }
}

// This must match the version defined in system.properties
kotlin {
  jvmToolchain(17)
}

repositories {
  google()
  mavenCentral()
  maven(url = "https://jitpack.io")  // Required for the vapi4k jars
}

dependencies {
  implementation(libs.vapi4k.core)
  implementation(libs.vapi4k.dbms)
}

// Required for heroku deployments
tasks.register("stage") {
  dependsOn("build", "clean")
  doLast {
    println("Stage task completed")
  }
}

// Required for heroku deployments
tasks.named("build") {
  mustRunAfter("clean")
}

tasks.withType<DependencyUpdatesTask> {
  rejectVersionIf {
    listOf("BETA", "-RC").any { candidate.version.uppercase().contains(it) }
  }
}
