package Classifier;

import java.util.ArrayList;

import Extractor.FeatureProcessor;
import Extractor.Image;
import Extractor.Output;

public class Classifier {

	ArrayList<Image> testData;
	ArrayList<Image> trainData;

	public static void main(String[] args) {
		ArrayList<Image> trainData = new Loader().loadData("train_image_data.ser");
		ArrayList<Image> testData = new Loader().loadData("test_image_data.ser");
		//ArrayList<Image> starTrainData=new Loader().loadData("train_starImages.ser");
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
