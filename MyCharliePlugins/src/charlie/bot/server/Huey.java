package charlie.bot.server;

import charlie.card.Card;
import charlie.card.Hand;
import charlie.card.Hid;
import charlie.util.Play;
import charlie.client.BasicStrategy;
import charlie.dealer.Dealer;
import charlie.dealer.Seat;
import charlie.plugin.IBot;
import java.util.List;
import java.util.Random;

/**
 * Huey bot is a plugin that run on the server side.
 * Each time the player hits Deal a new Huey will be created.
 * Huey implements IBot which is a branch of IPlayer from the plugins package.
 * @author claud
 */
public class Huey implements IBot, Runnable {

    final int MAX_THINKING = 5;
    Seat mine;
    Hand myHand;
    Dealer dealer;
    Random ran = new Random();
    boolean isMyTurn = false;
    public Card upCard;

    @Override
    public Hand getHand() {
        return myHand;
    }

    @Override
    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    @Override
    public void sit(Seat seat) {
        mine = seat;
        myHand = new Hand(new Hid(seat));
    }

    @Override
    public void startGame(List<Hid> hids, int shoeSize) {
        
    }

    @Override
    public void endGame(int shoeSize) {
        
    }

    @Override
    public void deal(Hid hid, Card card, int[] values) {
        Seat seat = hid.getSeat();
        if(seat == Seat.DEALER && card != null){
            this.upCard = card;
        }
        if(this.isMyTurn){
            play(hid);
        }
        else{
            this.isMyTurn = false;
            return;
        }
    }

    @Override
    public void insure() {
        
    }

    @Override
    public void bust(Hid hid) {
        
    }

    @Override
    public void win(Hid hid) {
        
    }

    @Override
    public void blackjack(Hid hid) {
        
    }

    @Override
    public void charlie(Hid hid) {
        
    }

    @Override
    public void lose(Hid hid) {
        
    }

    @Override
    public void push(Hid hid) {
        
    }

    @Override
    public void shuffling() {
        
    }

    @Override
    public void play(Hid hid) {
        if(hid.getSeat() != mine){
            this.isMyTurn = false;
            return;
        }
        this.isMyTurn = true;
        new Thread(this).start();
    }

    @Override
    public void split(Hid newHid, Hid origHid) {
        
    }

    @Override
    public void run() {
        try{
           int thinking = ran.nextInt(MAX_THINKING * 1000);
            Thread.sleep(thinking);
            Play play = BasicStrategy.getPlay(myHand, upCard);
            if(play == Play.STAY || play == Play.SPLIT){
                dealer.stay(this, myHand.getHid());
            }
            if(play == Play.HIT){
                dealer.hit(this, myHand.getHid());
            }
            if(play == Play.DOUBLE_DOWN){
                dealer.doubleDown(this, myHand.getHid());
            }
            else{
                dealer.stay(this, myHand.getHid());
            }

        }
        catch(InterruptedException ex){
            System.out.println("Error");
        }
        
    }
    
}
