package orangeHrm.poms;

import orangeHrm.core.AbstractPOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class Index extends AbstractPOM {

    public Index(WebDriver driver){
      super(driver);
    }

    @FindBy(xpath = "//a[@id='welcome']")
    public WebElement welcomeLink;

    @FindBy(xpath = "//a[text()='Logout']")
    public WebElement logoutLink;

    public void checkingWelcomeLinkText(String loggedPersonName){
        Assert.assertEquals(welcomeLink.getText(), "Welcome " + loggedPersonName);
    }
}
