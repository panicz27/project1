import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {


    @FindBy(css = "[name=username]")
    WebElement login;

    @FindBy(css = "[name=password]")
    WebElement password;

    @FindBy(css = "[name=login_submit")
    WebElement loginButton;

    LoginPage(WebDriver driver) {
        super(driver);
    }

    LoginPage setLogin(String login) {
        this.login.click();
        this.login.clear();
        this.login.sendKeys(login);
        return new LoginPage(driver);
    }

    LoginPage setPassword(String password) {
        this.password.click();
        this.password.clear();
        this.password.sendKeys(password);
        return new LoginPage(driver);
    }

    public HomePage clickOnLoginButton() {
        this.loginButton.click();
        return new HomePage(driver);
    }

}
