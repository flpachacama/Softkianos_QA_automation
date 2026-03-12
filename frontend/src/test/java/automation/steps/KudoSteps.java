package automation.steps;

import automation.pages.KudoFormPage;
import automation.pages.LandingPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class KudoSteps {

    @Steps
    private LandingPage landingPage;

    @Steps
    private KudoFormPage kudoFormPage;

    @Given("the user opens SofkianOS landing page")
    public void theUserOpensSofkianOSLandingPage() {
        landingPage.openLandingPage();
    }

    @When("the user navigates to the Kudos form")
    public void theUserNavigatesToTheKudosForm() {
        landingPage.goToKudosForm();
        kudoFormPage.waitUntilFormIsVisible();
    }

    @When("the user fills the kudo form with:")
    public void theUserFillsTheKudoFormWith(DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> data = rows.get(0);

        kudoFormPage.selectFromUser(data.get("from"));
        kudoFormPage.selectToUser(data.get("to"));
        kudoFormPage.selectCategory(data.get("category"));
        kudoFormPage.enterMessage(data.get("message"));
    }

    @When("the user submits the kudo using the slider")
    public void theUserSubmitsTheKudoUsingTheSlider() {
        kudoFormPage.submitUsingSlider();
    }

    @Then("the user should see a successful kudo submission message")
    public void theUserShouldSeeASuccessfulKudoSubmissionMessage() {

        Assert.assertTrue(
                "Expected success toast to confirm the async request was accepted",
                kudoFormPage.isSuccessToastVisible()
        );
    }
}