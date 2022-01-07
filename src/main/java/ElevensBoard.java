import java.util.ArrayList;

// The ElevensBoard class represents the board in a game of Elevens.
// This class will contain all the code that implements the rules of the game
public class ElevensBoard extends Board {

    // The size (number of cards) on the board is fixed at 9
    private static final int BOARD_SIZE = 9;

    // These three arrays will be used in the constructor:
    // The ranks of the cards for this game to be sent to the deck
    private static final String[] RANKS = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king" };
    // The suits of the cards for this game to be sent to the deck.
    private static final String[] SUITS = { "spades", "hearts", "diamonds", "clubs" };
    // The values of the cards for this game to be sent to the deck.
    private static final int[] POINT_VALUES = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0 };

    // Creates a new ElevensBoard instance with a standard 52 card deck
    public ElevensBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
    }

    // Given a list of the **indexes** of some cards on the board,
    // returns true if **any** two of the given cards adds up to exactly 11
    // and false otherwise
    public boolean containsPairSum11(ArrayList<Integer> cardIndexes) {
        for (int i:cardIndexes){
            for (int j:cardIndexes){
                if (cardAt(i).getPointValue()+cardAt(j).getPointValue()==11){
                    return true;
                }
            }
        }
        return false;
    }

    // Given a list of the **indexes** of some cards on the board,
    // returns true if there is at least 1 Jack, at least 1 Queen, and at least 1 King
    // amongst the selected cards, and false otherwise
    public boolean containsJQK(ArrayList<Integer> cardIndexes) {
        boolean jack=false;
        boolean queen=false;
        boolean king=false;
        for (int i:cardIndexes){
            if (cardAt(i).getRank().equals("jack")){
                jack=true;
            }
            else if (cardAt(i).getRank().equals("queen")){
                queen=true;
            }
            else if (cardAt(i).getRank().equals("king")){
                king=true;
            }
            if (jack&&queen&&king){
                return true;
            }
        }

        return false;
    }

    // Determine if there are any legal plays left on the board.
    // A legal play is either a pair of non-face cards whose values
    // add to 11, or (2) a group of three cards consisting of a jack, a
    // queen, and a king in some order.
    @Override
    public boolean anotherPlayIsPossible() {
        // allCards is a list of the indexes of all cards on the board
        ArrayList<Integer> allCards = getAllCardIndexes();

        // YOUR CODE HERE
        // Just 1-2 lines of code needed
        if (containsPairSum11(allCards)||containsJQK(allCards)){
            return true;
        }
        return false;
    }

    // Determines if the selected cards form a valid group for removal. In Elevens,
    // the legal groups are (1) a pair of non-face cards whose values add to 11, and
    // (2) a group of three cards consisting of a jack, a queen, and a king in some order
    @Override
    public boolean isLegal(ArrayList<Integer> selectedCards) {
        if (selectedCards.size()==2){
            if (cardAt(selectedCards.get(0)).getPointValue()+cardAt(selectedCards.get(1)).getPointValue()==11){
                return true;
            }
        }
        else if (selectedCards.size()==3){
            boolean jack=false;
            boolean queen=false;
            boolean king=false;
            for (int i:selectedCards){
                if (cardAt(i).getRank().equals("jack")){
                    jack=true;
                }
                else if (cardAt(i).getRank().equals("queen")){
                    queen=true;
                }
                else if (cardAt(i).getRank().equals("king")){
                    king=true;
                }
                if (jack&&queen&&king){
                    return true;
                }
            }

            return false;
        }
        return false;
    }
}
