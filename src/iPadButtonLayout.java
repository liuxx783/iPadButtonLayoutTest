import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class iPadButtonLayout {
    public static void main(String[] args) throws Exception {
     // --------------Before Test---------------
        //Initialize Chrome web driver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.shopkeepapp.com/ipad-layout");
        Thread.sleep(2000);

        //Web log in
        WebElement storeName = driver.findElement(By.id("store_name"));
        storeName.sendKeys("test-ll-1");
        WebElement userName = driver.findElement(By.id("login"));
        userName.sendKeys("linglu_liu@shopkeep.com");
        WebElement pwd = driver.findElement(By.id("password"));
        pwd.sendKeys("LLpassword123");
        driver.findElement(By.id("submit")).click();
        (new WebDriverWait(driver, 20)).until(ExpectedConditions.titleContains("ShopKeep BackOffice"));

    //--------------A Selection of Functional Test---------------
    //Test Case 1: Search item
        WebElement searchBox  = driver.findElement(By.id("item-search-input"));
        searchBox.sendKeys("Coffee");

        //To check if expected items in search result:
        List<WebElement> coffeeItem = driver.findElements(By.xpath("//div[contains(@id, 'item-list')]//span[contains(@class, 'description') and text() = 'Coffee'] "));
        List<WebElement> coffeeBeansItem = driver.findElements(By.xpath("//div[contains(@id, 'item-list')]//span[contains(@class, 'description') and text() = 'Coffee Beans'] "));
        if(coffeeItem.size() != 0 && coffeeBeansItem.size() != 0){
            System.out.println("Test case 1 is Pass: Coffee and Coffee Beans are Present");
        }else{
            System.out.println("Test case 1 is Fail: Coffee and Coffee Beans are Absent");
        }

     //Test Case2: Drag and Drop item
        WebElement coffeeButton = driver.findElement(By.xpath("//div[contains(@id, 'item-list')]//span[contains(@class, 'description') and text() = 'Coffee'] "));
        WebElement itemDropPlace = driver.findElement(By.xpath("//div[contains(@class, 'button_page')]//div[contains(@class, 'si-placeholder')][2]"));

        Actions builder = new Actions(driver);
        Action dragAndDrop = builder.clickAndHold(coffeeButton)
                .moveToElement(itemDropPlace)
                .release(itemDropPlace)
                .build();
        dragAndDrop.perform();

        Thread.sleep(3000);

        //To check if coffee button is successfully added in button page
        List<WebElement> buttonInPage = driver.findElements(By.xpath("//div[contains(@class, 'si-button color-ababab ui-draggable')]//div[contains(@class, 'button-name')]//p[text()= 'Coffee']"));
        if(coffeeItem.size() != 0 && coffeeBeansItem.size() != 0){
            System.out.println("Test case 2 is Pass: Coffee button is in page");
        }else{
            System.out.println("Test case 1 is Fail: Coffee button is not in page");
        }

    // Test Case 3: Add a new page and rename a page
        WebElement pageNameTextBox = driver.findElement(By.id("button-page-name"));
        WebElement addPageButton = driver.findElement(By.id("add-button-page"));

        pageNameTextBox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        pageNameTextBox.sendKeys("Test");
        addPageButton.click();

        //To verify if test page is added successfully
        List<WebElement> testButtonTab = driver.findElements(By.xpath("//a[contains(@class, 'page-tab')]//span[contains(@class, 'name') and text() = 'Test']"));
        if(testButtonTab.size() != 0){
            System.out.println("Test case 3 is Pass: Test button page is created successfully");
        }else{
            System.out.println("Test case 3 is Fail: Test button page is absent");
        }
        Thread.sleep(2000);

     // --------------After Test---------------
        driver.quit();
    }

}