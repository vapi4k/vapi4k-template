# Release Notes — v1.2.0 (2026-07-10)

The full version history is in [CHANGELOG.md](CHANGELOG.md). This file
covers the most recent release only and is intended for use as the body of a
GitHub release.

This is the first template release versioned independently of the bundled
Vapi4k library.

## Highlights

- **Vapi4k 1.8.0**, **Ktor 3.5.1**, and **Kotlin 2.4.0** — the latest upstream
  features and fixes.
- **Gradle 9.6.1 wrapper.**
- **Continuous integration.** A new GitHub Actions workflow
  (`.github/workflows/ci.yml`) builds the project on every push and pull
  request.
- **Build script refactor.** `build.gradle.kts` configuration is split into
  `configureKotlin`, `configureKtor`, and `configureVersions` helpers. The
  `dependencyUpdates` pre-release filter now uses a delimiter-aware regex that
  rejects an unstable candidate only when the current version is stable, so
  dependencies intentionally tracked on a pre-release line still surface newer
  pre-releases.
- **Makefile modernization.** Parsed project and dependency versions, a
  self-documenting `help` target, guards for the release/Docker targets, and
  Docker build/run/push shortcuts. The `versioncheck` target is now `versions`.
- **Project history docs.** `CHANGELOG.md` and `RELEASE_NOTES.md` were added.

## Upgrade notes

If you have a fork or downstream copy of this template, no source changes are
required. To match this release:

1. Upgrade the Gradle wrapper to **9.6.1** (`make upgrade-wrapper` or
   `./gradlew wrapper --gradle-version 9.6.1`).
2. Pull the dependency versions from `gradle/libs.versions.toml` (Vapi4k
   **1.8.0**, Ktor **3.5.1**, Kotlin **2.4.0**).

## Build & run

```bash
./gradlew buildFatJar
java -jar build/libs/vapi4k-template.jar
```

Or via the Makefile:

```bash
make jar
make run-jar
```
