package com.cw.codetoaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.cw.models.Fighter;
import com.cw.models.FighterA;

// TODO read this http://www.gamefromscratch.com/post/2011/11/15/Telling-Eclipse-to-use-the-JDK-instead-of-JRE.aspx
public class Main {

	public static void main(String[] args) {
		String code = "" +
			"int i = 41;\n" +
			"i++;\n" +
			"System.out.println(\"Code is compiled and executed at runtime; i = \" + i);\n";
		Bohdan b = BohdanFactory.getBohdan("007", code);
		b.execute(null, null);
		
		String code2 = "" +
				"System.out.println(\"Game started at: \" + env.getStartDatetime());\n" +
				"System.out.println(\"players: \" + env.getPlayersCount());\n" +
				"System.out.println(\"My name is: \" + self.getName());\n" + 
				"System.out.println(\"My level is: \" + self.getLvl());\n" +
				"if (self.getLvl() >= 5) \n" +
				"    System.out.println(\"I am awesome\");\n";
		Bohdan b2 = BohdanFactory.getBohdan("Pavuk", code2);
		GameEnvironment env = Configuration.getGameEnvironment();
		CertainFighter self = Configuration.getCertainFighter();
		b2.execute(self, env);
	}
	
}

class Configuration {
	static GameEnvironment getGameEnvironment () {
		return new GameEnvironment(2, new Date().toString(), new ArrayList<>(
					Arrays.asList(new FighterA[] { new Fighter(), new Fighter()})));
	}
	static CertainFighter getCertainFighter() {
		return new CertainFighter("Denys Melnichenko", 5);
	}
}