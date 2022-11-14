package in.nbt.documentservice.restapi.runner;

import in.nbt.documentservice.restapi.helper.Configvariable;
import in.nbt.documentservice.restapi.helper.ReportGenerator;
import in.nbt.documentservice.restapi.helper.TestUtils;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ComponentScan(basePackages = {"in.*"})
@Configuration
@CucumberOptions(
        features = "classpath:features",
        glue = {"in/nbt/documentservice/restapi/stepdef"},
        tags = "@TemplateRegister",
        plugin = {
                "pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "json:target/cucumber-reports/report.json",
                "html:target/site/cucumber-pretty",
                "json:target/cucumber.json",
                "rerun:target/cucumber-rerun.txt"
        },
        monochrome = true
)
public class CucumberRunner {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now;
    LocalDateTime later;

//    private static String OS = System.getProperty("os.name").toLowerCase();

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeSuite(alwaysRun = true)
    public void setUpEnvironmentToTest() {
        Configvariable conf = new Configvariable();
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml"); //look for logging config
        conf.setupEnvironmentProperties(System.getenv("envi")); //pass through env-variables

        now = LocalDateTime.now();
      //  TestUtils.slackNotifierMsg(":-|  API-Automation-Run-Started on: " + dtf.format(now));
        TestUtils.log().info("Test Run Started");
    }

    @AfterSuite(alwaysRun = true)
    public void garbageCollect() {
        ReportGenerator.genReports();
        later = LocalDateTime.now();
    //    TestUtils.slackNotifierMsg(":-)  API-Automation-Run-Completed on: " + dtf.format(later));

//        if (OS.indexOf("win") >= 0) {
//           // do something
//        }
//        if (OS.indexOf("mac") >= 0) {
//            // do something
//        }
//        if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") >= 0) {
//            // do something
//        }

     //   TestUtils.slackNotifierFile(System.getProperty("user.dir") + "/test-output-sequential/PdfReport/test_result_seq.pdf");
        TestUtils.log().info("Test Run Completed");

    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runScenario(pickle.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        /*
         * if(testNGCucumberRunner==null) testNGCucumberRunner = new
         * TestNGCucumberRunner(this.getClass());
         */
        return testNGCucumberRunner.provideScenarios();
    }

    @BeforeClass(alwaysRun = true)
    public void setUpCucumber() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }

}