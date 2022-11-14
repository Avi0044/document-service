package in.nbt.documentservice.restapi.stepdef;


import in.nbt.documentservice.restapi.helper.JmeterDSLMethods;
import in.nbt.documentservice.restapi.runner.CucumberRunner;
import in.nbt.documentservice.restapi.helper.ApiEndPoints;
import in.nbt.documentservice.restapi.helper.Configvariable;
import in.nbt.documentservice.restapi.model.ApiFunctions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class APISteps extends CucumberRunner {

    private ApiFunctions api = new ApiFunctions();
    private Configvariable var = new Configvariable();
    private ApiEndPoints ep = new ApiEndPoints();
    private JmeterDSLMethods perf = new JmeterDSLMethods();

    @Given("I make a template list request with given data")
    public void iMakeATemplateListRequestWithGivenData(DataTable table) {
        Map<String, String> hm = table.asMap(String.class, String.class);
        Map<String,String> param = new HashMap<>();
        Set<String> keys = hm.keySet();
        for (String key : keys) {
            param.put(key,hm.get(key));
        }
        api.getTemplateListCheck(param);
    }


    @Then("I verify the succesfull response of request")
    public void iVerifyTheSuccesfullResponseOfRequest(DataTable table) {
        Map<String, String> hm = table.asMap(String.class, String.class);
        Set<String> keys = hm.keySet();
        for (String key : keys) {
            var.setStringVariable(hm.get(key), key);
        }
        api.verifyGetTemplateResponse();
    }

    @Given("I make a request to register template with given data")
    public void iMakeARequestToRegisterTemplateWithGivenData(DataTable table) {
        Map<String, String> hm = table.asMap(String.class, String.class);
        Set<String> keys = hm.keySet();
        for (String key : keys) {
            var.setStringVariable(hm.get(key), key);
        }
        api.verifyRegisteredTemplateRequest();

    }

    @Then("I verify the succesfull response of registered template")
    public void iVerifyTheSuccesfullResponseOfRegisteredTemplate(DataTable table) {
        Map<String, String> hm = table.asMap(String.class, String.class);
        Set<String> keys = hm.keySet();
        for (String key : keys) {
            var.setStringVariable(hm.get(key), key);
        }
        api.verifyRegisteredResponse();
    }

    @Given("I make a patch request with given data")
    public void iMakeAPatchRequestWithGivenData(DataTable table) {
        Map<String, String> hm = table.asMap(String.class, String.class);
        Set<String> keys = hm.keySet();
        for (String key : keys) {
            var.setStringVariable(hm.get(key), key);
        }
        api.verifyUpdateTemplateRequest();

    }

    @Then("I verify the succesfull response of update template")
    public void iVerifyTheSuccesfullResponseOfUpdateTemplate(DataTable table) {
        Map<String, String> hm = table.asMap(String.class, String.class);
        Set<String> keys = hm.keySet();
        for (String key : keys) {
            var.setStringVariable(hm.get(key), key);
        }
        api.verifyupdateResponse();
    }

    @Given("I make a request for the Document with given data")
    public void iMakeARequestForTheDocumentWithGivenData(DataTable table) {
        Map<String, String> hm = table.asMap(String.class, String.class);
        Map<String,String> param = new HashMap<>();
        Set<String> keys = hm.keySet();
        for (String key : keys) {
            param.put(key,hm.get(key));
        }
        api.verifyDocumentTemplateRequest(param);
    }

    @Then("I verify the successful response of document template")
    public void iVerifyTheSuccessfulResponseOfDocumentTemplate(DataTable table) {
        Map<String, String> hm = table.asMap(String.class, String.class);
        Set<String> keys = hm.keySet();
        for (String key : keys) {
            var.setStringVariable(hm.get(key), key);
        }
        api.verifyDocumentTemplateResponse();
    }

    @And("Validate the performance of {string} service for Template")
    public void validateThePerformanceOfServiceForTemplate(String endPoint, DataTable table) {
        Map<String, String> hm = table.asMap(String.class, String.class);
        Set<String> keys = hm.keySet();
        for (String key : keys) {
            var.setStringVariable(hm.get(key), key);
        }
        if (endPoint.equals("RegisterTemplate")) {
            perf.testPerformanceRegisterTemplate(endPoint);
        } else if (endPoint.equals("TEMPLATE_LIST")) {
            perf.testPerformanceTemplate(endPoint);
        } else if (endPoint.equals("DocumentCreation")){
            perf.testPerformanceTemplateDocument(endPoint);
        }
    }

    @Then("I verify the error response of registered template")
    public void iVerifyTheErrorResponseOfRegisteredTemplate(DataTable table) {
        Map<String, String> hm = table.asMap(String.class, String.class);
        Set<String> keys = hm.keySet();
        for (String key : keys) {
            var.setStringVariable(hm.get(key), key);
        }
        api.verifyRegisteredErrorResponse();
    }

    @Then("I verify the error response of request")
    public void iVerifyTheErrorResponseOfRequest(DataTable table) {
        Map<String, String> hm = table.asMap(String.class, String.class);
        Set<String> keys = hm.keySet();
        for (String key : keys) {
            var.setStringVariable(hm.get(key), key);
        }
        api.verifyGetTemplateListErrorResponse();

    }

    @Then("I verify the error response of document template")
    public void iVerifyTheErrorResponseOfDocumentTemplate(DataTable table) {
        Map<String, String> hm = table.asMap(String.class, String.class);
        Set<String> keys = hm.keySet();
        for (String key : keys) {
            var.setStringVariable(hm.get(key), key);
        }
        api.verifyDocumentErrorResponse();
    }

    @Given("I make a request to register template without name with given data")
    public void iMakeARequestToRegisterTemplateWithoutNameWithGivenData(DataTable table) {
        Map<String, String> hm = table.asMap(String.class, String.class);
        Set<String> keys = hm.keySet();
        for (String key : keys) {
            var.setStringVariable(hm.get(key), key);
        }
        api.verifyRegisteredTemplateWithoutnameRequest();

    }

    @Given("I make a request to register template without template with given data")
    public void iMakeARequestToRegisterTemplateWithoutTemplateWithGivenData(DataTable table) {
        Map<String, String> hm = table.asMap(String.class, String.class);
        Set<String> keys = hm.keySet();
        for (String key : keys) {
            var.setStringVariable(hm.get(key), key);
        }
        api.verifyRegisteredTemplateWithoutTemplateRequest();
    }
}









