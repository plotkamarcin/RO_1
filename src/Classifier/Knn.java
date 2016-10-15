package Classifier;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import Extractor.Image;

public class Knn {

	private class Result {
		private int originalLabel;
		private int classifiedLabel;

		public int getOriginalLabel() {
			return originalLabel;
		}

		public void setOriginalLabel(int originalLabel) {
			this.originalLabel = originalLabel;
		}

		public int getClassifiedLabel() {
			return classifiedLabel;
		}

		public void setClassifiedLabel(int classifiedLabel) {
			this.classifiedLabel = classifiedLabel;
		}

		public Result(int o, int c) {
			this.originalLabel = o;
			this.classifiedLabel = c;
		}
	}

	private ArrayList<Image> testData;
	private ArrayList<Image> trainData;
	private ArrayList<Result> finalResults;

	private String[] tempResults;

	private int[][] confusionMatrix;

	public Knn(ArrayList<Image> test, ArrayList<Image> train) {
		this.testData = test;
		this.trainData = train;
		this.finalResults = new ArrayList<Result>();
	}

	public void calculateEuclideanDistances(int objectIndex, int numberOfNeighbours) {

		ArrayList<DistanceMetric> tmpEuclid = new ArrayList<>();
		double eDistance = 0.0;
		for (int j = 0; j < trainData.size(); j++) {
			eDistance = Math.pow(
					Math.pow(testData.get(objectIndex).getFeature1() - trainData.get(j).getFeature1(), 2.0)
							+ Math.pow(testData.get(objectIndex).getFeature2() - trainData.get(j).getFeature2(), 2.0)
							+ Math.pow(testData.get(objectIndex).getFeature3() - trainData.get(j).getFeature3(), 2.0),
					0.5);
			tmpEuclid.add(new DistanceMetric(eDistance, trainData.get(j).getImageId()));
		}

		findNNearestNeighbours(numberOfNeighbours, tmpEuclid);
		findDominantClass(objectIndex);
	}

	public void calculateMinkovskyDistances() {

		double index = 0.0;
		for (Image i : testData) {
			ArrayList<DistanceMetric> tmp = new ArrayList<>();

			double mDistance = 0.0;
			for (int j = 0; j < trainData.size(); j++) {
				mDistance = Math.pow(Math.pow(i.getFeature1() - testData.get(j).getFeature1(), 3.0)
						+ Math.pow(i.getFeature2() - testData.get(j).getFeature2(), 3.0)
						+ Math.pow(i.getFeature3() - testData.get(j).getFeature3(), 3.0), 0.3333);
				tmp.add(new DistanceMetric(mDistance, trainData.get(j).getImageId()));
			}

			DecimalFormat formatter = new DecimalFormat("#0.000");
			System.out.println(formatter.format(((index++) / testData.size()) * 100) + "%");
			// this.minkovskyDistances.add(tmp);

		}
	}

	public void calculateChebyshevDistances() {

		double index = 0.0;
		for (Image i : testData) {
			ArrayList<DistanceMetric> tmp = new ArrayList<>();

			double cDistance = 0.0;

			for (int j = 0; j < trainData.size(); j++) {
				cDistance = Math.max(
						Math.max(i.getFeature1() - testData.get(j).getFeature1(),
								i.getFeature2() - testData.get(j).getFeature2()),
						i.getFeature3() - testData.get(j).getFeature3());
				tmp.add(new DistanceMetric(cDistance, trainData.get(j).getImageId()));
			}

			DecimalFormat formatter = new DecimalFormat("#0.000");
			System.out.println(formatter.format(((index++) / testData.size()) * 100) + "%");
			// this.chebyshevDistances.add(tmp);

		}
	}

	public void calculateTaxiDistances() {

		double index = 0.0;
		for (Image i : testData) {
			ArrayList<DistanceMetric> tmp = new ArrayList<>();

			double tDistance = 0.0;

			for (int j = 0; j < trainData.size(); j++) {
				tDistance = Math.abs(i.getFeature1() - testData.get(j).getFeature1())
						+ Math.abs(i.getFeature2() - testData.get(j).getFeature2())
						+ Math.abs(i.getFeature3() - testData.get(j).getFeature3());
				tmp.add(new DistanceMetric(tDistance, trainData.get(j).getImageId()));
			}

			DecimalFormat formatter = new DecimalFormat("#0.000");
			System.out.println(formatter.format(((index++) / testData.size()) * 100) + "%");
			// this.taxiDistances.add(tmp);

		}
	}

