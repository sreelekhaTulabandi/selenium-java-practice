# Selenium Java Practice

A hands-on Selenium WebDriver practice repository using Java, Maven, and TestNG. The examples are organized by topic and use beginner-friendly practice websites.

## Tech stack

- Java 17
- Selenium WebDriver 4.25.0
- TestNG 7.10.2
- Maven

## Project structure

```text
selenium-java-practice/
├── pom.xml
├── README.md
└── src/
    └── test/
        └── java/
            └── basics/
                └── BrowserLaunchNavigationTest.java
```

## Getting started

1. Install JDK 17 or later.
2. Install Maven.
3. Install Google Chrome.
4. Clone this repository.
5. Run the tests:

```bash
mvn clean test
```

Selenium Manager (included with Selenium 4.6+) can automatically manage the compatible browser driver in supported environments.

## Practice topics

| Topic | Example | Status |
|---|---|---|
| Browser launch and navigation | Open a site, verify title, navigate to another page | Added |
| Locators | ID, name, class name, CSS selector, XPath | Planned |
| Waits | Implicit, explicit, fluent waits | Planned |
| Web elements | Text, attributes, enabled/selected/displayed | Planned |
| Alerts, frames and windows | Browser interactions | Planned |
| Dropdowns and tables | Select options and read tabular data | Planned |
| Actions class | Mouse and keyboard interactions | Planned |
| Page Object Model | Maintainable test structure | Planned |

## Practice website

[The Internet – Herokuapp](https://the-internet.herokuapp.com/)

## Commit convention

Use topic-focused messages, for example: `Add browser launch and navigation tests`.

---
This repository is for learning and interview preparation. Each topic will be added with a small, runnable example.
