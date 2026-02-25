# JPEGmini Video Compression - UI Automated Test

## About the Project

This project implements a UI automated test using Selenium WebDriver, following the Page Object Model (POM) design
pattern. The test subject is [JPEGmini](https://jpegmini.com/) and its web-based functionality for video compression,
accessible at [https://jpegmini.com/compress-videos](https://jpegmini.com/compress-videos).

### Precondition

A non-optimized video file is required for the test. The video should be as small as possible to minimize the waiting
time for compression during test execution. Test video files are placed under `src/test/resources/`.

### Test Steps

1. Navigate to the JPEGmini homepage ([https://jpegmini.com/](https://jpegmini.com/))
2. Click the **Compress Videos** button
3. Use the drag-and-drop mechanism to upload the selected file into the drop zone and initiate optimization
4. Wait until the file compression completes
5. Assert on the web page that the compressed file size is smaller than the original, and note the reduction
6. Click the **Download the video** button
7. Wait for the video file to download
8. Compare the reference and compressed file sizes to verify that the compressed file is smaller

---

## Tech Stack

| Technology             | Version | Description                                 |
|------------------------|---------|---------------------------------------------|
| **Java**               | 17      | Programming language                        |
| **Maven**              | -       | Build and dependency management             |
| **Selenium WebDriver** | 4.41.0  | Browser automation library                  |
| **Cucumber**           | 7.34.2  | BDD (Behavior Driven Development) framework |
| **TestNG**             | -       | Test runner and assertion framework         |
| **Gherkin**            | -       | Natural language format for feature files   |

---

## Project Structure

```
test-beamr/
├── pom.xml                                         # Maven dependencies and build configuration
├── downloads/                                      # Directory where compressed videos are downloaded
├── src/
│   └── test/
│       ├── configuration.properties                # Runtime settings (browser, timeouts, video file)
│       ├── java/com/poyraz/
│       │   ├── pages/
│       │   │   ├── BasePage.java                   # Abstract base class for all page objects
│       │   │   ├── HomePage.java                   # Home page elements and actions
│       │   │   └── VideoCompressionPage.java       # Video compression page elements and actions
│       │   ├── runners/
│       │   │   └── TestRunner.java                 # Cucumber test runner (TestNG-based)
│       │   ├── step_definitions/
│       │   │   ├── Hooks.java                      # @Before / @After hooks (setup & teardown)
│       │   │   └── VideoCompressionStepDef.java    # Step implementations for the feature file
│       │   └── utilities/
│       │       ├── ConfigurationReader.java        # Reads configuration.properties file
│       │       ├── Driver.java                     # Thread-safe WebDriver management (Singleton)
│       │       └── Helper.java                     # Utility methods (e.g., extracting numbers from text)
│       └── resources/
│           ├── example-video.mp4                   # Test video file 1
│           ├── example-video2.mp4                  # Test video file 2
│           └── features/
│               └── video-compression.feature       # BDD scenario file (Gherkin)
```

---

## Design Patterns

### Page Object Model (POM)

Each page is modeled as a separate class (`HomePage`, `VideoCompressionPage`). Web elements are defined using `@FindBy`
annotations and initialized via `PageFactory.initElements()`. All pages extend the `BasePage` abstract class which
provides common reusable methods.

### Singleton Driver (Thread-Safe)

The `Driver` class uses `InheritableThreadLocal` to provide a separate WebDriver instance per thread, enabling parallel
test execution support.

### BDD (Behavior Driven Development)

Test scenarios are written in Gherkin language. Each step (Given/When/Then) maps to a Java method in the
`step_definitions` package.

---

## Configuration

Runtime settings are managed via `src/test/configuration.properties`:

```properties
browser=chrome                       # Browser choice: chrome or firefox
input-video=example-video2.mp4       # Video file name to upload (located under src/test/resources)
compression-timeout-seconds=180      # Max wait time for compression to finish (seconds)
download-timeout-seconds=120         # Max wait time for download to complete (seconds)
```

---

## Supported Browsers

| Browser     | Details                                                           |
|-------------|-------------------------------------------------------------------|
| **Chrome**  | ChromeOptions configures auto-download directory to `downloads/`  |
| **Firefox** | FirefoxProfile configures auto-download directory to `downloads/` |

> **Note:** The project does not use WebDriverManager. Selenium 4.41.0's built-in **Selenium Manager** handles driver
> binary management automatically.

---

## Hooks (Setup & Teardown)

| Hook      | Purpose                                                             |
|-----------|---------------------------------------------------------------------|
| `@Before` | Cleans up old files in the `downloads/` folder before each scenario |
| `@After`  | Closes the browser after each scenario (`Driver.closeDriver()`)     |

---

## Prerequisites

- **Java 17** or higher installed
- **Maven** installed and added to PATH
- **Chrome** and/or **Firefox** browser installed

---

## Running the Tests

### Using Maven (Recommended)

```bash
mvn clean test
```

### Running with a specific browser

Change the `browser` value in `src/test/configuration.properties`:

```properties
browser=firefox
```

Then run:

```bash
mvn clean test
```

### Using an IDE

Right-click on `TestRunner.java` and select **Run**.

---

## Test Reports

After test execution, the following reports are generated under the `target/` directory:

| Report File                    | Format                    |
|--------------------------------|---------------------------|
| `target/cucumber-reports.html` | HTML                      |
| `target/cucumber-reports.json` | JSON                      |
| `target/cucumber-reports.xml`  | JUnit XML                 |
| `target/surefire-reports/`     | TestNG HTML & XML reports |

---

## Notes

- Compressed videos are downloaded to the `downloads/` folder at the project root.
- The `downloads/` folder is automatically cleaned before each test run.
- Test video files are located under `src/test/resources/`. To test with a different video, place the file in that
  directory and update the `input-video` value in `configuration.properties`.
