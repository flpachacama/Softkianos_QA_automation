package automation.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class KudoHistoryPage extends PageObject {

    @FindBy(xpath = "//button[normalize-space()='Historial']")
    private WebElementFacade historyButton;

    @FindBy(xpath = "//h2[contains(.,'Historial de')]")
    private WebElementFacade historyTitle;

    @FindBy(xpath = "//button[normalize-space()='Actualizar']")
    private WebElementFacade refreshButton;

    @FindBy(xpath = "//section[contains(@class,'grid')]")
    private WebElementFacade kudoListContainer;

    @FindBy(xpath = "//article")
    private List<WebElementFacade> kudoItems;

    public void openHistory() {
        historyButton.waitUntilClickable().click();
        historyTitle.waitUntilVisible();
    }

    public List<String> getKudosList() {
        kudoListContainer.waitUntilVisible();
        waitForCondition().until(driver -> !kudoItems.isEmpty() || refreshButton.isCurrentlyVisible());
        return kudoItems.stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());
    }

    public boolean isKudoVisible(String message) {
        final int maxAttempts = 8;

        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            boolean found = getKudosList().stream().anyMatch(item -> item.contains(message));
            if (found) {
                return true;
            }

            if (refreshButton.isCurrentlyVisible()) {
                refreshButton.click();
            }

            waitABit(1500);
        }

        return false;
    }
}
