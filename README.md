# Selenium Java Practice

A hands-on Selenium WebDriver practice repository using Java, Maven, and TestNG. Each topic contains runnable examples and interview-oriented exercises.

## Tech stack

- Java 17
- Selenium WebDriver 4.25.0
- TestNG 7.10.2
- Maven

## Getting started

1. Install JDK 17 or later, Maven, and Google Chrome.
2. Clone this repository.
3. Run all TestNG tests:

```bash
mvn clean test
```

Selenium Manager (included with Selenium 4.6+) can manage a compatible browser driver automatically in supported environments.

## Project structure

```text
src/test/java/
├── basics/       # Browser launch and navigation
├── locators/     # Selenium By locator strategies
├── waits/        # Explicit waits and synchronization
├── webelements/  # Text, attributes, state, typing and checkboxes
└── dropdowns/    # HTML select dropdowns
```

## Topic tracker

| # | Topic | Concepts covered | Practice class | Status |
|---|---|---|---|---|
| 01 | Browser launch and navigation | get, navigate, title, URL, quit | `basics/BrowserLaunchNavigationTest` | Added |
| 02 | Locator strategies | ID, name, CSS selector, XPath, link text | `locators/LocatorStrategiesTest` | Added |
| 03 | Explicit waits | WebDriverWait, visibility, clickable conditions | `waits/ExplicitWaitTest` | Added |
| 04 | WebElement interactions | displayed, enabled, attributes, text, sendKeys, clear, selected | `webelements/WebElementInteractionsTest` | Added |
| 05 | Dropdown selection | Select, selectByVisibleText, selectByValue, selected option | `dropdowns/DropdownSelectionTest` | Added |
| 06 | Alerts, frames and windows | Alert API, frame switching, window handles | Planned |
| 07 | Actions class | Mouse hover, right-click, drag-and-drop, keyboard | Planned |
| 08 | Tables and lists | Read rows/cells, iterate and assert table data | Planned |
| 09 | Page Object Model | Page classes, locators, reusable actions | Planned |

## Practice exercises

### Topic 02 — Locators
- Add a test using `By.className` and `By.tagName` on a suitable page.
- Write two relative XPath expressions: one using an attribute and one using text.
- Explain when CSS selectors are simpler than XPath, and when XPath axes are useful.

### Topic 03 — Waits
- Replace a fixed `Thread.sleep` (if you use one in your own experiment) with an explicit wait.
- Try `invisibilityOfElementLocated` and `textToBePresentInElementLocated`.
- Explain the difference between implicit and explicit waits. Avoid mixing them without understanding the timeout interaction.

### Topic 04 — WebElements
- Verify the username field's placeholder and input type.
- Use `getCssValue` to inspect a visible element's style.
- Add a test that checks a checkbox only when it is not already selected.

### Topic 05 — Dropdowns
- Try `selectByIndex` and `deselectAll` on a multi-select dropdown.
- Explain why Selenium's `Select` class works only with native HTML `<select>` elements.
- Add a negative assertion for the default selected option.

## Practice website

[The Internet — Herokuapp](https://the-internet.herokuapp.com/)

## Good practices

- Prefer explicit waits for dynamic UI behavior.
- Keep each test independent and close the browser in an `@AfterMethod(alwaysRun = true)`.
- Use meaningful test and commit names.
- Never commit credentials, tokens, or other secrets.

This repository is for hands-on learning and SDET interview preparation.
