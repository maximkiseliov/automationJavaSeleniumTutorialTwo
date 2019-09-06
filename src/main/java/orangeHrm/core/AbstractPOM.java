package orangeHrm.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPOM {
    protected WebDriver driver;
    protected Actions builder;

    public AbstractPOM(WebDriver driver){
        this.driver = driver;
        // Is required in terms to use @FindBy
        PageFactory.initElements(driver, this); //Added to abstract class because will be used in every POM
    }
    public AbstractPOM(WebDriver driver, Actions builder){
        this.driver = driver;
        this.builder = builder;
        // Is required in terms to use @FindBy
        PageFactory.initElements(driver, this); //Added to abstract class because will be used in every POM
    }

}
