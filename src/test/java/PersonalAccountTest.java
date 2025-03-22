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

public class PersonalAccountTest {

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
    @DisplayName("Переход в личный кабинет")
    @Description("Проверка возможности входа в личный кабинет после нажатия на кнопку Личный кабинет на главной странице")
    public void MoveToProfileFromMain() {
        driver.get(Data.loginURL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Data.email, Data.valid_password);
        MainPage mainPage = new MainPage(driver);
        mainPage.profileButtonClick();
        PersonalPage personalPage = new PersonalPage(driver);
        assertTrue(personalPage.exitButtonIsDisplayed());
    }


    @Test
    @DisplayName("Переход в конструктор через кнопку Конструктор")
    @Description("Проверка возможности перехода к конструктору после нажатия на кнопку Конструктор в профиле пользователя")
    public void SwitchingToConstructorAfterConstructorButtonClick() {
        driver.get(Data.loginURL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Data.email, Data.valid_password);
        MainPage mainPage = new MainPage(driver);
        mainPage.profileButtonClick();
        PersonalPage personalPage = new PersonalPage(driver);
        personalPage.constructorButtonClick();
        assertTrue(mainPage.profileButtonIsDisplayed());
    }

    @Test
    @DisplayName("Переход в конструктор через Логотип")
    @Description("Проверка возможности перехода к конструктору после нажатия на Логотип в профиле пользователя")
    public void SwitchingToConstructorAfterLogoClick() {
        driver.get(Data.loginURL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Data.email, Data.valid_password);
        MainPage mainPage = new MainPage(driver);
        mainPage.profileButtonClick();
        PersonalPage personalPage = new PersonalPage(driver);
        personalPage.logoClick();
        assertTrue(mainPage.profileButtonIsDisplayed());
    }

}
