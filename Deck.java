package Card_n_Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.io.Serializable;

public class Deck implements Serializable{
    // list of cards
    private List<Card> contents;

    public Deck() {
        contents = new ArrayList<>();
    }

    public List<Card> getContents() { return contents; }

    public void setContents(List<Card> contents) { this.contents = contents; }

    public Deck(List<Card> contents) {
        this.contents = contents;
    }

    public static Deck convert_to_deck(List<List<String>> card_set) {
        List<String> card_template = card_set.get(0);
        String card_type = card_template.get(0);
        List<Card> card_list = new ArrayList<>();
        for(int i = 1; i < card_set.size(); i++) {
            List<String> card_str = card_set.get(i);
            String card_name = card_str.get(0);
            HashMap<String, String> attributes = new HashMap<>();
            //HashMap<String, String[]> multi_attributes = new HashMap<>();
            for (int j = 1; j < card_template.size(); j++) {
                if (j < card_str.size()) {
                    attributes.put(card_template.get(j), card_str.get(j));
                }
            }
            card_list.add(new Card(card_type, card_name, attributes ));
        }
        return new Deck(card_list);
    }

    public static Deck combine(List<Deck> decks) {
        List<Card> deckl = new ArrayList<>();
        for (Deck deck_ : decks) {
            deckl.addAll(deck_.contents);
        }
        return new Deck(deckl);
    }

    public boolean isEmpty() { return (contents.size() == 0); }

    public void shuffle() {
        Collections.shuffle(contents);
    }

    public Card draw() {
        Card dc = contents.get(0);
        contents.remove(dc);
        return dc;
    }

    public void insert(Card card_i) {
        contents.add(card_i);
        shuffle();
    }

    public void top(Card card_t) {
        contents.add(0, card_t);
    }

    public Card peek() {
        if (contents.size() == 0) return null;
        return contents.get(0);
    }

    public boolean remove(Card card_r) {
        return (contents.remove(card_r));
    }

    // retrieves an array of cards on the top of the deck
    public Card[] peek(int p) {
        Card[] peekc = new Card[p];
        if (p > contents.size()) p = contents.size();
        for(int i = 0; i < p; i++) {
            peekc[i] = contents.get(i);
        }
        return peekc;
    }

    //** Gets a card matching a given type
    public Card getNextOfType(String cardType) {
        int i = 0;
        Card tmp_c = contents.get(i);
        while (!tmp_c.card_type.equals(cardType) && i < contents.size()) i++;
        if (tmp_c.card_type.equals(cardType)) {
            contents.remove(tmp_c);
            return tmp_c;
        } else return null;
    }

    public Card getNextofType(String cardType, Boolean remove) {
        Card retrieved = getNextOfType(cardType);
        if (remove) {
            contents.remove(retrieved);
        }
        return retrieved;
    }

    //** Gets a card with a matching attribute value
    public Card getNextOfAttribute(String att_match, String desired) {
        if (att_match.equals("Name")) {
            for (Card tmp_c : contents) {
                if (tmp_c.name.equals(att_match)) {
                    return tmp_c;
                }
            }
        }
        else {
            for (Card tmp_c : contents) {
                String attribute = tmp_c.attributes.get(att_match);
                if (attribute != null) {
                    if (attribute.equals(desired)) {
                        contents.remove(tmp_c);
                        return tmp_c;
                    }
                }
            }
        }
        return null;
    }

    public Card getNextofAttribute(String att_match, String desired, Boolean remove) {
        Card retrieved = getNextOfAttribute(att_match, desired);
        if (remove) {
            contents.remove(retrieved);
        }
        return retrieved;
    }

    //** Gets a card with an integer attribute below or equal to a provided cap
    public Card getNext_StatCap(String att_match, int cap) {
        for (Card tmp_c : contents) {
            String attribute = tmp_c.attributes.get(att_match);
            if (attribute != null) {
                int stat = Integer.getInteger(attribute);
                if (stat <= cap){
                    contents.remove(tmp_c);
                    return tmp_c;
                }
            }
        }
        return null;
    }

    public Card getNext_StatCap(String att_match, int cap, Boolean remove) {
        Card retrieved = getNext_StatCap(att_match, cap);
        if (remove) {
            contents.remove(retrieved);
        }
        return retrieved;
    }

    @Override
    public String toString() {
        return "aFJNKAJNKER.Deck{" +
                "contents=" + contents +
                '}';
    }
}
