package ru.travelling.pageobject;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.page;

import java.time.Duration;

public class TourOfferPage {

    public static final String URL = System.getProperty("app.url");

    @FindBy(how = How.XPATH, using = ".//span[text()='Купить']")
    private SelenideElement buyButton;

    @FindBy(how = How.XPATH, using = ".//span[text()='Купить в кредит']")
    private SelenideElement buyOnCreditButton;

    @FindBy(how = How.XPATH, using = ".//div[contains(@class, 'notification_status')]")
    private ElementsCollection notificationCollection;

    public PaymentByCardPage buyTour() {
        buyButton.click();
        return page(PaymentByCardPage.class);
    }


    public PaymentByCardPage buyOnCreditTour() {
        buyOnCreditButton.click();
        return page(PaymentByCardPage.class);
    }


    public String getNotificationTitle(int notificationNumber) {
        return notificationCollection
                .shouldBe(CollectionCondition.sizeGreaterThan(notificationNumber - 1), Duration.ofSeconds(30))
                .get(notificationNumber).$(byXpath("./div[@class='notification__title']"))
                .innerHtml();
    }


    public String getNotificationContent(int notificationNumber) {
        return notificationCollection
                .shouldBe(CollectionCondition.sizeGreaterThan(notificationNumber - 1), Duration.ofSeconds(30))
                .get(notificationNumber)
                .$(byXpath(".//div[@class='notification__content']")).innerHtml();
    }


    public int getNotificationCount() {
        return notificationCollection.size();
    }

}
