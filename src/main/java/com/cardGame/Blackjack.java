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
	boolean bust = false;
	boolean dealerTurn = false;

	
	
	
	public Blackjack(){
		deck = new Stack();
		playerDeck = new Stack<>();
		dealerDeck = new Stack<>();
		tokens = 100;
		bet = 0;
		generateCards();
	}
	
	
	
	
	public Stack<Card> hit() {
		
		if (pValue > 21){
			bust = true;
		}
		else {
			Card leCard = deck.pop();
			playerDeck.push(leCard);
			pValue += leCard.value;
		}
		return playerDeck;
		
		
	}
	
	public void newGame(){
		
		bet = 0;
		
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
		
		bust = false;
		betted = false;
		
		
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
		   
		   System.out.println("pValue: " +pValue);
		   System.out.println("dValue: " +dValue);
		   
		   if (pValue > 21) {
			   System.out.println("Bust");
			   System.out.println("Lost: " +tokens);
		   }
		   else if (pValue > dValue && pValue < 22) {
			   tokens += 2*bet;
			   System.out.println("Won: " +tokens);
		   }
		   else if (dValue > pValue && dValue > 21) {
			   tokens += 2*bet;
			   System.out.println("Won: " +tokens);
		   }
		   else if (dValue > pValue && dValue < 22) {
			   System.out.println("Lost: " +tokens);
		   }
		   else if (dValue == pValue) {
			   tokens += bet;
			   System.out.println("Tie: " +tokens);
		   }
		   else {
			   System.out.println("Lost: " +tokens);
		   }
		   pValue = 0;
		   dValue = 0;
		   
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
		
		if (pValue < 22) {
			while (dValue < 17) {
				Card card = deck.pop();
				dealerDeck.push(card);
				dValue += card.value;
			}
		}

		
		
	}
	
	
	
	
}
