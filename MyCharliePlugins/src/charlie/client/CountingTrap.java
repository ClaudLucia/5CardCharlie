/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.client;

import charlie.card.Card;
import charlie.card.Shoe;
import charlie.message.Message;
import charlie.message.view.to.Deal;
import charlie.message.view.to.GameStart;
import charlie.message.view.to.Shuffle;
import charlie.plugin.ITrap;
import org.apache.log4j.Logger;

/**
 *
 * @author claud
 */
public class CountingTrap implements ITrap{
protected static Logger LOG = Logger.getLogger(Trap.class);


    int shoeSZ;
    float runCount;
    float trueCount;
    float deckSZ;
    float bet;
    boolean shuffle;

    @Override
    public void onSend(Message msg) {
        
    }

    @Override
    public void onReceive(Message msg) {
        
    if(msg instanceof Deal){
        //Get the deal message
        Deal deal = (Deal)msg;
        Card card = deal.getCard();
        //Check if null
        if(card == null){
            return;
        }
        
        
        //Calculate the running Count using the Hi-Lo method
        if(2 >= card.getRank() && card.getRank() <= 6){
           runCount += 1;
        }
//        if(7 >= card.getRank() && card.getRank() <= 9){
//           runCount += 0;
//        }
        if(card.isAce()){
           runCount -=1 ;
        }
        if(card.isFace() == true){
           runCount -=1 ;
        }
//        else{
//            runCount -=1 ;
//        }
    }
    if(msg instanceof GameStart){
        //Get the shoe size and count it
        shoeSZ = ((GameStart) msg).shoeSize();
        shoeSZ--;
        
    }
    
    if (msg instanceof Shuffle){
        //Add code to trap the Shuffle message and flag it as as shuffle pending
        shuffle = ((Shuffle) msg).equals(true);
        
    }
        
        
        //Calculate the true count
        deckSZ = shoeSZ/52;        
        trueCount = (runCount/deckSZ);
        
        //Calculate the bet 
        bet = (int) (Math.max(1, trueCount + 1)+ 0.5);
        
       
        
        LOG.info("Shoe Size:" + shoeSZ + ", Running Count: " + runCount + ", True Count: " + trueCount + ", Bet: " + bet + " (chips)");
        LOG.info("Shuffle pending: " + shuffle);
    }
    
}
