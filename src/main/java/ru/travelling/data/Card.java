package ru.travelling.data;

import com.github.javafaker.Faker;
import lombok.*;
import org.checkerframework.checker.units.qual.C;

import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
public class Card {
    public String number;
    public String month;
    public String year;
    public String holder;
    public String cvc;

    public static Card getApprovedCard() {
        Card card = new Card("4444 4444 4444 4441", "09", "24", "Popov Igor", "123");
        return card;
    }

    public static Card getDeclinedCard() {
        Card card = new Card("4444 4444 4444 4442", "09", "24", "Popov Igor", "123");
        return card;
    }

    public static Card getRandomCard() {
        Faker faker = new Faker();
        String number = faker.lorem().characters(16, true);
        String month = String.format("%02d", faker.date().birthday().getMonth() + 1);
        String year = String.format("%ty", faker.date().future(9999, TimeUnit.DAYS).getYear());
        String holder = faker.name().fullName();
        String cvc = faker.lorem().characters(3, true);
        Card card = new Card(number, month, year, holder, cvc);
        return card;
    }

    public static Card getEmptyCard() {
        Card card = new Card("", "", "", "", "");
        return card;
    }

    public static Card getInvalidFieldsCard() {
        Card card = new Card("55555", "2", "2", "888", "11");
        return card;
    }
}
