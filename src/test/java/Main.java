import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get("http://automationpractice.com/index.php");

        driver.findElement(By.cssSelector(".login")).click();

        driver.findElement(By.name("email_create")).sendKeys("qwe+66646541@qwe.com");

        driver.findElement(By.cssSelector("button.exclusive")).click();

        driver.findElement(By.id("id_gender1")).click();

        driver.findElement(By.id("customer_firstname")).sendKeys("Ime");

        driver.findElement(By.id("customer_lastname")).sendKeys("Prezime");

        driver.findElement(By.id("passwd")).sendKeys("TestPass123");

        Select days = new Select(driver.findElement(By.id("days")));
        days.selectByValue("1");

        Select months = new Select(driver.findElement(By.id("months")));
        months.selectByValue("1");

        Select years = new Select(driver.findElement(By.id("years")));
        years.selectByValue("2000");

        driver.findElement(By.id("newsletter")).click();

        driver.findElement(By.id("optin")).click();

        driver.findElement(By.id("company")).sendKeys("Company");

        driver.findElement(By.id("address1")).sendKeys("Company 123");

        driver.findElement(By.id("city")).sendKeys("Beograd");

        Select state = new Select(driver.findElement(By.id("id_state")));
        state.selectByVisibleText("Florida");

        driver.findElement(By.id("postcode")).sendKeys("11111");

        driver.findElement(By.id("phone_mobile")).sendKeys("32132132132");

        driver.findElement(By.id("alias")).clear();
        driver.findElement(By.id("alias")).sendKeys("Address 1");

        driver.findElement(By.id("submitAccount")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"http://automationpractice.com/index.php?controller=my-account");
        Assert.assertEquals(driver.findElement(By.cssSelector("a.account>span")).getText(), "Ime Prezime");

        driver.findElement(By.cssSelector(".logout")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }
}