# Design: CI/CD Pipeline with GitHub Actions and Quality Gates

## Context

The TransUnion API Automation Framework uses Java 21, Gradle, Serenity BDD 4.2.16, Screenplay, and Cucumber. Test executions generate rich HTML reports in `target/site/serenity` and JUnit XML reports in `build/test-results/test`. See `proposal.md` for motivation and `specs/ci-cd-pipeline/spec.md` for requirements.

## Goals / Non-Goals

**Goals:**
- Implement a robust, automated GitHub Actions workflow (`.github/workflows/api-automation-ci.yml`).
- Enforce code style and formatting standards via Checkstyle plugin in Gradle.
- Enforce code quality, security scan, and Quality Gates via SonarQube / SonarCloud integration.
- Enable seamless triggering via Git events (`push`, `pull_request` to `main`) and manual dispatch (`workflow_dispatch`).
- Provide parameterized inputs with safe defaults for environment selection and Cucumber tag filtering.
- Cache Gradle dependencies efficiently to reduce build duration.
- Persist Serenity BDD reports and JUnit results as GitHub Actions artifacts.
- Deploy the Serenity HTML report to GitHub Pages for instantaneous web access.
- Generate an informative execution summary in `GITHUB_STEP_SUMMARY`.

**Non-Goals:**
- JaCoCo code coverage measurement for the test automation code (Serenity BDD scenario coverage serves as functional coverage).
- Provisioning dynamic cloud test environments or container clusters.

## Decisions

### 1. Static Analysis & Quality Gate
- **Checkstyle**: Add `checkstyle` Gradle plugin and rules configuration (`config/checkstyle/checkstyle.xml`) based on Google Java Style conventions tailored for Screenplay.
- **SonarQube / SonarCloud**: Add `org.sonarqube` plugin in Gradle. In GitHub Actions, execute `gradle sonar` (or Sonar action) utilizing `SONAR_TOKEN` and `SONAR_HOST_URL` (or SonarCloud) with `qualitygate.wait=true` to enforce the quality gate before or alongside testing.

### 2. Workflow Architecture & Triggers
- **Decision**: Use a single unified workflow file `.github/workflows/api-automation-ci.yml` supporting `push`, `pull_request`, and `workflow_dispatch`.
- **Inputs**:
  - `environment`: Choice (`default`, `dev`, `qa`, `staging`), default `default`.
  - `tags`: String input, default `""` (runs full suite).
- **Alternative considered**: Separate workflow files for PR vs manual dispatch.
  - *Rationale*: A single workflow centralizes build logic, caching, static analysis, and reporting steps without duplication.

### 3. Java 21 & Gradle Setup
- **Decision**: Use `actions/setup-java@v4` with distribution `temurin` (Java 21) combined with `gradle/actions/setup-gradle@v4`.
- **Rationale**: `gradle/actions/setup-gradle` provides automatic caching of Gradle wrappers, downloaded dependencies, and build cache entries, cutting run times substantially.

### 4. Test Execution and Failure Handling
- **Decision**: Execute `./gradlew check test --no-daemon` with dynamic `-Denvironment` and optional `-Dcucumber.filter.tags`.
- **Failure Handling**: Use `if: always()` on reporting and artifact upload steps so that failures still produce full Serenity diagnostics and HTML reports.

### 5. Report Publishing to GitHub Pages
- **Decision**: Use `peaceiris/actions-gh-pages@v4` (or GitHub Actions Pages deployment) publishing `target/site/serenity` to branch `gh-pages` when the job runs on `main`.
- **Rationale**: Keeps a permanent living documentation site hosted on GitHub Pages without requiring external hosting.

## Risks / Trade-offs

- **[Risk] GitHub Pages permissions** → Workflow will declare `permissions: contents: write` or `pages: write` / `id-token: write` to allow publishing without permission errors.
- **[Risk] Missing Sonar secrets in forks/PRs** → Make Sonar step conditional on `SONAR_TOKEN` presence or document repository secret requirements.
- **[Risk] Flaky external mock endpoints (reqres.in)** → Mitigated by clean retry capabilities via `workflow_dispatch` and verbose error logging in Serenity.