	private void findNNearestNeighbours(int numberOfNeighbours, ArrayList<DistanceMetric> list) {
		tempResults = new String[numberOfNeighbours];
		Collections.sort(list, new DistanceMetricComparator());
		for (int i = 0; i < numberOfNeighbours; i++) {
			tempResults[i] = Integer.toString(list.get(i).getLabel());
			System.out.println("Item label: " + Double.toString(list.get(i).getLabel()) + " distance "
					+ Double.toString(list.get(i).getMetricValue()));
		}
	}

	private void findDominantClass(int objectIndex) {
		Set<String> dm = new HashSet<String>(Arrays.asList(tempResults));
		String[] uniqueValues = dm.toArray(new String[0]);
		int[] counts = new int[uniqueValues.length];
		for (int i = 0; i < uniqueValues.length; i++) {
			for (int j = 0; j < tempResults.length; j++) {
				if (tempResults[j].equals(uniqueValues[i])) {
					counts[i]++;
				}
			}
		}
		for (int i = 0; i < uniqueValues.length; i++)
			System.out.println("value :" + uniqueValues[i] + " occured: " + counts[i] + "times");

		int max = counts[0];
		for (int counter = 1; counter < counts.length; counter++) {
			if (counts[counter] > max) {
				max = counts[counter];
			}
		}
		// System.out.println("Dominant class is "+uniqueValues[0]+" with " +
		// max +" occurences");
		int freq = 0;
		for (int counter = 0; counter < counts.length; counter++) {
			if (counts[counter] == max) {
				freq++;
			}
		}

		// index of most freq value if we have only one mode
		int index = -1;
		if (freq == 1) {
			for (int counter = 0; counter < counts.length; counter++) {
				if (counts[counter] == max) {
					index = counter;
					break;
				}
			}
			// System.out.println("one majority class, index is: "+index);
			System.out.println("Dominant label is " + uniqueValues[0] + " with " + max + " occurences");
			System.out.println("original label: "+testData.get(objectIndex).getImageId());
			finalResults.add(new Result(testData.get(objectIndex).getImageId(),Integer.parseInt(uniqueValues[index])));
		} else {// we have multiple modes
			int[] ix = new int[freq];// array of indices of modes
			System.out.println("There are: " + freq + " classes with same distance");
			int ixi = 0;
			for (int counter = 0; counter < counts.length; counter++) {
				if (counts[counter] == max) {
					ix[ixi] = counter;// save index of each max count value
					ixi++; // increase index of ix array
				}
			}

			for (int counter = 0; counter < ix.length; counter++)
				System.out.println("class label: " + uniqueValues[ix[counter]]);

			// now choose one at random
			Random generator = new Random();
			// get random number 0 <= rIndex < size of ix
			int rIndex = generator.nextInt(ix.length);
			System.out.println("Choosing random index: " + rIndex);
			int nIndex = ix[rIndex];
			// return unique value at that index
			System.out.println("Picked label: " + uniqueValues[nIndex]);
			System.out.println("original label: "+testData.get(objectIndex).getImageId());
			finalResults.add(new Result(testData.get(objectIndex).getImageId(),Integer.parseInt(uniqueValues[nIndex])));
		}
	}

	public void showConfusionMatrix() {
		confusionMatrix = new int[10][10];
		
		System.out.println("\n ");
		
		for(Result r:finalResults){
			Integer.toString(confusionMatrix[r.getOriginalLabel()][r.getClassifiedLabel()]++);
		}
		
		System.out.print("  ");
		for(int i=0;i<10;i++){
			System.out.print(Integer.toString(i)+"\t");
		}
		System.out.println(" ");
		
		
		for(int i=0;i<10;i++){
			System.out.print(i+" ");
			for(int j=0;j<10;j++){
				System.out.print(Integer.toString(confusionMatrix[i][j])+"\t");
			}
			System.out.println(" ");
		}
		double efficiency=0.0;
			for(int j=0;j<10;j++){
				efficiency+=confusionMatrix[j][j];
			}
			DecimalFormat formatter = new DecimalFormat("#0.000");
		System.out.println("Efficiency: "+formatter.format((efficiency/testData.size()*100))+" %");
        
	}
}
