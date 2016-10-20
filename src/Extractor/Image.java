package Extractor;

import java.io.Serializable;

public class Image implements Serializable {
	
	private static final long serialVersionUID = 4623746839237756244L;
	
private int imageId;
private double feature1;
private double feature2;
private double feature3;
private double feature4;

private transient int[] imageTable;

public int[] getImageTable() {
	return imageTable;
}

public void setImageTable(int[] imageTable) {
	this.imageTable = imageTable;
}

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

public double getFeature4() {
	return feature4;
}

public void setFeature4(double feature4) {
	this.feature4 = feature4;
}

Image(int[] image, int size, int label){
	imageTable= new int[size*size];
	imageTable=image;
	imageId= label;
}

public double calculateFirstFeature(){
	//pole cyfry
int tmp=0;
for(int i=0;i<imageTable.length;i++){
	if(imageTable[i]>0){
		tmp++;
	}
}
return tmp;
}
public double calculateSecondFeature(){
	
	//operatory sobela
	int[] sobelH = {-1,0,1,-2,0,2,-1,0,1};
	int[] sobelV = {-1,-2,-1,0,0,0,1,2,1};
	
	double [] tempH = new double[28*28];
	double [] tempV = new double[28*28];
	for(int j=1;j<27;j++){
	for(int i=1;i<27;i++){
		tempH[28*j+i]=(imageTable[28*j+i]*sobelH[4]+imageTable[(28*j+i)-1]*sobelH[3]+imageTable[(28*j+i)+1]*sobelH[5]
				+imageTable[(28*j+i)-29]*sobelH[0]+imageTable[(28*j+i)-28]*sobelH[1]+imageTable[(28*j+i)-27]*sobelH[2]
				+imageTable[(28*j+i)+27]*sobelH[6]+imageTable[(28*j+i)+28]*sobelH[7]+imageTable[(28*j+i)+29]*sobelH[8])/9;
	}
	}
	for(int j=1;j<27;j++){
	for(int i=1;i<27;i++){
		tempV[28*j+i]=(imageTable[28*j+i]*sobelV[4]+imageTable[(28*j+i)-1]*sobelV[3]+imageTable[(28*j+i)+1]*sobelV[5]
				+imageTable[(28*j+i)-29]*sobelV[0]+imageTable[(28*j+i)-28]*sobelV[1]+imageTable[(28*j+i)-27]*sobelV[2]
				+imageTable[(28*j+i)+27]*sobelV[6]+imageTable[(28*j+i)+28]*sobelV[7]+imageTable[(28*j+i)+29]*sobelV[8])/9;
	}
	}
	double avgX=0.0;
	double avgY=0.0;
	
	for(int i=0;i<28*28;i++){
		avgX+=tempH[i];
		avgY+=tempV[i];
	}
	
	avgX/=(28*28);
	avgY/=(28*28);
	return Math.atan(avgY/avgX);
}
public double calculateThirdFeature(){
	// srednia g³êbokoœæ X
	double depthX=0.0;
	double[] temp=new double[28];
	
	for(int i=0;i<28;i++){
		for(int j=0;j<28;j++){
			if(imageTable[28*i+j]>0){
				temp[i]+=1;
			}
		}
	}
	
	for(int i=0;i<28;i++){
		depthX+=temp[i];
	}
	return depthX/28;
}

public double calculateFourthFeature(){
	//srednia glebokosc Y
	double depthY=0.0;
	double[] temp=new double[28];
	
	for(int i=0;i<28;i++){
		for(int j=0;j<28;j++){
			if(imageTable[28*j+i]>0){
				temp[i]+=1;
			}
		}
	}
	
	for(int i=0;i<28;i++){
		depthY+=temp[i];
	}
	return depthY/28;
}
private double calculateMomentum(int p, int q, int[] tab){
	double sum=0.0;
	for(int i=0;i<28;i++){
		for(int j=0;j<28;j++){
			sum+=Math.pow(i, p)*Math.pow(j, q)*tab[j*28+i];
		}
	}
	return sum;
}
}
