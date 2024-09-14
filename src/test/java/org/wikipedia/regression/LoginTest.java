package org.wikipedia.regression;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.wikipedia.common.CommonPage;
import org.wikipedia.common.Path;
import org.wikipedia.common.TestBase;
import org.wikipedia.common.WikiUtils;
import org.wikipedia.drivers.DriverManager;
import org.wikipedia.pages.LoginPage;

import java.util.Properties;

public class LoginTest extends TestBase {

    private LoginPage loginPage;
    private CommonPage commonPage;


    @BeforeClass(alwaysRun = true)
    public void initialize(){
        loginPage = new LoginPage();
        commonPage = new CommonPage();
    }

    @Test(priority = 1, description = "Verify that when inputting the credentials at Wikipedia Login page, the user is able to login into Wikipedia.", groups = {"regression"})
    public void TestCases_C01()  {
        WikiUtils.log("Start of TestCase - TestCases_C01...");
        SoftAssert softAssert = new SoftAssert();
        // Launch Wikipedia Login URL in Chrome Browser
        DriverManager.getDriver().get(Path.WIKIPEDIA_LOGIN_URL);

        // Enter Username and Password -
        loginPage.getUsername(5000).sendKeys(TestBase.getUserDetails().getProperty("username"));
        loginPage.getPassword().sendKeys(TestBase.getUserDetails().getProperty("password"));

        // Click Log in button -
         commonPage.buttonOnPage("Log in", 1).click();
        WikiUtils.log("End of TestCase - TestCases_C01...");
   }
}
