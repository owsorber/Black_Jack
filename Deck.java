/**
 * This class constructs a deck of 52 Card objects
 *
 * Owen Sorber
 * 3/6/18
 */
import java.util.Random;
public class Deck
{
    final int cardsInDeck = 52, numbersInDeck = 13;
    public final String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
    private Card[] deck;
    private int currentLocation = 0;
    
    public Deck() {
        deck = new Card[cardsInDeck];
        int cardNumber = 0;
        for (int i = 0; i < suits.length; i++) {
            for (int j = 1; j <= numbersInDeck; j++) {
                deck[cardNumber] = new Card(j, suits[i]);
                cardNumber++;
            }
        }
    }
    
    public void resetDeck() {
        currentLocation = 0;
    }
    
    // Deals the first card from the deck
    public Card deal() {
        int location = currentLocation;
        currentLocation++;
        return deck[location];
    }
    
    // Shuffles the deck by rearranging the order of the cards in the array
    public void shuffle() {
        Random random = new Random();
        int getRand;
        for (int i = 0; i < deck.length; i++) {
            Card current = deck[i];
            getRand = i + random.nextInt(deck.length - i);
            deck[i] = deck[getRand];
            deck[getRand] = current;
        }
    }
    
    // Outputs the complete deck
    public String toString() {
        String output = "";
        for (int i = 0; i < deck.length; i++) {
            output += deck[i].toString() + "\n";
        }
        return output;
    }
}
