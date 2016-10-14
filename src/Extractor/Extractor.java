package Extractor;

import java.util.ArrayList;
import java.util.Scanner;

import Viewer.Loader;

public class Extractor {


public static void main(String[] args) {
        Loader loaderTrain = new Loader();
        loaderTrain.loadSet("train");
        FeatureProcessor processor= new FeatureProcessor(loaderTrain);
        processor.calculateFeatures();
        Output writer = new Output();
        writer.saveToFile("train_image_data.ser",processor);
        
        Loader loaderTest = new Loader();
        loaderTest.loadSet("t10k");
        FeatureProcessor processor2= new FeatureProcessor(loaderTest);
        processor2.calculateFeatures();
        Output writer2 = new Output();
        writer2.saveToFile("test_image_data.ser",processor2);
}
}
