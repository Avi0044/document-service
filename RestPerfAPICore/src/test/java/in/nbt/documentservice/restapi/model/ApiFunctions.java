package in.nbt.documentservice.restapi.model;

import in.nbt.documentservice.restapi.helper.*;
import in.nbt.documentservice.restapi.helper.*;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApiFunctions {

    // TODO: Spring injection broken
    @Autowired
    private JsonReader jsonReader = new JsonReader();
    private ApiEndPoints ep = new ApiEndPoints();
    private RestAPIMethods rest = new RestAPIMethods();
    private Configvariable var = new Configvariable();
    private Map<String, String> endPointParams = new HashMap<>();
    String successResponse;


    public void getTemplateListCheck(Map<String, String> params) {
        Map<String, String> endpointsparams = new HashMap<>();
        endpointsparams = params;
        Response response = rest.sendRequest("GETH", ConfigDetails.Host_Prod, ep.TEMPLATE_LIST, "",headersXKey(), endpointsparams);
        TestUtils.log().info("**RESPONSE>> " + response.asString());
        if (response.getStatusCode() == 200) {
            String appID = response.path("templates[0].appId").toString();
            var.setStringVariable(appID, "appID");
            String templateStatus = response.path("templates[0].status").toString();
            var.setStringVariable(templateStatus, "templateStatus");
            String templateType = response.path("templates[0].type").toString();
            var.setStringVariable(templateType, "templateType");
        }
        else if (response.getStatusCode() == 404){
            String status = response.path("status").toString();
            var.setStringVariable(status, "status");
            String  errormessage= response.path("message").toString();
            var.setStringVariable(errormessage, "errormessage");
        }
        TestUtils.log().info("GetTemplate- request is made successfully");
    }

    private Map<String, String> headersXKey() {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Api-Key", ConfigDetails.XAPPKey);
        return headers;
    }
    public void verifyGetTemplateResponse() {
        Response response = rest.getResponse();
        response.then().statusCode(Integer.parseInt(var.getStringVar("response_status_code")));
        Assert.assertEquals(var.getStringVar("appID"), var.getStringVar("appId"));
        Assert.assertEquals(var.getStringVar("templateStatus"), var.getStringVar("status"));
        Assert.assertEquals(var.getStringVar("templateType"), var.getStringVar("type"));
        Assert.assertTrue((response.asString()).contains(var.getStringVar("templateID")));
        TestUtils.log().info("Get Template request is successfully verified");
    }

    public void verifyGetTemplateListErrorResponse() {
        Response response = rest.getResponse();
        response.then().statusCode(Integer.parseInt(var.getStringVar("response_status_code")));
        Assert.assertEquals(var.getStringVar("status"), var.getStringVar("Status"));
        Assert.assertEquals(var.getStringVar("errormessage"), var.getStringVar("Message"));
        TestUtils.log().info("Get Template error request is successfully verified");
    }
    public void verifyRegisteredTemplateRequest() {
        Response response = rest.sendRequest("POSTH", ConfigDetails.Host_Prod, ep.TEMPLATE_LIST, "/testData/registerTemplate.json", headersXKey());
        TestUtils.log().info("**RESPONSE>> " + response.asString());
            String appID = response.path("appId").toString();
            var.setStringVariable(appID, "appID");
            String templateID = response.path("id").toString();
            var.setStringVariable(templateID, "templateID");
        }

    public void verifyRegisteredResponse() {
        Response response = rest.getResponse();
        response.then().statusCode(Integer.parseInt(var.getStringVar("response_status_code")));
        Assert.assertEquals(var.getStringVar("appID"), var.getStringVar("appId"));
        TestUtils.log().info("**REGISTERED Template ID>> " + var.getStringVar("templateID"));
        TestUtils.log().info("Template registered response is successfully verified");

    }
    public void verifyRegisteredErrorResponse() {
        Response response = rest.getResponse();
        response.then().statusCode(Integer.parseInt(var.getStringVar("response_status_code")));
        Assert.assertEquals(var.getStringVar("status"), var.getStringVar("Status"));
        Assert.assertEquals(var.getStringVar("Message"), var.getStringVar("errorMessage"));
        TestUtils.log().info("Template registered error response is successfully verified");

    }

    public void verifyUpdateTemplateRequest() {
        Response response = rest.sendRequest("PATCHHead", ConfigDetails.Host_Prod, ep.TEMPLATE_LIST, "/testData/updateTemplate.json", headersXKey());
        TestUtils.log().info("**RESPONSE>> " + response.asString());
        String appID = response.path("appId").toString();
        var.setStringVariable(appID, "appID");
        String templateStatus = response.path("status").toString();
        var.setStringVariable(templateStatus, "templateStatus");
        String templatedid = response.path("id").toString();
        var.setStringVariable(templatedid, "templatedid");

    }

    public void verifyupdateResponse() {
        Response response = rest.getResponse();
        response.then().statusCode(Integer.parseInt(var.getStringVar("response_status_code")));
        Assert.assertEquals(var.getStringVar("appID"), var.getStringVar("appId"));
        Assert.assertEquals(var.getStringVar("templateStatus"), var.getStringVar("status"));
        Assert.assertEquals(var.getStringVar("templatedid"), var.getStringVar("id"));
        TestUtils.log().info("Template update response is successfully verified");
    }

    public void verifyDocumentTemplateRequest(Map<String, String> params) {
        Map<String, String> endpointsparams = new HashMap<>();
        endpointsparams = params;
        Response response = rest.sendRequest("POSTP", ConfigDetails.Host_Prod, ep.DocumentCreation, "/testData/documentTemplate.json", headersXKey(),endpointsparams);
        TestUtils.log().info("**RESPONSE>> " + response.asString());
        if(response.getStatusCode() == 404){
            String status = response.path("status").toString();
            var.setStringVariable(status, "status");
            String errorMessage = response.path("message").toString();
            var.setStringVariable(errorMessage, "errorMessage");
        }
    }

    public void verifyDocumentTemplateResponse() {
        Response response = rest.getResponse();
        response.then().statusCode(Integer.parseInt(var.getStringVar("response_status_code")));
        TestUtils.log().info("Document generate response is successfully verified");
    }


    public void verifyDocumentErrorResponse() {
        Response response = rest.getResponse();
        response.then().statusCode(Integer.parseInt(var.getStringVar("response_status_code")));
        Assert.assertEquals(var.getStringVar("status"), var.getStringVar("Status"));
        Assert.assertEquals(var.getStringVar("Message"), var.getStringVar("errorMessage"));
        TestUtils.log().info("Document generate error response is successfully verified");
    }

    public void verifyRegisteredTemplateWithoutnameRequest() {
        Response response = rest.sendRequest("POSTH", ConfigDetails.Host_Prod, ep.TEMPLATE_LIST, "/testData/registerTemplateError.json", headersXKey());
        TestUtils.log().info("**RESPONSE>> " + response.asString());
            String status = response.path("status").toString();
            var.setStringVariable(status, "status");
            String errorMessage = response.path("subErrors[0].message").toString();
            var.setStringVariable(errorMessage, "errorMessage");

    }

    public void verifyRegisteredTemplateWithoutTemplateRequest() {
        Response response = rest.sendRequest("POSTH", ConfigDetails.Host_Prod, ep.TEMPLATE_LIST, "/testData/registerWithoutTemplateData.json", headersXKey());
        TestUtils.log().info("**RESPONSE>> " + response.asString());
            String status = response.path("status").toString();
            var.setStringVariable(status, "status");
            String errorMessage = response.path("subErrors[0].message").toString();
            var.setStringVariable(errorMessage, "errorMessage");

    }
}
