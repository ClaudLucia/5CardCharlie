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
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package charlie.sidebet.view;

import charlie.audio.Effect;
import charlie.audio.SoundFactory;
import charlie.card.Card;
import charlie.card.Hand;
import charlie.card.Hid;
import charlie.plugin.ISideBetView;
import charlie.util.Constant;
import charlie.util.Point;
import charlie.view.ACard;
import charlie.view.AHand;
import charlie.view.AMoneyManager;
import charlie.view.sprite.AtStakeSprite;
import charlie.view.sprite.Chip;

import charlie.view.sprite.ChipButton;
import charlie.view.sprite.TurnIndicator;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import org.apache.log4j.Logger;

/**
 * This class implements the side bet view
 * @author Ron Coleman, Ph.D.
 */
public class SideBetView implements ISideBetView {
    private final Logger LOG = Logger.getLogger(SideBetView.class);
    
    public final static int X = 400;
    public final static int Y = 200;
    public final static int DIAMETER = 50;
    
    // Area for the chips 
    public final static int PLACE_CHIP_X = X + DIAMETER + 10;
    public final static int PLACE_CHIP_Y = Y + DIAMETER / 4;        
    
    protected Font font = new Font("Arial", Font.BOLD, 18);
    protected BasicStroke stroke = new BasicStroke(3);

    protected Font font1 = new Font("Arial", Font.BOLD, 14);

    // Hand outcomes
    public enum Outcome { None, Win, Lose};
    
    //Win and Lose font, background, and foreground
    protected Font stateF = new Font("Arial", Font.PLAIN, 18);
    protected Font outcomeF = new Font("Arial", Font.BOLD, 18);
    protected Color stateColor = Color.WHITE;
    
    protected Color loseColorBg = new Color(250,58,5);
    protected Color loseColorFg = Color.WHITE;
    
    protected Color winColorFg = Color.BLACK;
    protected Color winColorBg = new Color(116,255,4);
    
    protected Color pushColorFg = Color.BLACK;
    protected Color pushColorBg = Color.CYAN;
    protected AHand.Outcome outcome = AHand.Outcome.None; 
    protected List<ACard> cards = new ArrayList<>();
    protected Hand hand;
    
    
    // See http://docs.oracle.com/javase/tutorial/2d/geometry/strokeandfill.html
    protected float dash1[] = {10.0f};
    protected BasicStroke dashed
            = new BasicStroke(3.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f, dash1, 0.0f);   

    protected List<ChipButton> buttons;
    protected int amt = 0;
    protected AMoneyManager moneyManager;
    
    protected Random ran = new Random();

    //Chips Images
    protected List<Chip> chipPics = new ArrayList<>();
    
    
    public SideBetView() {
        LOG.info("side bet view constructed");
    }
    
    /**
     * Sets the money manager.
     * @param moneyManager 
     */
    @Override
    public void setMoneyManager(AMoneyManager moneyManager) {
        this.moneyManager = moneyManager;
        this.buttons = moneyManager.getButtons();
    }
    
    /**
     * Registers a click for the side bet.
     * @param x X coordinate
     * @param y Y coordinate
     */
    @Override
    public void click(int x, int y) {
        int oldAmt = amt;
        
        // Test if any chip button has been pressed.
        for(ChipButton button: buttons) {
            if(button.isPressed(x, y)) {
                amt += button.getAmt();
                LOG.info("A. side bet amount "+button.getAmt()+" updated new amt = "+amt);
                
                //Use SoundFactory with the CHIPS_IN effect for placing a bet
                //Or for CHIPS_OUT effect SoundFactory.play(CHIPS_IN)/(CHIPS_OUT)

                int n = chipPics.size();
                
                int placeX = PLACE_CHIP_X + n * DIAMETER/3 + ran.nextInt(10)-10;
                
                int placeY = PLACE_CHIP_Y + ran.nextInt(5)-5;
                
                Chip chip = new Chip(button.getImage(), placeX, placeY, button.getAmt());
                
                chipPics.add(chip);

                SoundFactory.play(Effect.CHIPS_IN);

            } 
            
        }
        
        if(oldAmt == amt) {
            amt = 0;
            SoundFactory.play(Effect.CHIPS_OUT);
            chipPics.clear();
            LOG.info("B. side bet amount cleared");
            
        }
    }

