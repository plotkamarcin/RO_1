package Classifier;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import Extractor.Image;

public class Knn {
	private ArrayList<Image> testData;
	private ArrayList<Image> trainData;
	private ArrayList<ArrayList<DistanceMetric>> euclideanDistances;
	private ArrayList<ArrayList<DistanceMetric>> minkovskyDistances;
	private ArrayList<ArrayList<DistanceMetric>> chebyshevDistances;
	private ArrayList<ArrayList<DistanceMetric>> taxiDistances;
	private String[] results;

	public Knn(ArrayList<Image> test, ArrayList<Image> train) {
		this.testData = test;
		this.trainData = train;
		euclideanDistances = new ArrayList<ArrayList<DistanceMetric>>();
	}

	public void calculateEuclideanDistances() {

		double index = 0.0;

		for (Image i : testData) {
			if (index < 1.0) {
				ArrayList<DistanceMetric> tmp = new ArrayList<>();
				double eDistance = 0.0;
				for (int j = 0; j < trainData.size(); j++) {
					eDistance = Math.pow(Math.pow(i.getFeature1() - trainData.get(j).getFeature1(), 2.0)
							+ Math.pow(i.getFeature2() - trainData.get(j).getFeature2(), 2.0)
							+ Math.pow(i.getFeature3() - trainData.get(j).getFeature3(), 2.0), 0.5);
					tmp.add(new DistanceMetric(eDistance, trainData.get(j).getImageId()));
				}

				DecimalFormat formatter = new DecimalFormat("#0.000");
				System.out.println(formatter.format(((index++) / testData.size()) * 100) + "%");

				this.euclideanDistances.add(tmp);

			}
		}

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
			this.minkovskyDistances.add(tmp);

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
			this.chebyshevDistances.add(tmp);

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
			this.taxiDistances.add(tmp);

		}
	}

	public void findNNearestNeighbours(int numberOfNeighbours) {
		results = new String[numberOfNeighbours];
		Collections.sort(euclideanDistances.get(0), new DistanceMetricComparator());
		for (int i = 0; i < numberOfNeighbours; i++) {
			results[i] = Integer.toString(euclideanDistances.get(0).get(i).getLabel());
			System.out.println("Item label: " + Double.toString(euclideanDistances.get(0).get(i).getLabel())
					+ " distance " + Double.toString(euclideanDistances.get(0).get(i).getMetricValue()));
		}
	}

	public void findDominantClass() {
		Set<String> dm = new HashSet<String>(Arrays.asList(results));
		String[] uniqueValues = dm.toArray(new String[0]);
		int[] counts = new int[uniqueValues.length];
		for (int i = 0; i < uniqueValues.length; i++) {
			for (int j = 0; j < results.length; j++) {
				if (results[j].equals(uniqueValues[i])) {
					counts[i]++;
				}
			}
		}
		for (int i = 0; i < uniqueValues.length; i++)
			System.out.println("value :"+uniqueValues[i]+" occured: "+counts[i] + "times");

		int max = counts[0];
		for (int counter = 1; counter < counts.length; counter++) {
			if (counts[counter] > max) {
				max = counts[counter];
			}
		}
		System.out.println("max # of occurences for value "+uniqueValues[0]+": " + max);
	}

	public void showConfusionMatrix() {
          
	}
}
