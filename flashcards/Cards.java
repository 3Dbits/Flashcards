package flashcards;

import java.util.Scanner;

public class Cards {
    //private String card;
    private String definitionOfCard;

    public Cards(String definitionOfCard) {
        this.definitionOfCard = definitionOfCard;
    }

    /*
    public Cards(String card, String definitionOfCard) {
        this.card = card;
        this.definitionOfCard = definitionOfCard;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
    */

    public String getDefinitionOfCard() {
        return definitionOfCard;
    }

    public void setDefinitionOfCard(String definitionOfCard) {
        this.definitionOfCard = definitionOfCard;
    }

}
