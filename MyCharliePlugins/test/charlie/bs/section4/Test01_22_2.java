/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.bs.section4;

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
public class Test01_22_2 {
     @Test
    public void test() {
        // Generate an initially empty hand
        Hand myHand = new Hand(new Hid(Seat.YOU));
        
        // Put two cards in hand: 10+10
        Card card1 = new Card(10,Card.Suit.DIAMONDS);
        Card card2 = new Card(10,Card.Suit.HEARTS);
        
        myHand.hit(card1);
        myHand.hit(card2);
        
        // Create dealer up card: 5
        Card upCard = new Card(5,Card.Suit.DIAMONDS);
        
        // Construct advisor and test it
        IAdvisor advisor = new Advisor();
  
        Play advice = advisor.advise(myHand, upCard);
        
        // Validate the advise
        assertEquals(advice, Play.STAY);
    }
}
