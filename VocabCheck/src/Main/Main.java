package Main;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		long wordCounter = 0;
		List <Word> wordList = new ArrayList<Word>();

		// Read a text file into the word list
		System.out.println("Reading the text file:");
		Scanner s = null;
		try
		{
			s = new Scanner(new File("c:/temp/textfile.txt"));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String word1 = "", word2 = "", word3 = "", word4 = "";
		while (s.hasNext())
		{
		    word1 = s.next();
		    
	    	// Trim special characters from the word 
	    	word1 = word1.replace("¬", "").replace(".", "").replace(",", "").replace("!", "").replace("?", "").replace(":", "").replace(";", "").replace("~", "");
	    	word1 = word1.replace("-", "").replace("–", "").replace("%", "").replace("”", "").replace("’", "").replace("(", "").replace(")", "").replace("*", "");
	    	word1 = word1.replace("•", "").toLowerCase();

	    	if (word1.equals("")) continue;
	    	
	    	// Store the word
	    	wordList.add(new Word(word4, word3, word2, word1));
	    	wordCounter++;
	    	if (wordCounter % 100 == 0) System.out.println("Reading " + wordCounter + " words...");
	    		
	    	// Shift the words one step.
	    	word4 = word3;
	    	word3 = word2;
	    	word2 = word1;
		}
		s.close();

		System.out.println("Alayzing the text file:");
		System.out.println("(The speed will increase, so please be patient!)");
		wordCounter = 0;
		for(int i=0; i<wordList.size(); i++)
		{			
			for(int j=i+1; j<wordList.size(); j++)
			{
				// Compare word[i] with all following word[j]:
				if(wordList.get(i).checkA1==false)
				{
					// Check single words
					if(wordList.get(i).stub1.equals(wordList.get(j).stub1))
					{
						wordList.get(i).matchA1++;				
						// Mark the secondary object as checked
						wordList.get(j).checkA1=true;
					}
				}
				if(wordList.get(i).checkA2==false)
				{
					// Check groups of two words
					if(wordList.get(i).stub2.equals(wordList.get(j).stub2))
					{
						wordList.get(i).matchA2++;
						// Mark the secondary object as checked
						wordList.get(j).checkA2=true;
					}
				}
				if(wordList.get(i).checkA3==false)
				{
					// Check groups of three words
					if(wordList.get(i).stub3.equals(wordList.get(j).stub3))
					{
						wordList.get(i).matchA3++;
						// Mark the secondary object as checked
						wordList.get(j).checkA3=true;
					}
				}
				if(wordList.get(i).checkA4==false)
				{
					// Check groups of three words
					if(wordList.get(i).stub4.equals(wordList.get(j).stub4))
					{
						wordList.get(i).matchA4++;
						// Mark the secondary object as checked
						wordList.get(j).checkA4=true;
					}
				}
			}
			for(int j=i+1; j<(i+300) && j<wordList.size(); j++)
			{
				// Compare word[i] with up to 300 following word[j]:
				if(wordList.get(i).stub1.equals(wordList.get(j).stub1)) { wordList.get(i).match31++; }
				if(wordList.get(i).stub2.equals(wordList.get(j).stub2)) { wordList.get(i).match32++; }
				if(wordList.get(i).stub3.equals(wordList.get(j).stub3)) { wordList.get(i).match33++; }
				if(wordList.get(i).stub4.equals(wordList.get(j).stub4)) { wordList.get(i).match34++; }
			}
	    	wordCounter++;
	    	if (wordCounter % 100 == 0) System.out.println("Analyzing " + wordCounter + " words...");
		}
		
		System.out.println("====================");
		System.out.println("LOCAL SINGLE MATCHES");
		System.out.println("====================");
		System.out.println("(Words that are used more than 2 times within a single A5-page of text.)");	
		System.out.println("(Words shorter than 5 characters are only listed when they are used 10 or more times.)");
		for(int i=0; i<wordList.size(); i++)
		{
			if(wordList.get(i).match31 < 4) continue;
			if(wordList.get(i).word1.equals("")) continue;
			if(wordList.get(i).match31 < 10 && wordList.get(i).word1.length() < 5) continue;
			
			// Was the same word already noted within short range?
			boolean noList = false;
			for(int j=i-300; j<i; j++)
			{ 
				if (j < 0) continue;
				if (wordList.get(i).word1.equals(wordList.get(j).word1))
				{
					noList = true;
					break;
				}
			}
			if(noList) continue;

			System.out.println("Pos " + i + ": " + wordList.get(i).match31 + ": " + wordList.get(i).word1);
		}
		System.out.println("====================");
		System.out.println("LOCAL DOUBLE MATCHES");
		System.out.println("====================");
		System.out.println("(Word combinations that are used more than 3 times within a single A5-page of text.)");	
		for(int i=0; i<wordList.size(); i++)
		{
			if(wordList.get(i).match32 < 3) continue;
			if(wordList.get(i).word1.equals("")) continue;
			
			// Was the same word combination already noted within short range?
			boolean noList = false;
			for(int j=i-300; j<i; j++)
			{ 
				if (j < 0) continue;
				if (wordList.get(i).word1.equals(wordList.get(j).word1) && wordList.get(i).word2.equals(wordList.get(j).word2))
				{
					noList = true;
					break;
				}
			}
			if(noList) continue;
			
			System.out.println("Pos " + i + ": " + wordList.get(i).match32 + ": " + wordList.get(i).word2);
		}
		System.out.println("=====================");
		System.out.println("LOCAL TRIPPLE MATCHES");
		System.out.println("=====================");
		System.out.println("(Word combinations that are used more than once within a single A5-page of text.)");	
		for(int i=0; i<wordList.size(); i++)
		{
			if(wordList.get(i).match33 < 2) continue;
			if(wordList.get(i).word1.equals("")) continue;
				
			// Was the same word combination already noted within short range?
			boolean noList = false;
			for(int j=i-300; j<i; j++)
			{ 
				if (j < 0) continue;
				if (wordList.get(i).word1.equals(wordList.get(j).word1) && wordList.get(i).word2.equals(wordList.get(j).word2) && wordList.get(i).word3.equals(wordList.get(j).word3))
				{
					noList = true;
					break;
				}
			}
			if(noList) continue;
			
			System.out.println("Pos " + i + ": " + wordList.get(i).match33 + ": " + wordList.get(i).word3);
		}		
		
		System.out.println("=============");
		System.out.println("DOUBLED WORDS");
		System.out.println("=============");
		System.out.println("(Words that are written twice in a row.)");	
		for(int i=0; i<wordList.size() - 1; i++)
		{
			// Was a word used twice in a row?
			String w1 = wordList.get(i).stub1;
			if(w1.equals("")) continue;
			String w2 = wordList.get(i+1).stub1;
			if (w1.equals(w2)) System.out.println("Pos " + i + ": " + wordList.get(i).word1 + " " + wordList.get(i+1).word1);
		}
		System.out.println("===============");
		System.out.println("DOUBLED DOUBLES");
		System.out.println("===============");
		System.out.println("(Word pairs that are written twice in a row.)");	
		for(int i=0; i<wordList.size() - 2; i++)
		{
			// Was a word used twice in a row?
			String w1 = wordList.get(i).stub2;
			if(w1.equals("")) continue;
			String w2 = wordList.get(i+2).stub2;
			if (w1.equals(w2)) System.out.println("Pos " + i + ": " + wordList.get(i).word2 + " + " + wordList.get(i+2).word2);
		}
		System.out.println("=============");
		System.out.println("CLOSE DOUBLES");
		System.out.println("=============");
		System.out.println("(Word pairs that are written twice in a row with 1 or 2 words in between.)");	
		for(int i=0; i<wordList.size() - 4; i++)
		{
			// Was a word used twice in a row?
			String w1 = wordList.get(i).stub2;
			if(w1.equals("")) continue;
			String w2 = wordList.get(i+3).stub2;
			String w3 = wordList.get(i+4).stub2;
			if (w1.equals(w2)) System.out.println("Pos " + i + ": " + wordList.get(i).word2 + " + " + wordList.get(i+3).word2);
			if (w1.equals(w3)) System.out.println("Pos " + i + ": " + wordList.get(i).word2 + " + " + wordList.get(i+4).word2);
		}

		Comparator<Word> alphabetize = (Word a, Word b) -> {
		    return -b.word4.compareTo(a.word4);
		};
		Collections.sort(wordList, alphabetize);		
		Comparator<Word> frequencyA1 = (Word a, Word b) -> {
		    return b.matchA1 - a.matchA1;
		};
		Collections.sort(wordList, frequencyA1);		
		System.out.println("=====================");
		System.out.println("GLOBAL SINGLE MATCHES");
		System.out.println("=====================");
		System.out.println("(Words that are used often throughout the text.)");
		System.out.println("(Shorter words need to be more frequent to be listed.)");
		for(int i=0; i<wordList.size(); i++)
		{
			if(wordList.get(i).matchA1 < 3) continue;
			if(wordList.get(i).word1.equals("")) continue;
			if(wordList.get(i).word1.length() <= 2 && wordList.get(i).matchA1 < wordList.size()/250) continue;
		    if(wordList.get(i).word1.length() <= 6 && wordList.get(i).matchA1 < wordList.size()/500) continue;
		    if(wordList.get(i).word1.length() <= 10 && wordList.get(i).matchA1 < wordList.size()/4000) continue;
			if(wordList.get(i).matchA1 < wordList.size()/8000) continue;
			System.out.println(wordList.get(i).matchA1 + ": " + wordList.get(i).word1);
		}
		Comparator<Word> frequencyA2 = (Word a, Word b) -> {
		    return b.matchA2 - a.matchA2;
		};
		Collections.sort(wordList, frequencyA2);				
		System.out.println("=====================");
		System.out.println("GLOBAL DOUBLE MATCHES");
		System.out.println("=====================");
		System.out.println("(Combinations on two words that are used often throughout the text.)");	
		for(int i=0; i<wordList.size(); i++)
		{
			if(wordList.get(i).matchA2 < 3) continue;
			if(wordList.get(i).word1.equals("")) continue;
			if(wordList.get(i).matchA2 < wordList.size()/3000) continue;
			System.out.println(wordList.get(i).matchA2 + ": " + wordList.get(i).word2);
		}
		Comparator<Word> frequencyA3 = (Word a, Word b) -> {
		    return b.matchA3 - a.matchA3;
		};
		Collections.sort(wordList, frequencyA3);				
		System.out.println("======================");
		System.out.println("GLOBAL TRIPPLE MATCHES");
		System.out.println("======================");
		System.out.println("(Combinations of three words that are used more than twice throughout the text.)");	
		for(int i=0; i<wordList.size(); i++)
		{
			if(wordList.get(i).matchA3 < 3) continue;
			if(wordList.get(i).word1.equals("")) continue;
			System.out.println(wordList.get(i).matchA3 + ": " + wordList.get(i).word3);
		}
		Comparator<Word> frequencyA4 = (Word a, Word b) -> {
		    return b.matchA4 - a.matchA4;
		};
		Collections.sort(wordList, frequencyA4);				
		System.out.println("========================");
		System.out.println("GLOBAL QUADRUPLE MATCHES");
		System.out.println("========================");
		System.out.println("(Combinations of four words that are used more than twice throughout the text.)");	
		for(int i=0; i<wordList.size(); i++)
		{
			if(wordList.get(i).matchA4 < 3) continue;
			if(wordList.get(i).word1.equals("")) continue;
			System.out.println(wordList.get(i).matchA4 + ": " + wordList.get(i).word4);
		}
		
		System.out.println("=======");
		System.out.println("THE END");
		System.out.println("=======");
	}
}
