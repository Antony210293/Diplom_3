import model.Browser;
import model.Data;
import pageobject.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class Constructor {

    private WebDriver driver;
    // для смены браузера в константе нужно поменять присвоенное значение на "FIREFOX"
    private static final String DEFAULT_BROWSER_NAME = "CHROME";
    private static final String BROWSER_NAME_ENV_VARIABLE = "BROWSER_NAME";

    @Before
    public void before() {
        String browserName = System.getenv(BROWSER_NAME_ENV_VARIABLE);
        driver = Browser.createForName(browserName != null ? browserName : DEFAULT_BROWSER_NAME);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    @Description("Проверка возможности перехода к разделу Начинки")
    public void switchingToSectionTopping() {
        driver.get(Data.MAIN_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.sectionToppingClick();
        String expectedText = "Начинки";
        String actualText = mainPage.returnSelectedSection(expectedText);
        assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    @Description("Проверка возможности перехода к разделу Соусы")
    public void switchingToSectionSauce() {
        driver.get(Data.MAIN_URL);
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
    public void switchingToSectionBun() {
        driver.get(Data.MAIN_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.sectionToppingClick();
        mainPage.sectionBunClick();
        String expectedText = "Булки";
        String actualText = mainPage.returnSelectedSection(expectedText);
        assertEquals(expectedText, actualText);
    }
}
