package com.cardGame;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Stack;


public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener{

	private Solitaire game;
	JPanel dealer;
	JPanel playersCards;




	public static String IMG_PATH = System.getProperty("user.dir")+"/src/main/java/com/cardGame/resources/";
   public GUI(Solitaire game){
	   this.game= game;
        //Create and set up the window.
       setTitle("Solitaire");
       setSize(900,700);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       // this supplies the background
       try {
		Image blackImg = ImageIO.read(new File(IMG_PATH+"background.jpg"));
		
		setContentPane(new ImagePanel(blackImg));

       }catch(IOException e) {
    	   e.printStackTrace();
       }
       
       /*******
        * This is just a test to make sure images are being read correctly on your machine. Please replace
        * once you have confirmed that the card shows up properly. The code below should allow you to play the solitare
        * game once it's fully created.
        */
	   
	   this.setResizable(false);


	   //The entire play area
	   JPanel gameArea = new JPanel();
	   gameArea.setLayout(null);
	   gameArea.setOpaque(false);
	   gameArea.setBounds(0,0,900,550);
	   gameArea.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
	   this.add(gameArea);
	   
	   
	   //This is where the dealer's cards will be
	   dealer = new JPanel();
	   dealer.setOpaque(false);
	   dealer.setLayout(null);
	   dealer.setBounds(100,50,400,200);
	   dealer.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
	   gameArea.add(dealer);


	   //Panel where the controls will be
	   JPanel controls = new JPanel();
	   controls.setOpaque(false);
	   controls.setLayout(null);
	   controls.setBounds(650, 350, 200, 150);
	   controls.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GREEN));
	   gameArea.add(controls);

	   //This is where the player's cards will be
	   playersCards = new JPanel();
	   playersCards.setOpaque(false);
	   playersCards.setLayout(null);
	   playersCards.setBounds(100, 300, 400, 200);
	   playersCards.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.ORANGE));
	   gameArea.add(playersCards);


        Card card = new Card(2, Card.Suit.Diamonds);
        System.out.println(card);
        dealer.add(card);

		Card card2 = new Card(3, Card.Suit.Diamonds);
		System.out.println(card2);
		dealer.add(card2);

        this.setVisible(true);
    }
   
   public JLayeredPane drawPile(Stack<Card> stackIn) {

	    Object cards[];
	    JLayeredPane ret = new JLayeredPane();
	    ret.setOpaque(false);
	    ret.setLayout(null);
	    ret.setBounds(0, 0, 100, 300);
	    ret.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.ORANGE));
	    playersCards.add(ret);
	    cards = stackIn.toArray(); //please note we convert this stack to an array so that we can iterate through it backwards while drawing. You’ll need to cast each element inside cards to a <Card> in order to use the methods to adjust their position
	    
	    for (int i = 0; i < cards.length; i++) {
	    	ret.add((Card)cards[i]);
	    }
	 
	    return ret;

	 

	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
