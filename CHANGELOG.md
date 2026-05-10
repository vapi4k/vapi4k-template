# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project tracks the bundled [Vapi4k](https://github.com/vapi4k/vapi4k)
library version.

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

[1.7.0]: https://github.com/vapi4k/vapi4k-template/compare/c16fbbf...HEAD
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
