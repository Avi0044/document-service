package in.nbt.documentservice.restapi.helper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class TestUtils extends in.nbt.core.restapi.helper.TestUtils {
    public static final long WAIT = 10;

    @Override
    public HashMap<String, String> parseStringXML(InputStream file) throws Exception {
        return super.parseStringXML(file);
    }

    @Override
    public String dateTime() {
        return super.dateTime();
    }

    public static Logger log() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }

    public static void slackNotifierMsg(String msg) {
        String requestBody = "{\"text\": \"" + msg + "\"}";
        given().body(requestBody).contentType(ContentType.JSON).post(
                "https://hooks.slack.com/services/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"
        )
                .then()
                .statusCode(200);

    }

    public static void slackNotifierFile(String filePath) {
        Response req = given()
                .header("Authorization", "Bearer xoxb-58922511458-2668343242918-vUJ8lDhOLWVQMXwVxo2WMhCq")
                .contentType("multipart/form-data")
                .multiPart("file", new File(filePath))
                .multiPart("initial_comment", "B-)  AEPS v2 API Run Report: ")
                .multiPart("channels", "C02KC67DW7N")
                .when()
                .post("https://slack.com/api/files.upload");

        req.then()
                .statusCode(200);

    }

}
