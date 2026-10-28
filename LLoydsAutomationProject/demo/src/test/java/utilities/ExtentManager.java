package utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {
            ExtentSparkReporter html=new ExtentSparkReporter(filePath);
            html.config().setDocumentTitle("iOS Automation Test Report");
            html.config().setReportName("iOS Automation Test Report");
            html.config().setTheme(Theme.DARK);
            extent = new ExtentReports();
            extent.attachReporter(html);
        }

        return extent;
    }

    public synchronized static ExtentReports getReporter() {
        return extent;
    }


}