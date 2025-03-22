import Model.Data;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.PersonalPage;
import Steps.UserCreateAccount;
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

import static Model.Data.registerURL;
import static org.junit.Assert.assertTrue;

public class LogOutTest {

    private WebDriver driver;

    @Before
    public void createUser() {
        UserSteps userSteps = new UserSteps();
        UserCreateAccount userCreateAccount = new UserCreateAccount(Data.email, Data.valid_password, Data.name);
        userSteps.userCreate(userCreateAccount);
    }

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
        UserSteps userSteps = new UserSteps();
        UserLogin userLogin= new UserLogin(Data.email, Data.valid_password);
        userSteps.userDeleteAfterLogin(userLogin);
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверка возможности выйти из аккаунта после нажатия на кнопку Выход в профиле пользователя")
    public void LogoutAfterExitButtonClick() {
        driver.get(Data.loginURL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Data.email, Data.valid_password);
        MainPage mainPage = new MainPage(driver);
        mainPage.profileButtonClick();
        PersonalPage personalPage = new PersonalPage(driver);
        personalPage.exitButtonClick();
        assertTrue(loginPage.loginButtonIsDisplayed());
    }
}
