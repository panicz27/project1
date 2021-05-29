package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import templates.Page;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SelectDatesPage extends Page {
    @FindBy(css = "button.v-datefield-button")
    List<WebElement> dateButtons;

    @FindBy(css = "input.v-datefield-textfield")
    List<WebElement> inputs;

    @FindBy(xpath = "//*[contains(@id, 'GenericFilterPanel-button-ok')]")
    WebElement zmienButton;


    public SelectDatesPage(WebDriver driver) {
        super(driver);
    }

    public CalendarPage clickOnDateFromButton() {
        dateButtons.get(0).click();
        return new CalendarPage(driver);
    }

    public CalendarPage clickOnEndToButton() {
        dateButtons.get(1).click();
        return new CalendarPage(driver);
    }

    public AllOrdersPage clickOnZmienButton() {
        zmienButton.click();
        return new AllOrdersPage(driver);
    }

    public void assertInputsHaveCorrectValues(LocalDate startDate, LocalDate endDate) {
        this.assertDateFromHasCorrectValue(startDate);
        this.assertDateToHasCorrectValue(endDate);
    }

    private void assertDateFromHasCorrectValue(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String expectedDate = date.format(formatter);
        String actualDate = inputs.get(0).getAttribute("value");
        Assert.assertEquals(expectedDate, actualDate);
    }

    private void assertDateToHasCorrectValue(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String expectedDate = date.format(formatter);
        String actualDate = inputs.get(1).getAttribute("value");
        Assert.assertEquals(expectedDate, actualDate);
    }
}
