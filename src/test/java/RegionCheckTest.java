import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;


/**
 * Created by Иванка on 23.10.2017.
 */
public class RegionCheckTest {
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");

        driver = new ChromeDriver();
        baseUrl = "http://www.sberbank.ru/ru/person/";

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void test() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.xpath("(//A[@class='kit-link kit-link_color_black region-list__toggler'])")).click();
        WebElement webElement1 = driver.findElement(By.xpath("//a[@class='kit-link kit-link_color_black region-list__link' and text()='Нижегородская область']"));
        (new WebDriverWait(driver, 50)).until(ExpectedConditions.visibilityOf(webElement1));
        webElement1.click();

        System.out.println("Result:     " + driver.findElement(By.xpath("//span[@class='region-list__name']")).getText());
        assertEquals("Нижегородская область", driver.findElement(By.xpath("//span[@class='region-list__name']")).getText());

        WebElement webElement = driver.findElement(By.xpath("//div[@class='social__wrapper']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);

        List<WebElement> socialIconsList = driver.findElements(By.xpath("//span[contains(@class, 'social__icon')]"));
        assertEquals(6, socialIconsList.size());
    }

    @After
    public void tearDown() {
        driver.quit();
    }



}
