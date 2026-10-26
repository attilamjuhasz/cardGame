package com.cardGame;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Blackjack {
	ArrayList<Stack <Card>> columns;
	Queue<Card> deck;
	int tokens;
	int bet;
	
	
	//the part of your program that's in charge of game rules goes here.
	public BlackJack(){
	}
	
	
	
	public void bet(int bet) {
		this.bet = bet;
		tokens -= this.bet;
	}
	
	
	
	
}
