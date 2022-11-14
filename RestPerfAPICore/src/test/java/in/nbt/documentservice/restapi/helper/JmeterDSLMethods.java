package in.nbt.documentservice.restapi.helper;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import in.nbt.documentservice.restapi.helper.*;
import io.cucumber.gherkin.internal.com.eclipsesource.json.Json;
import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.MimeTypes.Type;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

public class JmeterDSLMethods {

    private ApiEndPoints ep = new ApiEndPoints();
    private JsonReader jsonReader = new JsonReader();
    private Configvariable var = new Configvariable();
    String jsonFilePath;
 /*   String threadCount = System.getProperty("thread_count");
    String iterationCount = System.getProperty("iteration_count");*/

    //Here is a simple example test
    // + with 2 threads/users iterating 10 times each to send HTTP POST requests
    // with a request JSON body to (host + end-point):

    public void testPerformanceTemplate(String endPoint) {
        String host = ConfigDetails.Host_Prod;
//        String requestBody = var.getStringVarList();
        TestPlanStats stats = null;
        String baseUrl  = host+ep.getEndPoint(endPoint);
        try {
            stats = testPlan(
                    threadGroup(Integer.parseInt(var.getStringVar("thread_count")),
                            Integer.parseInt(var.getStringVar("iteration_count")),
                            httpSampler(baseUrl)
                                    .param("appId","0101")
                                    .param("status","ACTIVE")
                                    .param("type","Cash Withdrawal")
                                    .header("X-Api-Key","3615958d-7687-4d37-8c5f-84d22ec3e8c6")
                                    .method(HTTPConstants.GET)
                    ),
                    //this is just to log details of each request stats
                    jtlWriter("test-logs/perf-test-" + Instant.now().toString().replace(":", "-") + ".jtl")
            ).run();
        } catch (IOException e) {
            System.out.println("Inside catch block of Performance testing");
            e.printStackTrace();
            TestUtils.log().info("[JMeter] Request Failed with Error : " + e);
        }
        int val = (Duration.ofSeconds(60)).compareTo(stats.overall().sampleTimePercentile99());
        // Assert.assertTrue(val>0);
        TestUtils.log().info("[JMeter] Performance is validated, less that  : " + val);
    }

    public void testPerformanceRegisterTemplate(String endPoint) {
        String host = ConfigDetails.Host_Prod;
        jsonFilePath = "/testData/registerTemplate.json";
        String requestBody = jsonReader.getJsonString(jsonFilePath);
        requestBody = var.expandValue(requestBody);
        TestPlanStats stats = null;

        try {
            stats = testPlan(
                    threadGroup(Integer.parseInt(var.getStringVar("thread_count")),
                            Integer.parseInt(var.getStringVar("iteration_count")),
                            httpSampler(host+ep.getEndPoint(endPoint))
                                    .header("X-Api-Key","3615958d-7687-4d37-8c5f-84d22ec3e8c6")
                                    .post(requestBody, ContentType.APPLICATION_JSON)
                    ),
                    //this is just to log details of each request stats
                    jtlWriter("test-logs/perf-test-" + Instant.now().toString().replace(":", "-") + ".jtl")
            ).run();
        } catch (IOException e) {
            System.out.println("Inside catch block of Performance testing");
            e.printStackTrace();
            TestUtils.log().info("[JMeter] Request Failed with Error : " + e);
        }
        int val = (Duration.ofSeconds(60)).compareTo(stats.overall().sampleTimePercentile99());
        // Assert.assertTrue(val>0);
        TestUtils.log().info("[JMeter] Performance is validated, less that  : " + val);
    }

    public void testPerformanceTemplateDocument(String endPoint) {
        String host = ConfigDetails.Host_Prod;
        jsonFilePath = "/testData/documentTemplate.json";
        String requestBody = jsonReader.getJsonString(jsonFilePath);
        requestBody = var.expandValue(requestBody);
        TestPlanStats stats = null;
        String baseUrl  = host+ep.getEndPoint(endPoint)+"?"+"templateId=634fa40ac4557e0726c78d20&appId=01&docType=PDF";

        try {
            stats = testPlan(
                    threadGroup(Integer.parseInt(var.getStringVar("thread_count")),
                            Integer.parseInt(var.getStringVar("iteration_count")),
                            httpSampler(baseUrl)
                                    .header("X-Api-Key","3615958d-7687-4d37-8c5f-84d22ec3e8c6")
//                                    .param("templateId","634fa40ac4557e0726c78d20")
//                                    .param("appId","01")
//                                    .param("docType","PDF")
                                    .post(requestBody, ContentType.APPLICATION_JSON)

                    ),
                    //this is just to log details of each request stats
                    jtlWriter("test-logs/perf-test-" + Instant.now().toString().replace(":", "-") + ".jtl")
            ).run();
        } catch (IOException e) {
            System.out.println("Inside catch block of Performance testing");
            e.printStackTrace();
            TestUtils.log().info("[JMeter] Request Failed with Error : " + e);
        }
        int val = (Duration.ofSeconds(60)).compareTo(stats.overall().sampleTimePercentile99());
        // Assert.assertTrue(val>0);
        TestUtils.log().info("[JMeter] Performance is validated, less that  : " + val);
    }
}