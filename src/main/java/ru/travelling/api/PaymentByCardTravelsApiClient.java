package ru.travelling.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import ru.travelling.constans.Endpoints;
import ru.travelling.data.Card;

import static io.restassured.RestAssured.given;

public class PaymentByCardTravelsApiClient {

    static RequestSpecification getBaseReqSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(Endpoints.BASE_URL)
                .build();
    }

    public ValidatableResponse payByCard(Card card) {
        return given()
                .spec(getBaseReqSpec())
                .body(card)
                .when()
                .post(Endpoints.BASE_URL + Endpoints.PAY_PATH)
                .then();
    }

    public ValidatableResponse creditByCard(Card card) {
        return given()
                .spec(getBaseReqSpec())
                .body(card)
                .when()
                .post(Endpoints.BASE_URL + Endpoints.CREDIT_PATH)
                .then();
    }
}
