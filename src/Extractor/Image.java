package Extractor;

public class Image {

	
private int imageId;
private int feature1;
private int feature2;
private int feature3;

private int[] imageTable;

Image(int[] image, int size, int label){
	imageTable= new int[size*size];
	imageTable=image;
	imageId= label;
}

public String calculateFirstFeature(){
int tmp=0;
for(int i=0;i<imageTable.length;i++){
	if(imageTable[i]>0){
		tmp++;
	}
}
feature1=tmp;
return Integer.toString(imageId)+" -> "+Integer.toString(tmp);
}
private void calculateSecondFeature(){
	
}
private void calculateThirdFeature(){
	
}

}
