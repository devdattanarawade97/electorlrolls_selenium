package electrolrolls;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.logging.log4j.core.Logger;  // Importing Logger from log4j
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import electrolrolls.pages.Homepage;  // Importing the Homepage class from a different package

public class App {
   
    // Initialize a logger for this class
    public static Logger logger = (Logger) LoggerFactory.getLogger("App.class");

    // Method to create a WebDriver instance
    public static WebDriver createDriver() throws MalformedURLException {
        URL url = new URL("http://localhost:4444/wd/hub");
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        WebDriver driver = new RemoteWebDriver(url, capabilities);
        if (driver != null) {
            System.out.println("Launched driver successfully");
            logger.info("Launched driver successfully");
        }
        return driver;
    }

    public static void main(String[] args) throws MalformedURLException,InterruptedException {
        // Create a WebDriver instance
        WebDriver driver = createDriver();
        Homepage home = new Homepage(driver);

        // Call the getPollingStations method on the Homepage object
        boolean status = home.getPollingStations("https://ceotserms2.telangana.gov.in/ts_erolls/rolls.aspx");



        
        // Check the status and print the result
        if (status) {
            System.out.println("Test case 'test01' passed");
        } else {
            System.out.println("Test case 'test01' failed");
        }
    }
}
