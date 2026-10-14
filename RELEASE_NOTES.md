# Release Notes — v1.2.2 (2026-10-13)

The full version history is in [CHANGELOG.md](CHANGELOG.md). This file
covers the most recent release only and is intended for use as the body of a
GitHub release.

This is a maintenance release: dependency and tooling updates, Dependabot,
a hardened CI workflow, and a one-line source change to follow a Vapi4k
deprecation.

## Highlights

- **Vapi4k 1.8.2**, **Ktor 3.6.0**, and **Kotlin 2.4.21** — the latest
  upstream features and fixes.
- **Gradle 9.8.1 wrapper.**
- **Dependabot.** `.github/dependabot.yml` checks weekly and waits 7 days
  after a release before proposing it. GitHub Actions updates arrive in one
  grouped PR. Gradle dependencies, plugins, and the wrapper get one PR for all
  minor and patch updates, and a PR of their own for each major update.
- **Newer, pinned CI actions.** `.github/workflows/ci.yml` moves to checkout
  **v7**, setup-java **v6**, and gradle/actions **v6**, each pinned to a commit
  SHA. The checkout no longer keeps the GitHub token in the workspace.
- **Shorter dependency reports.** `make versions` now lists only the
  dependencies with newer versions, plus any that could not be checked. The new
  `make versions-all` target prints the plugin's full report, up-to-date
  dependencies included. The Gradle versions plugin moves to **0.66.0** to
  support this.
- **`parseJson()` in `CallCustomer.kt`.** Vapi4k 1.8.2 moves to common-utils
  5.x, which deprecates `String.toJsonElement()` in favor of `parseJson()`.
- **Line-ending normalization.** `.gitattributes` now normalizes all text files,
  keeping LF for `gradlew` and CRLF for `*.bat`.

## Upgrade notes

To bring a fork or downstream copy of this template up to this release:

1. Upgrade the Gradle wrapper to **9.8.1** (`make upgrade-wrapper` or
   `./gradlew wrapper --gradle-version 9.8.1`).
2. Pull the dependency versions from `gradle/libs.versions.toml` (Vapi4k
   **1.8.2**, Ktor **3.6.0**, Kotlin **2.4.21**, versions plugin **0.66.0**).
3. Replace calls to `String.toJsonElement()` with `String.parseJson()`
   (`import com.pambrose.common.json.parseJson`). The old function still
   compiles but is deprecated.
4. To keep `make versions` short, copy the `outputFormatter` block from
   `configureVersions()` in `build.gradle.kts`. Without it, both `versions`
   targets print the full report.

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
