/**
 * This class provides a layout for Card objects to be used in a Deck class.
 * 
 * Owen Sorber
 * 3/6/18
 */
public class Card
{
    private int num;
    private String suit;
    
    public Card(int n, String s) {
        num = n;
        suit = s;
    }
    
    public int getNum() {
        return num;
    }
    
    public int blackJackNum() {
        if (num >= 10) 
            return 10;
        else
            return num;
    }
    
    public String getSuit() {
        return suit;
    }
    
    public String toString() {
        String[] royalty = {"Jack", "Queen", "King"};
        
        if (num == 1)
            return "Ace of " + suit;
        
        if (num >= 11) {
            return royalty[num - 11] + " of " + suit;
        }
        
        return num + " of " + suit;
    }
}
