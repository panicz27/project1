package pages;

import common.Utils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import templates.Page;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AllOrdersPage extends Page {
    @FindBy(css = "div.v-button-link > span> span")
    WebElement buttonWithDateRanges;

    private Utils utils = new Utils();

    private By comboBoxes = By.xpath("//div[(@role = 'combobox')]");

    AllOrdersPage(WebDriver driver) {
        super(driver);
    }

    public SearchBoxPage clickOnArrowToSeeAllFilterValues(String dropdownListName) {
        utils.waitUntilElementVisible(driver, comboBoxes);
        List<WebElement> elements = driver.findElements(comboBoxes);
        for (WebElement element : elements) {
            String text = element.findElement(By.cssSelector("input")).getAttribute("value");
            if (text.equals(dropdownListName)) {
                WebElement searchedElement = element.findElement(By.cssSelector("div.v-filterselect-button"));
                searchedElement.click();
            }
        }
        return new SearchBoxPage(driver);
    }


    private String getStringButton() {
        return (buttonWithDateRanges.getText());
    }

    public void checkButtonWithFilterContent(LocalDate startDate, LocalDate endDate) {
        String expectedString = startDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " - " +
                endDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        Assert.assertTrue(getStringButton().contains(expectedString));
    }
}
