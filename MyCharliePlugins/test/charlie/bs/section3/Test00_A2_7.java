/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.bs.section3;

import charlie.card.Card;
import charlie.card.Hand;
import charlie.card.Hid;
import charlie.client.Advisor;
import charlie.dealer.Seat;
import charlie.plugin.IAdvisor;
import charlie.util.Play;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author claud
 */
public class Test00_A2_7 {
     @Test
    public void test() {
        // Generate an initially empty hand
        Hand myHand = new Hand(new Hid(Seat.YOU));
        
        // Put two cards in hand: 1+2
        Card card1 = new Card(1,Card.Suit.CLUBS);
        Card card2 = new Card(2,Card.Suit.HEARTS);
        
        myHand.hit(card1);
        myHand.hit(card2);
        
        // Create dealer up card: 7
        Card upCard = new Card(7,Card.Suit.HEARTS);
        
        // Construct advisor and test it
        IAdvisor advisor = new Advisor();
  
        Play advice = advisor.advise(myHand, upCard);
        
        // Validate the advise
        assertEquals(advice, Play.HIT);
    }
}
