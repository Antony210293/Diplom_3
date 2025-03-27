package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {

    private WebDriver driver;

    private By loginButton = By.xpath(".//a[text()='Войти']");
    public ForgotPasswordPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Нажатие на кнопку войти")
    public void loginButtonClick () {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
    }

}
