package ge.ee.utils;

public class Utils {

    public static void log(String message){
        if(ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().info(message);
        }

        // System.out.println("LOG: " + message);
    }

}