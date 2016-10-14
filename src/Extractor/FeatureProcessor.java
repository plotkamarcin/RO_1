package Extractor;

import java.util.ArrayList;

import Viewer.Loader;

public class FeatureProcessor {

	private ArrayList<Image> images;
	

	public ArrayList<Image> getImages() {
		return images;
	}
	public void setImages(ArrayList<Image> images) {
		this.images = images;
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
		for(Image i:images){
			i.setFeature1(i.calculateFirstFeature());
		}		
	}

}
