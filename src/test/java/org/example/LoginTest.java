package org.example;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class LoginTest {
    WebDriver driver;   // 👈 class-level driver
    ExtentReports extent;
    ExtentSparkReporter spark;
    ExtentTest test;

    @Test(priority = 1)
    public void launchDriver() {
        // Initialize Extent Reports
        spark = new ExtentSparkReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        test = extent.createTest("Launch Driver Test");
        test.log(Status.INFO, "Initializing Extent Reports");

        System.setProperty("webdriver.chrome.driver",   "C:\\Users\\aadit\\OneDrive\\Documents\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        test.log(Status.INFO, "Setting ChromeDriver property");

        driver = new ChromeDriver();   // 👈 initialize here
        test.log(Status.INFO, "ChromeDriver initialized");

        driver.manage().window().maximize();
        test.log(Status.INFO, "Browser maximized");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        test.log(Status.INFO, "Implicit wait set");

        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        test.log(Status.INFO, "Navigated to URL");

        System.out.println("Test 1 title is: " + driver.getTitle());
        test.log(Status.INFO, "Page title: " + driver.getTitle());

        test.log(Status.PASS, "Launch Driver Test passed");
    }

    @Test (priority = 2)
    public void screenshot() throws IOException {
        test = extent.createTest("Screenshot Test");
        test.log(Status.INFO, "Starting screenshot test");

        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        test.log(Status.INFO, "Screenshot captured");

        FileUtils.copyFile(src, new File("./screenshot1.png"));
        test.log(Status.INFO, "Screenshot saved to file");

        test.addScreenCaptureFromPath("./screenshot1.png");
        test.log(Status.INFO, "Screenshot attached to report");

        System.out.println("Test 2: Screenshot taken");
        test.log(Status.PASS, "Screenshot Test passed");
    }


    @Test (priority = 3)
    public void navigate() {
        test = extent.createTest("Navigate Test");
        test.log(Status.INFO, "Starting navigate test");

        driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
        test.log(Status.INFO, "Navigated to new URL");

        System.out.println("Test 3 title is: " + driver.getTitle());
        test.log(Status.INFO, "Page title: " + driver.getTitle());

        test.log(Status.PASS, "Navigate Test passed");
    }

    @Test (priority = 4)
    public void quitDriver() {
        test = extent.createTest("Quit Driver Test");
        test.log(Status.INFO, "Starting quit driver test");

        driver.quit();
        test.log(Status.INFO, "Driver closed");

        System.out.println("Test 4: Driver Closed");
        test.log(Status.PASS, "Quit Driver Test passed");

        // Flush the report
        extent.flush();
        test.log(Status.INFO, "Extent Reports flushed");
    }
}
