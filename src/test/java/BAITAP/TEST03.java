package BAITAP;


import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/* “Verify that you cannot add more product in cart than the product available in store”

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile

4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed

"The requested quantity for "Sony Xperia" is not available.

5. Verify the error message

6. Then click on �EMPTY CART� link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.

7. Verify cart is empty

 */
public class TEST03 {




    @Test
    public static void Testcase03(){
        // Khởi tạo WebDriver
        WebDriver driver =  driverFactory.getChromeDriver();
        try {
            // TC03: Verify that you cannot add more product in cart than the product available in store
            // Step 1: Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // Step 2: Click on "MOBILE" menu
            WebElement mobileMenu = driver.findElement(By.xpath("//a[normalize-space()='Mobile']"));
            mobileMenu.click();

            // Step 3: Click on "ADD TO CART" for Sony Xperia mobile
            WebElement sonyXperiaAddToCart = driver.findElement(By.xpath("//li[2]//div[1]//div[3]//button[1]//span[1]//span[1]"));
            sonyXperiaAddToCart.click();

            // Step 4: Change "QTY" value to 1000 and click "UPDATE" button. Verify the error message
            WebElement qtyInput = driver.findElement(By.xpath("//input[@title='Qty']"));
            qtyInput.clear();
            qtyInput.sendKeys("1000");
            WebElement updateButton = driver.findElement(By.xpath("//button[@title='Update']"));
            updateButton.click();
            WebElement errorMessage = driver.findElement(By.xpath("//li[@class='error-msg']//ul//li"));
            //Assert.assertTrue(errorMessage.isDisplayed());

            // Step 5: Verify the error message
            Assert.assertEquals(errorMessage.getText(),"The requested quantity for \"Sony Xperia\" is not available.");

            // Step 6: Click on "EMPTY CART" link
            WebElement emptyCartLink = driver.findElement(By.xpath("//span[contains(text(),'Empty Cart')]"));
            emptyCartLink.click();
            WebElement emptyCartMessage = driver.findElement(By.xpath("//h1[normalize-space()='Shopping Cart is Empty']"));
            //Assert.assertTrue(emptyCartMessage.isDisplayed());

            // Step 7: Verify cart is empty
            Assert.assertEquals(emptyCartMessage.getText(),"SHOPPING CART IS EMPTY");
            //6. screenshot
            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("C:\\KI 5\\SWT301\\selenium-webdriver-java\\screenshot3.png"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        } finally {
            // Đóng trình duyệt
            driver.quit();
        }
    }
}
