package ru.netology.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.travelling.data.Card;
import ru.travelling.pageobject.PaymentByCardPage;
import ru.travelling.pageobject.TourOfferPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.codeborne.selenide.Selenide.open;

public class UiTest {

    @Test
    @DisplayName("Успешная покупка при оплате дебетовой картой")
    public void buyingWithDebitCardSuccess() {
        PaymentByCardPage paymentByCardPage = open(TourOfferPage.URL, TourOfferPage.class)
                .buyTour()
                .fillCardDetails(Card.getApprovedCard())
                .continueButtonClick();
        assertEquals("Успешно", paymentByCardPage.getNotificationTitle(0), "Сообщения об успехе нет");
        assertEquals("Операция одобрена Банком.", paymentByCardPage.getNotificationContent(0), "Сообщения об успехе нет");
        assertEquals(1, paymentByCardPage.getNotificationCount(), "Сообщение не одно");
    }

    @Test
    @DisplayName("Отказ в покупке билета при оплате дебетовой картой")
    public void buyingWithDebitCardNotEnoughMoneyFail() {
        PaymentByCardPage paymentByCardPage = open(TourOfferPage.URL, TourOfferPage.class)
                .buyTour()
                .fillCardDetails(Card.getDeclinedCard())
                .continueButtonClick();
        assertEquals("Ошибка", paymentByCardPage.getNotificationTitle(0), "Сообщения об ошибке неверно");
        assertEquals("Ошибка! Банк отказал в проведении операции.", paymentByCardPage.getNotificationContent(0), "Сообщения об ошибке нет");
        assertEquals(1, paymentByCardPage.getNotificationCount(), "Сообщение не одно");
    }

    @Test
    @DisplayName("Успешная покупка при оплате в кредит картой")
    public void buyingWithCreditCardSuccess() {
        PaymentByCardPage paymentByCardPage = open(TourOfferPage.URL, TourOfferPage.class)
                .buyOnCreditTour()
                .fillCardDetails(Card.getApprovedCard())
                .continueButtonClick();
        assertEquals("Успешно", paymentByCardPage.getNotificationTitle(0), "Сообщения об успехе нет");
        assertEquals("Операция одобрена Банком.", paymentByCardPage.getNotificationContent(0), "Сообщения об успехе нет");
        assertEquals(1, paymentByCardPage.getNotificationCount(), "Сообщение не одно");
    }

    @Test
    @DisplayName("Отказ в покупке в кредит")
    public void buyingWithCreditCardNotEnoughMoneyFail() {
        PaymentByCardPage paymentByCardPage = open(TourOfferPage.URL, TourOfferPage.class)
                .buyOnCreditTour()
                .fillCardDetails(Card.getDeclinedCard())
                .continueButtonClick();
        assertEquals("Ошибка", paymentByCardPage.getNotificationTitle(1), "Сообщения об ошибке нет");
        assertEquals("Ошибка! Банк отказал в проведении операции.", paymentByCardPage.getNotificationContent(1), "Сообщения об ошибке нет");
        assertEquals(1, paymentByCardPage.getNotificationCount(), "Сообщение не одно");
    }

    @Test
    @DisplayName("Все поля пустые при оплате дебетовой картой")
    public void buyingWithDebitCardEmptyFieldsFail() {
        PaymentByCardPage paymentByCardPage = open(TourOfferPage.URL, TourOfferPage.class)
                .buyTour()
                .fillCardDetails(Card.getEmptyCard());
        paymentByCardPage.continueButtonClick();
        assertEquals("Неверный формат", paymentByCardPage.getErrorNumber(), "Сообщение об ошибке неверное");
        assertEquals("Неверный формат", paymentByCardPage.getErrorMonth(), "Сообщение об ошибке неверное");
        assertEquals("Неверный формат", paymentByCardPage.getErrorYear(), "Сообщение об ошибке неверное");
        assertEquals("Поле обязательно для заполнения", paymentByCardPage.getErrorName(), "Сообщение об ошибке неверное");
        assertEquals("Неверный формат", paymentByCardPage.getErrorCvc(), "Сообщение об ошибке неверное");
    }

    @Test
    @DisplayName("Все поля невалидные при оплате дебетовой картой")
    public void buyingWithDebitCardInvalidFieldsFail() {
        PaymentByCardPage paymentByCardPage = open(TourOfferPage.URL, TourOfferPage.class)
                .buyTour()
                .fillCardDetails(Card.getInvalidFieldsCard());
        paymentByCardPage.continueButtonClick();
        assertEquals("Неверный формат", paymentByCardPage.getErrorNumber(), "Сообщение об ошибке неверное");
        assertEquals("Неверный формат", paymentByCardPage.getErrorMonth(), "Сообщение об ошибке неверное");
        assertEquals("Неверный формат", paymentByCardPage.getErrorYear(), "Сообщение об ошибке неверное");
        assertEquals("Поле обязательно для заполнения", paymentByCardPage.getErrorName(), "Сообщение об ошибке неверное");
        assertEquals("Неверный формат", paymentByCardPage.getErrorCvc(), "Сообщение об ошибке неверное");
    }

    @Test
    @DisplayName("Все поля пустые при оплате кредитной картой")
    public void buyingWithCreditCardEmptyFieldsFail() {
        PaymentByCardPage paymentByCardPage = open(TourOfferPage.URL, TourOfferPage.class)
                .buyOnCreditTour()
                .fillCardDetails(Card.getEmptyCard());
        paymentByCardPage.continueButtonClick();
        assertEquals("Неверный формат", paymentByCardPage.getErrorNumber(), "Сообщение об ошибке неверное");
        assertEquals("Неверный формат", paymentByCardPage.getErrorMonth(), "Сообщение об ошибке неверное");
        assertEquals("Неверный формат", paymentByCardPage.getErrorYear(), "Сообщение об ошибке неверное");
        assertEquals("Поле обязательно для заполнения", paymentByCardPage.getErrorName(), "Сообщение об ошибке неверное");
        assertEquals("Неверный формат", paymentByCardPage.getErrorCvc(), "Сообщение об ошибке неверное");
    }

    @Test
    @DisplayName("Все поля невалидные при оплате кредитной картой")
    public void buyingWithCreditCardInvalidFieldsFail() {
        PaymentByCardPage paymentByCardPage = open(TourOfferPage.URL, TourOfferPage.class)
                .buyOnCreditTour()
                .fillCardDetails(Card.getInvalidFieldsCard());
        paymentByCardPage.continueButtonClick();
        assertEquals("Неверный формат", paymentByCardPage.getErrorNumber(), "Сообщение об ошибке неверное");
        assertEquals("Неверный формат", paymentByCardPage.getErrorMonth(), "Сообщение об ошибке неверное");
        assertEquals("Неверный формат", paymentByCardPage.getErrorYear(), "Сообщение об ошибке неверное");
        assertEquals("Поле обязательно для заполнения", paymentByCardPage.getErrorName(), "Сообщение об ошибке неверное");
        assertEquals("Неверный формат", paymentByCardPage.getErrorCvc(), "Сообщение об ошибке неверное");
    }
}
