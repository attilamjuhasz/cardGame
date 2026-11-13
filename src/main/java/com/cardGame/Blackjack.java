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
	boolean game;
	int pValue;
	int dValue;
	boolean betted;
	boolean bust;
	boolean dealerTurn;
	boolean noMoreHits;
	boolean noMoreStands;
	boolean lost;
	boolean won;
	boolean nextRoundAvailable;

	
	
	
	public Blackjack(){
		deck = new Stack();
		playerDeck = new Stack<>();
		dealerDeck = new Stack<>();
		tokens = 100;
		bet = 0;
		game = true;
		pValue = 0;
		dValue = 0;
		betted = false;
		bust = false;
		dealerTurn = false;
		noMoreHits = false;
		noMoreStands = false;
		lost = false;
		won = false;
		nextRoundAvailable = false;
		generateCards();
	}
	
	
	
	
	public void hit() {
		
		
		if (!bust && !noMoreHits){
			Card leCard = deck.pop();
			playerDeck.push(leCard);
			pValue += leCard.value;
		}
		
		
		if (pValue > 21){
			bust = true;
		}
		
		
		
	}
	
	public void newGame(){
		
		bust = false;
		betted = false;
		noMoreHits = false;
		noMoreStands = false;
		nextRoundAvailable = false;
		
		
		deck.clear();
		generateCards();
		playerDeck.clear();
		dealerDeck.clear();
		
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
		   if (pValue > 21) {
		   }
		   else if (pValue > dValue && pValue < 22) {
			   tokens += 2*bet;
		   }
		   else if (dValue > pValue && dValue > 21) {
			   tokens += 2*bet;
		   }
		   else if (dValue > pValue && dValue < 22) {
		   }
		   else if (dValue == pValue) {
			   tokens += bet;
		   }
		   else {
		   }

		   if (tokens < 1){
			lost = true;
		   }
		   else if (tokens >= 1000){
			won = true;
		   }

		   pValue = 0;
		   dValue = 0;
		   bet = 0;
		   nextRoundAvailable = true;
		   
	   }
	
	
	
	
	public void bet(int bet) {
		if (tokens >= bet && betted == false) {
			this.bet = bet;
			tokens -= this.bet;
			betted = true;
		}
	}
		
		
		
	
	public void dealerTurn() {


		
		if (pValue < 22 && !noMoreStands) {
			while (dValue < 17) {
				Card card = deck.pop();
				dealerDeck.push(card);
				dValue += card.value;
			}
		}

		
		
	}
	
	
	
	
}
