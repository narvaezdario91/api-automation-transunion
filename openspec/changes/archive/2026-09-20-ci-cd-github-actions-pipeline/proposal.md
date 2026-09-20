# Proposal: CI/CD Pipeline with GitHub Actions and Quality Gates

## Why

To ensure continuous quality, rapid feedback, and high code standards for the TransUnion API Automation Framework, automated execution and static analysis must be integrated into the CI/CD lifecycle. This automates the execution of Screenplay Serenity BDD tests on every pull request and push to `main`, enforces static code analysis (Checkstyle) and SonarQube/SonarCloud Quality Gates, and provides a configurable manual trigger (`workflow_dispatch`) for on-demand test runs across different environments and tags, publishing centralized interactive reports via GitHub Pages.

## What Changes

- Add GitHub Actions CI/CD workflow (`.github/workflows/api-automation-ci.yml`).
- Configure automated execution on `push` and `pull_request` against the `main` branch.
- Integrate static code analysis via **Checkstyle** for code style and clean code conventions.
- Integrate **SonarQube / SonarCloud** analysis and Quality Gate evaluation (blocking PRs on quality gate failures).
- Configure manual execution via `workflow_dispatch` with customizable parameters:
  - `environment`: Target environment (`default`, `dev`, `qa`, `staging`). Default: `default`.
  - `tags`: Cucumber tag expression filter (e.g. `@user`, `@smoke`, `@regression`). Default: empty (runs full suite).
- Set up automated Java 21 JDK (Temurin) and Gradle caching using `gradle/actions/setup-gradle`.
- Generate and archive Serenity BDD HTML reports and JUnit test results as workflow artifacts.
- Deploy Serenity HTML reports automatically to GitHub Pages (`gh-pages` / GitHub Pages deployment) as living documentation.
- Add GitHub Step Summary displaying high-level execution results, Quality Gate status, and direct links to reports.

## Capabilities

### New Capabilities
- `ci-cd-pipeline`: Defines requirements for automated CI/CD test orchestration, static code analysis (Checkstyle), SonarQube Quality Gates, execution parameterization, artifact retention, and report publishing using GitHub Actions.

### Modified Capabilities
<!-- No modifications to existing spec requirements -->

## Impact

- **CI/CD Infrastructure**: Adds `.github/workflows/api-automation-ci.yml`.
- **Build Configuration**: Configures `checkstyle` and `org.sonarqube` plugins in `build.gradle` and rules file `config/checkstyle/checkstyle.xml`.
- **Reporting & Quality**: Automated deployment to GitHub Pages, SonarCloud analysis, and artifact storage.
- **Developers & QA**: Enforces high code quality on PRs and enables on-demand parameterized test execution from GitHub UI.
