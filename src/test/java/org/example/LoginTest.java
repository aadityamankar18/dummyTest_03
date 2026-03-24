package org.example;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
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
    public void launchDriver() throws InterruptedException {
        // Initialize Extent Reports
        spark = new ExtentSparkReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        test = extent.createTest("Launch Driver Test");
        test.log(Status.INFO, "Initializing Extent Reports");

        System.setProperty("webdriver.chrome.driver", "C:\\JAR Files\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        test.log(Status.INFO, "Setting ChromeDriver property");

        driver = new ChromeDriver();   // 👈 initialize here
        test.log(Status.INFO, "ChromeDriver initialized");

        driver.manage().window().maximize();
        test.log(Status.INFO, "Browser maximized");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        test.log(Status.INFO, "Implicit wait set");

    }

    @Test(priority = 2)
    public void openFirstLink() throws InterruptedException {

        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        test.log(Status.INFO, "Navigated to URL");

        System.out.println("Test 1 title is: " + driver.getTitle());
        test.log(Status.INFO, "Page title: " + driver.getTitle());

//----------------------------------------------------------
        driver.navigate().to("https://www.youtube.com/");
        test.log(Status.INFO, "Navigated to YouTube URL");

        Thread.sleep(6000);
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getWindowHandles());

        test.log(Status.INFO, "Retrieved page title and URL");

        Thread.sleep(1000);
        driver.navigate().back();
        test.log(Status.INFO, "Navigated back to previous page");

        driver.navigate().forward();
        test.log(Status.INFO, "Navigated forward to YouTube page");

        driver.navigate().refresh();
        test.log(Status.INFO, "Page refreshed");

        driver.switchTo().newWindow(WindowType.TAB);  // 👈 open new tab
        test.log(Status.INFO, "Switched to new tab");

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        test.log(Status.INFO, "Navigated to Automation Practice URL in new tab");

        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parentID = it.next();

        WebElement radio1 = driver.findElement(By.name("radioButton"));
        radio1.click();
        boolean selected = radio1.isSelected();
        System.out.println("Is the radio button selected? " + selected);
        test.log(Status.INFO, "Located radio button element and clicked it");

        driver.switchTo().window(parentID);
        test.log(Status.INFO, "Switched to parent window");

//----------------------------------------------------------
        test.log(Status.PASS, "Launch Driver Test passed");
    }

    @Test (priority = 3)
    public void actionClass() throws InterruptedException {
        driver.get("https://www.amazon.com/");

        Actions action = new Actions(driver);
        WebElement signIn = driver.findElement(By.xpath("//div[@id='nav-link-accountList']//a[contains(@class,'nav-progressive-attribute')]"));

        action.click(signIn).build().perform();
        test.log(Status.INFO, "Clicked on Sign In using Actions class");

        Thread.sleep(2000);
        System.out.println("Test 1 title is: " + driver.getTitle());

        driver.navigate().back();
        Thread.sleep(2000);
        test.log(Status.INFO, "Navigated back to previous page");

        WebElement selectLanguage = driver.findElement(By.xpath("//span[contains(@class,'icp-nav-link-inner')]"));

        action.moveToElement(selectLanguage).build().perform();
        test.log(Status.INFO, "Moved to Sign In element using Actions class");

        Thread.sleep(2000);

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        action.moveToElement(searchBox).click();
        action.keyDown(Keys.SHIFT).sendKeys("laptop").keyUp(Keys.SHIFT).sendKeys(Keys.ENTER).build().perform();
        test.log(Status.INFO, "Entered text in search box using Actions class");

        System.out.println("Test 2 title is: " + driver.getTitle());
    }

    @Test (priority = 4)
    public void selectClass() throws InterruptedException {


        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.id("dropdown-class-example"))));

        Select select = new Select(driver.findElement(By.id("dropdown-class-example")));
        select.selectByIndex(0);
        test.log(Status.INFO, "Selected option 1 by index using Select class");

        select.selectByIndex(1);
        test.log(Status.INFO, "Selected option 2 by index using Select class");

        select.selectByIndex(3);
        test.log(Status.INFO, "Selected option 3 by index using Select class");

        Thread.sleep(2000);

        select.selectByValue("option2");
        test.log(Status.INFO, "Selected option 2 by value using Select class");
        select.selectByValue("option1");
        test.log(Status.INFO, "Selected option 3 by value using Select class");

        Thread.sleep(2000);

        System.out.println("Test 1 title is: " + driver.getCurrentUrl());

    }

    @Test (priority = 5)
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


    @Test (priority = 6)
    public void navigate() {
        test = extent.createTest("Navigate Test");
        test.log(Status.INFO, "Starting navigate test");

        driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
        test.log(Status.INFO, "Navigated to new URL");

        System.out.println("Test 3 title is: " + driver.getTitle());
        test.log(Status.INFO, "Page title: " + driver.getTitle());

        test.log(Status.PASS, "Navigate Test passed");
    }

    @Test (priority = 7)
    public void dummy() {
        test = extent.createTest("dummy Test");
        test.log(Status.INFO, "Starting dummy test");

        driver.navigate().to("https://www.google.com/");
        test.log(Status.INFO, "Navigated to Google URL");

        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        test.log(Status.INFO, "Page refreshed and implicit wait set");

        Actions act = new Actions(driver);
        WebElement searchBox = driver.findElement(By.xpath("//textarea[@id='APjFqb']"));
        searchBox.sendKeys(Keys.SHIFT, "aaditya");
        searchBox.sendKeys(Keys.ENTER);
        test.log(Status.INFO, "Entered text in search box");

        System.out.println("The google page ID is: " + driver.getWindowHandles());
        test.log(Status.INFO, "Google Page ID copied.");

        System.out.println("Test 4 title is: " + driver.getTitle());
        test.log(Status.INFO, "Page title: " + driver.getTitle());

        test.log(Status.PASS, "Dummy Test passed");
    }

    @Test (priority = 8)
    public void quitDriver() {
        test = extent.createTest("Quit Driver Test");
        test.log(Status.INFO, "Starting quit driver test");

        driver.quit();
        test.log(Status.INFO, "Driver closed");

        System.out.println("Test 5: Driver Closed");
        test.log(Status.PASS, "Quit Driver Test passed");

        // Flush the report
        extent.flush();
        test.log(Status.INFO, "Extent Reports flushed");
    }
}
