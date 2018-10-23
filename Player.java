/**
 * This class provides the layout for each player in a game of BlackJack.
 *
 * Owen Sorber
 * 3/6/18
 */
import java.util.ArrayList;
public class Player
{
    private ArrayList<Card> hand;
    private int numCards;
    private int wins;
    
    public Player(Deck deck) {
        wins = 0;
        numCards = 2;
    }
    
    // Build a player's hand from cards dealt from the deck
    public void buildHand(Deck deck) {
        hand = new ArrayList<Card>();
        for (int i = 0; i < numCards; i++) {
            hand.add(deck.deal());
        }
    }
    
    // Reset hand by removing cards currently in the hand
    public void resetHand() {
        int size = hand.size();
        for (int i = 0; i < size; i++)
            hand.remove(0); // removes first card in hand and shifts everything left
    }
    
    // Add card to the hand
    public void addCard(Deck deck) {
        hand.add(deck.deal());
    }
    
    public void addWin() {
        wins++;
    }
    public int getWins() {
        return wins;
    }
    
    // Determine if player has won the game based on min - minimum number of games you need to win
    public boolean hasWon(int min) {
        return wins >= min;
    }
    
    // Determine if player has a sum over 21 which means it's too high
    public boolean over() {
        return sum() > 21;
    }
    
    // Calculate sum of player's cards
    public int sum() {
        int total = 0;
        for (Card n : hand) {
            total += n.blackJackNum();
        }
        
        // Account for aces being equal to 1 or 11
        if (total <= 11 && numAces() >= 1)
            total += 10;
        
        return total;
    }
    
    // Find number of aces
    public int numAces() {
        int num = 0;
        for (Card n : hand) {
            if (n.getNum() == 1) num++;
        }
        return num;
    }
    
    public ArrayList getHand() {
        return hand;
    }
   
    public String toString() {
        String str = "";
        for (Card n : hand) {
            str += n.toString() + "; ";
        }
        return str;
    }
}
