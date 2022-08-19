package ru.travelling.api;

import io.restassured.response.ValidatableResponse;
import ru.travelling.constans.Endpoints;
import ru.travelling.data.Card;

import static io.restassured.RestAssured.given;

public class PaymentByCardTravelsApiClient extends TravelsApiClient {

    public ValidatableResponse payByCard(Card card) {
        return given()
                .spec(getBaseReqSpec())
                .body(card)
                .when()
                .post(Endpoints.BASE_URL + Endpoints.PAY_URL)
                .then();
    }

    public ValidatableResponse creditByCard(Card card) {
        return given()
                .spec(getBaseReqSpec())
                .body(card)
                .when()
                .post(Endpoints.BASE_URL + Endpoints.CREDIT_URL)
                .then();
    }
}
