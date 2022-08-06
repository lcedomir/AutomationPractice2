import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestExample {
    WebDriver driver;

//    public String WAIT = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WAIT");
//    public String ENV = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("ENV");
//    public String BROWSER = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("BROWSER");

    @BeforeMethod
    @Parameters({"WAIT","ENV","BROWSER"})
    public void setup(String WAIT, String ENV, String BROWSER) {
        if (BROWSER.equalsIgnoreCase("CHROME")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (BROWSER.equalsIgnoreCase("FIREFOX")) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(WAIT), TimeUnit.SECONDS);

        if (ENV.equalsIgnoreCase("QA")) {
            driver.get("https://www.saucedemo.com/");
        } else if (ENV.equalsIgnoreCase("STG")) {
            driver.get("https://www.saucedemo.com/");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Parameters({"username", "password", "errorMessage", "hi"})
    public void login(String username, String password, String errorMessage, @Optional("Optional parameter") String hi) {
        driver.findElement(By.cssSelector("#user-name")).sendKeys(username);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#login-button")).click();

        if (errorMessage != "") {
            Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(), errorMessage);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        }

        System.out.println(hi);
    }
}