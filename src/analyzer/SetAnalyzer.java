package analyzer;
import java.util.ArrayList;

public class SetAnalyzer {
	
	private static ArrayList<Card> hand = new ArrayList<Card>();
	private static ArrayList<Card> set = new ArrayList<Card>();
	private static int gameComparisons = 0;
	private static int gameNoSets = 0;
	private static ArrayList<Integer> allGameComparisons = new ArrayList<Integer>();
	private static ArrayList<Integer> allGameNoSets = new ArrayList<Integer>();
	private static int gameCount = 20000;
	static ArrayList<Integer> handComparisons = new ArrayList<Integer>();
	static ArrayList<Integer> handComparisonsAll = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < gameCount; i++){
			//System.out.println("Starting game " + i);
			gameComparisons = 0;
			gameNoSets = 0;
			playGame();			
			allGameComparisons.add(gameComparisons);
			allGameNoSets.add(gameNoSets);
		}
		
		long totalTime = System.currentTimeMillis() - startTime;
		
		System.out.println("Total simulation time: " + totalTime + " ms");
		System.out.println("Time per game: " + ((double)totalTime * 1000 / gameCount) + " us");
		System.out.println("");
		
		Stats compStats = new Stats(allGameComparisons);
		System.out.println("Mean comparisons per game: " + compStats.getMean());	
		System.out.println("StdDev comparisons per game: " + compStats.getStdDev());
		System.out.println("");
		
		Stats noSetsStats = new Stats(allGameNoSets);
		System.out.println("Mean no sets per game: " + noSetsStats.getMean());	
		System.out.println("StdDev no sets per game: " + noSetsStats.getStdDev());
		System.out.println("");
		
		Stats handComparisonsStats = new Stats(handComparisonsAll);
		System.out.println("Mean comparisons per hand: " + handComparisonsStats.getMean());	
		System.out.println("StdDev comparisons per hand: " + handComparisonsStats.getStdDev());
		System.out.println("");
	}
	
	public static void playGame(){
		// Create the deck
		Deck deck = Deck.getInstance();
		deck.shuffleDeck();
		handComparisons.clear();
				
		hand = deck.getHand();		
		
		while(deck.getCardsRemaining() > 0 || hand.size() > 0){
			
			boolean noSetsFound = false;
						
			//System.out.println("Cards remaining: " + deck.getCardsRemaining() + " Hand size: " + hand.size());
			//printCards(hand);
			set = checkForSets();
			if(set.size() > 0){
				//System.out.println("Set found: ");
				//printCards(set);
			}
			else{
				//System.out.println("No sets found!!!");
				noSetsFound = true;
				gameNoSets++;
			}
			
			// Deal 3 new cards if there are currently 9 cards	
			if(noSetsFound && deck.getCardsRemaining() > 0){
				for(int i = 0; i < 3; i++){
				hand.add(deck.dealCard());
				}
			}
			else{
				while(hand.size() < 12 && deck.getCardsRemaining() > 0){				
					hand.add(deck.dealCard());				
				}
			}
			
			if(deck.getCardsRemaining() == 0 && noSetsFound){
				Stats handCompStats = new Stats(handComparisons);
				handComparisonsAll.add((int)handCompStats.getMean());
//				System.out.println("Game Over!");
//				System.out.println("");
//				System.out.println("This game took " + gameComparisons + " comparisons");				
//				System.out.println("Mean comparisons per hand: " + handCompStats.getMean());	
//				System.out.println("StdDev comparisons per hand: " + handCompStats.getStdDev());
//				System.out.println("");				
				break;
			}
		}
	}
	
	public static void printCards(ArrayList<Card> cards){
		System.out.println("Number\tShape\tColor\tFill");
		for(Card thisCard : cards){			
			System.out.println(thisCard.getNum() + "\t" + thisCard.getShapeName() + "\t" + thisCard.getColorName() + "\t" + thisCard.getFillName());
		}
		System.out.println("");
	}
	
	public static ArrayList<Card> checkForSets(){
		ArrayList<Card> set = new ArrayList<Card>();		
		int comparisons = 0;
		
		outerloop:
		for(int i = 0; i < hand.size(); i++){
			Card first = hand.get(i);
			for(int j = i+1; j < hand.size(); j++){
				Card second = hand.get(j);
				Card third  = Card.getSetComplement(first, second);
				for(int k = 0; k < hand.size(); k++){
					// Don't compare to the comparison cards
					if(k == i || k == j)
						continue;
					
					gameComparisons++;					
					comparisons++;
					
					if(hand.get(k).equals(third)){
						set.add(first);
						set.add(second);
						set.add(third);
						hand.remove(i);
						hand.remove(j-1);
						if(k > j)
							hand.remove(k-2);
						else if(k < i)
							hand.remove(k);
						break outerloop;
					}						
				}
			}
		}
		
		//System.out.println("This hand took " + comparisons + " comparisons");
		handComparisons.add(comparisons);
		return set;
	}

}
