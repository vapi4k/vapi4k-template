import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

val kotlin_version: String by project
val logback_version: String by project
val vapi4k_version: String by project

plugins {
  val kotlinVersion: String by System.getProperties()
  val ktorVersion: String by System.getProperties()
  val versionsVersion: String by System.getProperties()

  java
  kotlin("jvm") version kotlinVersion
  id("io.ktor.plugin") version ktorVersion
  id("com.github.ben-manes.versions") version versionsVersion
}

group = "com.myapp"
version = "1.0.0"

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
  jvmToolchain(11)
}

repositories {
  google()
  mavenCentral()
  maven(url = "https://jitpack.io")  // Required for the vapi4k jars
}

dependencies {
  implementation("com.github.vapi4k.vapi4k:vapi4k-core:$vapi4k_version")
  implementation("com.github.vapi4k.vapi4k:vapi4k-dbms:$vapi4k_version")

  testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
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
