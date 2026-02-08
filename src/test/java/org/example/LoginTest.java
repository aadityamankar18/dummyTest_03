package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    WebDriver driver;   // 👈 class-level driver
    @Test(priority = 1)
    public void launchDriver() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\aadit\\OneDrive\\Documents\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();   // 👈 initialize here
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        System.out.println("Test 1 title is: " + driver.getTitle());

    }

    @Test (priority = 2)
    public void navigate() throws InterruptedException {
        driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
        System.out.println("Test 2 title is: " + driver.getTitle());

    }

    @Test (priority = 3)
    public void quitDriver() throws InterruptedException {

        Thread.sleep(2000);
        driver.quit();
        System.out.println("Test 2 title is: Driver Closed");
    }
}
