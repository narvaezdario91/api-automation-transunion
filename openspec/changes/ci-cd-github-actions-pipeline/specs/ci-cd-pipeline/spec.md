# Spec Delta: CI/CD Pipeline

## Purpose

Provides continuous integration and delivery automation through GitHub Actions to execute static code analysis, enforce SonarQube Quality Gates, execute API test suites, archive test artifacts, and publish interactive Serenity BDD reports to GitHub Pages.

## ADDED Requirements

### Requirement: Static Code Analysis and Style Enforcement
The CI/CD pipeline SHALL execute Checkstyle static code analysis on every push and pull request, verifying compliance with Java standards, Screenplay conventions, and formatting guidelines.

#### Scenario: Code style check on pull request
- **WHEN** a pull request is created or updated
- **THEN** Checkstyle SHALL analyze all source files and fail the step if style or clean-code violations are detected

### Requirement: SonarQube / SonarCloud Analysis and Quality Gate
The CI/CD pipeline SHALL run SonarQube / SonarCloud static analysis on every pull request and push to `main`, validating security hotspots, code smells, duplication, and enforcing the Quality Gate.

#### Scenario: Quality Gate passes
- **WHEN** SonarQube analyzes the codebase and all Quality Gate criteria are met
- **THEN** the pipeline SHALL allow the build and test pipeline to proceed

#### Scenario: Quality Gate fails
- **WHEN** SonarQube detects high-severity code smells, bugs, or security issues violating the Quality Gate
- **THEN** the pipeline SHALL fail the job and block pull request merging

### Requirement: Automated Trigger on Push and Pull Request
The CI/CD pipeline SHALL automatically trigger test executions on code push and pull requests targeting the `main` branch, running the complete test suite.

#### Scenario: Pull request opened or updated
- **WHEN** a developer opens or updates a Pull Request targeting the `main` branch
- **THEN** the pipeline SHALL automatically trigger the test workflow and execute the full test suite against the default environment

#### Scenario: Code pushed to main
- **WHEN** commits are pushed directly or merged into the `main` branch
- **THEN** the pipeline SHALL automatically trigger the test workflow and execute all tests

### Requirement: Parameterized Manual Workflow Execution
The CI/CD pipeline SHALL support manual execution (`workflow_dispatch`) allowing users to choose the target execution environment and optional Cucumber tag filter expressions.

#### Scenario: Manual execution with default values
- **WHEN** a user triggers the workflow manually without modifying parameters
- **THEN** the pipeline SHALL execute tests against the `default` environment with no tag filter (full suite)

#### Scenario: Manual execution with custom environment and tag filter
- **WHEN** a user triggers the workflow selecting environment `qa` and tags `@user and not @manual`
- **THEN** the pipeline SHALL pass `-Denvironment=qa` and `-Dcucumber.filter.tags="@user and not @manual"` to Gradle during execution

### Requirement: Serenity BDD and JUnit Report Artifact Archiving
The CI/CD pipeline SHALL archive the generated Serenity BDD HTML reports and JUnit XML test results as downloadable workflow artifacts, regardless of whether tests pass or fail.

#### Scenario: Successful and failed test artifact archiving
- **WHEN** the test execution step completes with passing or failing tests
- **THEN** the pipeline SHALL upload the `target/site/serenity` directory and `build/test-results` as workflow artifacts retained for inspection

### Requirement: Automated Living Documentation Publication via GitHub Pages
The CI/CD pipeline SHALL publish the latest Serenity BDD HTML report to GitHub Pages when tests are executed on the `main` branch or when manual dispatch succeeds.

#### Scenario: Deployment to GitHub Pages on main branch run
- **WHEN** the workflow completes execution on the `main` branch
- **THEN** the pipeline SHALL deploy the `target/site/serenity` report to GitHub Pages

### Requirement: CI Job Summary Generation
The CI/CD pipeline SHALL output a structured Markdown summary to `GITHUB_STEP_SUMMARY` detailing execution parameters, Quality Gate status, test outcome status, and artifact download links.

#### Scenario: Summary generation after workflow completion
- **WHEN** the pipeline finishes all build and test steps
- **THEN** a Markdown execution summary SHALL be posted to the workflow step summary
