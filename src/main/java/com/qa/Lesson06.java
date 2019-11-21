package com.qa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Lesson06 {

    // Instance of WebDriver
    private WebDriver driver;
    private final String XPATH_CROSSED = "//span[contains(text(),'Samsung Galaxy S6')]/ancestor::" +
            "div[@data-el='table_col']//p/span[contains(@data-mce-style,'line-through')]";
    private final String XPATH = "//span[contains(text(),'Samsung Galaxy S6')]/ancestor::" +
            "div[@class='ptsTableElementContent ptsElArea']//p/span[contains(@data-mce-style,'21pt;')]";
    /**
     * Set up method
     */
    @Before
    public void setUp() {

        // If you want to disable infobars please use this code
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        // Initialize path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        // Initialize instance of ChromeDriver and add options
        driver = new ChromeDriver(options);
        // Set 10 second for implicitly wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Maximize window
        driver.manage().window().maximize();
    }

    /**
     * Open test page, search and quit
     */
    @Test
    public void testPriceComparision() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // Test for   https://supsystic.com/example/comparison-example/

        driver.get("https://supsystic.com/example/comparison-example/");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(XPATH))));
        String price1 = driver.findElement(By.xpath(XPATH)).
                getText().replaceAll("^.*?(-?\\d+(\\.\\d+)?).*$", "$1");
        String price2 = driver.findElement(By.xpath(XPATH_CROSSED)).
                getText().replaceAll("^.*?(-?\\d+(\\.\\d+)?).*$", "$1");

        System.out.println("Samsung Galaxy S6  " + "$" + price1);
        System.out.println("Samsung Galaxy S6 crossed " + "$" + price2);
        float result1 = Float.parseFloat(price1);
        float result2 = Float.parseFloat(price2);
        System.out.println("Difference = " + (result2 - result1));
        // Test for   https://unicode-table.com/ru/

        driver.get("https://unicode-table.com/ru/");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li[@data-code='81']"))));
        System.out.println(driver.findElement(By.xpath("//li[@data-code='81']")).getText());
        System.out.println(driver.findElement(By.xpath("//li[@data-code='36']")).getText());
        System.out.println(driver.findElement(By.xpath("//li[@data-code='65']")).getText());

    }

    /**
     * After method, quit driver
     */
    @After
    public void tearDown() {
        driver.quit();
    }
}