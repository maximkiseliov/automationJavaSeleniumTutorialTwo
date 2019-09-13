package orangeHrm;

import orangeHrm.core.Helpers;
import orangeHrm.poms.Index;
import orangeHrm.poms.Login;
import orangeHrm.poms.Menu;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static systemRelatedPackage.systemRelatedClass.CHROME_DRIVE_PATH;

public class orangeHrmTestsDeprecated {
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
//This is replaced
// new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//form[@id='frmLogin']"))));
//By this
        Helpers.waitElementToBeVisible(driver, loginPage.loginForm, 10);
        loginPage.checkLoginPanelHeading("LOGIN Panel");
//        login.introduceUserName(ADMIN_USERNAME);
//        login.introducePassword(ADMIN_PASSWORD);
//        login.clickLoginButton();
//Since using @FindBy elements can be accessed directly
        loginPage.userNameInput.sendKeys(ADMIN_USERNAME);
        loginPage.passwordInput.sendKeys(ADMIN_PASSWORD);
        loginPage.loginBtn.click();

// Giving  time to load Welcome link element
//This is replaced
//new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(welcomeLinkXpath)));
//By this
        Helpers.waitElementToBeVisible(driver, indexPage.welcomeLink, 10);

//        String welcomeLinkText  = driver.findElement(welcomeLinkXpath).getText();
//        Assert.assertTrue(StringUtils.equals(welcomeLinkText, "Welcome " + ADMIN_USERNAME));
        indexPage.assertWelcomeLinkText(ADMIN_USERNAME);
    }

    public void logout(){
        Index indexPage = new Index(driver);
        Login loginPage = new Login(driver);
//        driver.findElement(welcomeLinkXpath).click();
        indexPage.welcomeLink.click();

// Giving  time to load Logout link element
//This is replaced
// new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Logout']"))));
//By this
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
    public void addNewUserTest(){
        Menu adminMenu = new Menu(driver, builder);
        loginAsAdmin();
//Code commented below was replaced using Menu POM method
//        WebElement adminBtn = driver.findElement(By.xpath("//div[@class='menu']//a[@id='menu_admin_viewAdminModule' and contains(., 'Admin')]"));
//        WebElement userManagementLink = driver.findElement(By.xpath("//a[@id='menu_admin_UserManagement' and text()='User Management']"));
//        WebElement usersLink = driver.findElement(By.xpath("//a[@id='menu_admin_viewSystemUsers' and text()='Users']"));
//        Action navigateToUsersAction = builder.moveToElement(adminBtn).moveToElement(userManagementLink).moveToElement(usersLink).click().build();
//        navigateToUsersAction.perform();
        adminMenu.navigateToUsers();


        // Giving  time to load Logout link element
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(
                driver.findElement(pageHeaderXpath)));

        Assert.assertEquals(driver.findElement(pageHeaderXpath).getText(), "System Users");

        WebElement addBtn = driver.findElement(By.xpath("//input[@id='btnAdd' and @type='button']"));
        addBtn.click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(
                driver.findElement(pageHeaderXpath)));

        Assert.assertEquals(driver.findElement(pageHeaderXpath).getText(), "Add User");

        Select userRoleSelect = new Select(driver.findElement(By.xpath("//label[text()='User Role']/following-sibling::select[@id='systemUser_userType']")));
        userRoleSelect.selectByVisibleText("ESS");

        String employeeName = "Steven Edwards";
        WebElement employeeNameInput = driver.findElement(By.xpath("//label[text()='Employee Name']/following-sibling::input[@id='systemUser_employeeName_empName']"));
        employeeNameInput.sendKeys(employeeName);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//div[@class='ac_results']"))));
        WebElement foundEmployee = driver.findElement(By.xpath("//div[@class='ac_results']/ul"));
        Action selectEmployeeAction = builder.moveToElement(foundEmployee).click().build();
        selectEmployeeAction.perform();

        String userName = employeeName.toLowerCase().replace(" ", ".") + RandomStringUtils.randomNumeric(2,4);
        WebElement userNameInput = driver.findElement(By.xpath("//label[text()='Username']/following-sibling::input[@id='systemUser_userName']"));
        userNameInput.sendKeys(userName);

        Select statusSelect = new Select(driver.findElement(By.xpath("//label[text()='Status']/following-sibling::select[@id='systemUser_status']")));
        statusSelect.selectByVisibleText("Enabled");

        String randomPassword = RandomStringUtils.randomAlphanumeric(8,12);
        WebElement passwordInput = driver.findElement(By.xpath("//label[text()='Password']/following-sibling::input[@id='systemUser_password']"));
        passwordInput.sendKeys(randomPassword);

        WebElement confirmPasswordInput = driver.findElement(By.xpath("//label[text()='Confirm Password']/following-sibling::input[@id='systemUser_confirmPassword']"));
        confirmPasswordInput.sendKeys(randomPassword);

        System.out.printf("Employee Name: '%s'%nUsername: '%s'%nPassword: '%s'", employeeName, userName, randomPassword);

        WebElement saveBtn = driver.findElement(By.xpath("//input[@id='btnSave' and @type='button']"));
        saveBtn.click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.textToBe(
                pageHeaderXpath, "System Users"));

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='message success fadable']")).isDisplayed());
    }
}
