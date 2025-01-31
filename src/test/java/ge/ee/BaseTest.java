package ge.ee;

import ge.ee.utils.ConfigReader;
import ge.ee.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get("https://ee.ge");
    }

    @AfterMethod
    public void tearDown(){
        DriverManager.quit();
    }

}