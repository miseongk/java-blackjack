package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {

    public static Deck create() {
        List<Card> cards = new ArrayList<>();
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        return new Deck(cards);
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(new Card(symbol, type));
        }
    }

}
