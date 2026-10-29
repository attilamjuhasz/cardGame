package com.cardGame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Stack;

public class Blackjack {
	ArrayList<Stack <Card>> columns;
	Stack<Card> deck;
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
	
	
	
	
	public void hit(GUI gui) {
		
		Card leCard = deck.pop();
		playerDeck.push(leCard);
		
		gui.drawPile(playerDeck);
		
	}
	
	
	public Stack<Card> generateCards() {
		ArrayList<Card> list = new ArrayList();
		//loop through all cards
		//make an arrayList
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 14; j++) {
				if (i == 0) {
					list.add(new Card(j, Card.Suit.Diamonds));
				}
				else if (i == 1) {
					list.add(new Card(j, Card.Suit.Spades));
				}
				else if (i == 2) {
					list.add(new Card(j, Card.Suit.Hearts));
				}
				else {
					list.add(new Card(j, Card.Suit.Clubs));
				}
				
			}
		}
		
		
		Collections.shuffle(list);
		//take all the cards and put them into a stack
		Stack<Card> stackOfCards = new Stack<>();
		for (int i = 0; i < list.size(); i++) {
			stackOfCards.push(list.get(i));
			
		}
		
		
		return stackOfCards;
	}
	   
	   
	   public void gameOver() {
		   if (tokens <= 0) {
			   game = false;
		   }
	   }
	
	
	
	
	   public finishRound() {
		   int num = bet * 2;
		   tokens += bet;
		   
	   }
	
	
	
		public void bet(int bet) {
			if (tokens >= bet) {
				this.bet = bet;
				tokens -= this.bet;
			}
		}
		
	
	public void dealerTurn() {
		
	}
	
	public void stand() {
		dealerTurn();
	}
	
	
	
	
}
