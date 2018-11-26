package fuzzylogic;

import java.util.ArrayList;
import java.util.List;

public class InferenceRule {

	private List<String> type = new ArrayList<String>(); //AND , OR.
	private int numOfPremise=0;
	private List<String> Premise = new ArrayList<String>(); // 2 premise --> ["temp=warm"] , ["cover=sunny"].
	private String output; // Ex : "speed=fast"
	
	public InferenceRule() {
		super();
		
	}

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public int getNumOfPremise() {
		return numOfPremise;
	}

	public void setNumOfPremise(int numOfPremise) {
		this.numOfPremise = numOfPremise;
	}

	public List<String> getPremise() {
		return Premise;
	}

	public void setPremise(List<String> premise) {
		Premise = premise;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
}
