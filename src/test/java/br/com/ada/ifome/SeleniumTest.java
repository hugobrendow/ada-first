package br.com.ada.ifome;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumTest {
    public WebDriver driver;

    @Test
    public void testandoDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/hugobrendow/ada/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();

        driver.get("https://google.com");

        String titulo = driver.getTitle();
        Assertions.assertEquals("Google", titulo);

        driver.manage().timeouts().implicitlyWait(400, TimeUnit.MILLISECONDS);

        WebElement searchBox = driver.findElement(By.name("q"));
        WebElement searchButton = driver.findElement(By.name("btnK"));

        searchBox.sendKeys("Selenium");
        searchButton.click();

        searchBox = driver.findElement(By.name("q"));
        String value = searchBox.getAttribute("value");
        Assertions.assertEquals("Selenium", value);

        driver.quit();
    }
}