package Extractor;

import java.io.Serializable;

public class Image implements Serializable {
	
	private static final long serialVersionUID = 4623746839237756244L;
	
private int imageId;
private int feature1;
private int feature2;
private int feature3;

public int getFeature1() {
	return feature1;
}

public void setFeature1(int feature1) {
	this.feature1 = feature1;
}

public int getFeature2() {
	return feature2;
}

public void setFeature2(int feature2) {
	this.feature2 = feature2;
}

public int getFeature3() {
	return feature3;
}

public void setFeature3(int feature3) {
	this.feature3 = feature3;
}

private transient int[] imageTable;

Image(int[] image, int size, int label){
	imageTable= new int[size*size];
	imageTable=image;
	imageId= label;
}

public int calculateFirstFeature(){
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
