import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
public class SeleniumExample {

    private static WebDriver driver;

    @BeforeAll
    public static void setDriver() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver");
        System.out.println("Getting webdriver property");
        System.getProperty("webdriver.chrome.driver");
    }

    @BeforeClass
    public static void openBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver");
        System.out.println("Getting webdriver property");
        System.getProperty("webdriver.chrome.driver");
        ChromeOptions options = new ChromeOptions();
        if(System.getProperty("webdriver.chrome.driver") != null) {
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }
        else if(System.getProperty("phantomjs_binary_path") != null)
//            driver = new PhantomJSDriver();
            System.out.println("blank");
        else
            throw new RuntimeException("Unknown web driver specified.");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }

    @Test()
    public void browserInitTest() {
        driver.get("http://www.google.com/");

        Assert.assertEquals(driver.getTitle(),"Google");
    }

    @Test()
    public void seleniumWebsiteTest() {
        driver.get("https://www.selenium.dev/");

        Assert.assertEquals(driver.getTitle(),"Selenium");
    }
}
