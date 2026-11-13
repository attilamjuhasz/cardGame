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
import java.lang.classfile.Label;
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
	JButton nextRound;
	JButton stand;
	JLabel labelTokens;
	JLabel labelBet;
	JLabel winScreen;
	JLabel loseScreen;
	JLabel result;
	JLabel vJack;
	JLabel vQueen;
	JLabel vKing;
	JLabel vAce;



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
		labels.setBounds(700, 100, 150, 200);
		labels.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.green));
		gameArea.add(labels);
		
		labelTokens = new JLabel("Tokens: "+game.tokens);
		labelTokens.setFont(new Font("Serif", Font.PLAIN, 20));
		labelTokens.setForeground (Color.black);
		labelTokens.setBounds(30,0,100,50);
		labels.add(labelTokens);
		
		labelBet = new JLabel("Bet: "+game.bet);
		labelBet.setForeground (Color.black);
		labelBet.setBounds(30,0,100,105);
		labels.add(labelBet);


		vJack = new JLabel("Jack: 11");
		vJack.setFont(new Font("Serif", Font.PLAIN, 10));
		vJack.setForeground (Color.blue);
		vJack.setBounds(30,0,100,155);
		vJack.setVisible(true);
		labels.add(vJack);

		vQueen = new JLabel("Queen: 12");
		vQueen.setFont(new Font("Serif", Font.PLAIN, 10));
		vQueen.setForeground (Color.blue);
		vQueen.setBounds(30,0,100,175);
		vQueen.setVisible(true);
		labels.add(vQueen);

		vKing = new JLabel("King: 13");
		vKing.setFont(new Font("Serif", Font.PLAIN, 10));
		vKing.setForeground (Color.blue);
		vKing.setBounds(30,0,100,195);
		vKing.setVisible(true);
		labels.add(vKing);

		vAce = new JLabel("Ace: 1");
		vAce.setFont(new Font("Serif", Font.PLAIN, 10));
		vAce.setForeground (Color.blue);
		vAce.setBounds(30,0,100,215);
		vAce.setVisible(true);
		labels.add(vAce);
		
		winScreen = new JLabel("YOU WON!");
		winScreen.setForeground (Color.red);
		winScreen.setBounds(30,0,100,255);
		winScreen.setVisible(false);
		labels.add(winScreen);

		loseScreen = new JLabel("GAME OVER!");
		loseScreen.setForeground (Color.red);
		loseScreen.setBounds(30,0,100,255);
		loseScreen.setVisible(false);
		labels.add(loseScreen);

		result = new JLabel("");
		result.setForeground (Color.red);
		result.setBounds(30,0,100,255);
		result.setVisible(false);
		labels.add(result);
		
		

		JPanel winA = new JPanel();
		winA.setOpaque(true);
		winA.setLayout(null);
		winA.setBounds(500, 0, 300, 50);
		winA.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.green));
		gameArea.add(winA);
		JLabel winB = new JLabel("You need 1000 tokens to WIN!");
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
				
				if (game.betted){
					game.hit();
				}
				ret.removeAll();
				ret.add(drawPile(game.playerDeck));
				revalidate();
				repaint();
				
	        }
	      }); 

		JButton bet5 = new JButton("Bet 5");
		bet5.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {  
	           game.bet(5);
				update();

	          }
	      }); 
		
		JButton bet15 = new JButton("Bet 15");
		bet15.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {  
	           game.bet(15);
				update();
	          }
	      }); 

		JButton bet30 = new JButton("Bet 30");
		bet30.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {  
	           game.bet(30);
				update();
	          }
	      }); 
		
		JButton betAll = new JButton("Bet All");
		betAll.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {  
	           game.bet(game.tokens);
				update();
	          }
	      }); 
		

		
		stand = new JButton("Stand");
		GUI gui = this;
		stand.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {  
	           
			   if (!game.won && !game.lost){
				
				
				game.dealerTurn();
				dealerRet.removeAll();
				dealerRet.add((gui.drawPileDealer(game.dealerDeck, true)));
				revalidate();
				repaint();

				game.noMoreHits = true;
				game.noMoreStands = true;

				if (game.pValue > 21) {
					result.setText("Bust");
				}
				else if (game.pValue > game.dValue && game.pValue < 22) {
					result.setText("Won round");
				}
				else if (game.dValue > game.pValue && game.dValue > 21) {
					result.setText("Won round");
				}
				else if (game.dValue > game.pValue && game.dValue < 22) {
					result.setText("Lost round");
				}
				else if (game.dValue == game.pValue) {
					result.setText("Tied round");
				}
				else {
					result.setText("Lost round");
				}

				result.setVisible(true);
				
				game.gameOver();
			   }
	           
	           
		   	    
		 
	           
	          }
	      });
		
		nextRound = new JButton("Next Round");
		nextRound.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {  

				result.setVisible(false);

	           if (game.tokens > 0 && game.tokens < 1000 && game.nextRoundAvailable) {
	        	   game.newGame();
	           }
	           ret.removeAll();
	           dealerRet.removeAll();
	           ret.add(gui.drawPile(game.playerDeck));
	           dealerRet.add(gui.drawPileDealer(game.dealerDeck, false));
	           revalidate();
	           repaint();
	           
	           ret.add(gui.drawPile(game.playerDeck));
	           dealerRet.add(gui.drawPileDealer(game.dealerDeck, false));
	           revalidate();
	           repaint();
	           
	           update();
	                 
	        
		   	    
		 
	           
	          }
	      });
		
		controls.add(bet5);
		controls.add(bet15);
		controls.add(bet30);
		controls.add(betAll);
		controls.add(hit);
		controls.add(stand);
		controls.add(nextRound);

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
	    
	 
	    return ret;

	 

	}
   
   public JLayeredPane drawPileDealer(Stack<Card> stackIn, boolean dTurn) {

	    Object cards[];
	    cards = stackIn.toArray(); //please note we convert this stack to an array so that we can iterate through it backwards while drawing. You’ll need to cast each element inside cards to a <Card> in order to use the methods to adjust their position
	    JLayeredPane dRet = new JLayeredPane();
	    for (int i = 0; i < cards.length; i++) {
			Card card = (Card) cards[i];
			card.setBounds(i * 20, 0, 100, 150);
			if (!dTurn && i == 0) {
				card.hide();
			}else if(dTurn && i ==0) {
				card.show();
			}
			dRet.add(card, (Integer)i);
	    }
	    dRet.setBounds(new Rectangle(0, 0, cards.length*((Card)cards[0]).getWidth(), cards.length* ((Card)cards[0]).getHeight()));
	   
	    
	    return dRet;

	 

	}

	private void update() {
	   labelTokens.setText("Tokens: " + game.tokens);
	   labelBet.setText("Bet: " + game.bet);
	   if (!game.lost && !game.won){
		ret.removeAll();
		dealerRet.removeAll();
		ret.add(drawPile(game.playerDeck));
		dealerRet.add(drawPileDealer(game.dealerDeck, game.dealerTurn));
		revalidate();
		repaint();
	   }
       
       
       if (game.won) {
    	   winScreen.setVisible(true);
       }
	   else if (game.lost){
		loseScreen.setVisible(true);
	   }
       else {
           winScreen.setVisible(false);
		   loseScreen.setVisible(false);
       }
    
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
