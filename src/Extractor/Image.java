package Extractor;

import java.io.Serializable;

public class Image implements Serializable {
	
	private static final long serialVersionUID = 4623746839237756244L;
	
private int imageId;
private double feature1;
private double feature2;
private double feature3;

public int getImageId() {
	return imageId;
}

public void setImageId(int imageId) {
	this.imageId = imageId;
}

public double getFeature1() {
	return feature1;
}

public void setFeature1(double feature1) {
	this.feature1 = feature1;
}

public double getFeature2() {
	return feature2;
}

public void setFeature2(double feature2) {
	this.feature2 = feature2;
}

public double getFeature3() {
	return feature3;
}

public void setFeature3(double feature3) {
	this.feature3 = feature3;
}

private transient int[] imageTable;

Image(int[] image, int size, int label){
	imageTable= new int[size*size];
	imageTable=image;
	imageId= label;
}

public double calculateFirstFeature(){
int tmp=0;
for(int i=0;i<imageTable.length;i++){
	if(imageTable[i]>0){
		tmp++;
	}
}
feature1=tmp;
return tmp;
}
private void calculateSecondFeature(){
	
}
private void calculateThirdFeature(){
	
}

}
