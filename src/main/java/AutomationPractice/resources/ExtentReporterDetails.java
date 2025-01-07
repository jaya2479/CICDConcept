package AutomationPractice.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReporterDetails {

	public static  ExtentReports getReportObject()
	{
        String path = System.getProperty("user.dir")+"//resources//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("TestNG Concept Tests");
		reporter.config().setDocumentTitle("TestNG Concept Reports");

		ExtentReports extReport = new ExtentReports();
		extReport.attachReporter(reporter);
		extReport.setSystemInfo("TestNG Learning", "Jaya");
		return extReport;
	}

}
