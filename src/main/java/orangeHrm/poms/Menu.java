package orangeHrm.poms;

import orangeHrm.core.AbstractPOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Menu extends AbstractPOM {

    public Menu(WebDriver driver, Actions builder){
        super(driver, builder);
    }

    @FindBy(xpath = "//a[@id='menu_admin_viewAdminModule' and contains(.,'Admin')]")
    public WebElement adminMenuBtn;

    @FindBy(xpath = "//a[@id='menu_admin_UserManagement' and text()='User Management']")
    public WebElement userManagementAdminSubOptionLink;

    @FindBy(xpath = "//a[@id='menu_admin_viewSystemUsers' and text()='Users']")
    public WebElement usersUserManagementSubOptionLink;

    public void navigateToUsers(){
        Action navigateToUsersAction = builder.moveToElement(adminMenuBtn).moveToElement(userManagementAdminSubOptionLink).moveToElement(usersUserManagementSubOptionLink).click().build();
        navigateToUsersAction.perform();
    }
}
