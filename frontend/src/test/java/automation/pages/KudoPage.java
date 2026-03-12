package automation.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class KudoPage extends PageObject {

    @FindBy(xpath = "//button[normalize-space()='Acceder']")
    private WebElementFacade accessButton;

    @FindBy(xpath = "//h2[contains(.,'Reconoce a un')]")
    private WebElementFacade kudosTitle;

    @FindBy(name = "from")
    private WebElementFacade fromSelect;

    @FindBy(name = "to")
    private WebElementFacade toSelect;

    @FindBy(name = "category")
    private WebElementFacade categorySelect;

    @FindBy(name = "message")
    private WebElementFacade messageTextarea;

    @FindBy(css = "div[class*='cursor-pointer'][class*='rounded-full']")
    private WebElementFacade sliderTrack;

    @FindBy(css = "div[class*='w-16'][class*='bg-brand']")
    private WebElementFacade sliderHandle;

    @FindBy(xpath = "//*[contains(text(),'Kudo enviado')]")
    private WebElementFacade successToast;

    public void openLandingPage() {
        openUrl("http://localhost:5173");
    }

    public void goToKudosForm() {
        accessButton.waitUntilClickable().click();
        kudosTitle.waitUntilVisible();
    }

    public void selectFromUser(String fromUser) {
        fromSelect.waitUntilClickable().selectByVisibleText(fromUser);
    }

    public void selectToUser(String toUser) {
        toSelect.waitUntilClickable().selectByVisibleText(toUser);
    }

    public void selectCategory(String category) {
        categorySelect.waitUntilClickable().selectByVisibleText(category);
    }

    public void enterMessage(String message) {
        messageTextarea.waitUntilVisible().clear();
        messageTextarea.type(message);
    }

    public void submitUsingSlider() {
        sliderTrack.waitUntilVisible();
        sliderHandle.waitUntilVisible();

        int dragDistance = Math.max(sliderTrack.getSize().getWidth() - sliderHandle.getSize().getWidth() - 2, 200);
        new Actions(getDriver())
            .clickAndHold(sliderHandle)
            .moveByOffset(dragDistance, 0)
            .pause(Duration.ofMillis(150))
            .release()
            .perform();

        // Fallback for environments where drag actions are flaky.
        if (!waitForSuccessToast()) {
            sliderHandle.sendKeys(Keys.ARROW_RIGHT);
        }
    }

    public boolean isSuccessToastVisible() {
        return waitForSuccessToast() && successToast.isCurrentlyVisible();
    }

    private boolean waitForSuccessToast() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(8));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Kudo enviado')]")));
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }
}
