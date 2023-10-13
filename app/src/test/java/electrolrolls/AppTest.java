package electrolrolls;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import electrolrolls.pages.Homepage;

public class AppTest {
    
    public static WebDriver driver;
    public static Homepage home;

    @BeforeMethod
    public static void createDriver() throws MalformedURLException {
        try {
            URL url = new URL("http://localhost:4444/wd/hub");
            final DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            driver = new RemoteWebDriver(url, capabilities);
            if (driver != null) {
                System.out.println("Launched driver successfully");
                driver.manage().window().maximize();
                home = new Homepage(driver);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    
    @Test
    @Parameters({"URL"})
    public static void getPollingStationsTest(String url) {
        boolean status = false;
        try {
            System.out.println("Test method called");
            Thread.sleep(8000);
            status = home.getPollingStations(url);
            Assert.assertEquals(status, true, "Failed to get polling stations");
            System.out.println("Test case passed: " + status);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
