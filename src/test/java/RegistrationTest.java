import model.Data;
import pageobject.RegisterPage;
import steps.UserLogin;
import steps.UserSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static model.Data.REGISTER_URL;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {

    private WebDriver driver;
    private boolean skipUserDelete = false;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver();
        driver.get(REGISTER_URL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @After
    public void deleteUser() {
        if (!skipUserDelete) {
            UserSteps userSteps = new UserSteps();
            UserLogin userLogin = new UserLogin(Data.EMAIL, Data.VALID_PASSWORD);
            userSteps.userDeleteAfterLogin(userLogin);
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка возможности регистрации пользователя с валидными данными")
    public void successfulRegistrationWithValidData() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registration(Data.NAME, Data.EMAIL, Data.VALID_PASSWORD);
        registerPage.clickRegistrationButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        UserLogin userLoginRequest = new UserLogin(Data.EMAIL, Data.VALID_PASSWORD);
        UserSteps userSteps = new UserSteps();
        userSteps.userLogin(userLoginRequest)
                .assertThat().body("success", equalTo(true))
                .and()
                .statusCode(200);
    }

    @Test
    @DisplayName("Ошибка регистрации")
    @Description("Проверка ошибки при попытке регистрации пользователя с паролем менее 6 символов")
    public void failedRegistrationWithPasswordLessThen6Symbols() {
        skipUserDelete = true;
        driver.get(Data.REGISTER_URL);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registration(Data.NAME, Data.EMAIL, Data.WRONG_PASSWORD);
        assertTrue(registerPage.wrongPasswordTextIsDisplayed());
    }
}
