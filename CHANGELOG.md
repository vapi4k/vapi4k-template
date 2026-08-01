# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).
As of **1.2.0**, the template is versioned independently of the bundled
[Vapi4k](https://github.com/vapi4k/vapi4k) library, and each release records
the Vapi4k version it ships. Earlier entries (**1.7.0** and below) mirror the
bundled Vapi4k library version.

## [1.2.1] - 2026-08-01

Maintenance release. Ships **Vapi4k 1.8.1**, **Ktor 3.5.1**, **Kotlin 2.4.10**,
and the **Gradle 9.6.1** wrapper.

### Changed
- Vapi4k upgraded to **1.8.1**; Kotlin to **2.4.10**.
- Gradle versions plugin upgraded to **0.57.0**, and its plugin id updated from
  `com.github.ben-manes.versions` to the relocated
  `io.github.ben-manes.versions`.
- Documentation refreshed to match the shipped dependency versions
  (`README.md`, `CLAUDE.md`, `llms.txt`).

## [1.2.0][1.2.0-2026] - 2026-07-10

First template release versioned independently of the bundled Vapi4k library.
Ships **Vapi4k 1.8.0**, **Ktor 3.5.1**, **Kotlin 2.4.0**, and the **Gradle
9.6.1** wrapper.

### Added
- GitHub Actions CI workflow (`.github/workflows/ci.yml`) that builds the
  project on every push and pull request.
- `CHANGELOG.md` and `RELEASE_NOTES.md` documenting the project's history.
- Makefile `help` target, release/Docker/image guards, and Docker
  build/run/push shortcuts.

### Changed
- Vapi4k upgraded to **1.8.0**; Ktor to **3.5.1**; Kotlin to **2.4.0**.
- Gradle wrapper upgraded to **9.6.1**.
- `build.gradle.kts` refactored into `configureKotlin`, `configureKtor`, and
  `configureVersions` helper functions.
- Dependency-update filtering now uses a delimiter-aware regex that rejects a
  pre-release candidate only when the current version is stable, so
  intentionally-tracked pre-release lines still surface newer pre-releases.
- Makefile modernized: parsed project/dependency versions, target guards,
  Docker hygiene, and the `versioncheck` target renamed to `versions`.
- Version catalog key renamed from `gradle` to `gradle-wrapper`.

## [1.7.0] - 2026-04-08

### Added
- `.env` / `.env.example` for environment-variable-based configuration.

### Changed
- Vapi4k upgraded to **1.7.0**; Ktor upgraded to **3.4.2**.
- Vapi4k Maven coordinates moved from `com.github.vapi4k.vapi4k` to `com.vapi4k`
  and resolved from Maven Central.
- Voice and model configurations refreshed (Anthropic Claude Opus 4, DeepGram
  Luna, ElevenLabs Paula, Groq Llama3 70B, PlayHT Jack).
- README points at the new Vapi4k documentation URL (`docs.vapi4k.com`).
- JVM toolchain pinned to **21** in `build.gradle.kts` and `system.properties`.

### Removed
- JitPack repository declaration (no longer needed now that Vapi4k publishes to
  Maven Central).
- Unused JitPack configuration files.

## [1.6.1] - 2026-03-20

### Changed
- Vapi4k upgraded to **1.6.1**; Kotlin to **2.3.20**; Ktor to **3.4.1**.
- Migrated build to **JVM 21**.
- Fixed the Heroku `stage` task so deployments build the fat JAR cleanly.
- README Kotlin badge updated to 2.3.20.

## [1.4.0] - 2026-02-18

### Changed
- Vapi4k upgraded to **1.4.0**; Kotlin to **2.3.10**; Ktor to **3.4.0**.
- Refreshed model types and miscellaneous build configuration.
- Gradle wrapper upgraded to **9.2.1**; opted in to resolving `-SNAPSHOT`
  Vapi4k versions during the 1.3.3-SNAPSHOT bridge.

## [1.3.2] - 2025-08-18

### Changed
- Vapi4k upgraded to **1.3.2** (patch-only release).

## [1.3.1] - 2025-08-18

### Changed
- Vapi4k upgraded to **1.3.1**; Kotlin to **2.2.10**; Ktor to **3.2.3**.

## [1.3.0] - 2025-06-26

### Added
- Centralized dependency management via `gradle/libs.versions.toml` (version
  catalog). All versions now live in one place.

### Changed
- Vapi4k upgraded to **1.3.0**; Kotlin to **2.2.0**; Ktor to **3.2.0**.

## [1.2.4] - 2025-02-16

### Changed
- Vapi4k upgraded to **1.2.4**; Ktor to **3.1.0**.
- Gradle property names normalized from `kotlin_version` / `vapi4k_version`
  to `kotlinVersion` / `vapi4kVersion`.

## [1.2.3] - 2025-02-08

### Changed
- Vapi4k upgraded to **1.2.3**; Kotlin to **2.1.10**; Ktor to **3.0.3**.

## [1.2.0] - 2024-11-27

### Changed
- Vapi4k upgraded to **1.2.0**; Kotlin to **2.1.0**.

## [1.1.1] - 2024-11-01

### Changed
- Vapi4k upgraded to **1.1.1**; Kotlin to **2.0.21**.

## [1.1.0] - 2024-10-30

### Added
- Initial public template with three Vapi4k application types in a single Ktor
  server: inbound call app, outbound call app (`/callCustomer`), and web talk
  app (`/talkApp`, served at `GET /talk`).
- `WeatherLookup` service-tool example using `@ToolCall` / `@Param`.
- `TalkPage.kt` (kotlinx.html) and `CallCustomer.kt` CLI entry point.
- Docker (`Dockerfile`, Alpine + JDK 21, non-root `vapi_user`), Heroku
  (`Procfile`, `system.properties`), and `Makefile` shortcuts.

### Changed
- Vapi4k pinned to **1.1.0**; Ktor upgraded to **3.0.1**; Kotlin **2.0.20**.

[1.2.1]: https://github.com/vapi4k/vapi4k-template/compare/e25364c...HEAD
[1.2.0-2026]: https://github.com/vapi4k/vapi4k-template/compare/8b86ea6...e25364c
[1.7.0]: https://github.com/vapi4k/vapi4k-template/compare/c16fbbf...8b86ea6
[1.6.1]: https://github.com/vapi4k/vapi4k-template/compare/49dfb5f...1a3ec08
[1.4.0]: https://github.com/vapi4k/vapi4k-template/compare/0b846c5...cadd1d0
[1.3.2]: https://github.com/vapi4k/vapi4k-template/compare/bcba402...0b846c5
[1.3.1]: https://github.com/vapi4k/vapi4k-template/compare/21e1e1d...bcba402
[1.3.0]: https://github.com/vapi4k/vapi4k-template/compare/c69a000...7b02edb
[1.2.4]: https://github.com/vapi4k/vapi4k-template/compare/1ae92b3...c69a000
[1.2.3]: https://github.com/vapi4k/vapi4k-template/compare/4ba4c0e...1ae92b3
[1.2.0]: https://github.com/vapi4k/vapi4k-template/compare/54fecd5...4ba4c0e
[1.1.1]: https://github.com/vapi4k/vapi4k-template/compare/bc65da0...54fecd5
[1.1.0]: https://github.com/vapi4k/vapi4k-template/compare/c964400...bc65da0
