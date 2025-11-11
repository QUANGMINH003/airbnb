package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReportManager {
    private static ExtentReports extentReports;

    public static synchronized ExtentReports getReport() {
        if(extentReports == null) {
            extentReports = createInstance();
        }
        return extentReports;
    }

    private static ExtentReports createInstance() {
        try {
            Path reportDir = Paths.get("reports");
            if(!Files.exists(reportDir)) {
                Files.createDirectories(reportDir);
            }
        } catch (Exception ignored) {}
        String reportPath = "reports/ExtentReport.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

        spark.config().setDocumentTitle("CapStone Testing 06");
        spark.config().setReportName("Testing Demo 4 With Playwright");
        spark.config().setTheme(Theme.DARK);

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        return extent;
    }
}
