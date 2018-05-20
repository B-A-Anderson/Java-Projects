import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PokerGame {

	public static void main(String[] args) {
		/* 	Take in input of card numbers and output
		*	The hand that would give. Just some Practice
		*/
		System.out.println("Please enter 5 card numbers NS seperated by a comma. N= number S= suit:");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		input = input.toUpperCase();
		input = input.replaceAll("\\s","");
		String[] cards = input.split(",");
		Set<String>  myHand = new HashSet<String>();
		for(String card: cards)
		{
			myHand.add(card);
		}
		//Create a HashSet of possible values to compare against
		char[] cardValues = {'2','3','4','5','6','7','8','9','T','J','Q','K','A'};
		char[] Suits = {'H','D','C','S'};
		Set<String>  pValues = new HashSet<String>();
		for(int c = 0; c < cardValues.length; c++)
		{
			for( char suit : Suits)
			{
				String possible = "" + cardValues[c] + suit;
				pValues.add(possible);
			}//end for			
		}//end for
		
		//Make sure the Input has correct Values
		if(pValues.containsAll(myHand) != true){
			System.out.println("This input is not valid. Please input a card value. A card value\n"
				+ "consist of a number between 2-9 or a letter T,J,Q,K,A and Suit Letter\n"
				+ "H,D,C,or S. Please use correct input.");
		}else if(myHand.size() != 5) {
			System.out.println("There can only be 5 values. Please only add 5 values\n"
					+ "Duplicate values are prohibited");
		}else
		checkHand(cards);
	}//end main


	static void checkHand(String[] hand)
	{
		char[] values = new char[hand.length];
		Set<Character> royal = new HashSet<Character>();
		royal.add('K');
		royal.add('Q');
		royal.add('J');
		royal.add('T');
		royal.add('A');
		int h = 0,d = 0,c = 0,s = 0, rank=0;
		for(int i = 0; i < hand.length; i++)
		{
			char[] card = hand[i].toCharArray();
			values[i] = card[0];

			String check = hand[i];
	
			if(check.contains("H"))
				h++;
			else if(check.contains("D"))
				d++;
			else if(check.contains("C"))
				c++;
			else if(check.contains("S"))
				s++;
			if (values[0] == values[i])
				rank++;
		}//end for
		if(h == 5 || d == 5|| c== 5 || s == 5) {
			if(royal.contains(values[0]) && royal.contains(values[1]) && royal.contains(values[2]) && royal.contains(values[3]) && royal.contains(values[4]))
				System.out.println("Royal flush");
			else
				System.out.println("flush");		
		}else if(rank == 4)
			System.out.println("four of a kind");
		else if(rank == 3)
			System.out.println("three of a kind");
		else if(rank == 2)
			System.out.println("pair");
		else
			System.out.println("No pair");
	}
	
}//End class
