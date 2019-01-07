/*
 Copyright (c) 2014 Ron Coleman

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS
.FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package charlie.sidebet.test;

import charlie.card.Card;

/**
 *
 * @author Marist User
 */
public class Shoe extends charlie.card.Shoe {
    @Override
    public void init() {
        cards.clear();
        
        
        // Test case #1
        //You
        cards.add(new Card(7,Card.Suit.DIAMONDS));
        //Dealer
        cards.add(new Card(Card.KING,Card.Suit.HEARTS));
        
        //You
        cards.add(new Card(9,Card.Suit.DIAMONDS));
        //Dealer
        cards.add(new Card(9,Card.Suit.SPADES)); 
        
        //You
        cards.add(new Card(3,Card.Suit.CLUBS));
        
        
        
        // Test case #2
        //You
        cards.add(new Card(7,Card.Suit.HEARTS));
        //Dealer
        cards.add(new Card(Card.KING,Card.Suit.CLUBS));
        
        //You
        cards.add(new Card(9,Card.Suit.SPADES));
        //Dealer
        cards.add(new Card(8,Card.Suit.DIAMONDS)); 
        
        //You
        cards.add(new Card(3,Card.Suit.CLUBS));
        
        
        
        // Test case #3
        //You
        cards.add(new Card(9,Card.Suit.HEARTS));
        //Dealer
        cards.add(new Card(Card.KING,Card.Suit.CLUBS));
        
        //You
        cards.add(new Card(7,Card.Suit.SPADES));
        //Dealer
        cards.add(new Card(8,Card.Suit.DIAMONDS)); 
        
        //You
        cards.add(new Card(3,Card.Suit.CLUBS)); 
        
        
        
        // Test case #4
        //You
        cards.add(new Card(7,Card.Suit.SPADES));
        //Dealer
        cards.add(new Card(Card.KING,Card.Suit.CLUBS));
        
        //You
        cards.add(new Card(9,Card.Suit.DIAMONDS));
        //Dealer
        cards.add(new Card(10,Card.Suit.HEARTS)); 
        
        //You
        cards.add(new Card(3,Card.Suit.CLUBS));
        
        
        
        // Test case #5
        //You
        cards.add(new Card(9,Card.Suit.SPADES));
        //Dealer
        cards.add(new Card(Card.KING,Card.Suit.DIAMONDS));
        
        //You
        cards.add(new Card(7,Card.Suit.HEARTS));
        //Dealer
        cards.add(new Card(10,Card.Suit.CLUBS)); 
        
        //You
        cards.add(new Card(3,Card.Suit.SPADES)); 
        
        
        
        // Test case #6
        //You
        cards.add(new Card(Card.KING,Card.Suit.HEARTS));
        //Dealer
        cards.add(new Card(Card.KING,Card.Suit.SPADES));
        
        //You
        cards.add(new Card(Card.QUEEN,Card.Suit.HEARTS));
        //Dealer
        cards.add(new Card(8,Card.Suit.DIAMONDS)); 
        
        
        
        // Test case #7
        //You
        cards.add(new Card(Card.KING,Card.Suit.SPADES));
        //Dealer
        cards.add(new Card(Card.KING,Card.Suit.CLUBS));
        
        //You
        cards.add(new Card(Card.QUEEN,Card.Suit.DIAMONDS));
        //Dealer
        cards.add(new Card(8,Card.Suit.DIAMONDS)); 
        
        
        
        // Test case #8
        //You
        cards.add(new Card(8,Card.Suit.DIAMONDS));
        //Dealer
        cards.add(new Card(Card.KING,Card.Suit.SPADES));
        
        //You
        cards.add(new Card(5,Card.Suit.CLUBS));
        //Dealer
        cards.add(new Card(6,Card.Suit.DIAMONDS));
        
        //STAY in order to win.  Make the Dealer draw another card so he can BUST.
        //Dealer
        cards.add(new Card(Card.KING,Card.Suit.DIAMONDS));
        
        
        
        // Test case #9
        //You
        cards.add(new Card(7,Card.Suit.CLUBS));
        //Dealer
        cards.add(new Card(Card.KING,Card.Suit.DIAMONDS));
        
        //You
        cards.add(new Card(6,Card.Suit.HEARTS));
        //Dealer
        cards.add(new Card(6,Card.Suit.SPADES));
        
        //STAY in order to win.  Make the Dealer draw another card so he can BUST.
        //Dealer
        cards.add(new Card(Card.KING,Card.Suit.SPADES));
        
        
        
        // Test case #10
        //You
        cards.add(new Card(6,Card.Suit.SPADES));
        //Dealer
        cards.add(new Card(Card.KING,Card.Suit.DIAMONDS));
        
        //You
        cards.add(new Card(8,Card.Suit.CLUBS));
        //Dealer
        cards.add(new Card(6,Card.Suit.HEARTS));
        
        //STAY in order to win.  Make the Dealer draw another card so he can BUST.
        //Dealer
        cards.add(new Card(Card.KING,Card.Suit.DIAMONDS));
        
    }
    
    @Override
    public boolean shuffleNeeded() {
        return false;
    }
}
