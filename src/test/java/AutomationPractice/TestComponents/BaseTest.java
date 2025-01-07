package AutomationPractice.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import AutomationPractice.Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    public WebDriver driver;
    public LoginPage loginPage;

    // Method to initialize the browser based on the property file
    public WebDriver initializeBrowser() throws IOException {
        Properties prop = new Properties();
        FileInputStream fileIS = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\AutomationPractice\\resources\\GlobalData.properties");
        prop.load(fileIS);

        // Read browser name from system properties or the properties file
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
        
        
        //String browserName = prop.getProperty("browser");
        
        // Initialize the appropriate WebDriver based on the browser name
        if (browserName.contains("chrome")) {
        	 
        	ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup(); 
            if(browserName.contains("headless")){
            	options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1400, 900));
        	 
            
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        return driver;
    }

    // Method to capture screenshot of the current browser window
    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//resources//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//resources//" + testCaseName + ".png";
    }

    // This method is executed before each test method to initialize the browser and launch the login page
    @BeforeMethod(alwaysRun = true)
    public LoginPage launchingPage() throws IOException {
        driver = initializeBrowser(); // <-- This line initializes the browser
        loginPage = new LoginPage(driver); // Create an instance of LoginPage
        loginPage.goTo(); // Navigate to the login page
        return loginPage;
    }

    // This method is executed after each test method to close the browser
    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit(); // Use quit() to close all browser instances and end the WebDriver session
    }
}









// Potential issue: If initializeBrowser() is called again outside @BeforeMethod, it can cause the browser to open twice.
// Ensure initializeBrowser() is only called once per test case.

// The following method is commented out and can be used to read JSON data into a list of HashMaps
// public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException
// {
//
// // Read JSON to String
// String jsonContent = FileUtils.readFileToString(new File(FilePath), StandardCharsets.UTF_8);
//
// // Convert String to HashMap using ObjectMapper
// ObjectMapper mapper = new ObjectMapper();
// List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
// return data;
//
// }
