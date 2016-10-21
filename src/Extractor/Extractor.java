package extractor;

import featrueExtractors.*;
import viewer.*;

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
        starProcessor.showImage(starProcessor.getStarImages().get(0).getImageTable());
        
        StarLoader starPlainSet = new StarLoader();
        starPlainSet.loadSet("E:\\ro\\STaR_database\\test_plain\\");
        FeatureProcessor starPlainProcessor = new FeatureProcessor(starPlainSet);
        starProcessor.calculateStarFeatures();
        Output writerStarPlainSet = new Output();
        writerStarPlainSet.saveToFile("plain_starImages.ser",starPlainProcessor);
        starPlainProcessor.showImage(starPlainProcessor.getStarImages().get(15).getImageTable());
        
        HarrisEdgeExtractor harris = new HarrisEdgeExtractor();
        SobelEdgeExtractor sobel = new SobelEdgeExtractor();
        int temp[] = new int[256*256];
        temp=starPlainProcessor.getStarImages().get(15).getImageTable();
        harris.init(temp, 256, 256, 0.12);
        sobel.init(temp, 256, 256);
        int result1[]=harris.process();
        int result2[]=sobel.process();
        result2=sobel.threshold(result2, 50);
        starPlainProcessor.showImage(result1);
        starPlainProcessor.showImage(result2);
        int res = harris.getProgress();
}
}
