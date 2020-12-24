package project3_martinez;

public class card {
     String cardValue;
     String cardSuit;

    public card(String cardValue, String cardSuite) {
        this.cardValue = cardValue;
        this.cardSuit = cardSuite;
    }
    
    public String getCardValue() {
        return cardValue;
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }

    public String getCardSuit() {
        return cardSuit;
    }

    public void setCardSuit(String cardSuit) {
        this.cardSuit = cardSuit;
    }
    
    @Override
    public String toString() {
        return  cardValue +" of " + cardSuit + "s";
    }
    
}
