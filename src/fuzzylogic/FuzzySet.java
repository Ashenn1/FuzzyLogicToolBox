package fuzzylogic;

import java.util.ArrayList;
import java.util.List;

public class FuzzySet {

	
	public int setCount; //number of linguistic variables in the fuzzy set.
	public String setName; // Ex: Temperature , Cover
	List<LinguisticElement>elements = new ArrayList<>(setCount); //each element contains --> (name(freezing) ,type(trapezoidal), range(0,0,30,50))
	
	
	
}
