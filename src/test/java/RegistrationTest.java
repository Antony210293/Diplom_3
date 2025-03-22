import Model.Data;
import PageObject.RegisterPage;
import Steps.UserLogin;
import Steps.UserSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static Model.Data.loginURL;
import static Model.Data.registerURL;
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
        driver.get(registerURL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @After
    public void deleteUser() {
        if (!skipUserDelete) {
            UserSteps userSteps = new UserSteps();
            UserLogin userLogin = new UserLogin(Data.email, Data.valid_password);
            userSteps.userDeleteAfterLogin(userLogin);
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка возможности регистрации пользователя с валидными данными")
    public void SuccessfulRegistrationWithValidData() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registration(Data.name, Data.email, Data.valid_password);
        registerPage.clickRegistrationButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        UserLogin userLoginRequest = new UserLogin(Data.email, Data.valid_password);
        UserSteps userSteps = new UserSteps();
        userSteps.userLogin(userLoginRequest)
                .assertThat().body("success", equalTo(true))
                .and()
                .statusCode(200);
    }

    @Test
    @DisplayName("Ошибка регистрации")
    @Description("Проверка ошибки при попытке регистрации пользователя с паролем менее 6 символов")
    public void FailedRegistrationWithPasswordLessThen6Symbols() {
        skipUserDelete = true;
        driver.get(Data.registerURL);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registration(Data.name, Data.email, Data.wrong_password);
        assertTrue(registerPage.wrongPasswordTextIsDisplayed());
    }
}
