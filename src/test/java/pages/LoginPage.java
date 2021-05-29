package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import templates.Page;

public class LoginPage extends Page {


    @FindBy(css = "[name=username]")
    WebElement login;

    @FindBy(css = "[name=password]")
    WebElement password;

    @FindBy(css = "[name=login_submit")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage setLogin(String login) {
        this.login.click();
        this.login.clear();
        this.login.sendKeys(login);
        return new LoginPage(driver);
    }

    public LoginPage setPassword(String password) {
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
