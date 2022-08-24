package ru.travelling.data;

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

    public static Card getEmptyCard() {
        Card card = new Card("", "", "", "", "");
        return card;
    }

    public static Card getInvalidFieldsCard() {
        Card card = new Card("55555", "2", "2", "888", "11");
        return card;
    }
}
