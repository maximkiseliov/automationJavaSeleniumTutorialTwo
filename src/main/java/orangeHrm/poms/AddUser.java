package orangeHrm.poms;

import orangeHrm.core.AbstractPOM;

import orangeHrm.core.Helpers;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddUser extends AbstractPOM {

    public AddUser(WebDriver driver, Actions builder){
        super(driver, builder);
    }

    @FindBy(xpath = "//label[text()='User Role']/following-sibling::select[@id='systemUser_userType']")
    public WebElement userRoleSelect;

    @FindBy(xpath = "//label[text()='Employee Name']/following-sibling::input[@id='systemUser_employeeName_empName']")
    public WebElement employeeNameInput;

    @FindBy(xpath = "//div[@class='ac_results']")
    public WebElement foundEmployeesElement;

    //TODO: Make it more flexible
    @FindBy(xpath = "//div[@class='ac_results']/ul[li]")
    public WebElement firstFoundEmployee;

    @FindBy(xpath = "//label[text()='Username']/following-sibling::input[@id='systemUser_userName']")
    public WebElement userNameInput;

    @FindBy(xpath = "//label[text()='Status']/following-sibling::select[@id='systemUser_status']")
    public WebElement statusSelect;

    @FindBy(xpath = "//label[text()='Password']/following-sibling::input[@id='systemUser_password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//label[text()='Confirm Password']/following-sibling::input[@id='systemUser_confirmPassword']")
    public WebElement confirmPasswordInput;

    @FindBy(xpath = "//input[@id='btnSave' and @type='button']")
    public WebElement saveBtn;

    public void selectUserRoleFromDropDown(String userRole){
        Select userRoleDropDown = new Select(userRoleSelect);
        userRoleDropDown.selectByVisibleText(userRole);
    }

    public void findAndSelectFirstFound(String employeeName){
        employeeNameInput.sendKeys(employeeName);
        Helpers.waitElementToBeVisible(driver, foundEmployeesElement, 10);
        Action selectEmployeeAction = builder.moveToElement(firstFoundEmployee).click().build();
        selectEmployeeAction.perform();
    }

    public void introduceGeneratedUserName(String employeeName){
        String generatedUserName = generateUserName(employeeName);
        userNameInput.sendKeys(generatedUserName);
    }

    public String generateUserName(String employeeName){
        return employeeName.toLowerCase().replace(" ", ".") + RandomStringUtils.randomNumeric(2,4);
    }

    public void selectStatusFromDropDown(String status){
        Select statusDropDown = new Select(statusSelect);
        statusDropDown.selectByVisibleText(status);
    }

    public String generatePassword(){
        return RandomStringUtils.randomAlphanumeric(8,12);
    }

    public void introduceGeneratedPasswordAndConfirm(){
        String generatedPassword = generatePassword();
        passwordInput.sendKeys(generatedPassword);
        confirmPasswordInput.sendKeys(generatedPassword);
    }
}
