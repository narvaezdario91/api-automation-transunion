# Tasks: CI/CD Pipeline with GitHub Actions and Quality Gates

## 1. Static Analysis & Quality Gate Configuration

- [x] 1.1 Configure Checkstyle plugin and custom rules file `config/checkstyle/checkstyle.xml` in `build.gradle` and verify with `./gradlew checkstyleMain checkstyleTest`.
- [x] 1.2 Configure SonarQube plugin (`org.sonarqube`) and properties in `build.gradle`.

## 2. Workflow Definition & Triggers

- [x] 2.1 Create `.github/workflows/api-automation-ci.yml` with triggers for `push`, `pull_request` against `main`, and `workflow_dispatch` with configurable inputs (`environment` choice and `tags` string).
- [x] 2.2 Configure Java 21 JDK setup (`actions/setup-java@v4` with Temurin) and Gradle caching (`gradle/actions/setup-gradle@v4`) within the workflow.

## 3. Static Analysis & Test Execution Jobs

- [x] 3.1 Configure Checkstyle analysis and SonarQube / SonarCloud Quality Gate scanner step in the GitHub Actions workflow.
- [x] 3.2 Configure test execution step with dynamic `-Denvironment` and `-Dcucumber.filter.tags` parameters, ensuring Serenity report aggregation runs.

## 4. Artifacts & Reporting

- [x] 4.1 Configure upload of Serenity HTML report (`target/site/serenity`) and JUnit test results (`build/test-results`) using `actions/upload-artifact@v4` with `if: always()`.
- [x] 4.2 Configure deployment of Serenity HTML reports to GitHub Pages (`gh-pages`) when executed on the `main` branch or manual runs.
- [x] 4.3 Configure `GITHUB_STEP_SUMMARY` markdown output summarizing run parameters, Quality Gate status, test outcomes, and report links.

## 5. Documentation & Verification

- [x] 5.1 Update project `README.md` with CI/CD pipeline documentation, Quality Gate details, manual dispatch instructions, and GitHub Pages report link.
- [x] 5.2 Validate workflow YAML structure and verify local `./gradlew check test` execution.
