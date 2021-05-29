import lombok.SneakyThrows;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchBoxPage extends Page {
    @FindBy(css = "table>tbody>tr>td>span")
    List<WebElement> options;
    @FindBy(css = "div.v-filterselect-nextpage")
    WebElement nextButton;

    @FindBy(css = "div.v-filterselect-status")
    WebElement pageNumberElement;

    private String pageNumber = pageNumberElement.getText();

    public void waitForSearchPageToBeChanged(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return !pageNumberElement.getText().equals(pageNumber);
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }


    private By nextButtonLocation = By.cssSelector("div.v-filterselect-nextpage");

    SearchBoxPage(WebDriver driver) {
        super(driver);
    }

    @SneakyThrows
    public void clickOnCorrectFilterName(String text) {
        for (WebElement option : options) {
            if (option.getText().equals(text)) {
                option.click();
                return;
            }
        }
        if (isListScrollable()) {
            nextButton.click();
            waitForSearchPageToBeChanged(driver);
            new SearchBoxPage(driver)
                    .clickOnCorrectFilterName(text);
        } else {
            Assert.assertFalse("Cannot find element " + text + " on list", true);
        }
    }

    private boolean isListScrollable() {
        try {
            driver.findElement(nextButtonLocation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
