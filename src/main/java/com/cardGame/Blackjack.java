package com.cardGame;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Blackjack {
	ArrayList<Stack <Card>> columns;
	Stack<Card> playerDeck;
	Stack<Card> dealerDeck;
	int tokens;
	int bet;
	boolean game = true;

	
	
	
	public Blackjack(){
		playerDeck = new Stack<>();
		dealerDeck = new Stack<>();
		tokens = 0;
		bet = 0;
	}
	
	
	
	
	public void hit() {
		
		playerDeck.push(this.newCard());
		
	}
	
	public Card newCard() {
		
	}
	   
	   //FINISHROUND METHOD

//		public void finishRound() {
//		   if (!playerNumber > 21 && playerNumber > dealerNumber) {
//			   money += bet;
//		   }
//		   else {
//			   money -= bet;
//		   }	   
	//   }
	   
	   
	   public void gameOver() {
		   if (tockens <= 0) {
			   game = false;
		   }
	   }
	
	
	
	
	
	public void bet(int bet) {
		this.bet = bet;
		tokens -= this.bet;
	}
	
	public void stand() {
		this.dealerTurn();
	}
	
	public void dealerTurn() {
		
	}
	
		public void newGame() {
		ArrayList<Card> list = new ArrayList(); 
		//loop through all cards
		//make an arrayList
		
		Collections.shuffle(list);
		//take all the cards and put them into a stack
	}
	
	
	
}
