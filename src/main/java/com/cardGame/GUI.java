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

	private Blackjack game;
	JPanel dealer;
	JPanel playersCards;
	JLayeredPane ret;
	JLayeredPane dealerRet;
	JButton hit;
	JButton bet;
	JButton stand;


	public static String IMG_PATH = System.getProperty("user.dir")+"/src/main/java/com/cardGame/resources/";
   public GUI(Blackjack game){
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
		controls.setLayout(new FlowLayout());
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

		ret = new JLayeredPane();
		ret.setOpaque(false);
		ret.setLayout(null);
		ret.setBounds(0, 0, 400, 200);
		ret.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.ORANGE));
		playersCards.add(ret);
		
		dealerRet = new JLayeredPane();
		dealerRet.setOpaque(false);
		dealerRet.setLayout(null);
		dealerRet.setBounds(0, 0, 400, 200);
		dealerRet.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.ORANGE));
		dealer.add(dealerRet);
		
		JPanel labels = new JPanel();
		labels.setOpaque(true);
		labels.setLayout(null);
		labels.setBounds(700, 100, 150, 100);
		labels.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.green));
		gameArea.add(labels);
		JLabel aba = new JLabel("Tokens: "+game.tokens);
		aba.setFont(new Font("Serif", Font.PLAIN, 20));
		aba.setForeground (Color.black);
		aba.setBounds(30,0,100,50);
		labels.add(aba);
		JLabel bbb = new JLabel("Bet: "+game.bet);
		bbb.setForeground (Color.black);
		bbb.setBounds(30,0,100,105);
		labels.add(bbb);

		JPanel winA = new JPanel();
		winA.setOpaque(true);
		winA.setLayout(null);
		winA.setBounds(500, 0, 300, 50);
		winA.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.green));
		gameArea.add(winA);
		JLabel winB = new JLabel("You need 100 tokens to WIN!");
		winB.setFont(new Font("Serif", Font.PLAIN, 20));
		winB.setForeground (Color.black);
		winB.setBounds(30,0,290,50);
		winA.add(winB);

		
		game.newGame();
		ret.add(drawPile(game.playerDeck));
		dealerRet.add(drawPileDealer(game.dealerDeck, false));
		
		revalidate();
		repaint();
		
		
		hit = new JButton("Hit");
		hit.addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {  
	           System.out.println("here");
	           Stack<Card> pCards = game.hit();
	           if (!game.bust) {
	        	   ret.add(drawPile(pCards));
	           }
	           revalidate();
	           repaint();
	        }
	      }); 

		JButton bet5 = new JButton("Bet 5");
		bet5.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {  
	           game.bet(5);
	          }
	      }); 
		
		JButton bet15 = new JButton("Bet 15");
		bet15.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {  
	           game.bet(15);
	          }
	      }); 

		JButton bet30 = new JButton("Bet 30");
		bet30.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {  
	           game.bet(30);
	          }
	      }); 
		
		JButton betAll = new JButton("Bet All");
		betAll.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {  
	           game.bet(game.tokens);
	          }
	      }); 

		
		stand = new JButton("Stand");
		stand.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {  
	           game.dealerTurn();
	          }
	      }); 
		
		controls.add(bet5);
		controls.add(bet15);
		controls.add(bet30);
		controls.add(betAll);
		controls.add(hit);
		controls.add(stand);

		this.setVisible(true);
    }
   
   //Precondition: gui is defined and stack is defined
   //Postcondition: places cards in stack in order ontop of eachother
   public JLayeredPane drawPile(Stack<Card> stackIn) {

	    Object cards[];
	    cards = stackIn.toArray(); //please note we convert this stack to an array so that we can iterate through it backwards while drawing. You’ll need to cast each element inside cards to a <Card> in order to use the methods to adjust their position
	    JLayeredPane ret = new JLayeredPane();
	    for (int i = 0; i < cards.length; i++) {
			Card card = (Card) cards[i];
			card.setBounds(i * 20, 0, 100, 150);
			ret.add(card, (Integer)i);
	    }
	    ret.setBounds(new Rectangle(0, 0, cards.length*((Card)cards[0]).getWidth(), cards.length* ((Card)cards[0]).getHeight()));
	    
	    if (game.pValue > 21) {
	    	game.gameOver();
	    }
	 
	    return ret;

	 

	}
   
   public JLayeredPane drawPileDealer(Stack<Card> stackIn, boolean dTurn) {

	    Object cards[];
	    cards = stackIn.toArray(); //please note we convert this stack to an array so that we can iterate through it backwards while drawing. You’ll need to cast each element inside cards to a <Card> in order to use the methods to adjust their position
	    JLayeredPane dealerRet = new JLayeredPane();
	    for (int i = 0; i < cards.length; i++) {
			Card card = (Card) cards[i];
			card.setBounds(i * 20, 0, 100, 150);
			if (!dTurn && i == 0) {
				card.hide();
			}
			dealerRet.add(card, (Integer)i);
	    }
	    dealerRet.setBounds(new Rectangle(0, 0, cards.length*((Card)cards[0]).getWidth(), cards.length* ((Card)cards[0]).getHeight()));
	    
	    if (game.pValue > 21) {
	    	game.gameOver();
	    }
	 
	    return dealerRet;

	 

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
