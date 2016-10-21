package viewer;

import java.awt.event.KeyEvent;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.security.auth.kerberos.KerberosKey;


public class ImageViewer {
	

	   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Loader loader = new Loader();
		loader.loadSet("train-images");
		ImageViewerGui iw = new ImageViewerGui(loader);
        iw.setVisible(true);
        loader.showNumber(23);
	}



}
