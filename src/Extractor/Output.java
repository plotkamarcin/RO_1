package Extractor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Output {

	
	public void saveToFile(String name, FeatureProcessor fp){
	    PrintWriter pw;
		try {
			pw = new PrintWriter(new FileOutputStream(name));
		    for (String s : fp.firstFeatureVector)
		        pw.println(s);
		    pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		

		}
	}
}
