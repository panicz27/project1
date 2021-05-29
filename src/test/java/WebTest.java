import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public abstract class WebTest {
    protected WebDriver driver;

    protected final static String LOGIN = "demo@firetms.com";
    protected final static String PASSWORD = "demo";

    public void initPage() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\panic\\Downloads\\chromedriver89\\chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://tryfiretms.com/web/login");
        driver.manage().window().maximize();
    }
}
