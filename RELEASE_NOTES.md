# Release Notes — v1.7.0 (2026-04-08)

The full version history is in [CHANGELOG.md](CHANGELOG.md). This file
covers the most recent release only and is intended for use as the body of a
GitHub release.

## Highlights

- **Vapi4k 1.7.0** and **Ktor 3.4.2** — pulled in the latest upstream features
  and fixes.
- **Vapi4k now resolves from Maven Central.** The Maven group ID changed from
  `com.github.vapi4k.vapi4k` to `com.vapi4k`, and the JitPack repository has
  been removed from the build.
- **`.env`-based configuration.** A new `.env.example` documents the
  environment variables the template reads at startup; copy it to `.env`
  locally. Heroku and Docker continue to inject env vars through their own
  mechanisms.
- **Voice and model configurations refreshed** across all three apps:
  - Inbound call app — OpenAI GPT-4 Turbo + DeepGram Luna
  - Outbound call app (`/callCustomer`) — Anthropic Claude Opus 4 + ElevenLabs Paula
  - Web talk app (`/talkApp`, UI at `GET /talk`) — Groq Llama3 70B + PlayHT Jack
- **JVM 21** is now required (already the default in the Docker image and
  `system.properties`; now also enforced by the Gradle toolchain).
- **README** points at the new documentation site, `https://docs.vapi4k.com/`.

## Upgrade notes

If you have a fork or downstream copy of this template:

1. Replace any `com.github.vapi4k.vapi4k:vapi4k-*` dependency coordinates with
   `com.vapi4k:vapi4k-*`.
2. Remove the JitPack `maven(url = "https://jitpack.io")` repository entry.
3. Make sure your build environment has **JDK 21** available
   (`./gradlew --version` should report Java 21).
4. Copy `.env.example` to `.env` and populate the variables your deployment
   needs.

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
