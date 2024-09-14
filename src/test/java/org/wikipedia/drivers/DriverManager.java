package org.wikipedia.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.wikipedia.common.TestBase;
import org.wikipedia.common.WikiUtils;

import java.io.File;
import java.util.Properties;

public class DriverManager {

    private static final String LOCAL_PATH_FOR_BROWSER_PROPERTIES;
    private static final String KEY_BROWSER_NAME = "browser.name";
    private static final String KEY_BROWSER_RESOLUTION= "browser.resolution";
    private static WebDriver driver;
    private static Properties browserProps;

    static {
        LOCAL_PATH_FOR_BROWSER_PROPERTIES = File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "properties" + File.separator + "browser" + File.separator;
    }

    @BeforeSuite(alwaysRun = true)
    @Parameters({"browser.properties", "user.login.properties"})
    public void setup(String browserProperties, String userLoginProperties) {
        WikiUtils.log("DriverManager::setup() - WebDriver setup started...");
        browserProps = WikiUtils.getProperties(browserProperties, "browser");
        driver = createDriverInstance(browserProps);
        TestBase.setUserDetails(userLoginProperties, "jenkinsparameters");
        WikiUtils.log("DriverManager::setup() - WebDriver setup completed...");
    }

    /**
     * Method to create WebDriver Instance
     * @param browserProps
     * @return
     */
    private static WebDriver createDriverInstance(Properties browserProps) {
        WebDriver driver = null;
        try {
            driver = setupCapabilitiesAndOptions(browserProps);
        } catch (Exception e) {
            WikiUtils.log("DriverManager::createDriverInstance() | Exception desc : " + e.getMessage());
        }
        WikiUtils.log("DriverManager::createDriverInstance() - WebDriver instance created...");
        return driver;
    }

    /**
     * Method to set capabilities and option while instanciating the Web Driver -
     * @param browserProps
     * @return
     */
    private static WebDriver setupCapabilitiesAndOptions(Properties browserProps) {
        WikiUtils.log("In method DriverManager::setupCapabilitiesAndOptions()");
        switch (browserProps.getProperty( KEY_BROWSER_NAME )) {
            case "FIREFOX":
                WikiUtils.log("Launching firefox browser");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                // ToDo : Set options
                return (WebDriver) new FirefoxDriver(firefoxOptions);

            case "EDGE":
                WikiUtils.log("Launching Edge browser");
                EdgeOptions edgeOptions = new EdgeOptions();
                // ToDo : Set options
                return (WebDriver) new EdgeDriver(edgeOptions);

            case "SAFARI":
                WikiUtils.log("Launching Safari browser");
                SafariOptions safariOptions = new SafariOptions();
                // ToDo : Set options
                return (WebDriver) new SafariDriver(safariOptions);

            default:    // Chrome
                WikiUtils.log("Launching Chrome browser");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized"); 			// open Browser in maximized mode
                chromeOptions.addArguments("disable-infobars"); 		// disabling infobars
                chromeOptions.addArguments("--disable-extensions");     // Disable extensions
                chromeOptions.setAcceptInsecureCerts(true);				// AcceptInsecureCerts
                chromeOptions.addArguments("--ignore-certificate-errors");
                chromeOptions.addArguments("--remote-allow-origins=*");
                if (browserProps.getProperty(KEY_BROWSER_RESOLUTION).toString().equalsIgnoreCase("maximum"))
                    chromeOptions.addArguments("window-size=start-maximized");
                else
                    chromeOptions.addArguments("window-size=" + browserProps.getProperty(KEY_BROWSER_RESOLUTION));
                chromeOptions.addArguments("start-maximized");
                WikiUtils.log("ChromeOptions have been set in ChromeDriver.");
                return (WebDriver) new ChromeDriver(chromeOptions);
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * Method to safely close and quit the browser
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.close();  // Closes the current browser window
            driver.quit();   // Closes all windows and safely ends the WebDriver session
            WikiUtils.log("Browser closed and driver quit.");
        }
    }
}
