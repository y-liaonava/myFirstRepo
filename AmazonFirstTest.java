//https://github.com/RedRoverSchool/JenkinsQA_05.git
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmazonFirstTest {

    private static WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();// customizing window dimensions
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);// waiting
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();

    }



    @Test
    public static void goodSelecting() {


        driver.get("https://www.amazon.es/");
        WebElement ccsButton = driver.findElement(By.xpath("//*[@id='sp-cc-accept']"));
        ccsButton.click();
        WebElement searchTextBox = driver.findElement(By.xpath("//*[@id = 'twotabsearchtextbox']"));
        searchTextBox.sendKeys("lego city ");
        WebElement searchButton = driver.findElement(By.xpath("//*[@id = 'nav-search-submit-button']"));
        searchButton.click();
        List<WebElement> pricesList = new ArrayList<WebElement>();
        pricesList = driver.findElements(By.xpath( "//*[@class = 'a-price-whole']"));
        for (int i = 0; i< pricesList.size(); i++){

            String arrValue = pricesList.get(i).getText();
            String changedArrValue = arrValue.replace(',', '.');
            Double arrValueWithPoint = Double.parseDouble(changedArrValue);
            if (arrValueWithPoint<30 && arrValueWithPoint>29.8){
                pricesList.get(i).click();
                break;

            }
        }
    }


}




