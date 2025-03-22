import Model.Data;
import PageObject.ForgotPasswordPage;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.RegisterPage;
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

public class LoginTest {

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
    @DisplayName("Вход на главной странице по кнопке Войти в аккаунт")
    @Description("Проверка возможности входа в аккаунт после нажатия на кнопку Войти в аккаунт на главной странице")
    public void LoginMainPageLoginButton() {
        driver.get(Data.mainURL);
        MainPage mainPage = new MainPage(driver);
        mainPage.loginButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.loginButtonIsDisplayed());
        loginPage.login(Data.email, Data.valid_password);
        assertTrue(mainPage.createOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Вход на главной странице по кнопке Личный кабинет")
    @Description("Проверка возможности входа в аккаунт после нажатия на кнопку Личный кабинет на главной странице")
    public void LoginMainPageProfileButton() {
        driver.get(Data.mainURL);
        MainPage mainPage = new MainPage(driver);
        mainPage.profileButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.loginButtonIsDisplayed());
        loginPage.login(Data.email, Data.valid_password);
        assertTrue(mainPage.createOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Вход со страницы регистрации")
    @Description("Проверка возможности входа в аккаунт после нажатия на кнопку Войти на странице регистрации")
    public void LoginRegisterPageLoginButton() {
        driver.get(Data.registerURL);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.loginButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.loginButtonIsDisplayed());
        loginPage.login(Data.email, Data.valid_password);
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.createOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Вход со страницы восстановления пароля")
    @Description("Проверка возможности входа в аккаунт после нажатия на кнопку Войти на странице восстановления пароля")
    public void LoginForgotPasswordPageLoginButton() {
        driver.get(Data.forgotPasswordURL);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.loginButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.loginButtonIsDisplayed());
        loginPage.login(Data.email, Data.valid_password);
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.createOrderButtonIsDisplayed());
    }
}
