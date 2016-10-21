package extractor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class Output {

	
	public void saveToFile(String name, FeatureProcessor fp){
	      try {
	          FileOutputStream fileOut =
	          new FileOutputStream(name);
	          ObjectOutputStream out = new ObjectOutputStream(fileOut);
	          out.writeObject(fp.getImages());
	          out.close();
	          fileOut.close();
	          System.out.println("Serialized data is saved in "+name);
	       }catch(IOException i) {
	          i.printStackTrace();
	       }
	}
}
