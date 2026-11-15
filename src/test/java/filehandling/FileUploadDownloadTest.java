package filehandling;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Map;
import java.util.stream.Stream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/** Topic 14: Upload a classpath fixture and verify a browser-managed download. */
public class FileUploadDownloadTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private Path downloadDirectory;

    @BeforeMethod
    public void setUp() throws IOException {
        downloadDirectory = Files.createTempDirectory("selenium-downloads-");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", Map.of(
                "download.default_directory", downloadDirectory.toAbsolutePath().toString(),
                "download.prompt_for_download", false,
                "safebrowsing.enabled", true));

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void shouldUploadFileFromTestResources() throws IOException, URISyntaxException {
        var fixture = getClass().getClassLoader().getResource("upload-sample.txt");
        Assert.assertNotNull(fixture, "Upload fixture must exist in src/test/resources");
        Path file = Path.of(fixture.toURI()).toAbsolutePath();

        driver.get("https://the-internet.herokuapp.com/upload");
        driver.findElement(By.id("file-upload")).sendKeys(file.toString());
        driver.findElement(By.id("file-submit")).click();

        String uploadedName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("uploaded-files"))).getText();
        Assert.assertEquals(uploadedName, file.getFileName().toString());
    }

    @Test
    public void shouldDownloadFileIntoConfiguredDirectory() throws IOException {
        Path downloadedFile = downloadDirectory.resolve("some-file.txt");
        driver.get("https://the-internet.herokuapp.com/download");
        driver.findElement(By.linkText("some-file.txt")).click();

        boolean completed = wait.until(currentDriver -> {
            try (Stream<Path> files = Files.list(downloadDirectory)) {
                boolean downloadInProgress = files.anyMatch(path ->
                        path.getFileName().toString().endsWith(".crdownload"));
                return Files.exists(downloadedFile)
                        && Files.size(downloadedFile) > 0
                        && !downloadInProgress;
            } catch (IOException exception) {
                return false;
            }
        });

        Assert.assertTrue(completed, "Expected the downloaded file to finish in the configured folder");
        Assert.assertTrue(Files.size(downloadedFile) > 0, "Downloaded file should not be empty");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws IOException {
        if (driver != null) {
            driver.quit();
        }
        if (downloadDirectory != null && Files.exists(downloadDirectory)) {
            try (Stream<Path> files = Files.list(downloadDirectory)) {
                for (Path file : files.toList()) {
                    Files.deleteIfExists(file);
                }
            }
            Files.deleteIfExists(downloadDirectory);
        }
    }
}