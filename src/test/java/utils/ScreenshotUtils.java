package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/** Reusable utility that stores browser screenshots under Maven's target directory. */
public final class ScreenshotUtils {
    private static final DateTimeFormatter TIMESTAMP =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS");

    private ScreenshotUtils() { }

    public static Path capture(WebDriver driver, String name) throws IOException {
        String safeName = name.replaceAll("[^a-zA-Z0-9._-]", "_");
        Path directory = Paths.get("target", "screenshots");
        Files.createDirectories(directory);

        byte[] image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Path destination = directory.resolve(safeName + "-"
                + LocalDateTime.now().format(TIMESTAMP) + ".png");
        return Files.write(destination, image);
    }
}