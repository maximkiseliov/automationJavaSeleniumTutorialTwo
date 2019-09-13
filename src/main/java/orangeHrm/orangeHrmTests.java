package orangeHrm;

import orangeHrm.core.Helpers;
import orangeHrm.poms.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static systemRelatedPackage.systemRelatedClass.*;

public class orangeHrmTests {
    private static final String chromeDriverPath = CHROME_DRIVE_PATH;
    private static final String ADMIN_USERNAME = "Admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private static final String ORANGE_HRM_URL = "https://opensource-demo.orangehrmlive.com/";
    private static final String INCOGNITO_MODE = "--incognito";
    private static final String MAXIMIZED_WINDOW = "--start-maximized";
    private WebDriver driver;
    private Actions builder;
    private By pageHeaderXpath = By.xpath("//h1");

    @BeforeMethod
    public void setBrowser() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions chromeOptions = new ChromeOptions().addArguments(INCOGNITO_MODE, MAXIMIZED_WINDOW);

        driver = new ChromeDriver(chromeOptions);
        builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.get(ORANGE_HRM_URL);
    }

    @AfterMethod
    public void killBrowser() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    public void loginAsAdmin(){
        Login loginPage = new Login(driver);
        Index indexPage = new Index(driver);

        // Giving  time to load Login form
        Helpers.waitElementToBeVisible(driver, loginPage.loginForm, 10);
        loginPage.checkLoginPanelHeading("LOGIN Panel");

        //Since using @FindBy elements can be accessed directly
        loginPage.userNameInput.sendKeys(ADMIN_USERNAME);
        loginPage.passwordInput.sendKeys(ADMIN_PASSWORD);
        loginPage.loginBtn.click();

        // Giving  time to load Welcome link element
        Helpers.waitElementToBeVisible(driver, indexPage.welcomeLink, 10);

        indexPage.assertWelcomeLinkText(ADMIN_USERNAME);
    }

    public void logout(){
        Index indexPage = new Index(driver);
        Login loginPage = new Login(driver);
        indexPage.welcomeLink.click();

        // Giving  time to load Logout link element
        Helpers.waitElementToBeVisible(driver, indexPage.logoutLink, 10);
        indexPage.logoutLink.click();
        loginPage.assertLoginPageUrlAfterLogout(driver.getCurrentUrl());
    }

    @Test
    public void loginLogoutTest(){
        loginAsAdmin();
        logout();
    }

    @Test(dependsOnMethods = {"loginLogoutTest"})
    public void addNewUserTest() throws InterruptedException {
        loginAsAdmin();

        Menu adminMenuPage = new Menu(driver, builder);
        adminMenuPage.navigateToUsers();

        // Giving  time to load Logout link element
        Helpers.waitElementToBeVisible(driver,  driver.findElement(pageHeaderXpath), 10);
        Helpers.assertHeader(driver.findElement(pageHeaderXpath), "System Users");

        SystemUsers systemUsersPage = new SystemUsers(driver);
        systemUsersPage.addBtn.click();

        // Giving  time to load Logout link element
        Helpers.waitElementToBeVisible(driver,  driver.findElement(pageHeaderXpath), 10);
        Helpers.assertHeader(driver.findElement(pageHeaderXpath), "Add User");

        AddUser addUserPage = new AddUser(driver, builder);
        addUserPage.selectUserRoleFromDropDown("ESS");

        String employeeName = "John Smith";
        addUserPage.findAndSelectFirstFound(employeeName);

        addUserPage.introduceGeneratedUserName(employeeName);

        addUserPage.selectStatusFromDropDown("Enabled");

        addUserPage.introduceGeneratedPasswordAndConfirm();

        addUserPage.saveBtn.click();

        Helpers.waitTextToBe(driver, pageHeaderXpath, "System Users", 10);

        Helpers.assertElementDisplayed(systemUsersPage.successMessage);
    }
}
