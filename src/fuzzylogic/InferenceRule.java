package fuzzylogic;

import java.util.ArrayList;
import java.util.List;

public class InferenceRule {

	public List<String> type = new ArrayList(); //AND , OR , NOT.
	public int numOfPremise=0;
	public List<String> Premise = new ArrayList(); // 2 premise --> ["temp=warm"] , ["cover=sunny"].
	public String output; // Ex : "speed=fast"
	
	public InferenceRule() {
		super();
		
	}
	
	
	
}
