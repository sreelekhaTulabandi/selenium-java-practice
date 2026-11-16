package listeners;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/** Topic 21: Capture TestNG outcomes in an HTML Extent Spark report. */
public class ExtentReportListener implements ITestListener {
    private static final ThreadLocal<ExtentTest> CURRENT_TEST = new ThreadLocal<>();
    private static final ExtentReports REPORT = createReport();

    private static ExtentReports createReport() {
        try {
            Path reportPath = Paths.get("target", "extent-reports", "ExtentReport.html");
            Files.createDirectories(reportPath.getParent());
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath.toString());
            sparkReporter.config().setDocumentTitle("Selenium Java Practice Results");
            sparkReporter.config().setReportName("TestNG Execution Report");

            ExtentReports extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Framework", "Selenium + TestNG");
            extent.setSystemInfo("Java", System.getProperty("java.version"));
            return extent;
        } catch (Exception exception) {
            throw new IllegalStateException("Unable to initialize Extent report", exception);
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        CURRENT_TEST.set(REPORT.createTest(result.getTestClass().getName()
                + " :: " + result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        currentTest().pass("Test passed");
        CURRENT_TEST.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        currentTest().fail(result.getThrowable());
        CURRENT_TEST.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (result.getThrowable() == null) {
            currentTest().skip("Test skipped");
        } else {
            currentTest().skip(result.getThrowable());
        }
        CURRENT_TEST.remove();
    }

    @Override
    public void onFinish(ITestContext context) {
        REPORT.flush();
    }

    private ExtentTest currentTest() {
        ExtentTest test = CURRENT_TEST.get();
        if (test == null) {
            throw new IllegalStateException("No Extent test is associated with this execution thread");
        }
        return test;
    }
}