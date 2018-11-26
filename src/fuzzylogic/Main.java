package fuzzylogic;

public class Main {

	public static void main(String[] args) {
		
		
		FuzzyToolBox toolbox = new FuzzyToolBox();
		
		toolbox.readFile("Assignment3-Sample.txt");
		toolbox.fuzzification();
	    toolbox.Inference();
		toolbox.defuzzification();
		toolbox.show();
}

}
