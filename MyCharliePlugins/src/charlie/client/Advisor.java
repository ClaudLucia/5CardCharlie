/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.client;

import charlie.card.Card;
import charlie.card.Hand;
import charlie.plugin.IAdvisor;
import charlie.util.Play;
/**
 *
 * @author claud
 */
public class Advisor implements IAdvisor{

   protected BasicStrategy bs = new BasicStrategy();

    @Override
    public Play advise(Hand myHand, Card upCard) {
        return bs.getPlay(myHand, upCard);
    }
    
    
}
