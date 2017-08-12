import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class iPadButtonLayoutBase {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.shopkeepapp.com/ipad-layout");

        Thread.sleep(2000);

        WebElement storeName = driver.findElement(By.id("store_name"));
        storeName.sendKeys("test-ll-1");
        WebElement userName = driver.findElement(By.id("login"));
        userName.sendKeys("linglu_liu@shopkeep.com");
        WebElement pwd = driver.findElement(By.id("password"));
        pwd.sendKeys("LLpassword123");
        driver.findElement(By.id("submit")).click();

        (new WebDriverWait(driver, 20)).until(ExpectedConditions.titleContains("ShopKeep BackOffice"));




        driver.quit();
    }

}