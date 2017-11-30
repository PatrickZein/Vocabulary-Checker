package runner;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Analyzer {
	public static String specialTrim(String word) {
		return word.toLowerCase().replace("¬", "").replace(",", "").replace(".", "").replace("!", "").replace("?", "")
				.replace(":a", "").replace(":e", "").replace(":", "").replace(";", "").replace("~", "").replace("-", "").replace("–", "").replace("+", "")
				.replace("%", "").replace("”", "").replace("’", "").replace("‘", "").replace("(", "").replace(")", "").replace("*", "")
				.replace("#", "").replace("/", "").replace("\\", "").replace("…", "").replace("•", "");
	}

	public static boolean isVowel(String c) {
		if (c.equals("a") || c.equals("e") || c.equals("i") || c.equals("o") || c.equals("u"))
			return true;
		else
			return false;
	}

	public static List<Word> readFile(File file) throws FileNotFoundException {
		List<Word> wordList = new ArrayList<Word>();
		long wordCounter = 0;

		// Read a text file into the word list
		System.out.println("Reading the text file...");
		Scanner s = null;
		try {
			s = new Scanner(file, "UTF-8");
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File not found!");
		}
		String word1 = "", word2 = "", word3 = "", word4 = "";
		while (s.hasNext()) {
			word1 = s.next();

			// Trim special characters from the word
			word1 = specialTrim(word1);

			if (word1.equals(""))
				continue;

			// Store the word
			wordList.add(new Word(word4, word3, word2, word1));
			wordCounter++;
			if (wordCounter % 100 == 0)
				System.out.println("Reading " + wordCounter + " words...");

			// Shift the words one step.
			word4 = word3;
			word3 = word2;
			word2 = word1;
		}
		s.close();
		return wordList;
	}

	public static void analyzeFile(List<Word> wordList) {
		long wordCounter = 0;

		System.out.println("Alayzing the text file...");
		for (int i = 0; i < wordList.size(); i++) {
			for (int j = i + 1; j < wordList.size(); j++) {
				// Compare word[i] with all following word[j]:
				if (wordList.get(i).checkA1 == false) {
					// Check single words - partial match on stubs are OK.
					String wordi = wordList.get(i).word1;
					String wordj = wordList.get(j).word1;
					String stubi = wordList.get(i).stub1;
					String stubj = wordList.get(j).stub1;
					if (wordi.length() >= wordj.length() * 2 || wordj.length() >= wordi.length() * 2)
						continue;
					if (stubi.equals(stubj) || (stubj.length() > 2 && wordi.startsWith(stubj))
							|| (stubi.length() > 2 && wordj.startsWith(stubi))) {
						wordList.get(i).matchA1++;
						// Mark the secondary object as checked
						wordList.get(j).checkA1 = true;
						// Memorize alternative spellings?
						if (!wordList.get(i).word1.equals(wordList.get(j).word1))
							wordList.get(i).addVariant1(wordList.get(j).word1);
					}
				}
				if (wordList.get(i).checkA2 == false) {
					// Check groups of two words
					String wordi = wordList.get(i).word2;
					String wordj = wordList.get(j).word2;
					String stubi = wordList.get(i).stub2;
					String stubj = wordList.get(j).stub2;
					if (wordi.length() >= wordj.length() * 2 || wordj.length() >= wordi.length() * 2)
						continue;
					if (stubi.equals(stubj) || (stubj.length() > 2 && wordi.startsWith(stubj))
							|| (stubi.length() > 2 && wordj.startsWith(stubi))) {
						wordList.get(i).matchA2++;
						// Mark the secondary object as checked
						wordList.get(j).checkA2 = true;
						// Memorize alternative spellings?
						if (!wordList.get(i).word2.equals(wordList.get(j).word2))
							wordList.get(i).addVariant2(wordList.get(j).word2);
					}
				}
				if (wordList.get(i).checkA3 == false) {
					// Check groups of three words
					String wordi = wordList.get(i).word3;
					String wordj = wordList.get(j).word3;
					String stubi = wordList.get(i).stub3;
					String stubj = wordList.get(j).stub3;
					if (stubi.equals(stubj) || (stubj.length() > 2 && wordi.startsWith(stubj))
							|| (stubi.length() > 2 && wordj.startsWith(stubi))) {
						wordList.get(i).matchA3++;
						// Mark the secondary object as checked
						wordList.get(j).checkA3 = true;
						// Memorize alternative spellings?
						if (!wordList.get(i).word3.equals(wordList.get(j).word3))
							wordList.get(i).addVariant3(wordList.get(j).word3);
					}
				}
				if (wordList.get(i).checkA4 == false) {
					// Check groups of three words
					if (wordList.get(i).stub4.equals(wordList.get(j).stub4)) {
						wordList.get(i).matchA4++;
						// Mark the secondary object as checked
						wordList.get(j).checkA4 = true;
						// Memorize alternative spellings?
						if (!wordList.get(i).word4.equals(wordList.get(j).word4))
							wordList.get(i).addVariant4(wordList.get(j).word4);
					}
				}
			}
			for (int j = i + 1; j < (i + 300) && j < wordList.size(); j++) {
				// Compare word[i] with up to 300 following word[j]:
				if (wordList.get(i).stub1.equals(wordList.get(j).stub1)) {
					wordList.get(i).match31++;
					// Memorize alternative spellings?
					if (!wordList.get(i).word1.equals(wordList.get(j).word1))
						wordList.get(i).addLocVar1(wordList.get(j).word1);
				}
				if (wordList.get(i).stub2.equals(wordList.get(j).stub2)) {
					wordList.get(i).match32++;
					// Memorize alternative spellings?
					if (!wordList.get(i).word2.equals(wordList.get(j).word2))
						wordList.get(i).addLocVar2(wordList.get(j).word2);
				}
				if (wordList.get(i).stub3.equals(wordList.get(j).stub3)) {
					wordList.get(i).match33++;
					// Memorize alternative spellings?
					if (!wordList.get(i).word3.equals(wordList.get(j).word3))
						wordList.get(i).addLocVar3(wordList.get(j).word3);
				}
				if (wordList.get(i).stub4.equals(wordList.get(j).stub4)) {
					wordList.get(i).match34++;
					// Memorize alternative spellings?
					if (!wordList.get(i).word4.equals(wordList.get(j).word4))
						wordList.get(i).addLocVar4(wordList.get(j).word4);
				}
			}
			wordCounter++;
			if (wordCounter % 100 == 0)
				System.out.println("Analyzing " + wordCounter + " words...");
		}
	}

	public static void localSingles(List<Word> wordList) {
		System.out.println("====================");
		System.out.println("LOCAL SINGLE MATCHES");
		System.out.println("====================");
		System.out.println("(Words used often within a single A5-page of text.)");
		for (int i = 0; i < wordList.size(); i++) {
			if (wordList.get(i).match31 < 5)
				continue;
			if (wordList.get(i).word1.equals(""))
				continue;
			if (wordList.get(i).word1.length() < 3 && wordList.get(i).match31 < 20)
				continue;
			if (wordList.get(i).word1.length() < 5 && wordList.get(i).match31 < 10)
				continue;

			// Was the same word already noted within short range?
			boolean noList = false;
			for (int j = i - 300; j < i; j++) {
				if (j < 0)
					continue;
				if (wordList.get(i).word1.equals(wordList.get(j).word1)) {
					noList = true;
					break;
				}
			}
			if (noList)
				continue;

			System.out.println("Pos " + i + ": " + wordList.get(i).match31 + ": " + wordList.get(i).word1
					+ wordList.get(i).locVar1);
		}
	}

	public static void localDoubles(List<Word> wordList) {
		System.out.println("====================");
		System.out.println("LOCAL DOUBLE MATCHES");
		System.out.println("====================");
		System.out.println("(Word combinations used often within a single A5-page of text.)");
		for (int i = 0; i < wordList.size(); i++) {
			if (wordList.get(i).match32 < 4)
				continue;
			if (wordList.get(i).word1.equals(""))
				continue;

			// Was the same word combination already noted within short range?
			boolean noList = false;
			for (int j = i - 300; j < i; j++) {
				if (j < 0)
					continue;
				// if (wordList.get(i).word1.equals(""))
				// continue;
				if (wordList.get(i).word1.equals(wordList.get(j).word1)
						&& wordList.get(i).word2.equals(wordList.get(j).word2)) {
					noList = true;
					break;
				}
			}
			if (noList)
				continue;

			System.out.println("Pos " + i + ": " + wordList.get(i).match32 + ": " + wordList.get(i).word2
					+ wordList.get(i).locVar2);
		}
	}

	public static void localTripples(List<Word> wordList) {
		System.out.println("=====================");
		System.out.println("LOCAL TRIPPLE MATCHES");
		System.out.println("=====================");
		System.out.println("(Word combinations used often twice within a single A5-page of text.)");
		for (int i = 0; i < wordList.size(); i++) {
			if (wordList.get(i).match33 < 3)
				continue;
			if (wordList.get(i).word1.equals(""))
				continue;

			// Was the same word combination already noted within short range?
			boolean noList = false;
			for (int j = i - 300; j < i; j++) {
				if (j < 0)
					continue;
				// if (wordList.get(i).word1.equals(""))
				// continue;
				if (wordList.get(i).word1.equals(wordList.get(j).word1)
						&& wordList.get(i).word2.equals(wordList.get(j).word2)
						&& wordList.get(i).word3.equals(wordList.get(j).word3)) {
					noList = true;
					break;
				}
			}
			if (noList)
				continue;

			System.out.println("Pos " + i + ": " + wordList.get(i).match33 + ": " + wordList.get(i).word3
					+ wordList.get(i).locVar3);
		}
	}

	public static void doubledWords(List<Word> wordList) {
		System.out.println("=============");
		System.out.println("DOUBLED WORDS");
		System.out.println("=============");
		System.out.println("(Words written twice in a row.)");
		for (int i = 0; i < wordList.size() - 1; i++) {
			// Was a word used twice in a row?
			String w1 = wordList.get(i).word1;
			String s1 = wordList.get(i).stub1;
			if (w1.length() < 3)
				continue;
			String w2 = wordList.get(i + 1).word1;
			String s2 = wordList.get(i + 1).stub1;
			if (w2.length() < 3)
				continue;
			if (w1.length() >= w2.length() * 2 || w2.length() >= w1.length() * 2)
				continue;
			if (s1.equals(s2) || w1.startsWith(s2) || w2.startsWith(s1)) {
				System.out.println("Pos " + i + ": " + w1 + " " + w2);
				i += 2;
			}
		}
		System.out.println("===============");
		System.out.println("DOUBLED DOUBLES");
		System.out.println("===============");
		System.out.println("(Word pairs written twice in a row.)");
		for (int i = 0; i < wordList.size() - 2; i++) {
			// Was a word used twice in a row?
			String w1 = wordList.get(i).word2;
			String s1 = wordList.get(i).stub2;
			if (s1.length() < 4)
				continue;
			String w2 = wordList.get(i + 2).word2;
			String s2 = wordList.get(i + 2).stub2;
			if (s2.length() < 4)
				continue;
			if (w1.length() > w2.length() * 2 || w2.length() > w1.length() * 2)
				continue;
			if (s1.equals(s2)) {
				System.out.println("Pos " + i + ": " + w1 + " + " + w2);
				i += 3;
			}
		}
		System.out.println("=============");
		System.out.println("CLOSE DOUBLES");
		System.out.println("=============");
		System.out.println("(Word pairs written twice in a row with one or two words in between.)");
		for (int i = 0; i < wordList.size() - 4; i++) {
			// Was a word used twice in a row?
			String w1 = wordList.get(i).word2;
			String s1 = wordList.get(i).stub2;
			if (w1.length() < 5)
				continue;
			String w2 = wordList.get(i + 1).word2;
			String s2 = wordList.get(i + 1).stub2;
			if (w2.length() < 5)
				continue;
			String w3 = wordList.get(i + 4).word2;
			String s3 = wordList.get(i + 4).stub2;
			if (w3.length() < 5)
				continue;
			if (s1.equals(s2)) {
				System.out.println("Pos " + i + ": " + w1 + " + " + w3);
				i += 3;
			}
			if (s1.equals(s3)) {
				System.out.println("Pos " + i + ": " + w1 + " + " + w3);
				i += 4;
			}
		}
	}

	public static void globalSingles(List<Word> wordList) {
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
		System.out.println(
				"(Phonetically matching words used often throughout the text. Shorter words need to be more frequent to get listed. These words may also show up in longer combinations!)");
		for (int i = 0; i < wordList.size(); i++) {
			if (wordList.get(i).matchA1 < 3)
				continue;
			// if (wordList.get(i).word1.equals(""))
			// continue;
			if (wordList.get(i).variant1.length() < 4)
				continue;
			if (wordList.get(i).word1.length() < 6 && wordList.get(i).matchA1 < wordList.size() / 500)
				continue;
			if (wordList.get(i).matchA1 < wordList.size() / 3000)
				continue;
			System.out.println(wordList.get(i).matchA1 + ": " + wordList.get(i).word1 + wordList.get(i).variant1);
		}
	}

	public static void globalDoubles(List<Word> wordList) {
		Comparator<Word> alphabetize = (Word a, Word b) -> {
			return -b.word4.compareTo(a.word4);
		};
		Collections.sort(wordList, alphabetize);
		Comparator<Word> frequencyA2 = (Word a, Word b) -> {
			return b.matchA2 - a.matchA2;
		};
		Collections.sort(wordList, frequencyA2);
		System.out.println("=====================");
		System.out.println("GLOBAL DOUBLE MATCHES");
		System.out.println("=====================");
		System.out.println(
				"(Combinations of two phonetically matching words used often throughout the text. These words may also show up in longer combinations!)");
		for (int i = 0; i < wordList.size(); i++) {
			if (wordList.get(i).matchA2 < 4)
				continue;
			// if (wordList.get(i).word1.equals(""))
			// continue;
			if (wordList.get(i).matchA2 < wordList.size() / 6000)
				continue;
			System.out.println(wordList.get(i).matchA2 + ": " + wordList.get(i).word2 + wordList.get(i).variant2);
		}
	}

	public static void globalTripples(List<Word> wordList) {
		Comparator<Word> alphabetize = (Word a, Word b) -> {
			return -b.word4.compareTo(a.word4);
		};
		Collections.sort(wordList, alphabetize);
		Comparator<Word> frequencyA3 = (Word a, Word b) -> {
			return b.matchA3 - a.matchA3;
		};
		Collections.sort(wordList, frequencyA3);
		System.out.println("======================");
		System.out.println("GLOBAL TRIPPLE MATCHES");
		System.out.println("======================");
		System.out.println(
				"(Combinations of three phonetically matching words used more than twice throughout the text. These words may also show up in longer combinations!)");
		for (int i = 0; i < wordList.size(); i++) {
			if (wordList.get(i).matchA3 < 3)
				continue;
			if (wordList.get(i).word1.equals(""))
				continue;
			if (wordList.get(i).matchA3 < wordList.size() / 12000)
				continue;
			System.out.println(wordList.get(i).matchA3 + ": " + wordList.get(i).word3 + wordList.get(i).variant3);
		}
	}

	public static void globalQuadruples(List<Word> wordList) {
		Comparator<Word> alphabetize = (Word a, Word b) -> {
			return -b.word4.compareTo(a.word4);
		};
		Collections.sort(wordList, alphabetize);
		Comparator<Word> frequencyA4 = (Word a, Word b) -> {
			return b.matchA4 - a.matchA4;
		};
		Collections.sort(wordList, frequencyA4);
		System.out.println("========================");
		System.out.println("GLOBAL QUADRUPLE MATCHES");
		System.out.println("========================");
		System.out
				.println("(Combinations of four phonetically matching words used more than once throughout the text.)");
		for (int i = 0; i < wordList.size(); i++) {
			if (wordList.get(i).matchA4 < 2)
				continue;
			// if ((wordList.get(i).matchA4 < 2) && (wordList.get(i).word1.length() < 4))
			// continue;
			if (wordList.get(i).matchA4 < wordList.size() / 24000)
				continue;
			// if (wordList.get(i).word1.equals(""))
			// continue;
			System.out.println(wordList.get(i).matchA4 + ": " + wordList.get(i).word4 + wordList.get(i).variant4);
		}
	}

	public static void unique(List<Word> wordList) {
		int uniqueCount = 0;
		int vocabCount = 0;

		Comparator<Word> alphabetize = (Word a, Word b) -> {
			return -b.word4.compareTo(a.word4);
		};
		Collections.sort(wordList, alphabetize);
		System.out.println("=============");
		System.out.println("ALLITERATIONS");
		System.out.println("=============");
		for (int i = 0; i < wordList.size(); i++) {
			if (wordList.get(i).stub3.length() < 1)
				continue;
			String[] wordgroup = wordList.get(i).stub4.split(" ");
			if (wordgroup.length < 3)
				continue;
			String c0 = wordgroup[0];
			if (c0.length() < 1)
				continue;
			c0 = "" + c0.charAt(0);

			String c1 = wordgroup[1];
			if (c1.length() < 1)
				continue;
			c1 = "" + c1.charAt(0);

			String c2 = wordgroup[2];
			if (c2.length() < 1)
				continue;
			c2 = "" + c2.charAt(0);

			String c3 = "";
			if (wordgroup.length > 3) {
				c3 = wordgroup[3];
				if (c3.length() < 1)
					continue;
				c3 = "" + c3.charAt(0);

			}
			if ((c0.equals(c1) && c0.equals(c2)) /* || (isVowel(c0) && isVowel(c1) && isVowel(c2) && isVowel(c3)) */) {
				if (c0.equals(c3) /* || (isVowel(c0) && isVowel(c1) && isVowel(c2) && isVowel(c3)) */) {
					System.out.println(wordList.get(i).word4);
					i += 4;
				} else {
					System.out.println(wordList.get(i).word3);
					i += 3;
				}
			}
		}

		Comparator<Word> frequencyA1 = (Word a, Word b) -> {
			return b.matchA1 - a.matchA1;
		};
		Collections.sort(wordList, frequencyA1);
		System.out.println("============");
		System.out.println("UNIQUE WORDS");
		System.out.println("============");
		System.out.println("(Words only used once in the entire text - not even any phonetically close matches!)");
		for (int i = 0; i < wordList.size(); i++) {
			if (wordList.get(i).checkA1 || wordList.get(i).check31)
				continue;
			vocabCount++;
			if (wordList.get(i).matchA1 > 1)
				continue;
			String word = wordList.get(i).word1.replace("0", "").replace("1", "").replace("2", "").replace("3", "").replace("4", "").replace("5", "").replace("6", "").replace("7", "").replace("8", "").replace("9", "");
			if(word.length() < 2)
				continue;
			uniqueCount++;
			System.out.println(word);
		}
		System.out.println("Full vocabulary: " + vocabCount + " distinct words.");
		System.out.println("Unique words: " + uniqueCount + " of " + wordList.size() + " words = "
				+ String.format("%.2f", ((float) (uniqueCount * 10000) / wordList.size()) / 100) + "%");
	}

	public static void fullAnalysis(List<Word> wordList) {
		// The first five methods work with the sequential list of words.
		analyzeFile(wordList);
		localSingles(wordList);
		localDoubles(wordList);
		localTripples(wordList);
		doubledWords(wordList);

		// The following four methods will re-sort all words.
		globalSingles(wordList);
		globalDoubles(wordList);
		globalTripples(wordList);
		globalQuadruples(wordList);

		// This last method is mostly for fun.
		unique(wordList);

		System.out.println("=======");
		System.out.println("THE END");
		System.out.println("=======");
	}

	public static String fileName = System.getProperty("user.dir") + "/src/runner/analyzefile.txt";

	public static void analyze() throws FileNotFoundException {
		List<Word> wordList = new ArrayList<Word>();
		File file = new File(fileName);
		wordList = readFile(file);
		fullAnalysis(wordList);
	}

	public static void main(String[] args) throws FileNotFoundException {
		analyze();
	}
}