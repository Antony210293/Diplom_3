package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;
    //Кнопка Войти в аккаунт
    private By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    // Кнопка Личный кабинет
    private By profileButton = By.xpath(".//a[@href='/account']");
    // Кнопка Оформить заказ
    private By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    // раздел Булки
    private By sectionBun = By.xpath(".//span[text()='Булки']");
    // раздел Соусы
    private By sectionSauce = By.xpath(".//span[text()='Соусы']");
    // раздел Начинки
    private By sectionTopping = By.xpath(".//span[text()='Начинки']");
    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Нажатие кнопки войти для авторизации пользователя")
    public void loginButtonClick () {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие на кнопку Личный кабинет")
    public void profileButtonClick () {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(profileButton));
        driver.findElement(profileButton).click();
    }

    @Step("Нажатие на раздел Булки")
    public void sectionBunClick () {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(sectionBun));
        driver.findElement(sectionBun).click();
    }

    @Step("Нажатие на раздел Соусы")
    public void sectionSauceClick () {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(sectionSauce));
        driver.findElement(sectionSauce).click();
    }

    @Step("Нажатие на раздел Начинки")
    public void sectionToppingClick () {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(sectionTopping));
        driver.findElement(sectionTopping).click();
    }

    @Step("Проверка отображения кнопки перехода в Личный кабинет")
    public boolean profileButtonIsDisplayed() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(profileButton));
        try {
            return driver.findElement(profileButton).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Проверка отображения кнопки Оформить заказ")
    public boolean createOrderButtonIsDisplayed() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
        try {
            return driver.findElement(createOrderButton).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Возвращает выбранный элемент в конструкторе")
    public String returnSelectedSection(String sectionName) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.textToBe(By.xpath(".//div[contains(@class, 'current')]/span"), sectionName));
        return driver.findElement(By.xpath(".//div[contains(@class, 'current')]/span")).getText();
    }
}
