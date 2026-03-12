package automation.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("/")
public class LandingPage extends PageObject {

    @FindBy(xpath = "//button[normalize-space()='Acceder']")
    private WebElementFacade accessButton;

    public void openLandingPage() {
        open();
    }

    public void goToKudosForm() {
        accessButton.waitUntilClickable().click();
    }
}
