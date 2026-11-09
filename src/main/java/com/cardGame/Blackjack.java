package com.cardGame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Stack;

public class Blackjack {
	ArrayList<Stack <Card>> columns;
	Stack<Card> deck;
	public Stack<Card> playerDeck;
	Stack<Card> dealerDeck;
	int tokens;
	int bet;
	boolean game = true;
	int pValue = 0;
	int dValue = 0;
	boolean betted = false;

	
	
	
	public Blackjack(){
		deck = new Stack();
		playerDeck = new Stack<>();
		dealerDeck = new Stack<>();
		tokens = 100;
		bet = 0;
		generateCards();
	}
	
	
	
	
	public Stack<Card> hit() {
		
		Card leCard = deck.pop();
		playerDeck.push(leCard);
		pValue += leCard.value;
		return playerDeck;
		
		
	}
	
	public void newGame(){
		
		this.generateCards();
		Card one = deck.pop();
		Card two = deck.pop();
		Card three = deck.pop();
		Card four = deck.pop();
		
		playerDeck.push(one);
		playerDeck.push(two);
		dealerDeck.push(three);
		dealerDeck.push(four);
		
		pValue += (one.value + two.value);
		dValue += (three.value + four.value);
		
		
	}
	
	
	public void generateCards() {
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
		
		
		deck = stackOfCards;
		
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
		if (tokens >= bet && betted == false) {
			this.bet = bet;
			tokens -= this.bet;
			betted = true;
			System.out.println("Betted: " + bet);
		}
	}
		
		
		
	
	public void dealerTurn() {
		
		while (dValue < 17) {
			Card card = deck.pop();
			dealerDeck.push(card);
			dValue += card.value;
		}
		
	}
	
	
	
	
}
