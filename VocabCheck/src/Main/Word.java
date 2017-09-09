package Main;

public class Word {
	String word1, word2, word3 = new String("");
	boolean checkA1, checkA2, checkA3, check31, check32, check33;
	int matchA1, matchA2, matchA3, match31, match32, match33;

	public Word(String w1, String w2, String w3) {
		// TODO Auto-generated constructor stub
		this.word1 = w1;
		this.word2 = w1 + " " + w2;
		this.word3 = w1 + " " + w2 + " " + w3;
		this.checkA1 = false;
		this.checkA2 = false;
		this.checkA3 = false;
		this.check31 = false;
		this.check32 = false;
		this.check33 = false;
		this.matchA1 = 1;
		this.matchA2 = 1;
		this.matchA3 = 1;
		this.match31 = 1;
		this.match32 = 1;
		this.match33 = 1;
	}

}
