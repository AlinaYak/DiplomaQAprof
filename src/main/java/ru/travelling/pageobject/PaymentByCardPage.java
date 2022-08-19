package ru.travelling.pageobject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.travelling.data.Card;

import static com.codeborne.selenide.Selenide.page;

public class PaymentByCardPage {
    @FindBy(how = How.XPATH, using = ".//span[text()='Номер карты']/following-sibling::span/input")
    private SelenideElement numberInput;

    @FindBy(how = How.XPATH, using = ".//span[text()='Номер карты']/following-sibling::span/input/parent::span/following-sibling::span[@class='input__sub']")
    private SelenideElement numberSub;

    @FindBy(how = How.XPATH, using = ".//span[text()='Месяц']/following-sibling::span/input")
    private SelenideElement monthInput;

    @FindBy(how = How.XPATH, using = ".//span[text()='Месяц']/following-sibling::span/input/parent::span/following-sibling::span[@class='input__sub']")
    private SelenideElement monthSub;

    @FindBy(how = How.XPATH, using = ".//span[text()='Год']/following-sibling::span/input")
    private SelenideElement yearInput;

    @FindBy(how = How.XPATH, using = ".//span[text()='Год']/following-sibling::span/input/parent::span/following-sibling::span[@class='input__sub']")
    private SelenideElement yearSub;

    @FindBy(how = How.XPATH, using = ".//span[text()='Владелец']/following-sibling::span/input")
    private SelenideElement nameInput;

    @FindBy(how = How.XPATH, using = ".//span[text()='Владелец']/following-sibling::span/input/parent::span/following-sibling::span[@class='input__sub']")
    private SelenideElement nameSub;

    @FindBy(how = How.XPATH, using = ".//span[text()='CVC/CVV']/following-sibling::span/input")
    private SelenideElement cvcInput;

    @FindBy(how = How.XPATH, using = ".//span[text()='CVC/CVV']/following-sibling::span/input/parent::span/following-sibling::span[@class='input__sub']")
    private SelenideElement cvcSub;

    @FindBy(how = How.XPATH, using = ".//span[text()='Продолжить']")
    private SelenideElement continueButton;

    public PaymentByCardPage fillCardDetails(Card card) {
        numberInput.sendKeys(card.getNumber());
        monthInput.sendKeys(card.getMonth());
        yearInput.sendKeys(card.getYear());
        nameInput.sendKeys(card.getHolder());
        cvcInput.sendKeys(card.getCvc());
        return page(PaymentByCardPage.class);
    }

    public TourOfferPage continueButtonClick() {
        continueButton.click();
        return page(TourOfferPage.class);
    }


    public String getErrorNumber() {
        return numberSub.getText();
    }

    public String getErrorMonth() {
        return monthSub.getText();
    }

    public String getErrorYear() {
        return yearSub.getText();
    }

    public String getErrorName() {
        return nameSub.getText();
    }

    public String getErrorCvc() {
        return cvcSub.getText();
    }
}
