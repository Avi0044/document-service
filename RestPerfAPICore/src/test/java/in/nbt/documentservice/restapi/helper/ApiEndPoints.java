package in.nbt.documentservice.restapi.helper;

public class ApiEndPoints extends in.nbt.core.restapi.helper.ApiEndPoints {

    public static final String BAL_CHECK_1 = "/txn/balance";
    public static final String BAL_CHECK = "/aepsV3/V3/aepsbe";
    public static final String STATEMENT = "/txn/statement";
    public static final String WITHDRAW = "/txn/withdraw";
    public static final String AADHAARPAY = "/txn/pay";
    public static final String TEMPLATE_LIST = "/api/v1/templates";

    public static final String DocumentCreation = "/api/v1/documents";

    @Override
    public String getEndPoint(String endPoint) {
        switch (endPoint) {
            case "TEMPLATE_LIST":
            case "RegisterTemplate":
            case "Update_Template":
                return TEMPLATE_LIST;
            case "DocumentCreation":
                return DocumentCreation;
            default:
                return null;

        }
    }

    @Override
    public String getJSONFilePath(String endPoint) {
        switch (endPoint) {
            case "TEMPLATE_LIST":
            case "RegisterTemplate":
                return "/testData/registerTemplate.json";
            case "DocumentCreation":
                return "/testData/documentTemplate.json";
            default:
                return null;
        }
    }

}