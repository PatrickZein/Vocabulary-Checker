package test;

import org.junit.Test;

import runner.Analyzer;
import runner.Word;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Tester {
	@Test
	public void nullTest() {
	}

	@Test
	public void fileNotFoundErrorTest() {
		List<Word> wordList = new ArrayList<Word>();
		File file = new File("#missing#file#.#txt#");
		try {
			wordList = Analyzer.readFile(file);
		} catch (FileNotFoundException e) {
			System.out.println("Expected error: FileNotFound");
		}
		System.out.println("Wordlist size: " + wordList.size());
	}

	@Test
	public void specialTrimTest() {
		String str1 = Analyzer.specialTrim("abc-~,.!?");
		String str2 = Analyzer.specialTrim("%():;*abc");
		assertEquals(str1, str2);
	}

	@Test
	public void testVowelTrue() {
		assertTrue(Analyzer.isVowel("a") && Analyzer.isVowel("e") && Analyzer.isVowel("i") && Analyzer.isVowel("o")
				&& Analyzer.isVowel("u"));
	}

	@Test
	public void testVowelFalse() {
		assertFalse(Analyzer.isVowel("b"));
	}

	@Test
	public void smallWordMatchingTest() {
		String word1 = "abcdef";
		String word2 = "efghij";
		String word3 = "ijklmn";
		String word4 = "mnopqr";
		String word5 = "q";
		String word6 = "r";
		String word7 = "s";
		String word8 = "tq";
		String word9 = "";
		String word0 = "~+-!:;?";

		List<Word> wordList = new ArrayList<Word>();
		wordList.add(new Word(word4, word3, word2, word1));
		wordList.add(new Word(word5, word4, word3, word2));
		wordList.add(new Word(word6, word5, word4, word3));
		wordList.add(new Word(word7, word6, word5, word4));
		wordList.add(new Word(word4, word3, word2, word1));
		wordList.add(new Word(word4, word3, word2, ""));
		wordList.add(new Word(word4, word3, "", word1));
		wordList.add(new Word(word4, "", word2, word1));
		wordList.add(new Word("", word3, word2, word1));
		wordList.add(new Word("", "", word2, word1));
		wordList.add(new Word("", "", "", word1));
		wordList.add(new Word("", "", "", "!:;"));
		wordList.add(new Word("", "", "", ""));
		wordList.add(new Word(word4, word3, word2, word1 + "yx"));
		wordList.add(new Word(word4, word3, word2 + "yx", word1));
		wordList.add(new Word(word4, word3 + "yx", word2, word1));
		wordList.add(new Word(word4 + "yx", word3, word2, word1));
		wordList.add(new Word(word8, word7, word6, word5));
		wordList.add(new Word("", word3, word2, word1));
		wordList.add(new Word(word8, word7, word6, word5));
		wordList.add(new Word(word4, word3, word1, word1));
		wordList.add(new Word(word5, word3, word1, word1));
		wordList.add(new Word(word4, word3, word1, word1));
		wordList.add(new Word(word4, word5, word1, word1));
		wordList.add(new Word(word4, word3, word1, word1));
		wordList.add(new Word(word4, word3, word5, word1));
		wordList.add(new Word(word4, word3, word1, word5));
		wordList.add(new Word("a", word3 + "ab", word1, word1));
		wordList.add(new Word(word4, "b", word1 + "ab", word1));
		wordList.add(new Word(word4, word3, "c", word1 + "ab"));
		wordList.add(new Word(word4 + "ab", word3, "d", word5));

		wordList.add(new Word(word8, word7, word6, word5));
		wordList.add(new Word(word8 + "xyzy", word7, word6, word5));
		wordList.add(new Word(word8, word7 + "xyzy", word6, word5));
		wordList.add(new Word(word8, word7, word6 + "xyzy", word5));
		wordList.add(new Word(word8, word7, word6, word5 + "xyzy"));
		wordList.add(new Word(word8, word7, word6, word5 + "xzy"));
		wordList.add(new Word(word8, word7, word6, word5 + "x"));
		wordList.add(new Word(word0, word9, word1, word2));

		wordList.get(1).addLocVar1("a");
		wordList.get(1).addLocVar1("abcde");
		wordList.get(1).addLocVar2("");
		wordList.get(1).addLocVar3("abcd");
		wordList.get(1).addLocVar4("local4");
		wordList.get(1).addVariant1("abcd");
		wordList.get(1).addVariant2("var2");
		wordList.get(1).addVariant3("abcd");
		wordList.get(1).addVariant4("var4");

		Analyzer.fullAnalysis(wordList);
		assertNotSame(wordList.get(1).word1, wordList.get(2).word1);
	}

	@Test
	public void fullTextAnalysisTest() throws FileNotFoundException {
		short k = 9;
		int i = 9;
		Boolean b = (k == i);
		
		Analyzer.fileName = System.getProperty("user.dir") + "/src/test/textfile.txt";
		Analyzer.analyze();
	}
}
