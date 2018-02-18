package com.cw.codetoaction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;


public class BohdanFactory {

	private BohdanFactory() {}
	
	private final static File root = new File( "bohdans_classes" );
	private static URLClassLoader classLoader;
	static {
		if (!root.exists())
			root.mkdirs();
		try {
			classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static final String templateContent = "" +
			"import com.cw.codetoaction.CertainFighter;\r\n" +
			"import com.cw.codetoaction.GameEnvironment;\r\n" +
			"import com.cw.codetoaction.Bohdan;\r\n" +
			"import com.cw.models.FighterA;\r\n" +
			"public class %s extends Bohdan {\r\n" + 
			"    public void execute(CertainFighter self, GameEnvironment env) {\r\n" + 
			"        %s\r\n" + 
			"    }\r\n" + 
			"}\r\n";
	
	public static Bohdan getBohdan(String suffix, String code) {
		//System.out.println(templateContent);
		if (isSafeCode(code)) {
			if (writeBohdan(suffix, code)) {
				if (compileBohdan(suffix)) {
					System.out.println( "Code contains no errors. " );
					return getBohdan(suffix);
				} else {
					System.out.println( "Error in your code! " );
					return null; // TODO check
				}
			} else
				System.out.println("failed to write bohdan");
		} else {
			System.out.println("aborted: your code is not safe, your actions "
					+ "will be reported");
			//report(code);
		}
		return null;
	}
	
	private static boolean compileBohdan(String suffix) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		File sourceFile = new File( getFilePath(suffix) );
		if (compiler == null) {
			System.out.println("You probably didn't specify to run this program"
					+ " from JDK instead of JRE. Do this and try again.");
			System.exit(1);
		}
		return 0 == compiler.run(null, null, null, sourceFile.getPath());
	}

	private static Bohdan getBohdan(String suffix) {
		Bohdan res = null;
		try {
			Class<Bohdan> cls = (Class<Bohdan>) Class.forName( getClassName(suffix) , true, classLoader);
			res = cls.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	private static boolean writeBohdan(String suffix, String code) {
		File f = new File(getFilePath(suffix));
		String normalizedCode = code.replace("\n", ";\r\n        ");
		if (normalizedCode.length() >= 10)
			normalizedCode = normalizedCode.substring(0, normalizedCode.length() - 10);
		try ( BufferedWriter w = new BufferedWriter(new FileWriter(f)) ) {
			w.write(String.format(templateContent, getClassName(suffix), normalizedCode));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		//System.out.println(f.getAbsolutePath());
		return true;
	}
	
	private static String getClassName(String suffix) { 
		return String.format("Bohdan%s", suffix); 
	}
	
	private static String getFilePath(String suffix) { 
		return String.format(root.getPath() + File.separator + "Bohdan%s.java", suffix); 
	}
	
	private static boolean isSafeCode(String code) {
		return true;
	}

}
