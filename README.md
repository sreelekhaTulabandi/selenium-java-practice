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

The standard suite writes an Extent HTML report to `target/extent-reports/ExtentReport.html` and screenshots to `target/screenshots/` when screenshot tests run. Set `-Dheadless=true` to run Chrome headlessly or `-Dincognito=true` to launch an incognito session. To run only the isolated parallel-browser exercise, use `mvn clean test -Dtestng.suite=src/test/resources/testng-parallel.xml`.

Selenium Manager (included with Selenium 4.6+) can manage a compatible browser driver automatically in supported environments.

## Project structure

```text
src/test/java/
├── basics/       # Browser launch and navigation
├── locators/     # Selenium By locator strategies
├── waits/        # Explicit waits and synchronization
├── webelements/  # Text, attributes, state, typing and checkboxes
├── dropdowns/    # HTML select dropdowns
├── alerts/       # JavaScript alert, confirm and prompt dialogs
├── frames/       # Single and nested frame switching
├── windows/      # Window handles and context switching
├── actions/      # Mouse hover and context-click actions
├── filehandling/ # File upload and download workflows
├── cookies/      # Browser cookie lifecycle
├── shadowdom/    # Selenium 4 Shadow DOM access
├── keyboard/     # Keyboard input and key chords
├── api/          # REST API checks using Rest Assured
├── dataproviders/ # Data-driven TestNG examples
├── parallel/     # Thread-isolated browser tests
└── listeners/    # Extent HTML reporting
```

## Topic tracker

| # | Topic | Concepts covered | Practice class | Status |
|---|---|---|---|---|
| 01 | Browser launch and navigation | get, navigate, title, URL, quit | `basics/BrowserLaunchNavigationTest` | Added |
| 02 | Locator strategies | ID, name, CSS selector, XPath, link text | `locators/LocatorStrategiesTest` | Added |
| 03 | Explicit waits | WebDriverWait, visibility, clickable conditions | `waits/ExplicitWaitTest` | Added |
| 04 | WebElement interactions | displayed, enabled, attributes, text, sendKeys, clear, selected | `webelements/WebElementInteractionsTest` | Added |
| 05 | Dropdown selection | Select, selectByVisibleText, selectByValue, selected option | `dropdowns/DropdownSelectionTest` | Added |
| 06 | JavaScript alerts | Accept, dismiss, prompt text, alert wait | `alerts/JavaScriptAlertsTest` | Added |
| 07 | Frames | Single/nested frames, frame waits, default content | `frames/FramesTest` | Added |
| 08 | Multiple windows | Window handles, switching, closing and returning | `windows/MultipleWindowsTest` | Added |
| 09 | Actions class | Hover and context-click interactions | `actions/ActionsClassTest` | Added |
| 10 | Web tables | Headers, rows, cells, find records by data | `tables/WebTablesTest` | Added |
| 11 | Pagination | Next/previous page, state and result-range assertions | `pagination/PaginationTest` | Added |
| 12 | Page Object Model | Encapsulated locators, page actions and behavior tests | `pages/LoginPage` / `LoginPageTest` | Added |
| 13 | Framework foundation | Shared TestNG lifecycle and configurable driver factory | `base/BaseTest` / `DriverFactory` | Added |
| 14 | File upload and download | Upload fixture, configure download directory, verify completion | `filehandling/FileUploadDownloadTest` | Added |
| 15 | Cookies | Add, inspect, delete and clear cookies | `cookies/CookieManagementTest` | Added |
| 16 | Shadow DOM | Selenium 4 `getShadowRoot()` and nested element lookup | `shadowdom/ShadowDomTest` | Added |
| 17 | Keyboard interactions | Special keys, modifier keys and key chords | `keyboard/KeyboardInteractionsTest` | Added |
| 18 | REST API testing | GET/POST, status, content type and JSON body assertions | `api/PostsApiTest` | Added |
| 19 | Data-driven testing | TestNG `@DataProvider`, multiple valid/invalid scenarios | `dataproviders/LoginDataProviderTest` | Added |
| 20 | Parallel execution | ThreadLocal WebDriver and TestNG method parallelism | `parallel/ParallelBrowserTest` | Added |
| 21 | Test reporting | TestNG listener and Extent Spark HTML report | `listeners/ExtentReportListener` | Added |
| 22 | Browser options and capabilities | Headless/incognito launch flags and browser capabilities | `browseroptions/BrowserOptionsTest` | Added |
| 23 | Screenshot capture | Reusable screenshot utility and PNG artifact validation | `screenshots/ScreenshotCaptureTest` | Added |
| 24 | Browser console logs | Chrome browser log configuration and console assertions | `browserlogs/BrowserConsoleLogsTest` | Added |
| 25 | Web Storage | JavaScript access to localStorage and sessionStorage | `webstorage/WebStorageTest` | Added |
| 26 | Page-load strategies | NORMAL, EAGER and NONE readiness behavior | `browserconfig/PageLoadStrategyTest` | Added |
| 27 | Responsive viewports | Mobile and desktop viewport sizing | `viewports/ResponsiveViewportTest` | Added |
| 28 | Browser navigation history | Back, forward, refresh and explicit synchronization | `navigation/BrowserNavigationHistoryTest` | Added |
| 29 | Navigation timing | W3C Performance API navigation metrics | `performance/NavigationTimingTest` | Added |
| 30 | Relative locators | Locate elements by spatial relationship | `relativelocators/RelativeLocatorsTest` | Added |
| 31 | FluentWait | Custom polling, timeout and ignored exceptions | `fluentwait/FluentWaitTest` | Added |
| 32 | Stale element recovery | Wait for stale references and reacquire DOM nodes | `staleelements/StaleElementRecoveryTest` | Added |
| 33 | Browser permissions | Configure and verify Chrome notification permission | `permissions/NotificationPermissionTest` | Added |
| 34 | Element screenshots | Capture and validate a single WebElement PNG | `elementscreenshot/ElementScreenshotTest` | Added |
| 35 | DOM attributes and properties | Compare initial HTML attributes with live DOM properties | `domproperties/DomAttributePropertyTest` | Added |
| 36 | Accessibility metadata | Inspect computed accessible names and ARIA roles | `accessibility/AccessibleNameRoleTest` | Added |
| 37 | HTML5 form validation | Required fields, email validity and native validation messages | `htmlvalidation/Html5FormValidationTest` | Added |

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

