package orangeHrm.poms;

import orangeHrm.core.AbstractPOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SystemUsers extends AbstractPOM {

    public SystemUsers(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@id='btnAdd' and @type='button']")
    public WebElement addBtn;

    @FindBy(xpath = "//div[@class='message success fadable']")
    public WebElement successMessage;

}
