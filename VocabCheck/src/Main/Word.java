package Main;

public class Word {
	String word1, word2, word3, word4 = new String("");
	String stub1, stub2, stub3, stub4 = new String("");
	boolean checkA1, checkA2, checkA3, checkA4, check31, check32, check33, check34;
	int matchA1, matchA2, matchA3, matchA4, match31, match32, match33, match34;

	public Word(String w1, String w2, String w3, String w4) {
		// "Stubs" är ett slags ordstammar, som möjliggör viss matching av böjningsformer
		String stub1, stub2, stub3, stub4 = new String("");
		if (w1.length() > 3) stub1 = w1.substring(0, w1.length() - 2); else stub1 = w1;
		if (w2.length() > 3) stub2 = w2.substring(0, w2.length() - 2); else stub2 = w2;
		if (w3.length() > 3) stub3 = w3.substring(0, w3.length() - 2); else stub3 = w3;
		if (w4.length() > 3) stub4 = w4.substring(0, w4.length() - 2); else stub4 = w4;
		this.stub1 = stub1;
		this.stub2 = stub1 + " " + stub2;
		this.stub3 = stub1 + " " + stub2 + " " + stub3;
		this.stub4 = stub1 + " " + stub2 + " " + stub3 + " " + stub4;
		this.word1 = w1;
		this.word2 = w1 + " " + w2;
		this.word3 = w1 + " " + w2 + " " + w3;
		this.word4 = w1 + " " + w2 + " " + w3 + " " + w4;
		this.checkA1 = false;
		this.checkA2 = false;
		this.checkA3 = false;
		this.checkA4 = false;
		this.check31 = false;
		this.check32 = false;
		this.check33 = false;
		this.check34 = false;
		this.matchA1 = 1;
		this.matchA2 = 1;
		this.matchA3 = 1;
		this.matchA4 = 1;
		this.match31 = 1;
		this.match32 = 1;
		this.match33 = 1;
		this.match34 = 1;
	}
}
