package charlie.client;

import charlie.card.Hid;
import charlie.dealer.Seat;
import charlie.message.Message;
import charlie.message.view.to.Loose;
import charlie.message.view.to.Outcome;
import charlie.message.view.to.Win;
import charlie.plugin.ITrap;

import static charlie.util.Constant.PLAYER_BANKROLL;
import org.apache.log4j.Logger;


/**
 *
 * @author claud
 */
public class Trap implements ITrap {
    protected static Logger LOG = Logger.getLogger(Trap.class);
   
    protected int youWin = 0;
    protected int bankroll = 0;
    protected int youLose = 0;

    @Override
    public void onSend(Message message) {
    }
    
    @Override
    public void onReceive(Message msg) {
        if(msg instanceof Win) {
           Hid hid = ((Outcome) msg).getHid();
           if(hid.getSeat() == Seat.YOU)
            youWin++;
        
           if (hid.getSeat() == Seat.YOU)
            bankroll = (int) (PLAYER_BANKROLL + hid.amt);
        }
        


        if(msg instanceof Loose){
          Hid hid = ((Outcome)msg).getHid();
          if(hid.getSeat() == Seat.YOU)
            youLose++;
          
          if (hid.getSeat() == Seat.YOU)
            bankroll = (int) (PLAYER_BANKROLL - hid.amt);
        }
        LOG.info("Lost: " + youLose);
        LOG.info("Wins: " + youWin);
        LOG.info("Current bankroll: " + bankroll);

    }
    
    
 
}
