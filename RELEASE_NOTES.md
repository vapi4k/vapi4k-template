# Release Notes — v1.2.1 (2026-08-01)

The full version history is in [CHANGELOG.md](CHANGELOG.md). This file
covers the most recent release only and is intended for use as the body of a
GitHub release.

This is a maintenance release: dependency updates and documentation refreshes,
with no source or API changes.

## Highlights

- **Vapi4k 1.8.1** and **Kotlin 2.4.10** — upstream patch updates. Ktor stays
  at **3.5.1** and the Gradle wrapper at **9.6.1**.
- **Gradle versions plugin 0.57.0.** The plugin has been relocated, so its id
  changed from `com.github.ben-manes.versions` to
  `io.github.ben-manes.versions`. `./gradlew dependencyUpdates` and
  `make versions` behave the same.
- **Documentation refresh.** `README.md`, `CLAUDE.md`, and `llms.txt` now state
  the shipped dependency versions.

## Upgrade notes

No source changes are required for a fork or downstream copy of this template.
To match this release:

1. Pull the dependency versions from `gradle/libs.versions.toml` (Vapi4k
   **1.8.1**, Ktor **3.5.1**, Kotlin **2.4.10**).
2. If you copied the versions-plugin declaration into your own build, update the
   plugin id to `io.github.ben-manes.versions` — the old
   `com.github.ben-manes.versions` id is no longer the published coordinate for
   current releases.

The Gradle wrapper is unchanged from 1.2.0 (**9.6.1**).

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
