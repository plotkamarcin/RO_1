package Extractor;

import java.io.Serializable;

public class StarImage extends Image implements Serializable{

	private static final long serialVersionUID = -1314368100390599483L;
	
	StarImage(int[] image, int size, int label){
		super(image,size,label);
	}
    @Override
	public double calculateFirstFeature(){
       return 0.0;
	}
    @Override
	public double calculateSecondFeature(){
		return 0.0;
	}
    @Override
	public double calculateThirdFeature(){
		return 0.0;
	}

}
