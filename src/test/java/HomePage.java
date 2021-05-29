import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    @FindBy(css = "div.v-menubar> span:nth-child(3)")
    WebElement zlecenia;

    @FindBy(css = "img.close_image")
    WebElement advertisementCloseButton;

    private By wszystkieZlecenia = By.cssSelector("div.v-menubar-submenu>span:nth-child(3)");

    HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickOnZlecenia() {
        clickOnCloseAdvertisementButton();
        zlecenia.click();
        return new HomePage(driver);
    }

    public WszystkieZlecenia clickOnWszystkieZlecenia() {
        getWszystkieZleceniaButton().click();
        return new WszystkieZlecenia(driver);
    }

    private HomePage clickOnCloseAdvertisementButton() {
        advertisementCloseButton.click();
        return new HomePage(driver);
    }

    private WebElement getWszystkieZleceniaButton() {
        return driver.findElement(wszystkieZlecenia);
    }

}