### Topic 06 — JavaScript alerts
- Accept an alert, dismiss a confirmation, and enter text in a prompt.
- Explain why a JavaScript alert blocks normal DOM interaction until it is handled.
- Add an assertion for the result message after each alert action.

### Topic 07 — Frames
- Switch into an iframe by locator, interact with its content, then return using `defaultContent()`.
- Practice `parentFrame()` and explain the difference from `defaultContent()`.
- Add a test for switching to a frame by index or name.

### Topic 08 — Multiple windows
- Capture the parent handle before clicking a link that opens a new window.
- Switch to the child using the handle set, assert its title/content, close it, and return to the parent.
- Explain why window-handle iteration should not assume a particular ordering.

### Topic 09 — Actions class
- Hover over the second and third profile cards and verify their captions.
- Try `doubleClick()`, `clickAndHold()`, and `dragAndDrop()` on suitable pages.
- Explain the difference between a WebElement's `click()` and an Actions sequence.

## Practice website

[The Internet — Herokuapp](https://the-internet.herokuapp.com/)

## Good practices

- Prefer explicit waits for dynamic UI behavior.
- Keep each test independent and close the browser in an `@AfterMethod(alwaysRun = true)`.
- Use meaningful test and commit names.
- Never commit credentials, tokens, or other secrets.

This repository is for hands-on learning and SDET interview preparation.


### Topic 10 — Web tables
- Read table headers and assert the expected column count.
- Iterate rows and locate a record using a business key such as last name.
- Extract a cell from the matching row and validate its value.
- Extend the example to sort, filter, or validate totals.

### Topic 11 — Pagination
- Verify the initial result range, move to the next page, and assert the range changes.
- Navigate back and verify the original page state.
- Add coverage for the last-page/disabled-next condition and changing page sizes.
- Prefer waiting for a meaningful page-state change over fixed sleeps.

### Topic 12 — Page Object Model
- Keep page locators and UI actions inside the page class; keep business assertions in the test.
- Add methods for logout and validation messages.
- Discuss when a page method should return the same page object versus a different page object.
- Keep page objects focused; avoid putting test assertions inside them.

### Topic 13 — Shared framework setup
- Extend `BaseTest` for consistent browser setup and teardown.
- Run with the default Chrome browser: `mvn clean test`.
- Try `mvn clean test -Dbrowser=chrome` and explain how a future Firefox implementation could be added to `DriverFactory`.
- Discuss parallel execution and why a plain shared static WebDriver would be unsafe across TestNG threads.


### Topic 14 — File upload and download
- Upload the bundled `upload-sample.txt` fixture through the file input.
- Configure a dedicated download directory through Chrome preferences.
- Wait until the expected file exists, has content, and its `.crdownload` temporary file disappears.
- Extend the download test to validate the downloaded file's contents and handle a missing link gracefully.

### Topic 15 — Cookies
- Add a cookie with a name, value, path and HttpOnly flag.
- Read it back with `getCookieNamed` and assert its attributes.
- Delete one cookie, then compare `deleteCookieNamed` with `deleteAllCookies`.
- Explain why cookies are scoped to the current domain and why secure cookies require HTTPS.

### Topic 16 — Shadow DOM
- Locate the custom-element host, call Selenium 4's `getShadowRoot()`, then locate an element inside the returned SearchContext.
- Explain why ordinary document-level CSS/XPath searches do not pierce a shadow boundary.
- Extend the test to handle a missing host and nested shadow roots.

### Topic 17 — Keyboard interactions
- Use `Keys.ENTER` and inspect the page's reported key.
- Use Control+A to select an input value before replacing it.
- Practise Shift, Tab, Escape and multi-key sequences with `Actions`.
- Explain the difference between `WebElement.sendKeys` and `Actions` keyboard sequences.


### Topic 18 — REST API testing with Rest Assured
- Send a GET request and validate status code, content type, and selected JSON fields.
- Send a JSON POST request and assert the returned payload.
- Extend coverage with query parameters, path parameters, headers, negative status codes, and schema validation.
- Explain the difference between API-level checks and end-to-end UI tests.

### Topic 19 — TestNG DataProviders
- Supply multiple username/password/expected-message rows to one test method.
- Add boundary cases and explain how TestNG reports each data set.
- Move larger test data into JSON or CSV only when the data volume justifies it.
- Keep assertions in the test and reusable UI operations in the page object.

### Topic 20 — Parallel execution
- Run the isolated suite with `mvn clean test -Dtestng.suite=src/test/resources/testng-parallel.xml`.
- Observe that each test thread gets its own browser through `ThreadLocal<WebDriver>`.
- Explain why a single static WebDriver causes cross-test interference.
- Add a test that deliberately fails and confirm teardown still closes its browser.

### Topic 21 — Extent reporting
- Run the standard suite and open `target/extent-reports/ExtentReport.html`.
- Identify passed, failed and skipped test entries.
- Add exception details and a screenshot attachment for UI failures as an extension exercise.
- Explain why the listener uses a thread-local current test when methods execute concurrently.


### Topic 22 — Browser options and capabilities
- Run `mvn clean test -Dtestng.suite=src/test/resources/testng.xml -Dheadless=true` to exercise headless Chrome.
- Try `-Dincognito=true` and compare behavior with a normal browser session.
- Inspect `getCapabilities()` and explain the difference between browser name, browser version, and platform name.
- Add a configurable argument for disabling browser notifications and test the default behavior.

### Topic 23 — Screenshot capture
- Run `ScreenshotCaptureTest` and inspect the PNG under `target/screenshots/`.
- Capture screenshots for a second page and use a meaningful test-specific filename.
- Extend the listener to attach screenshots to failed Extent report entries.
- Explain when to capture a full-page screenshot versus a viewport screenshot.

### Topic 24 — Browser console logs
- Run the console log test and identify the custom marker in the browser log entries.
- Add a test that intentionally emits a JavaScript warning and assert its message or level.
- Explain why browser logging preferences must be enabled before creating the Chrome session.
- Discuss why console logs are diagnostic evidence, not a replacement for functional assertions.

### Topic 25 — Local and session storage
- Store a key in localStorage and verify it remains after a page refresh.
- Store a key in sessionStorage and clear it after reading.
- Compare storage lifetime and scope with cookies.
- Add cleanup in a `finally` block so practice data does not leak between tests.


### Topic 26 — Page-load strategies
- Compare Selenium's `NORMAL`, `EAGER` and `NONE` strategies and when each readiness condition is appropriate.
- Run `mvn clean test -Dtest=PageLoadStrategyTest -DpageLoadStrategy=eager` or use `normal` / `none`.
- Inspect the negotiated `pageLoadStrategy` capability and explain why strategy changes do not replace explicit waits.

### Topic 27 — Responsive viewport checks
- Resize the browser to mobile and desktop dimensions and inspect `window.innerWidth`.
- Explain the difference between outer window dimensions and the page's CSS viewport.
- Extend the test with breakpoint-specific assertions for a responsive application.

### Topic 28 — Browser navigation history
- Navigate from the home page to a second page, then use back and forward.
- Synchronize on URL and page-specific elements rather than fixed sleeps.
- Add refresh coverage and explain how redirects affect navigation history.

### Topic 29 — Navigation timing and performance
- Read the W3C Navigation Timing entry with `performance.getEntriesByType('navigation')`.
- Inspect response end, DOMContentLoaded, load event and navigation duration.
- Explain why timings are environment-dependent diagnostics and should not be asserted against brittle fixed thresholds.


### Topic 30 — Selenium 4 relative locators
- Locate input elements below their labels and a submit button below the password field.
- Practise `above`, `below`, `toLeftOf`, `toRightOf` and `near` with stable reference elements.
- Explain why relative locators use rendered element geometry and can be sensitive to responsive layout changes.

### Topic 31 — FluentWait
- Configure an explicit timeout and polling interval, and ignore only expected transient exceptions.
- Wait for delayed content on the dynamic loading example and assert its final text.
- Compare FluentWait with WebDriverWait and explain why broad exception suppression hides real failures.

### Topic 32 — Stale element recovery
- Keep a WebElement reference, trigger a DOM replacement, and wait for the old reference to become stale.
- Re-locate the element using its stable locator instead of repeatedly using the stale reference.
- Explain the difference between StaleElementReferenceException and NoSuchElementException.

### Topic 33 — Browser notification permissions
- Configure Chrome's profile preference to block web notifications before creating the browser session.
- Read `Notification.permission` from a page and assert that it is denied.
- Try granting permission in an isolated profile and explain why browser permissions are configuration, not application UI state.


### Topic 34 — Element screenshots
- Capture a specific form element using `WebElement.getScreenshotAs(OutputType.BYTES)`.
- Save the bytes under `target/element-screenshots` and validate the PNG output.
- Compare element screenshots with viewport screenshots and discuss when each is useful.

### Topic 35 — DOM attributes versus properties
- Read an input's initial `value` content attribute and live `value` DOM property.
- Type into the field and compare `getDomAttribute` with `getDomProperty`.
- Explain why an input's current value can change without changing its original HTML attribute.

### Topic 36 — Accessible names and ARIA roles
- Inspect `getAccessibleName()` and `getAriaRole()` for labelled form controls and buttons.
- Compare the computed accessible name with visible text and associated labels.
- Discuss how accessible metadata supports robust automation and accessibility-oriented checks, without replacing a full accessibility audit.

### Topic 37 — HTML5 form validation
- Create a required email field and check validity using `checkValidity()`.
- Exercise empty, malformed and valid values and inspect the browser's native `validationMessage`.
- Explain the difference between browser constraint validation and application-specific server-side validation.
