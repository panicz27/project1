package pages;

import common.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import templates.Page;

import java.time.LocalDate;
import java.util.List;

public class CalendarPage extends Page {
    @FindBy(css = "span.v-datefield-calendarpanel-month")
    WebElement monthAndYear;

    private By nextMonthLocator = By.cssSelector("button.v-button-nextmonth");

    private By previousMonthLocator = By.cssSelector("button.v-button-prevmonth");

    private By nextYearLocator = By.cssSelector("button.v-button-nextyear");

    private By previousYearLocator = By.cssSelector("button.v-button-prevyear");

    private Utils utils = new Utils();

    private String montAndYearText = monthAndYear.getText();


    private WebElement getNextMonthButton() {
        utils.waitUntilElementToBeClickable(driver, nextMonthLocator);
        return driver.findElement(nextMonthLocator);
    }

    private WebElement getPreviousMonthButton() {
        utils.waitUntilElementToBeClickable(driver, previousMonthLocator);
        return driver.findElement(previousMonthLocator);
    }

    private WebElement getNextYearButton() {
        utils.waitUntilElementToBeClickable(driver, nextYearLocator);

        return driver.findElement(nextYearLocator);
    }

    private WebElement getPreviousYearButton() {
        utils.waitUntilElementToBeClickable(driver, previousYearLocator);

        return driver.findElement(previousYearLocator);
    }

    CalendarPage(WebDriver driver) {
        super(driver);

    }

    private CalendarPage setDay(LocalDate date) {
        Utils util = new Utils();
        String dayOfMonth = String.valueOf(date.getDayOfMonth());
        By dayLocator = By.xpath("//*[text() = '" + dayOfMonth + "'] ");
        util.waitUntilElementToBeClickable(driver, dayLocator);
        List<WebElement> day = driver.findElements(dayLocator);
        for (WebElement el : day) {
            if (!el.getAttribute("class").contains("v-datefield-calendarpanel-day-offmonth")
                    && el.getAttribute("class").contains("v-datefield-calendarpanel-day")) {
                el.click();
            }
        }

        return new CalendarPage(driver);
    }


    private int getActualYear() {
        String actualYear = monthAndYear.getText().replaceAll("[^0-9]", "");
        return Integer.valueOf(actualYear);
    }

    private int getActualMonth() {
        String actualMonth = monthAndYear.getText();
        actualMonth = actualMonth.replaceAll("[0-9]", "");
        actualMonth = actualMonth.replaceAll("\\s+", "");
        int month = getMonthByName(actualMonth).monthNumber;
        return month;

    }


    public SelectDatesPage setCorrectDate(LocalDate date) {
        setMonth(date);
        setYear(date);
        setDay(date);
        return new SelectDatesPage(driver);
    }

    private CalendarPage setMonth(LocalDate expectedDate) {

        int expectedMonth = expectedDate.getMonthValue();
        int actualMonth = getActualMonth();

        if (expectedMonth > actualMonth) {
            while (expectedMonth != actualMonth) {
                getNextMonthButton().click();
                actualMonth = getActualMonth();

            }
        } else {
            while (expectedMonth != actualMonth) {
                getPreviousMonthButton().click();
                actualMonth = getActualMonth();
            }

        }
        return new CalendarPage(driver);
    }

    private CalendarPage setYear(LocalDate expectedDate) {
        int expectedYear = expectedDate.getYear();
        int actualYear = this.getActualYear();
        if (expectedYear > actualYear) {
            while (expectedYear != actualYear) {
                getNextYearButton().click();
                actualYear = getActualYear();


            }
        } else {
            while (expectedYear != actualYear) {
                getPreviousYearButton().click();
                actualYear = getActualYear();
            }

        }
        return new CalendarPage(driver);
    }


    private Months getMonthByName(String name) {
        for (Months month : Months.values()) {
            if (month.getName().equals(name))
                return month;
        }
        return null;
    }

    private enum Months {
        styczeń(1, "styczeń"),
        luty(2, "luty"),
        marzec(3, "marzec"),
        kwiecień(4, "kwiecień"),
        maj(5, "maj"),
        czerwiec(6, "czerwiec"),
        lipiec(7, "lipiec"),
        sierpień(8, "sierpień"),
        wrzesień(9, "wrzesień"),
        październik(10, "październik"),
        listopad(11, "listopad"),
        grudzień(12, "grudzień");

        int monthNumber;
        String name;

        protected String getName() {
            return this.name;
        }

        Months(int monthNumber, String name) {
            this.monthNumber = monthNumber;
            this.name = name;
        }

    }

}
