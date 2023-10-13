package electrolrolls.pages;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.checkerframework.checker.units.qual.degrees;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Homepage {
    
    // Initialize a logger for this class
    public static Logger logger = LoggerFactory.getLogger("homepage.class");
    
    WebDriver driver;
   
    // Constructor to initialize the WebDriver
    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    // Method for fetching polling stations
    public boolean getPollingStations(String url) throws InterruptedException {
       
        // XPATH constants
        String DISTRICT_NAME_ID = "//*[@id='ctl00_ContentPlaceHolder1_ddlDist']";
        String RANGAREDDY_TEXT = "//*[text()='14-Rangareddy']";
        String ASSEMBLY_CONSTITUENCY = "//*[@id='ctl00_ContentPlaceHolder1_ddlAC']";
        String LAL_BAHADUR_TEXT = "//*[text()='49-Lal Bahadur Nagar']";
        String GET_POLLING_STATION = "//*[@id='ctl00_ContentPlaceHolder1_btnlogin']";
        String POLLING_STATIONS_OUTPUT = "//*[text()='Polling Station No']";
     
        try {
            // Navigate to the specified URL
            driver.get(url);

            // Wait for the URL to match the expected URL
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.urlToBe(url));

            // Check if the current URL matches the specified URL
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.equals(url)) {
                return false;
            } else {
                logger.info("Opened URL successfully");
                System.out.println("Opened URL successfully");
            }

            // Select the district
            WebDriverWait districtSelecWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            districtSelecWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DISTRICT_NAME_ID)));
            WebElement districtNameElement = driver.findElement(By.xpath(DISTRICT_NAME_ID));
            districtNameElement.click();

            // Select Rangareddy district
            WebDriverWait districtNameWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement rangareddyWebElement = districtNameWait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RANGAREDDY_TEXT)));
            rangareddyWebElement.click();

            // Select assembly constituency
            WebDriverWait assemblyWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement assemblyConsElement = assemblyWait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(ASSEMBLY_CONSTITUENCY)));
            assemblyConsElement.click();

            // Select Lal Bahadur Nagar assembly constituency
            WebDriverWait lalBahadurWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement lalBahadurElement = lalBahadurWait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LAL_BAHADUR_TEXT)));
            lalBahadurElement.click();

            // Click the "Get Polling Station" button
            WebDriverWait getPollingButtonWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement getPollingButton = getPollingButtonWait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(GET_POLLING_STATION)));
            getPollingButton.click();

            // Wait for the polling station output to be displayed
            WebDriverWait pollingOutputWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement pollingOutputElement = pollingOutputWait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(POLLING_STATIONS_OUTPUT)));

            // Check if polling stations are displayed
            if (pollingOutputElement.isDisplayed()) {
                logger.info("Searched polling station successfully");
                System.out.println("Searched polling station successfully");
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            // Handle exceptions and log errors
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }
}
