package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Utils {

    public void waitUntilElementVisible(WebDriver driver, By elementLocator) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(elementLocator));
    }

    public void waitUntilElementToBeClickable(WebDriver driver, By elementLocator) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(elementLocator));
    }

}
