package Extractor;

import java.util.ArrayList;

import Viewer.Loader;

public class FeatureProcessor {

	private ArrayList<Image> images;
	ArrayList<String> firstFeatureVector;
	ArrayList<String> secondFeatureVector;
	ArrayList<String> thirdFeatureVector;
	
	
	public ArrayList<String> getFirstFeatureVector() {
		return firstFeatureVector;
	}
	public void setFirstFeatureVector(ArrayList<String> firstFeatureVector) {
		this.firstFeatureVector = firstFeatureVector;
	}
	public ArrayList<String> getSecondFeatureVector() {
		return secondFeatureVector;
	}
	public void setSecondFeatureVector(ArrayList<String> secondFeatureVector) {
		this.secondFeatureVector = secondFeatureVector;
	}
	public ArrayList<String> getThirdFeatureVector() {
		return thirdFeatureVector;
	}
	public void setThirdFeatureVector(ArrayList<String> thirdFeatureVector) {
		this.thirdFeatureVector = thirdFeatureVector;
	}


	public FeatureProcessor(Loader loader){
		images = new ArrayList<Image>();
		for(int pos=1;pos<loader.getDataLength();pos++){
			int [] temp= new int[28*28];
			for (int i=1;i<27*27;i++){
				temp[i]=loader.getDataImages()[(i+16)+(28*28)*(pos-1)]&0xff;
			}
			images.add(new Image(temp,28,loader.getDataLabels()[pos+7]));
		}
	}
	public void calculateFeatures() {
		firstFeatureVector = new ArrayList<String>();
		for(Image i:images){
			firstFeatureVector.add(i.calculateFirstFeature());
		}		
	}
}
