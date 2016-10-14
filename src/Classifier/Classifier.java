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
      Knn classifier = new Knn(testData,trainData);
      classifier.calculateEuclideanDistances();
      classifier.findNNearestNeighbours(5);
      classifier.findDominantClass();
      System.out.println("");  
}
}
