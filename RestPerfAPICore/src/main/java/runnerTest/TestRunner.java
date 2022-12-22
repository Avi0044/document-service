package runnerTest;

import org.testng.internal.ITestResultNotifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class TestRunner {

    public static String threadCount = System.getProperty("thread_count");
    public static String iterationCount = System.getProperty("iteration_count");
    public static String tags = System.getProperty("cucumber.filter.tags");
    public static String env = System.getProperty("env");

    public static String[] runCommand(String command) throws IOException {

            ArrayList list = new ArrayList();
            Process proc = Runtime.getRuntime().exec(command);
          InputStream iStr = proc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStr));
            String str;
            try{
                while ((str = br.readLine()) != null)
                list.add(str);
        }
            catch(Exception e) {
                System.err.println("Exception:"+e);
            }
            // Wait for process to terminate and catch any Exceptions.
        try {
            proc.waitFor();
        } catch (InterruptedException e) {
            System.err.println("Process was interrupted");
        }
        br.close(); // Done.
        return (String[]) list.toArray(new String[0]);
    }

    public static void main(String[] args) throws IOException {
        try {
            //  String command = System.getProperty("command", "mvn.cmd -DURL=\"" + env + "\" -Dcucumber.filter.tags=\"" + tags + "\" -Dthread_count=\"" + threadCount + "\" -Diteration_count=\"" + iterationCount + "\"  -Dtestng=\"apitestng\" verify");
            String command = System.getProperty("command", "mvn.cmd -DURL=\"" + env + "\" -Dcucumber.filter.tags=\"" + tags + "\" -Dtestng=\"apitestng\" verify");
            runCommand(command);
        } catch (IOException e) {
            System.err.println(e);
        }

    }


}
