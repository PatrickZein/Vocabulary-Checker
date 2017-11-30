package runner;

public class Word {
	public String word1;
	public String word2;
	public String word3;
	public String word4;
	public String stub1, stub2, stub3, stub4;
	public String variant1 = new String("");
	public String variant2 = new String("");
	public String variant3 = new String("");
	public String variant4 = new String("");
	public String locVar1 = new String("");
	public String locVar2 = new String("");
	public String locVar3 = new String("");
	public String locVar4 = new String("");
	boolean checkA1, checkA2, checkA3, checkA4, check31, check32, check33, check34;
	int matchA1, matchA2, matchA3, matchA4, match31, match32, match33, match34;

	public String phonetic(String s) {
		return s.replaceAll("x", "ks").replaceAll("z", "s").replaceAll("ge", "je").replaceAll("gi", "ji")
				.replaceAll("gy", "jy").replaceAll("gä", "jä").replaceAll("gö", "jö").replaceAll("ke", "tje")
				.replaceAll("ki", "tji").replaceAll("ky", "tjy").replaceAll("kä", "tjä").replaceAll("kö", "tjö")
				.replaceAll("á", "a").replaceAll("ae", "a").replaceAll("é", "e").replaceAll("è", "e")
				.replaceAll("ee", "e").replaceAll("í", "i").replaceAll("ó", "o").replaceAll("ou", "o")
				.replaceAll("u", "i").replaceAll("ü", "i").replaceAll("ú", "i").replaceAll("y", "i")
				.replaceAll("å", "o").replaceAll("ä", "a").replaceAll("ö", "o").replaceAll("och", "ok")
				.replaceAll("ah", "a").replaceAll("eh", "e").replaceAll("oh", "o").replaceAll("ch", "tj")
				.replaceAll("kj", "tj").replaceAll("sch", "sj").replaceAll("skj", "sj").replaceAll("stj", "sj")
				.replaceAll("hj", "j").replaceAll("q", "k").replaceAll("ck", "k").replaceAll("bb", "b")
				.replaceAll("dd", "d").replaceAll("ff", "f").replaceAll("gg", "g").replaceAll("jj", "j")
				.replaceAll("kk", "k").replaceAll("ll", "l").replaceAll("mm", "m").replaceAll("nn", "n")
				.replaceAll("pp", "p").replaceAll("rr", "r").replaceAll("ss", "s").replaceAll("tt", "t")
				.replaceAll("vv", "v");
	}

	public Word(String w1, String w2, String w3, String w4) {
		// "Stubs" är ett slags ordstammar, som möjliggör viss matching av
		// böjningsformer. Stubbarna är formade som generella fonetiska mönster!
		String stub1, stub2, stub3, stub4 = new String("");
		stub1 = phonetic(w1);
		stub2 = phonetic(w2);
		stub3 = phonetic(w3);
		stub4 = phonetic(w4);

		if (stub1.length() > 3)
			stub1 = stub1.substring(0, 4);
		if (stub2.length() > 3)
			stub2 = stub2.substring(0, 4);
		if (stub3.length() > 3)
			stub3 = stub3.substring(0, 4);
		if (stub4.length() > 3)
			stub4 = stub4.substring(0, 4);
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

	public void addVariant1(String s) {
		if (!this.variant1.contains(s))
			this.variant1 = this.variant1 + ", " + s;
	}

	public void addVariant2(String s) {
		if (!this.variant2.contains(s))
			this.variant2 = this.variant2 + ", " + s;
	}

	public void addVariant3(String s) {
		if (!this.variant3.contains(s))
			this.variant3 = this.variant3 + ", " + s;
	}

	public void addVariant4(String s) {
		if (!this.variant4.contains(s))
			this.variant4 = this.variant4 + ", " + s;
	}

	public void addLocVar1(String s) {
		if (!this.locVar1.contains(s))
			this.locVar1 = this.locVar1 + ", " + s;
	}

	public void addLocVar2(String s) {
		if (!this.locVar2.contains(s))
			this.locVar2 = this.locVar2 + ", " + s;
	}

	public void addLocVar3(String s) {
		if (!this.locVar3.contains(s))
			this.locVar3 = this.locVar3 + ", " + s;
	}

	public void addLocVar4(String s) {
		if (!this.locVar4.contains(s))
			this.locVar4 = this.locVar4 + ", " + s;
	}
}