package com.maximk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class googleNumberOfWordsTest {
    private static final String chromeDriverPath = "C:\\Users\\maximk\\IdeaProjects\\chromedriver_win32\\chromedriver.exe";
    private WebDriver driver;
    private ChromeOptions options;
    private String incognitoMode = "--incognito";
    private String maximizeWindow = "--start-maximized";
    private static final String googleURL = "https://google.com";
    private WebElement googleSearchField, googleSearchButton;
    private List<WebElement> links;
    private String searchWord;


    @Test
    public void getNumberOfSearchedWords() {
        searchWord = "Pug";

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        options = new ChromeOptions();
        options.addArguments(incognitoMode, maximizeWindow);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // waits max 5 seconds for element to appear
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); // waits max 30 seconds to load the page
        driver.get(googleURL);

        googleSearchField = driver.findElement(By.xpath("//input[@name='q']"));
        googleSearchField.clear();
        googleSearchField.sendKeys(searchWord);
        googleSearchField.submit();

        String urlPageResults = driver.getCurrentUrl();

        links = driver.findElements(By.xpath("//div[h2[not(contains(text(), 'People also ask'))]]//div[@class='r']//a[contains(., '" + searchWord + "') or contains(., '" + searchWord.toLowerCase() + "')]"));
        for (int i = 0; i < links.size(); i++) {
            links.get(i).click();
            System.out.println(driver.getTitle());
        }
//        for (WebElement link : links) {
//            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//            System.out.println("CLICKING -> " + link.getAttribute("href") + " link...");
//            link.click();
//            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//            System.out.println("Current Page Title: " + driver.getTitle());
//            System.out.println("Current URL: " + driver.getCurrentUrl());
//            String[] pageSource = driver.getPageSource().split(" ");
//            int numberOfWords = 0;
//            for (int i = 0; i < pageSource.length; i++) {
//                if (pageSource[i].toLowerCase().matches(".*" + searchWord.toLowerCase() + ".*"));
//                    numberOfWords ++;
//                }
//            System.out.println("Number of " + searchWord + " words at the page " + numberOfWords);
//            driver.get(urlPageResults);
//            System.out.println("\nNEXT LINK");
//            }
    }


    public boolean isRequiredPage(WebDriver d, String expectedTitle, int timeOut) {
        while (timeOut != 0){
            if (d.getTitle().toLowerCase().startsWith(expectedTitle))
                return true;
            timeOut --;
            d.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        }
        return false;
    }
}
