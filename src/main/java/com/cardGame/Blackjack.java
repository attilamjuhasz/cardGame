package com.cardGame;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Blackjack {
	ArrayList<Stack <Card>> columns;
	Queue<Card> deck;
	int tokens;
	int bet;
	int playerNumber = 0;
	int dealerNumber = 0;
	boolean game = true;

	public void hit() {
		
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
		   if (money <= 0) {
			   game = false;
		   }
	   }
	
	
	//the part of your program that's in charge of game rules goes here.
	public BlackJack(){
	}
	
	
	
	public void bet(int bet) {
		this.bet = bet;
		tokens -= this.bet;
	}
	
	
	
	
}
