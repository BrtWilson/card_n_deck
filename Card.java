package Card_n_Deck;

import java.util.Map;
import java.io.Serializable;

public class Card implements Serializable{
    // Map of attributes to data
    String card_type;
    String name;
    Map<String, String> attributes;

    public Card(String card_type, String name, Map<String, String> attributes) {
        this.card_type = card_type;
        this.name = name;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Card Type :'" + card_type + '\'' +
                "\nName :'" + name + "'\n" +
                attributes_string() +
                '}';
    }

    private String attributes_string() {
        return attributes.toString();
    }
}
