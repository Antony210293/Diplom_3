import model.Data;
import pageobject.ForgotPasswordPage;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegisterPage;
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

public class LoginTest {

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
    @DisplayName("Вход на главной странице по кнопке Войти в аккаунт")
    @Description("Проверка возможности входа в аккаунт после нажатия на кнопку Войти в аккаунт на главной странице")
    public void loginMainPageLoginButton() {
        driver.get(Data.MAIN_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.loginButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.loginButtonIsDisplayed());
        loginPage.login(Data.EMAIL, Data.VALID_PASSWORD);
        assertTrue(mainPage.createOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Вход на главной странице по кнопке Личный кабинет")
    @Description("Проверка возможности входа в аккаунт после нажатия на кнопку Личный кабинет на главной странице")
    public void loginMainPageProfileButton() {
        driver.get(Data.MAIN_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.profileButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.loginButtonIsDisplayed());
        loginPage.login(Data.EMAIL, Data.VALID_PASSWORD);
        assertTrue(mainPage.createOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Вход со страницы регистрации")
    @Description("Проверка возможности входа в аккаунт после нажатия на кнопку Войти на странице регистрации")
    public void loginRegisterPageLoginButton() {
        driver.get(Data.REGISTER_URL);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.loginButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.loginButtonIsDisplayed());
        loginPage.login(Data.EMAIL, Data.VALID_PASSWORD);
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.createOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Вход со страницы восстановления пароля")
    @Description("Проверка возможности входа в аккаунт после нажатия на кнопку Войти на странице восстановления пароля")
    public void loginForgotPasswordPageLoginButton() {
        driver.get(Data.FORGOT_PASSWORD_URL);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.loginButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.loginButtonIsDisplayed());
        loginPage.login(Data.EMAIL, Data.VALID_PASSWORD);
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.createOrderButtonIsDisplayed());
    }
}
