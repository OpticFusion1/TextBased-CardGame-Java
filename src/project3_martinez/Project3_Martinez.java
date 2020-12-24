package project3_martinez;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Project3_Martinez {

    private static Deck deck = new Deck();
    private static ArrayList<card> player = new ArrayList();
    private static ArrayList<card> comp = new ArrayList();
    private static int turn = 0;
    private static int winnerCount = 0;
    private static Scanner si = new Scanner(System.in);
    private static boolean winner = false;
    static String red = "\u001B[31m";       //colors for console (useless but was bored)
    static String green = "\u001B[32m";
    static String blue = "\u001B[36m";
    static String purple = "\u001B[35m";

    
    public static void main(String[] args) {
        
        deck.shuffleDeck();
        deck.firstDeal(player, comp);
        test(player);
        displayHand(player);

        while (winner == false) {
            if (deck.getDiscardDeck().empty()) {
                if (turn == 0) {      //Humans turn
                    System.out.println("The discard deck is empty -- you must draw a new card");
                    deck.drawCard(player);
                    System.out.println(blue + "You drew the " + player.get(4).getCardValue() + " of " + player.get(4).getCardSuit() + "s");
                    discardPromt(player);
                    displayHand(player);
                    test(player);
                    turn++;
                } else {        //robot
                    deck.drawCard(comp);
                    int cpRand = (int) (Math.random() * (5 - 1) + 1);
                    deck.discardCard(comp, cpRand);
                    test(comp);
                    turn--; //deincraminting turn back to 0
                }
            } else if (turn == 0) { //discard not empty
                System.out.println("Do you want to pick up the " + deck.getDiscardDeck().peek() + " (1) or draw a new card (2)? ");
                System.out.print("Selection: ");
                int sel;
                while (true) {
                    try {
                        sel = si.nextInt();
                        if (sel >= 1 && sel <= 2) {
                            if (sel == 1) {
                                player.add(deck.getDiscardDeck().pop());
                                System.out.println(blue + "You pikced up the " + player.get(4).cardValue + " of " + player.get(4).cardSuit + "s");
                                discardPromt(player);
                                test(player);
                                turn++;
                                break;
                            }
                            if (sel == 2) {
                                deck.drawCard(player);
                                System.out.println(blue+"You drew the " + player.get(4).cardValue + " of " + player.get(4).cardSuit + "s");
                                discardPromt(player);
                                test(player);
                                turn++;
                                break;
                            }
                        } else {
                            throw new InputMismatchException();
                        }
                    } catch (InputMismatchException e) {
                        System.out.print("Please enter a correct value: ");
                        si.nextLine();
                    }
                    turn++;
                }
            } else {    //robot  card in discard deck
                int cpRand = (int) (Math.random() * (3 - 1) + 1);
                int cpRand2 = (int) (Math.random() * (5 - 1) + 1);
                if (cpRand == 1) {
                    deck.drawCard(comp);
                    deck.discardCard(comp, cpRand2);
                    System.out.println(purple+"Your opponet discarded the " + deck.getDiscardDeck().peek().toString());
                }
                int cpRand3 = (int) (Math.random() * (5 - 1) + 1);
                if (cpRand == 2) {
                    comp.add(deck.getDiscardDeck().pop());
                    deck.discardCard(comp, cpRand3);
                    System.out.println(purple+"Your opponet discarded the " + deck.getDiscardDeck().peek().toString());
                }
                test(comp);
                turn--;
            }
            checkDeck();
            //debug();
        }
    }

    static void displayHand(ArrayList<card> dispHand) {
        System.out.println("Your hand Contains");
        for (int i = 0; i < dispHand.size(); i++) {
            System.out.println("\t" + dispHand.get(i).cardValue + " of " + dispHand.get(i).cardSuit + "s");
        }
    }

    static void discardPromt(ArrayList<card> hand) {
        System.out.println("Now your cards are");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println("\t" + (i + 1) + ".) " + hand.get(i).cardValue + " of " + hand.get(i).cardSuit + "s");
        }
        System.out.print("Which card would you like to discard: ");
        int sel;
        while (true) {
            try {
                sel = si.nextInt();
                if (sel <= 5 && sel >= 1) {
                    sel = sel - 1;
                    deck.discardCard(hand, sel);
                    break;
                }
                //throw new InputMismatchException();
            } catch (Error e) {
                System.out.print("Please enter a correct value: ");
                sel = si.nextInt();
                if (sel <= 5 && sel >= 1) {
                    sel = sel - 1;
                    deck.discardCard(hand, sel);
                    break;
                }
            }
        }
    }

    private static void checkDeck() {
        if (deck.getDrawDeck().isEmpty()) {
            int z = 44;
            for (int i = 0; i < z; i++) {
                deck.getDrawDeck().add(deck.getDiscardDeck().get(i));
            }
            deck.getDiscardDeck().clear();
            deck.shuffleDeck();
        }
    }

    static void debug() {
        System.out.println("DRAW DECK SIZE: " + deck.getDrawDeck().size());
        System.out.println("DisCard DECK SIZE: " + deck.getDiscardDeck().size());
        System.out.println("Hand Size P SIZE: " + player.size());
        System.out.println("Hand Size C SIZE: " + comp.size());
    }

    private static void test(ArrayList<card> hand) {
        winnerCount = 0;
        
        if(hand.get(0).cardValue == hand.get(1).cardValue){
            winnerCount++;
            if(hand.get(0).cardValue == hand.get(2).cardValue){
                winnerCount++;
                if(hand.get(0).cardValue == hand.get(3).cardValue){
                    winnerCount++;
                    if(hand.get(0).cardValue == hand.get(4).cardValue){
                        winnerCount++;
                        if(winnerCount == 3){
                            System.out.println("You HAVE WON");
                            winner = true;
                        }
                    }
                }
            }
        }
        
        winnerCount = 0;
        if(hand.get(1).cardValue == hand.get(0).cardValue){
            winnerCount++;
            if(hand.get(1).cardValue == hand.get(2).cardValue){
                winnerCount++;
                if(hand.get(1).cardValue == hand.get(3).cardValue){
                    winnerCount++;
                    if(hand.get(1).cardValue == hand.get(4).cardValue){
                        winnerCount++;
                        if(winnerCount == 3){
                            System.out.println("You HAVE WON");
                            winner = true;
                        }
                    }
                }
            }
        }
        
        
        
        
        
//        if (hand.get(0).cardValue == hand.get(1).cardValue && hand.get(0).cardValue == hand.get(2).cardValue && hand.get(0).cardValue == hand.get(3).cardValue) {
//            if (turn == 0) {
//                System.out.println(green+"You have won!!!");
//                winner = true;
//                displayHand(player);
//            }
//            if (turn == 1) {
//                System.out.println(red+"The computers have won! the world is doomed!");
//                winner = true;
//                displayHand(comp);
//            }
//        }
    }

}//



