package orangeHrm.poms;

import orangeHrm.core.AbstractPOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class Login extends AbstractPOM {

    public Login(WebDriver driver) {
        super(driver);
    }
    String orangeHrmUrlAfterLogout = "https://opensource-demo.orangehrmlive.com/index.php/auth/login";

//Keep as Strings
//    String loginPanelHeadingXpath = "//form[@id='frmLogin']/div[@id='logInPanelHeading']";
//    String userNameInputXpath = "//form[div[@id='logInPanelHeading']]/div[@id='divUsername']/input[@id='txtUsername' and @type='text']";
//    String passwordInputXpath = "//form[div[@id='logInPanelHeading']]/div[@id='divPassword']/input[@id='txtPassword' and @type='password']";
//    String loginBtnXpath = "//form[div[@id='logInPanelHeading']]//input[@id='btnLogin' and @type='submit' and @value='LOGIN']";

//Keep as Locators
//    By loginPanelHeadingLoc = By.xpath("//form[@id='frmLogin']/div[@id='logInPanelHeading']");
//    By userNameInputLoc = By.xpath("//form[div[@id='logInPanelHeading']]/div[@id='divUsername']/input[@id='txtUsername' and @type='text']");
//    By passwordInputLoc = By.xpath("//form[div[@id='logInPanelHeading']]/div[@id='divPassword']/input[@id='txtPassword' and @type='password']");
//    By loginBtnLoc = By.xpath("//form[div[@id='logInPanelHeading']]//input[@id='btnLogin' and @type='submit' and @value='LOGIN']");

//Keep using @FindBy
    @FindBy(xpath = "//form[@id='frmLogin']")
    public WebElement loginForm;

    @FindBy(xpath = "//form[@id='frmLogin']/div[@id='logInPanelHeading']")
    public WebElement loginPanelHeading;

    @FindBy(xpath = "//form[div[@id='logInPanelHeading']]/div[@id='divUsername']/input[@id='txtUsername' and @type='text']")
    public WebElement userNameInput;

    @FindBy(xpath = "//form[div[@id='logInPanelHeading']]/div[@id='divPassword']/input[@id='txtPassword' and @type='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//form[div[@id='logInPanelHeading']]//input[@id='btnLogin' and @type='submit' and @value='LOGIN']")
    public WebElement loginBtn;

    public void checkLoginPageUrlAfterLogout(String currentLink){
        Assert.assertEquals(currentLink, orangeHrmUrlAfterLogout);
    }
    public void checkLoginPanelHeading(String value){
        Assert.assertEquals(loginPanelHeading.getText(), value);
    }

    public void introduceUserName(String username){
        userNameInput.sendKeys(username);
    }

    public void introducePassword(String password){
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton(){
        loginBtn.click();
    }
}
