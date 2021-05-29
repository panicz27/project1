package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import templates.Page;

public class HomePage extends Page {

    @FindBy(css = "div.v-menubar> span:nth-child(3)")
    WebElement ordersElement;

    @FindBy(css = "img.close_image")
    WebElement advertisementCloseButton;

    @FindBy(css = "div.v-menubar-submenu>span:nth-child(3)")
    WebElement allOrdersElement;

    HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickOnOrders() {
        clickOnCloseAdvertisementButton();
        ordersElement.click();
        return new HomePage(driver);
    }

    public AllOrdersPage clickOnAllOrders() {
        allOrdersElement.click();
        return new AllOrdersPage(driver);
    }

    private HomePage clickOnCloseAdvertisementButton() {
        advertisementCloseButton.click();
        return new HomePage(driver);
    }

}
