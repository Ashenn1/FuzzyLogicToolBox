package fuzzylogic;

public class Main {

	public static void main(String[] args) {
		
		
		FuzzyToolBox toolbox = new FuzzyToolBox();
		
		toolbox.readFile("Lec-Sample.txt");
		toolbox.fuzzification();
	    //toolbox.InferenceBASIC();
	    toolbox.Inference();
		toolbox.defuzzification();
		//toolbox.show();
}

}