    /**
     * Informs view the game is over and it's time to update the bankroll for the hand.
     * @param hid Hand id
     */
    @Override
    public void ending(Hid hid) {
        double bet = hid.getSideAmt();
        
        if(bet == 0)
            return;

        LOG.info("side bet outcome = "+bet);
        
        // Update the bankroll
        moneyManager.increase(bet);
        
        LOG.info("new bankroll = "+moneyManager.getBankroll());
    }

    /**
     * Informs view the game is starting
     */
    @Override
    public void starting() {
    }

    /**
     * Gets the side bet amount.
     * @return Bet amount
     */
    @Override
    public Integer getAmt() {
        return amt;
    }

    /**
     * Updates the view
     */
    @Override
    public void update() {
    }

    /**
     * Renders the view
     * @param g Graphics context
     */
    @Override
    public void render(Graphics2D g) {
        
        // Draw the at-stake place on the table
        g.setColor(Color.RED); 
        g.setStroke(dashed);
        g.drawOval(X-DIAMETER/2, Y-DIAMETER/2, DIAMETER, DIAMETER);
        
        //Draw the at-stake amount
        //Draw the at-stake dollar amount inside the at-stake area
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(""+amt, X-5, Y+5);

        //Draws on the table the bet amounts(SUPER 7, ROYAL MATCH, EXACTLY)
        g.setFont(font1);
        g.drawString("SUPER 7 pays 3:1", X+35, Y-25);
        g.drawString("ROYAL MATCH pays 25:1", X+35, Y-5);
        g.drawString("EXACTLY 13 pays 1:1", X+35, Y+15);
        
        //Draw the chips using randomness to simluate chips being placed on the table
        for(Chip chip: chipPics){
            chip.render(g);
        }
        
        //Draw WIN or LOSE over the at-stake area, if sidebet wins or loses
        
        //If there are no Cards
        int sz = cards.size();
        if(sz == 0 || outcome == AHand.Outcome.None)
            return;
        
        // Calculate the outcome position
        FontMetrics fm = g.getFontMetrics(outcomeF);
        int x = X + 25;
        int y = Y - 10; 
        
        // Figure the outcome text
        String outcomeText = "";
        
        Card card = hand.getCard(0);
        Card card1 = hand.getCard(1);
        //Super 7
        if(card.getRank() == 7) {
            outcomeText += " " + outcome.toString().toUpperCase() + " ! "; 
        }
        
        //Exacly13 Rule
        //E.g., 6+7, 8+5, etc. on first two cards
        //FIRST TWO CARDS
        if(card.value() + card1.value() == 13){
            outcomeText += " " + outcome.toString().toUpperCase() + " ! "; 
        }
        
        //Royal Match Rule
        if(card.getSuit() == card1.getSuit()){
            if(card.getRank() == card.KING && card.getRank() == card.QUEEN){
                outcomeText += " " + outcome.toString().toUpperCase() + " ! "; 
            }
            else if(card.getRank() == card.QUEEN && card.getRank() == card.KING){
                outcomeText += " " + outcome.toString().toUpperCase() + " ! "; 
            }
        }
        else{
            outcomeText += " " + outcome.toString().toUpperCase() + " ! "; 
        }
        
        int w = fm.charsWidth(outcomeText.toCharArray(), 0, outcomeText.length());
        int h = fm.getHeight();
        
        // Paint the outcome background            
        if (outcome == AHand.Outcome.Lose)
            g.setColor(loseColorBg);

        else if(outcome == AHand.Outcome.Push)
            g.setColor(pushColorBg);
        else
            g.setColor(winColorBg);    

        g.fillRoundRect(x, y-h+5, w, h, 5, 5);

        // Paint the outcome foreground            
        if (outcome == AHand.Outcome.Lose)
            g.setColor(loseColorFg);

        else if(outcome == AHand.Outcome.Push)
            g.setColor(pushColorFg);
        else
            g.setColor(winColorFg);    

        g.setFont(outcomeF);
        g.drawString(outcomeText,x,y);
        
    }
}
