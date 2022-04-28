package com.service.gherkin;

import com.service.model.Response;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class GetEmployeeStepDef {

    @Autowired
    private TestRestTemplate template;

    private ResponseEntity<Response> response; // output


    @When("^the client calls /health$")
    public void the_client_issues_GET_health() throws Throwable {
        String url = "https://localhost:8081";
        response = template.getForEntity(url +"/v1/employee/all", Response.class);
    }

    @Then("^the client receives response status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        HttpStatus currentStatusCode = response.getStatusCode();
        assertTrue(currentStatusCode.is2xxSuccessful());
    }
}
