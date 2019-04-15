package com.smalaca;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class AppTest {
    @Test
    public void shouldAnswerWithTrue() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sebas_000\\Dropbox\\Training\\Automatyzacja Testów\\webdriver\\chrome\\73\\chromedriver.exe");

        // Initialize browser
        WebDriver driver=new ChromeDriver();

        // Open facebook
        driver.get("http://www.facebook.com");

        // Maximize browser
        driver.manage().window().maximize();

        driver.close();
    }
}
