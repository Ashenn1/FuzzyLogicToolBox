package fuzzylogic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		
		FuzzyToolBox toolbox = new FuzzyToolBox();
		
		toolbox.readFile("C:\\Users\\Soha Samad\\Desktop\\College\\Year 4\\Soft_Computing\\Tasks\\Assignment3-FuzzyLogic\\Lec-Sample.txt");
		toolbox.show();
		
}

}
