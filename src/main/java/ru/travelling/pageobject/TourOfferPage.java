package ru.travelling.pageobject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class TourOfferPage {

    public static final String URL = System.getProperty("app.url");

    @FindBy(how = How.XPATH, using = ".//span[text()='Купить']")
    private SelenideElement buyButton;

    @FindBy(how = How.XPATH, using = ".//span[text()='Купить в кредит']")
    private SelenideElement buyOnCreditButton;

    public PaymentByCardPage buyTour() {
        buyButton.click();
        return page(PaymentByCardPage.class);
    }

    public PaymentByCardPage buyOnCreditTour() {
        buyOnCreditButton.click();
        return page(PaymentByCardPage.class);
    }
}
