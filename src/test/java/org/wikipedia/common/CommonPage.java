package org.wikipedia.common;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.wikipedia.drivers.DriverManager;

import java.time.Duration;

public class CommonPage {

    /**
     * Method to wait for element to be located on Web Page.
     * @param timeMilliSec
     * @param locator
     */
    public static void genericExplicitWait(int timeMilliSec, By locator) {
        WikiUtils.log("Running genericExplicitWait() ");
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofMillis(timeMilliSec));

        try {
            WikiUtils.log("Waiting for visibility of " + locator);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            WikiUtils.log(locator + " element was not visible");
            e.printStackTrace();
        }
    }

    /**
     * Method to return the button web element.
     * @param buttonLabel
     * @param index
     * @return
     */
    public WebElement buttonOnPage(String buttonLabel, int index) {
        return DriverManager.getDriver().findElement((By.xpath("//button[text()='" + buttonLabel + "']["+index+"]")));
    }
}
