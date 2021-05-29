import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;

public class FireUpTest extends WebTest {

    @Before
    public void setUp() {
        super.initPage();
    }

    @Test
    public void findOrder() {
        LocalDate startDate = LocalDate.of(2019, 5, 1);
        LocalDate endDate = LocalDate.of(2019, 5, 30);

        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .setLogin(LOGIN)
                .setPassword(PASSWORD)
                .clickOnLoginButton()
                .clickOnZlecenia()
                .clickOnWszystkieZlecenia()
                .clickOnArrowToSeeAllFilterValues("Wybierz filtr")
                .clickOnCorrectFilterName("Data utworzenia");

        new SelectDatesPage(driver)
                .clickOnDateFromButton()
                .setCorrectDate(startDate)
                .clickOnEndToButton()
                .setCorrectDate(endDate)
                .assertInputsHaveCorrectValues(startDate, endDate);

        new SelectDatesPage(driver)
                .clickOnZmienButton()
                .checkButtonWithFilterContent(startDate, endDate);
    }
}
