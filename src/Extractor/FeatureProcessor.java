package Extractor;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
		
		int index=0;
		
		for(int j=0;j<loader.getImages().size();j++){
			int[]tmp = new int[256*256];
			byte[] pixels=null;
			 pixels= ((DataBufferByte) loader.getImages().get(j).getRaster().getDataBuffer()).getData();
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
	public void showImage(int pos){
		BufferedImage img = new BufferedImage(256, 256, BufferedImage.TYPE_BYTE_INDEXED);
		img.setRGB(0, 0,256,256,images.get(pos).getImageTable(),0,256);
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(img)));
		frame.pack();
		frame.setVisible(true);
	}

}
