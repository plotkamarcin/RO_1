package classifier;

import java.util.ArrayList;

import extractor.FeatureProcessor;
import extractor.Image;
import extractor.Output;
import extractor.StarImage;

public class Classifier {

	ArrayList<Image> testData;
	ArrayList<Image> trainData;

	public static void main(String[] args) {
		ArrayList<Image> trainData = new Loader().loadData("train_image_data.ser");
		ArrayList<Image> testData = new Loader().loadData("test_image_data.ser");
		ArrayList<StarImage> starTrainData=new Loader().loadData("train_starImages.ser");
		ArrayList<StarImage> starPlainData=new Loader().loadData("plain_starImages.ser");
		ArrayList<StarImage> starLightData=new Loader().loadData("light_starImages.ser");
		ArrayList<StarImage> star30degData=new Loader().loadData("30degSet_starImages.ser");
		Knn classifier = new Knn(testData, trainData);
		for (int i = 0; i <testData.size(); i++) {
			classifier.calculateEuclideanDistances(i, 100);
			//classifier.calculateMinkovskyDistances(i, 100);
			//classifier.calculateChebyshevDistances(i, 100);
			//classifier.calculateTaxiDistances(i, 100);
		}
		classifier.showConfusionMatrix();
		System.out.println("");
	}
}
