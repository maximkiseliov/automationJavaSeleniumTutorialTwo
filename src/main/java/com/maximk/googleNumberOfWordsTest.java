package com.maximk;

import bsh.StringUtil;
import org.apache.commons.lang3.StringUtils;
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
    private static final String googleURL = "https://google.com";
    private static final String incognitoMode = "--incognito";
    private static final String maximizeWindow = "--start-maximized";
    private WebDriver driver;
    private ChromeOptions options;
    private WebElement googleSearchField;
    private List<WebElement> links, linksInnerList;
    private String listOfLinksXpath, searchWord;


    @Test
    public void getNumberOfSearchedWords() {
        searchWord = "Cheetah";
        listOfLinksXpath = "//div[h2[not(contains(text(), 'People also ask'))]]//div[@class='r']//a[contains(., '" + searchWord + "') or contains(., '" + searchWord.toLowerCase() + "')]";

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        options = new ChromeOptions();
        options.addArguments(incognitoMode, maximizeWindow);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // waits max 5 seconds for element to appear
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); // waits max 30 seconds to load the page
        driver.get(googleURL);

        googleSearchField = driver.findElement(By.xpath("//input[@name='q']"));
        googleSearchField.clear();
        googleSearchField.sendKeys(searchWord);
        googleSearchField.submit();


        links = driver.findElements(By.xpath(listOfLinksXpath));
        int numberOfLinks = links.size();
        for (int i = 0; i < links.size(); i++) {
            linksInnerList = driver.findElements(By.xpath(listOfLinksXpath));
            linksInnerList.get(i).click();
            System.out.println("Page title: " + driver.getTitle());
            System.out.println("Page URL: " + driver.getCurrentUrl());
            System.out.println(searchWord + " word occurrences on the page: " + StringUtils.countMatches(driver.getPageSource().toLowerCase(), searchWord.toLowerCase()));
            driver.navigate().back();
        }
//        This is my method but I got StaleElementReferenceException
//        for (WebElement link : links) {
//            System.out.println("CLICKING -> " + link.getAttribute("href") + " link...");
//            link.click();
//            System.out.println("Current Page Title: " + driver.getTitle());
//            System.out.println("Current URL: " + driver.getCurrentUrl());

//        Below is replicating StringUtils.countMatches()
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

// This one is not working properly
    public boolean isRequiredPage(WebDriver d, String expectedTitle, int timeOut) { // checks if we are at desired page
        while (timeOut != 0){
            if (d.getTitle().toLowerCase().startsWith(expectedTitle))
                return true;
            timeOut --;
            d.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        }
        return false;
    }
}
