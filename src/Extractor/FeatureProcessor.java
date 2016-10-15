package Extractor;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.util.ArrayList;

import Viewer.Loader;
import Viewer.StarLoader;

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
	public FeatureProcessor(StarLoader loader){
		images = new ArrayList<Image>();
		int[]tmp = new int[256*256];
		int index=0;
		for(BufferedImage img:loader.getImages()){
			
			byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
			for(int i=0;i<pixels.length;i++){
				tmp[i]=pixels[i]&0xff;
			}
			images.add(new Image(tmp,256,index/25));
			index++;
		}
	}
	public void calculateFeatures() {
		for(Image i:images){
			i.setFeature1(i.calculateFirstFeature());
		}		
	}

}
