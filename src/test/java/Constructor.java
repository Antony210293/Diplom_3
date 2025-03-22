import Model.Data;
import PageObject.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static Model.Data.registerURL;
import static org.junit.Assert.assertEquals;

public class Constructor {

    private WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver();
        driver.get(registerURL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    @Description("Проверка возможности перехода к разделу Начинки")
    public void SwitchingToSectionTopping() {
        driver.get(Data.mainURL);
        MainPage mainPage = new MainPage(driver);
        mainPage.sectionToppingClick();
        String expectedText = "Начинки";
        String actualText = mainPage.returnSelectedSection(expectedText);
        assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    @Description("Проверка возможности перехода к разделу Соусы")
    public void SwitchingToSectionSauce() {
        driver.get(Data.mainURL);
        MainPage mainPage = new MainPage(driver);
        mainPage.sectionToppingClick();
        mainPage.sectionSauceClick();
        String expectedText = "Соусы";
        String actualText = mainPage.returnSelectedSection(expectedText);
        assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    @Description("Проверка возможности перехода к разделу Булки")
    public void SwitchingToSectionBun() {
        driver.get(Data.mainURL);
        MainPage mainPage = new MainPage(driver);
        mainPage.sectionToppingClick();
        mainPage.sectionBunClick();
        String expectedText = "Булки";
        String actualText = mainPage.returnSelectedSection(expectedText);
        assertEquals(expectedText, actualText);
    }
}
