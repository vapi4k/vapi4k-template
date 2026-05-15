.PHONY: default help clean build jar run-jar refresh versioncheck build-docker run-docker \
        push-docker release upgrade-wrapper \
        _require-version _require-gradle-version _require-image

VERSION := $(shell sed -n 's/^version=\(.*\)/\1/p' gradle.properties)
GRADLE_VERSION := $(shell sed -n 's/^gradle = "\(.*\)"/\1/p' gradle/libs.versions.toml)

# Override on the command line: `IMAGE_NAME=myorg/vapi4k-template make release`
IMAGE_NAME ?= docker_hub_username/vapi4k-template
PLATFORMS  := linux/amd64,linux/arm64/v8

default: help

help:  ## Show this help (list of targets)
	@awk 'BEGIN {FS = ":.*?## "; printf "Usage: make <target>\n\nTargets:\n"} \
		/^[a-zA-Z0-9_-]+:.*?## / {printf "  \033[36m%-22s\033[0m %s\n", $$1, $$2}' \
		$(MAKEFILE_LIST)

clean: ## Remove build artifacts
	./gradlew clean

build: ## Build (skips tests). Run `make clean build` for a cold rebuild.
	./gradlew build -x test

jar: build ## Build the fat JAR (build/libs/vapi4k-template.jar)
	./gradlew buildFatJar

run-jar: jar ## Build and run the fat JAR
	./gradlew runFatJar

refresh: ## Refresh dependencies and check for updates
	./gradlew --refresh-dependencies dependencyUpdates

versioncheck: ## Check for available dependency updates
	./gradlew dependencyUpdates --no-configuration-cache

build-docker: _require-version _require-image jar ## Build a single-arch Docker image tagged with the project version
	docker build -t $(IMAGE_NAME):$(VERSION) .

run-docker: _require-version _require-image ## Run the Docker image on port 8080
	docker run --rm -p 8080:8080 $(IMAGE_NAME):$(VERSION)

push-docker: _require-version _require-image ## Build and push a multiarch image (linux/amd64, linux/arm64/v8)
	# prepare multiarch
	docker buildx use buildx 2>/dev/null || docker buildx create --use --name=buildx
	docker buildx build --platform $(PLATFORMS) --push -t $(IMAGE_NAME):latest -t $(IMAGE_NAME):$(VERSION) .

release: push-docker ## Build and push the multiarch Docker image (single buildx pass)

# Gradle's documented upgrade procedure: the first run rewrites
# gradle-wrapper.properties using the *old* wrapper jar; the second run
# regenerates the wrapper itself with the new version.
upgrade-wrapper: _require-gradle-version  ## Upgrade the Gradle wrapper to the version pinned in libs.versions.toml
	./gradlew wrapper --gradle-version=$(GRADLE_VERSION) --distribution-type=bin
	./gradlew wrapper --gradle-version=$(GRADLE_VERSION) --distribution-type=bin

_require-version:
	@[ -n "$(VERSION)" ] || { echo "ERROR: Could not determine project version from gradle.properties" >&2; exit 1; }

_require-gradle-version:
	@[ -n "$(GRADLE_VERSION)" ] || { echo "ERROR: Could not determine gradle version from gradle/libs.versions.toml" >&2; exit 1; }

_require-image:
	@[ "$(IMAGE_NAME)" != "docker_hub_username/vapi4k-template" ] || { echo "ERROR: Set IMAGE_NAME first (e.g. IMAGE_NAME=myorg/vapi4k-template make ...)" >&2; exit 1; }
