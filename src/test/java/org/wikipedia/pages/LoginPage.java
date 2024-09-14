package org.wikipedia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.wikipedia.common.CommonPage;
import org.wikipedia.common.WikiUtils;
import org.wikipedia.drivers.DriverManager;

public class LoginPage {
    private WikiUtils utils = new WikiUtils();
    private CommonPage commonPage = new CommonPage();

    public WebElement getUsername(int timeInMilliSecToWait) {
        commonPage.genericExplicitWait(timeInMilliSecToWait, By.xpath("//input[@name='wpName']"));
        return DriverManager.getDriver().findElement(By.xpath("//input[@name='wpName']"));
    }

    public WebElement getPassword() {
        return DriverManager.getDriver().findElement(By.xpath("//input[@name='wpPassword']"));
    }

    public boolean isUsernameVisible(int waitForSeconds) {
        commonPage.waitForElementToBePresent(waitForSeconds, By.xpath("//span[contains(text(),'ShubhEducates')]"));
        return DriverManager.getDriver().findElement(By.xpath("//span[contains(text(),'ShubhEducates')]")).isDisplayed();
    }
}
