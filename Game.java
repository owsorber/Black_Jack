/**
 * This class performs a game of BlackJack using the Deck and Player classes
 *
 * Owen Sorber
 * 3/6/18
 */
import java.util.ArrayList;
import java.util.Scanner;
public class Game
{
    private Player player;
    private Player opponent;
    private Deck theDeck;
    private Scanner scan;
    private int gamesPlayed;
    private int numToWin;
    
    public Game(int numToWin) {
        // Initialize and shuffle game deck
        theDeck = new Deck();
        theDeck.shuffle();
        
        player = new Player(theDeck);
        opponent = new Player(theDeck);
        scan = new Scanner(System.in);
        gamesPlayed = 0;
        this.numToWin = numToWin;
    }
    
    // Method that runs all of the game's components
    public void play() {
        startGame();
        while(!player.hasWon(numToWin) && !opponent.hasWon(numToWin)) {
            player.buildHand(theDeck);
            opponent.buildHand(theDeck);
            System.out.println("Game Number " + (gamesPlayed + 1));
            System.out.println();
            System.out.println("Here are your cards:\n" + player.toString());
            System.out.println();
            promptAdding();
            System.out.println();
            manageOpponent();
            System.out.println();
            System.out.println("Press ENTER to see the result.");
            scan.nextLine();
            result();
            horizontalLine();
            
            // Reset variables
            player.resetHand();
            opponent.resetHand();
            theDeck.resetDeck();
            theDeck.shuffle();
            gamesPlayed++;
        }
        
        if (player.hasWon(numToWin)) 
            System.out.println("YOU WON IT ALL! CONGRATS!");
        else if (opponent.hasWon(numToWin)) 
            System.out.println("Unfortunately, your opponent won. Good game.");
    }
    
    // Start the game and output text to explain how the game will work
    private void startGame() {
        System.out.println("Welcome to Black Jack.\nYou will be facing the computer and need to win " + numToWin + " matches to win the game.");
        System.out.println("Press ENTER to begin.");
        scan.nextLine();
        horizontalLine();
    }
    
    // Ask user if they would like another card
    private void promptAdding() {
        System.out.println("Would you like another card? Remember that if your sum exceeds 21, you lose.");
        System.out.println("If yes, type \"y\". If no, type any other key.");
        
        while (scan.nextLine().equals("y")) {
            player.addCard(theDeck);
            System.out.println();
            System.out.println("Here's your new hand: " + player.toString());
            if (player.over()) {
                System.out.println("Looks like you're over 21 :(\nNow let's see what your opponent does.");
                return;
            }
            else {
                System.out.println("Would you like another card? Remember that if your sum exceeds 21, you lose.");
                System.out.println("If yes, type \"y\". If no, type any other key.");
            }
        }
    }
    
    // Internally prompt computer whether or not they want another card
    private void manageOpponent() {
        System.out.println("Your opponent has received 2 cards.");
        while (opponentWantsMore()) {
            opponent.addCard(theDeck);
            System.out.println("Your opponent decided to receive another card.");
        }
    }
    
    // Output match result to user
    private void result() {
        System.out.println("Result:");
        System.out.println("Your hand: " + player.toString());
        if (!player.over()) 
            System.out.println("Your sum: " + player.sum());
        else 
            System.out.println("You went over.");
        
        System.out.println("Opponent's hand: " + opponent.toString());
        if (!opponent.over()) 
            System.out.println("Opponent's sum: " + opponent.sum());
        else
            System.out.println("Opponent went over.");
        
        System.out.println();
        
        if (winner() == 0) 
            System.out.println("It was a tie!");
        else if (winner() == 1) {
            System.out.println("You won!");
            player.addWin();
        }
        else if (winner() == 2) {
            System.out.println("Your opponent won!");
            opponent.addWin();
        }
        
        System.out.println("Your wins: " + player.getWins());
        System.out.println("Opponent wins: " + opponent.getWins());
    }
    
    // Print a horiztonal line on the screen
    private void horizontalLine() {
        for (int i = 0; i < 100; i++)
            System.out.print("-");
        System.out.println();
    }
    
    // Return winner of a match
    private int winner() {
        if (player.over() && opponent.over() || player.sum() == opponent.sum()) return 0;
        else if (player.over() && !opponent.over()) return 2;
        else if (!player.over() && opponent.over()) return 1;
        else if (player.sum() > opponent.sum()) return 1;
        return 2;
    }
    
    // Algorithm that decides whether an opponent would want to draw another card based on probability of going over
    private boolean opponentWantsMore() {
        // Apply a bit of randomness to opponent's decision
        double rand = Math.random() * 0.4 - 0.2; 
        double rCoefficient = 1 + rand;
        
        int diff = 21 - opponent.sum(); // Amount away from 21
        double estimateProb = (double) diff / 13; // Estimate probability of going over
        if (player.getWins() > opponent.getWins()) 
            return estimateProb > (0.3 * rCoefficient);
        else if (player.getWins() < opponent.getWins()) 
            return estimateProb > (0.5 * rCoefficient);
        return estimateProb > (0.4 * rCoefficient);
    }
}
