
package charlie.client;

import charlie.card.Card;
import charlie.card.Hand;
import charlie.util.Play;

/**
 * This class is an implementation of the Basic Strategy rules.
 * @author claudrojas
 */
public class BasicStrategy {
    public final static Play P = Play.SPLIT;
    public final static Play H = Play.HIT;
    public final static Play S = Play.STAY;
    public final static Play D = Play.DOUBLE_DOWN;
    
    static Play[][] section1Rules = {
                // 2  3  4  5  6  7  8  9  T  A
        /* 21 */ { S, S, S, S, S, S, S, S, S, S  },
        /* 20 */ { S, S, S, S, S, S, S, S, S, S  },
        /* 19 */ { S, S, S, S, S, S, S, S, S, S  }, 
        /* 18 */ { S, S, S, S, S, S, S, S, S, S  }, 
        /* 17 */ { S, S, S, S, S, S, S, S, S, S  }
    };
    
    //We start dividing the sections from the top to bottom
    public static Play getPlay(Hand hand, Card upCard){
        Card card1 = hand.getCard(0);
        Card card2 = hand.getCard(1);
        
        if(hand.isPair()){
            // TODO: return doSection4(hand,upCard)
            if((card1.value() == 'A' && card2.value() == 'A') || (card1.value() == 8 && card2.value() == 8)) {
                return Play.SPLIT;
            }
            else if( card1.value() == 10 && card2.value() == 10) {
                return Play.STAY;
            }
            else if(card1.value() == 9 && card2.value() == 9) {
                if(upCard.value() == 7 || upCard.value() >= 10){
                    return Play.STAY;
                }
                else{
                    return Play.SPLIT;
                }
            }
            else if(card1.value() == 7 && card2.value() == 7){
                if(upCard.value() <= 7){
                    return Play.SPLIT;
                }
                if(upCard.value() > 7){
                    return Play.HIT;
                }
            }
            else if(card1.value() == 6 && card2.value() == 6){
                if(upCard.value() <= 6){
                    return Play.SPLIT;
                }
            }
            else if(card1.value() == 4 && card2.value() == 4)
                if(upCard.value() == 5 || upCard.value() == 6){
                return Play.SPLIT;
            }
            else if(card1.value() <= 3 && card2.value() <= 3 && upCard.value() <= 7){
                return Play.SPLIT;
            }
            else
                return Play.HIT;
        }
        else if(hand.size() == 2 && (card1.getRank() == Card.ACE || card2.getRank() == Card.ACE)) {
            // TODO: return doSection3(hand,upCard)
            //If first or second card is an Ace
            if(card1.value() >= 8 || card2.value() >= 8){
                return Play.STAY;
            }
            else if(card1.value() == 7 || card2.value() == 7){
                if(upCard.value() >= 3 && upCard.value() <= 6){
                    return Play.DOUBLE_DOWN;
                }
                else if(upCard.value() == 2 || (upCard.value() <= 8)){
                    return Play.STAY;
                }
            }
            else if((card1.value() >= 6 || card2.value() >= 6) && (upCard.value() == 5 || upCard.value() == 6)){
                return Play.DOUBLE_DOWN;
            }
            else if((card1.value() == 6 || card2.value() == 6) && (upCard.value() >= 3 && upCard.value() <= 6)){
                return Play.DOUBLE_DOWN;
            }
            else if(((card1.value() == 5 || card2.value() == 5) || (card1.value() == 4 || card2.value() == 5)) 
                  && (upCard.value() >= 4 && upCard.value() <= 6)){
                return Play.DOUBLE_DOWN;
            }
            else{
                return Play.HIT;
            }
        }
        else if(hand.getValue() >=5 && hand.getValue() < 12) {
            // TODO: return doSection2(hand,upCard)
            if(hand.getValue() == 11 && upCard.value() <= 10){
                if(upCard.value() <= 10 ){
                    return Play.DOUBLE_DOWN;
                }
                else{
                    return Play.HIT;
                }
            }
            else if(hand.getValue() == 10){
                if(upCard.value() <= 9){
                    return Play.DOUBLE_DOWN;
                }
                else{
                    return Play.HIT;
                }
            }
            else if(hand.getValue() == 9) {
                if (upCard.value() >= 3 && upCard.value() <= 6){
                    return Play.DOUBLE_DOWN;
                }
                else{
                    return Play.HIT;
                }
            }
            else{
                return Play.HIT;
            }
        }
        else if(hand.getValue() >= 12)
            return doSection1(hand,upCard);
        
        return Play.NONE;
    }
    
    /**
     * Does section 1 processing of the basic strategy, 12-21 (player) vs. 2-A (dealer)
     * @param hand Player's hand
     * @param upCard Dealer's up-card
     */
    protected static Play doSection1(Hand hand, Card upCard) {
        int value = hand.getValue();
        
        // Section one table built only for hand values >= 20 (see above).
        if(value < 20){
            if((value <=16 && value >=13) && upCard.value() <= 6 ){
                return Play.STAY;
            }
            else if(value == 12 && (upCard.value() >=4 && upCard.value() <=6)){
                return Play.STAY;
            }
            else{
                return Play.HIT;
            }
            //return Play.NONE;
        }
        
        // Subtract 21 since the player's hand starts at 21 and we're working
        // our way down through section 1
        int rowIndex = 21 - value;
        
        Play[] row = section1Rules[rowIndex];
        
        // Subtract 2 since the dealer's up-card start at 2
        int colIndex = upCard.getRank() - 2;
         
        if(upCard.isFace())
            colIndex = 10 - 2;

        // Ace is the 10th card (index 9)
        else if(upCard.isAce())
            colIndex = 9;
        
        Play play = row[colIndex];
        
        return play;    
    }
}
