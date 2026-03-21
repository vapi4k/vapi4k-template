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

Makefile shortcuts: `make build`, `make jar`, `make run-jar`, `make build-docker`, `make run-docker`.

## Architecture

This is a ~220-line Kotlin template with three Vapi4k application types configured in a single Ktor server:

**Application.kt** — Entry point (`fun main()`). Starts an embedded Ktor CIO server on port 8080 (or `PORT` env var). Installs the Vapi4k plugin with:
- **Inbound call app** — responds to incoming calls (OpenAI GPT-4 Turbo + DeepGram voice)
- **Outbound call app** (`/callCustomer`) — makes outbound calls (Anthropic Claude + ElevenLabs voice)
- **Web app** (`/talkApp`) — browser-based talk button (Groq Llama3 + PlayHT voice), serves UI at `GET /talk`

Each app configures a model, voice provider, system message, and the `WeatherLookup` service tool.

**Service Tools** — Defined as classes with `@ToolCall` annotation and `@Param`-annotated parameters. `WeatherLookup` demonstrates this pattern. Tools must implement `onInvoke()` returning a String.

**TalkPage.kt** — kotlinx.html DSL rendering the web talk page with `vapiTalkButton`.

**CallCustomer.kt** — Standalone CLI entry point for triggering outbound calls via `requestOutboundCall()`.

## Key Conventions

- **Kotlin DSL-heavy**: Vapi4k configuration, Ktor routing, and HTML generation all use Kotlin DSL builders
- **Version catalog**: All dependency versions managed in `gradle/libs.versions.toml`
- **JVM 17**: Required by both `build.gradle.kts` (`jvmToolchain`) and `system.properties` (Heroku)
- **Vapi4k jars come from JitPack**: The JitPack repository is required in `build.gradle.kts`
- **Code style**: ktlint / Kotlin official (`gradle.properties`)

## Dependencies

- **Kotlin** 2.3.20, **Ktor** 3.4.1, **Vapi4k** 1.6.1
- Gradle with Kotlin DSL (version in `gradle/wrapper/gradle-wrapper.properties`)
- Two Vapi4k libraries: `vapi4k-core` (voice app framework) and `vapi4k-dbms` (persistence)
- All versions managed in `gradle/libs.versions.toml` — update versions there, not in `build.gradle.kts`

## Deployment

Supports Heroku (Procfile), Docker (Dockerfile with Alpine/JDK17), or standalone JAR. The Dockerfile runs as non-root `vapi_user` with `-Xmx2048m`.
