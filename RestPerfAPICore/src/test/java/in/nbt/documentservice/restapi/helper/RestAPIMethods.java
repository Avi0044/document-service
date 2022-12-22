package in.nbt.documentservice.restapi.helper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAPIMethods extends in.nbt.core.restapi.helper.RestAPIMethods {
    @Override
    public synchronized Response sendRequest(String requestType, String baseURL, String endPoint, String jsonString) {
        return super.sendRequest(requestType, baseURL, endPoint, jsonString);
    }

    @Override
    public synchronized Response sendRequest(String requestType, String baseURL, String endPoint, String jsonString, Map<String, String> headers) {
        return super.sendRequest(requestType, baseURL, endPoint, jsonString, headers);
    }

    @Override
    public Response sendGetRequest(String url) {
        return super.sendGetRequest(url);
    }

    @Override
    public Response sendPatchRequest(String url, String jsonString) {
        return super.sendPatchRequest(url, jsonString);
    }

    @Override
    public void setEndPointParamsVar(Map<String, String> params) {
        super.setEndPointParamsVar(params);
    }

    @Override
    public void clearRequestSpecObject() {
        super.clearRequestSpecObject();
    }

    @Override
    public RequestSpecification getRequestSpec() {
        return super.getRequestSpec();
    }

    @Override
    public Response getResponse() {
        return super.getResponse();
    }

    @Override
    public RequestSpecification setBasicAuthentication(String token) {
        return super.setBasicAuthentication(token);
    }

    @Override
    public RequestSpecification setBasicAuthentication(String username, String password) {
        return super.setBasicAuthentication(username, password);
    }

    @Override
    public RequestSpecification setHeaderParams(Map<String, String> headerParams) {
        return super.setHeaderParams(headerParams);
    }

    @Override
    public RequestSpecification setBodyParam(Map<String, String> bodyContent) {
        return super.setBodyParam(bodyContent);
    }

    @Override
    public RequestSpecification setBodyParam(String bodyContent) {
        return super.setBodyParam(bodyContent);
    }

    @Override
    public RequestSpecification setBodyParam(String key, String value) {
        return super.setBodyParam(key, value);
    }

    @Override
    public Integer getStatusCode() {
        return super.getStatusCode();
    }

    @Override
    public String getResponseAsString() {
        return super.getResponseAsString();
    }

    @Override
    public String getValueFromResponse(String jsonKey) {
        return super.getValueFromResponse(jsonKey);
    }

    @Override
    public Object getObjectFromResponse(String jsonKey) {
        return super.getObjectFromResponse(jsonKey);
    }

    @Override
    public Object getObjectFromResponse(Response response, String jsonKey) {
        return super.getObjectFromResponse(response, jsonKey);
    }

    @Override
    public <T> T getResponseIntoObject(Response response, String jsonKey, Class<T> clazz) {
        return super.getResponseIntoObject(response, jsonKey, clazz);
    }
}
