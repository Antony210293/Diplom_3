import model.Data;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.PersonalPage;
import steps.UserCreateAccount;
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

import static model.Data.REGISTER_URL;
import static org.junit.Assert.assertTrue;

public class LogOutTest {

    private WebDriver driver;

    @Before
    public void createUser() {
        UserSteps userSteps = new UserSteps();
        UserCreateAccount userCreateAccount = new UserCreateAccount(Data.EMAIL, Data.VALID_PASSWORD, Data.NAME);
        userSteps.userCreate(userCreateAccount);
    }

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
        UserSteps userSteps = new UserSteps();
        UserLogin userLogin= new UserLogin(Data.EMAIL, Data.VALID_PASSWORD);
        userSteps.userDeleteAfterLogin(userLogin);
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверка возможности выйти из аккаунта после нажатия на кнопку Выход в профиле пользователя")
    public void logoutAfterExitButtonClick() {
        driver.get(Data.LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Data.EMAIL, Data.VALID_PASSWORD);
        MainPage mainPage = new MainPage(driver);
        mainPage.profileButtonClick();
        PersonalPage personalPage = new PersonalPage(driver);
        personalPage.exitButtonClick();
        assertTrue(loginPage.loginButtonIsDisplayed());
    }
}
