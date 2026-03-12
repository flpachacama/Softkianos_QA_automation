package automation.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
	features = "src/test/resources/features/send_kudo.feature",
	glue = "automation.steps",
	snippets = CucumberOptions.SnippetType.CAMELCASE
)

public class KudoRunner {
}
