package fuzzylogic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		
		FuzzyToolBox toolbox = new FuzzyToolBox();
		
		toolbox.readFile("Lec-Sample.txt");
		toolbox.show();
		toolbox.fuzzification();
	    toolbox.InferenceBASIC();
		
}

}
