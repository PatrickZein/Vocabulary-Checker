package Main;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		long wordCounter = 0;
		List <Word> wordList = new ArrayList<Word>();

		// Read a textfile into the word list
		Scanner s = null;
		try
		{
			s = new Scanner(new File("c:/temp/textfil.txt"));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> list = new ArrayList<String>();
		String word1 = "", word2 = "", word3 = "";
		while (s.hasNext())
		{
		    word1 = s.next();
		    
	    	// Trim special characters from the word 
	    	word1 = word1.replace("¬", "").replace(".", "").replace(",", "").replace("!", "").replace("?", "").replace(":", "").replace(";", "");
	    	word1 = word1.replace("-", "").replace("%", "").replace("”", "").replace("’", "").replace("(", "").replace(")", "").replace("*", "").replace("~", "");
	    	word1 = word1.toLowerCase();

	    	if (word1.equals("")) continue;
	    	
	    	// Store the word
	    	wordList.add(new Word(word3, word2, word1));
	    	wordCounter++;
	    	if (wordCounter % 1000 == 0) System.out.println("Reading " + wordCounter + " words...");
	    		
	    	// Shift the words one step.
	    	word3 = word2;
	    	word2 = word1;
		}
		s.close();

		wordCounter = 0;
		for(int i=0; i<wordList.size(); i++)
		{			
			for(int j=i+1; j<wordList.size(); j++)
			{
				// Compare word[i] with all following word[j]:
				if(wordList.get(i).checkA1==false)
				{
					// Check single words
					if(wordList.get(i).word1.equals(wordList.get(j).word1))
					{
						wordList.get(i).matchA1++;				
						// Mark the secondary object as checked
						wordList.get(j).checkA1=true;
					}
				}
				if(wordList.get(i).checkA2==false)
				{
					// Check groups of two words
					if(wordList.get(i).word2.equals(wordList.get(j).word2))
					{
						wordList.get(i).matchA2++;
						// Mark the secondary object as checked
						wordList.get(j).checkA2=true;
					}
				}
				if(wordList.get(i).checkA3==false)
				{
					// Check groups of three words
					if(wordList.get(i).word3.equals(wordList.get(j).word3))
					{
						wordList.get(i).matchA3++;
						// Mark the secondary object as checked
						wordList.get(j).checkA3=true;
					}
				}
			}
			for(int j=i+1; j<(i+300) && j<wordList.size(); j++)
			{
				// Compare word[i] with up to 300 following word[j]:
				if(wordList.get(i).word1.equals(wordList.get(j).word1)) { wordList.get(i).match31++; }
				if(wordList.get(i).word2.equals(wordList.get(j).word2)) { wordList.get(i).match32++; }
				if(wordList.get(i).word3.equals(wordList.get(j).word3)) { wordList.get(i).match33++; }
			}
	    	wordCounter++;
	    	if (wordCounter % 100 == 0) System.out.println("Analyzing " + wordCounter + " words...");
		}

		System.out.println("=====================");
		System.out.println("GLOBAL SINGLE MATCHES");
		for(int i=0; i<wordList.size(); i++)
		{
			if(wordList.get(i).word1.equals("")) continue;
			if(wordList.get(i).matchA1 > 1) System.out.println(wordList.get(i).matchA1 + ": " + wordList.get(i).word1);
		}
		System.out.println("=====================");
		System.out.println("GLOBAL DOUBLE MATCHES");
		for(int i=0; i<wordList.size(); i++)
		{
			if(wordList.get(i).matchA2 > 1) System.out.println(wordList.get(i).matchA2 + ": " + wordList.get(i).word2);
		}
		System.out.println("======================");
		System.out.println("GLOBAL TRIPPLE MATCHES");
		for(int i=0; i<wordList.size(); i++)
		{
			if(wordList.get(i).matchA3 > 1) System.out.println(wordList.get(i).matchA2 + ": " + wordList.get(i).word3);
		}
		
		System.out.println("====================");
		System.out.println("LOCAL SINGLE MATCHES");
		for(int i=0; i<wordList.size(); i++)
		{
			if(wordList.get(i).word1.equals("")) continue;
			if(wordList.get(i).match31 > 1) System.out.println(wordList.get(i).match31 + ": " + wordList.get(i).word1);
		}
		System.out.println("====================");
		System.out.println("LOCAL DOUBLE MATCHES");
		for(int i=0; i<wordList.size(); i++)
		{
			if(wordList.get(i).match32 > 1) System.out.println(wordList.get(i).match32 + ": " + wordList.get(i).word2);
		}
		System.out.println("=====================");
		System.out.println("LOCAL TRIPPLE MATCHES");
		for(int i=0; i<wordList.size(); i++)
		{
			if(wordList.get(i).match33 > 1) System.out.println(wordList.get(i).match33 + ": " + wordList.get(i).word3);
		}
		System.out.println("=====================");		
	}
}
