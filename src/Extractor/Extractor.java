package Extractor;

import java.util.ArrayList;
import java.util.Scanner;

import Viewer.Loader;

public class Extractor {



public static void main(String[] args) {
        Loader loader = new Loader();
        loader.loadSet("train-images");
        FeatureProcessor processor= new FeatureProcessor(loader);
        processor.calculateFeatures();
        Output writer = new Output();
        writer.saveToFile("image_data.ser",processor);
}
}
