package Extractor;

import java.util.ArrayList;
import java.util.Scanner;

import Viewer.Loader;
import Viewer.StarLoader;

public class Extractor {


public static void main(String[] args) {
        Loader loaderTrain = new Loader();
        loaderTrain.loadSet("train");
        FeatureProcessor processor= new FeatureProcessor(loaderTrain);
        processor.calculateFeatures();
        Output trainDigits = new Output();
        trainDigits.saveToFile("train_image_data.ser",processor);
        
        Loader loaderTest = new Loader();
        loaderTest.loadSet("t10k");
        FeatureProcessor processor2= new FeatureProcessor(loaderTest);
        processor2.calculateFeatures();
        Output testDigits = new Output();
        testDigits.saveToFile("test_image_data.ser",processor2);
        
        StarLoader starTrainSet = new StarLoader();
        starTrainSet.loadSet("E:\\ro\\STaR_database\\train\\");
        FeatureProcessor starProcessor = new FeatureProcessor(starTrainSet);
        starProcessor.calculateStarFeatures();
        Output writerStarTrain = new Output();
        writerStarTrain.saveToFile("train_starImages.ser",starProcessor);
        starProcessor.showImage(0);
        
        StarLoader starPlainSet = new StarLoader();
        starPlainSet.loadSet("E:\\ro\\STaR_database\\test_plain\\");
        FeatureProcessor starPlainProcessor = new FeatureProcessor(starPlainSet);
        starProcessor.calculateStarFeatures();
        Output writerStarPlainSet = new Output();
        writerStarPlainSet.saveToFile("plain_starImages.ser",starPlainProcessor);
        starPlainProcessor.showImage(0);

}
}
