package automation.steps;

import automation.pages.KudoPage;
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
    private KudoPage kudoPage;

    @Given("the user opens SofkianOS landing page")
    public void theUserOpensSofkianOSLandingPage() {
        kudoPage.openLandingPage();
    }

    @When("the user navigates to the Kudos form")
    public void theUserNavigatesToTheKudosForm() {
        kudoPage.goToKudosForm();
    }

    @When("the user fills the kudo form with:")
    public void theUserFillsTheKudoFormWith(DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> data = rows.get(0);

        kudoPage.selectFromUser(data.get("from"));
        kudoPage.selectToUser(data.get("to"));
        kudoPage.selectCategory(data.get("category"));
        kudoPage.enterMessage(data.get("message"));
    }

    @When("the user submits the kudo using the slider")
    public void theUserSubmitsTheKudoUsingTheSlider() {
        kudoPage.submitUsingSlider();
    }

    @Then("the user should see a successful kudo submission message")
    public void theUserShouldSeeASuccessfulKudoSubmissionMessage() {

        Assert.assertTrue(
                "Expected success toast to confirm the async request was accepted",
                kudoPage.isSuccessToastVisible()
        );
    }
}