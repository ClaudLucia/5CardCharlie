/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.sidebet.test;

import charlie.card.Card;
import charlie.card.Shoe;

/**
 *Super 7 is a type of side bet that has a payout of 3:1
 * when the player bets if the first card to be drawn out is a 7
 * 
 * @author claud
 */

public class Super7Shoe extends Shoe{

    @Override
    public void init() {        
        cards.clear();
        
        //SCENARIO 1 PLAYER WINS
        cards.add(new Card(7,Card.Suit.HEARTS));        // YOU: 7
        cards.add(new Card(Card.KING,Card.Suit.CLUBS)); // DEALER: 10
        cards.add(new Card(9,Card.Suit.SPADES));        // YOU: 16
        cards.add(new Card(8,Card.Suit.DIAMONDS));      // DEALER: 18
        cards.add(new Card(3,Card.Suit.CLUBS));         // Hit YOU: 19
        
        
        //SCENARIO 2 PLAYER WINS
        cards.add(new Card(4,Card.Suit.CLUBS));             // YOU: 4
        cards.add(new Card(8,Card.Suit.HEARTS));            // DEALER: 8
        cards.add(new Card(Card.QUEEN,Card.Suit.DIAMONDS)); // YOU: 14
        cards.add(new Card(Card.JACK,Card.Suit.HEARTS));    // DEALER: 18
        cards.add(new Card(7,Card.Suit.SPADES));            // Hit YOU: 21
        
        //SCENARIO 3 PLAYER LOSES
        cards.add(new Card(Card.KING,Card.Suit.DIAMONDS));// YOU: 10
        cards.add(new Card(8,Card.Suit.SPADES));          // DEALER: 8
        cards.add(new Card(Card.JACK,Card.Suit.CLUBS));   // YOU: 20
        cards.add(new Card(7,Card.Suit.HEARTS));          // DEALER: 15
        cards.add(new Card(5,Card.Suit.SPADES));          // Hit YOU: 25
        
        
        //SCENARIO 4 PLAYER LOSES
        cards.add(new Card(3,Card.Suit.CLUBS));            // YOU: 3
        cards.add(new Card(Card.JACK,Card.Suit.DIAMONDS)); // DEALER: 10
        cards.add(new Card(4,Card.Suit.CLUBS));            // YOU: 7
        cards.add(new Card(9,Card.Suit.SPADES));           // DEALER: 19
        cards.add(new Card(8,Card.Suit.HEARTS));           // Hit YOU: 15
        
    }
    
    
    @Override
    public boolean shuffleNeeded() {
        return false;
    }
    
}
