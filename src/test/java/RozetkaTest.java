import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class RozetkaTest {

    public static URL url;
    public static DesiredCapabilities capabilities;
    public static AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        url = new URL(URL_STRING);

        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.APP, new File("app/rozetka_4.5.1.apk").getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability("appPackage", "ua.com.rozetka.shop");
        capabilities.setCapability("appActivity", "ua.com.rozetka.shop.ui.InitActivity");
        driver = new AndroidDriver<MobileElement>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
    }

    @Test
    public void rozetkaTest() throws InterruptedException {
        Thread.sleep(5000);
        Assert.assertTrue(driver.findElementById("ua.com.rozetka.shop:id/main_tv_to_catalog").isDisplayed());
        driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]").click();
        driver.findElementById("ua.com.rozetka.shop:id/view_loadable_image_iv_image").click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElementById("ua.com.rozetka.shop:id/empty_cart_tv_title").getText(), "Корзина пуста");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }



}
