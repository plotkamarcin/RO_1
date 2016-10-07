package Viewer;

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
		loader.loadSet();
		System.out.println("Podaj nr pozycji");
		Scanner in = new Scanner(System.in);
		String a = in.next();
		while(true){
		loader.showNumber(Integer.parseInt(a));
		System.out.println("\nPodaj nr pozycji");
		a=in.next();
		}
       
	}


}
