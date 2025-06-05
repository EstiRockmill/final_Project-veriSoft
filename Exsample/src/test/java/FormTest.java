import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.util.List;
@ExtendWith(AllureTestWatcher.class)
@Listeners(TestListener.class)
public class FormTest {
    WebDriver driver ;
    @BeforeEach
    void setUp(TestInfo testInfo) throws Exception {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_controllers.html");
    }

    @AfterEach
    void tearDown() throws Exception {
        driver.quit();
    }
    @Test
    void test() {
        driver.findElement(By.name("firstname")).sendKeys("esti");
        driver.findElement(By.name("lastname")).sendKeys("Roockmill");
        Select area= new Select(driver.findElement(By.id("continents")));
        area.selectByVisibleText("Africa");
        driver.findElement(By.id("sex-1")).click();
        driver.findElement(By.id("exp-0")).click();
        driver.findElement(By.id("datepicker")).click();
        WebElement driver1 =driver.findElement(By.className("ui-datepicker-calendar"));
        List<WebElement> table = driver1.findElements(By.tagName("tr"));
        for (WebElement tableElement : table) {
            if (tableElement.getText().equals("13")) {
                driver1.click();
                break;
            }
        }
        String url1= driver.getCurrentUrl();

        System.out.println();
        driver.findElement(By.id("submit")).submit();
        String url=driver.getCurrentUrl();
        if(url.contains("esti")&&url.contains("Roockmil")){
            System.out.println("Test passed");
        }
        else{
            System.out.println("Test failed");
        }
    }
}
