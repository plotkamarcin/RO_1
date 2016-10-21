package extractor;

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
	//srodek ciezkosci cyfry reprezentowany jako wektor i wyznaczamy jego d�ugo��
	
int area=0;
for(int i=0;i<imageTable.length;i++){
	if(imageTable[i]>0){
		area++;
	}
}
double xPrime=0.0;
double yPrime=0.0;

for(int i=0;i<imageTable.length;i++){
	if(imageTable[i]>0){
		xPrime+=i;
	}
}
for(int i=0;i<27;i++){
	for(int j=0;j<27;j++){
			if(imageTable[j*27+i]>0){
		yPrime+=i;
	}
	}

}
xPrime=xPrime/area;
yPrime=yPrime/area;
return Math.pow((xPrime*xPrime+yPrime*yPrime), 0.5);

}
public double calculateSecondFeature(){
	
	//operatory sobela, wyznaczamy cyfre jako k�t wektora gradientu do osi OX
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
	return Math.atan2(avgX,avgY);
}
public double calculateThirdFeature(){
	//moment centralny M11
	double[]tmp= new double[28*28] ;
	for(int i=0;i<imageTable.length;i++){
		tmp[i]= normalize(imageTable[i]);
	}
	double m00 = calculateMomentum(0, 0, tmp);
	double m01 = calculateMomentum(0, 1, tmp);
	double m10 = calculateMomentum(1, 0, tmp);
	double m11 = calculateMomentum(1, 1, tmp);
	return m11-(m10/m00)*m01;
}

public double calculateFourthFeature(){
	//moment geometryczny M30
	double[]tmp= new double[28*28] ;
	for(int i=0;i<imageTable.length;i++){
		tmp[i]= normalize(imageTable[i]);
	}
	double m00 = calculateMomentum(0, 0, tmp);
	double m10 = calculateMomentum(1, 0, tmp);
	double m20 = calculateMomentum(2, 0, tmp);
	double m30 = calculateMomentum(3, 0, tmp);
	return m30-3*m20*(m10/m00)+2*m10*Math.pow((m10/m00), 2.0);
}

protected double calculateMomentum(int p, int q, double[] tab){
	double sum=0.0;
	for(int i=0;i<28;i++){
		for(int j=0;j<28;j++){
			sum+=Math.pow(i, p)*Math.pow(j, q)*tab[j*28+i];
		}
	}
	return sum;
}
protected double normalize(double value){
	return (value-0)/255.0;
}
}
