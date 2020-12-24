package project3_martinez;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Deck {

    private static Queue<card> drawDeck = new LinkedList<card>();
    private static Stack<card> discardDeck = new Stack();

    public Deck() {
        String[] cardSuit = {"Diamond", "Heart", "Club", "Spade"};
        String[] cardValues = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Ace", "Jack", "Queen", "King",};
        for (int i = 0; i < cardSuit.length; i++) {
            for (int j = 0; j < cardValues.length; j++) {
                drawDeck.add(new card(cardValues[j], cardSuit[i]));
            }
        }
    }

    public void displayDeck() {
        ArrayList<card> adeck = new ArrayList(drawDeck);
        for (int i = 0; i < drawDeck.size(); i++) {
            System.out.println(adeck.get(i));
        }
        System.out.println("Cards in drawDeck: " + adeck.size());
    }

    public void shuffleDeck() {
        Collections.shuffle((List<?>) drawDeck);
    }

    public void firstDeal(ArrayList<card> Player, ArrayList<card> Comp) {
        for (int i = 0; i < 4; i++) {
            Player.add(drawDeck.poll());
            Comp.add(drawDeck.poll());
        }
    }

    public void drawCard(ArrayList<card> hand){
        if (drawDeck.peek() != null) {
            hand.add(drawDeck.poll());
        } else {
            System.out.println("THE DRAW DECK IS EMPTY");
        }
    }

    public void drawdisCard(ArrayList<card> hand) {
        if(!discardDeck.empty()){
            System.out.println("Draw a card my dude");
        }else{
            System.out.println("Empty try again later");
        }
    }

    public void discardCard(ArrayList<card> hand, int sel) {
        discardDeck.add(hand.get(sel));
        hand.remove(sel);
    }
    
    public Queue<card> getDrawDeck() {
        return drawDeck;
    }

    public void setDrawDeck(Queue<card> drawDeck) {
        Deck.drawDeck = drawDeck;
    }

    public Stack<card> getDiscardDeck() {
        return discardDeck;
    }

    public void setDiscardDeck(Stack<card> discardDeck) {
        Deck.discardDeck = discardDeck;
    }
    
    
}