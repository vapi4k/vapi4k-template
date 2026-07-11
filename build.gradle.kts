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

application {
  mainClass.set(mainClassName)
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

configureKotlin()
configureKtor()
configureVersions()

fun Project.configureKotlin() {
// This must match the version defined in system.properties
  kotlin {
    jvmToolchain(jvmVersion)
  }
}

fun Project.configureKtor() {
  ktor {
    fatJar {
      // Change this to whatever name you want
      // It also has to be changed in the Dockerfile
      archiveFileName.set(fatJarName)
    }
  }
}

fun Project.configureVersions() {
  // A pre-release qualifier is a `.` or `-` delimiter followed by a known unstable
  // keyword. `m\d` matches milestones (`-M1`/`.M2`) without catching stable classifiers
  // like `-macos`/`-MR1`, and the `[.-]` delimiter catches both dash-style (`-alpha`)
  // and dot-style (Netty's `.Beta1`) qualifiers while leaving `-jre`/`.Final` stable.
  val preReleaseQualifier =
    Regex("""[.-](rc|beta|alpha|m\d|cr|snapshot|eap|dev|milestone|pre)""", RegexOption.IGNORE_CASE)

  fun isNonStable(version: String): Boolean = preReleaseQualifier.containsMatchIn(version)

  tasks.withType<DependencyUpdatesTask>().configureEach {
    notCompatibleWithConfigurationCache("the dependency updates plugin is not compatible with the configuration cache")
    // Reject a pre-release candidate only when the current version is stable. For
    // dependencies we intentionally track on a pre-release line (e.g. a detekt
    // alpha), newer pre-releases are still surfaced as available updates.
    rejectVersionIf {
      isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
  }
}