//below is failed AI logic.  RIP 

//    static void aiLogic() {
//        // 1 if discard is empty
//        // 2 if discard is not empty
//        if (deck.discardDeck.empty() == true) {
//            
//        } else {
//            deck.drawCard(comp);
//            int num1 = 0;
//            int num2 = 0;
//            int num3 = 0;
//            int num4 = 0;
//            int num5 = 0;
//            ArrayList<String> values = new ArrayList<>();
//            for (int i = 0; i < comp.size(); i++) {
//                values.add(comp.get(i).cardValue);
//            }
//            Object[] st = values.toArray();
//            for (Object s : st) {
//                if (values.indexOf(s) != values.lastIndexOf(s)) {
//                    values.remove(values.lastIndexOf(s));
//                }
//            }
//            ArrayList<card> dupeComp = new ArrayList();
//            dupeComp = comp;
//            for (int i = 0; i < values.size(); i++) {
//                for (int j = 0; j < dupeComp.size(); j++) {
//                    if (values.get(i) == dupeComp.get(j).cardValue) {
//                        if (values.size() == 5) {
//                            String s1 = values.get(0);
//                            String s2 = values.get(1);
//                            String s3 = values.get(2);
//                            String s4 = values.get(3);
//                            String s5 = values.get(4);
//                            if (dupeComp.get(j).cardValue == s1) {
//                                num1++;
//                            }
//                            if (dupeComp.get(j).cardValue == s2) {
//                                num2++;
//                            }
//                            if (dupeComp.get(j).cardValue == s3) {
//                                num3++;
//                            }
//                            if (dupeComp.get(j).cardValue == s4) {
//                                num4++;
//                            }
//                            if (dupeComp.get(j).cardValue == s5) {
//                                num5++;
//                            }
//                        }
//                        if (values.size() == 4) {
//                            String s1 = values.get(0);
//                            String s2 = values.get(1);
//                            String s3 = values.get(2);
//                            String s4 = values.get(3);
//                            if (dupeComp.get(j).cardValue == s1) {
//                                num1++;
//                            }
//                            if (dupeComp.get(j).cardValue == s2) {
//                                num2++;
//                            }
//                            if (dupeComp.get(j).cardValue == s3) {
//                                num3++;
//                            }
//                            if (dupeComp.get(j).cardValue == s4) {
//                                num4++;
//                            }
//                        }
//                        if (values.size() == 3) {
//                            String s1 = values.get(0);
//                            String s2 = values.get(1);
//                            String s3 = values.get(2);
//                            if (dupeComp.get(j).cardValue == s1) {
//                                num1++;
//                            }
//                            if (dupeComp.get(j).cardValue == s2) {
//                                num2++;
//                            }
//                            if (dupeComp.get(j).cardValue == s3) {
//                                num3++;
//                            }
//                        }
//                        if (values.size() == 2) {
//                            String s1 = values.get(0);
//                            String s2 = values.get(1);
//                            if (dupeComp.get(j).cardValue == s1) {
//                                num1++;
//                            }
//                            if (dupeComp.get(j).cardValue == s2) {
//                                num2++;
//                            }
//                        }
//                        if (values.size() == 1) {
//                            String s1 = values.get(0);
//                            if (dupeComp.get(j).cardValue == s1) {
//                                num1++;
//                            }
//                        }
//                    }
//                }
//            }
//            ArrayList<Integer> valuesInt = new ArrayList();
//            if (values.size() == 0) {
//                System.out.println("I really screwed something up");
//            }
//            if (values.size() == 1) {
//                System.out.println("Number of " + values.get(0) + "s: " + num1);
//                valuesInt.add(num1);
//                System.out.println("BIGGEST INT! " + Collections.max(valuesInt));
//            }
//            if (values.size() == 2) {
//                System.out.println("Number of " + values.get(0) + "s: " + num1);
//                System.out.println("Number of " + values.get(1) + "s: " + num2);
//                valuesInt.add(num1);
//                valuesInt.add(num2);
//                System.out.println("BIGGEST INT! " + Collections.max(valuesInt));
//            }
//            if (values.size() == 3) {
//                System.out.println("Number of " + values.get(0) + "s: " + num1);
//                System.out.println("Number of " + values.get(1) + "s: " + num2);
//                System.out.println("Number of " + values.get(2) + "s: " + num3);
//                valuesInt.add(num1);
//                valuesInt.add(num2);
//                valuesInt.add(num3);
//                System.out.println("BIGGEST INT! " + Collections.max(valuesInt));
//                
//            }
//            if (values.size() == 4) {
//                System.out.println("Number of " + values.get(0) + "s: " + num1);
//                System.out.println("Number of " + values.get(1) + "s: " + num2);
//                System.out.println("Number of " + values.get(2) + "s: " + num3);
//                System.out.println("Number of " + values.get(3) + "s: " + num4);
//                valuesInt.add(num1);
//                valuesInt.add(num2);
//                valuesInt.add(num3);
//                valuesInt.add(num4);
//                System.out.println("BIGGEST INT! " + Collections.max(valuesInt));
//            }
//            if (values.size() == 5) {
//                System.out.println("Number of " + values.get(0) + "s: " + num1);
//                System.out.println("Number of " + values.get(1) + "s: " + num2);
//                System.out.println("Number of " + values.get(2) + "s: " + num3);
//                System.out.println("Number of " + values.get(3) + "s: " + num4);
//                System.out.println("Number of " + values.get(4) + "s: " + num5);
//                valuesInt.add(num1);
//                valuesInt.add(num2);
//                valuesInt.add(num3);
//                valuesInt.add(num4);
//                valuesInt.add(num5);
//                System.out.println("BIGGEST INT! " + Collections.max(valuesInt));
//            }
//            
//            
//            //discard a card with lowest num
//            
//        }
    // }
