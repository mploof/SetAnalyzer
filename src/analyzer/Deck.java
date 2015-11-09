package analyzer;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	ArrayList<Card> deck = new ArrayList<Card>();
	
	//*********************** Singleton Framework ***********************//
	
	private static volatile Deck instance;	
	
	public static Deck getInstance(){
		if(instance == null) {
			synchronized(Deck.class){
				if(instance == null){
					instance = new Deck();
				}
			}
		}		
		return instance;
	}
	
	public static void destroy(){
		instance = null;
	}
	
	//*********************** Private Constructor ***********************//
	
	private Deck(){	
		shuffleDeck();
	}
	
	public void shuffleDeck(){
		
		this.deck.clear();
		
		// Create cards in deck	
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				for(int k = 0; k < 3; k++){
					for(int l = 0; l < 3; l++){
						Card thisCard = new Card(i, j, k, l);
						deck.add(thisCard);
					}
				}
			}
		}
		
		Collections.shuffle(deck);
	}
		
	public ArrayList<Card> getHand(){
		
		ArrayList<Card> ret = new ArrayList<Card>();
		
		for(int i = 0; i < 12; i++){
			ret.add(deck.get(0));
			deck.remove(0);
		}	
		
		return ret;
	}
	
	public Card dealCard(){
		Card ret = deck.get(0);
		deck.remove(0);		
		return ret;
	}
	
	public ArrayList<Card> getRemainingCards(){
		return deck;
	}
	
	public int getCardsRemaining(){
		return deck.size();
	}

}
