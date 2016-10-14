package Classifier;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Extractor.Image;

public class Loader {
	
	private ArrayList<Image> images=null;
	
	Loader(){
		
	}
	
	public void loadData(String name){
	
		 try {
	         FileInputStream fileIn = new FileInputStream(name);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         images = (ArrayList<Image>) in.readObject();
	         in.close();
	         fileIn.close();
	         System.out.println("Image Data Loaded");
	      }catch(IOException i) {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c) {
	         System.out.println("Image Data not found");
	         c.printStackTrace();
	         return;
	      }
	}

}
