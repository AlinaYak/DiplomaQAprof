package ru.netology.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.travelling.api.PaymentByCardTravelsApiClient;
import ru.travelling.db.DblUtils;
import ru.travelling.data.Card;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTest {
    private PaymentByCardTravelsApiClient paymentByCardTravelsApiClient;
    private Card card;

    @Test
    @DisplayName("При оплате по карте со статусом APPROVED")
    public void payByDebitCardSuccess() {
        paymentByCardTravelsApiClient = new PaymentByCardTravelsApiClient();
        card = Card.getApprovedCard();

        paymentByCardTravelsApiClient.payByCard(card).assertThat().statusCode(200);

        var statusForPayment = DblUtils.getStatusForPayment();
        assertEquals("APPROVED", statusForPayment);
    }

    @Test
    @DisplayName("При оплате по карте со статусом DECLINED")
    public void payByDebitCardNotEnoughMoneyFail() {
        paymentByCardTravelsApiClient = new PaymentByCardTravelsApiClient();
        card = Card.getDeclinedCard();
        paymentByCardTravelsApiClient.payByCard(card).assertThat().statusCode(200);


        var statusForPayment = DblUtils.getStatusForPayment();
        assertEquals("DECLINED", statusForPayment);
    }

    @Test
    @DisplayName("При отправке заявки на кредит по карте со статусом APPROVED")
    public void payByCreditCardSuccess() {
        paymentByCardTravelsApiClient = new PaymentByCardTravelsApiClient();
        card = Card.getApprovedCard();
        paymentByCardTravelsApiClient.creditByCard(card).assertThat().statusCode(200);


        var statusForPayment = DblUtils.getStatusForCredit();
        assertEquals("APPROVED", statusForPayment);
    }

    @Test
    @DisplayName("При отправке заявки на кредит по карте со статусом DECLINED")
    public void payByCreditCardNotEnoughMoneyFail() {
        paymentByCardTravelsApiClient = new PaymentByCardTravelsApiClient();
        card = Card.getDeclinedCard();
        paymentByCardTravelsApiClient.creditByCard(card).assertThat().statusCode(200);

        var statusForPayment = DblUtils.getStatusForCredit();
        assertEquals("DECLINED", statusForPayment);
    }
}
