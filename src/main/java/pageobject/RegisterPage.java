package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    // PageObject для страницы регистрации
    private WebDriver driver;
    // Имя
    private By nameField = By.xpath("(//*[contains(@class, 'input pr-6 pl-6')]/input)[1]");
    // Email
    private By emailField = By.xpath("(//*[contains(@class, 'input pr-6 pl-6')]/input)[2]");
    // Пароль
    private By passwordField = By.xpath(".//input[@type='password']");
    // Кнопка Зарегистрироваться
    private By registrButton = By.xpath(".//button[text()='Зарегистрироваться']");
    // Ошибка Некорректный пароль
    private By wrongPasswordText = By.xpath(".//p[text()='Некорректный пароль']");
    //Кнопка Войти
    private By loginButton = By.xpath(".//a[@href='/login']");
    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Регистрация пользователя")
    public void registration(String name, String email, String password) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(registrButton));
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(registrButton).click();
    }

    @Step("Проверка отображения ошибки о неправильном пароле")
    public boolean wrongPasswordTextIsDisplayed() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(wrongPasswordText));
        try {
            return driver.findElement(wrongPasswordText).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Нажатие на кнопку Войти")
    public void loginButtonClick () {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step("Нажать на кнопку регистрации")
    public void clickRegistrationButton() {
        driver.findElement(registrButton).click();
    }
}
