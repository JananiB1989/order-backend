import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class StepDefsIntegrationTest extends SpringIntegrationTest {

    @When("^the client creates /purchase-orders")
    public void the_client_issues_POST() throws Throwable {
        executePost();
    }

    @When("^the client calls /purchase-orders")
    public void the_client_issues_GET() throws Throwable {
        executeGet("http://localhost:8080/api/purchase-orders");
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = response.getStatusCode();
        assertThat("status code is incorrect : " + response.getBody(), currentStatusCode.value(), is(statusCode));
    }
}
