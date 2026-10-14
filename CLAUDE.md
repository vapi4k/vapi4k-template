# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Vapi4k Application Template — a Kotlin/Ktor web server using the [Vapi4k](https://github.com/vapi4k/vapi4k) plugin to build AI voice applications with [Vapi](https://vapi.ai). Docs: https://docs.vapi4k.com/

## Build Commands

```bash
./gradlew build -x test          # Build (no tests in this template)
./gradlew buildFatJar             # Create fat JAR (build/libs/vapi4k-template.jar)
java -jar build/libs/vapi4k-template.jar  # Run the fat JAR
./gradlew dependencyUpdates       # Check for dependency updates
```

Makefile shortcuts: `make build`, `make jar`, `make run-jar`, `make build-docker`, `make run-docker`,
`make versions` (only outdated dependencies, via the `outputFormatter` in `build.gradle.kts`), and `make versions-all`
(the plugin's full report; `--output-formatter=plain` overrides that formatter).

## Key Conventions

- **JVM 21**: pinned in *both* `build.gradle.kts` (`jvmToolchain`) and `system.properties` (Heroku). Changing one
  without the other breaks Heroku deploys.
- **Version catalog**: update dependency versions in `gradle/libs.versions.toml`, not in `build.gradle.kts`.
