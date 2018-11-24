package fuzzylogic;

import java.util.ArrayList;
import java.util.List;

public class FuzzySet {

	
	
	public int setCount; //number of linguistic variables in the fuzzy set.
	public String setName; // Ex: Temperature , Cover
	public int crispValue;
	public List<LinguisticElement>elements = new ArrayList<>(setCount); //each element contains --> (name(freezing) ,type(trapezoidal), range(0,0,30,50))
	public float centroid;
	
	


	public FuzzySet(int setCount, String setName, int crispValue, List<LinguisticElement> elements) {
		super();
		this.setCount = setCount;
		this.setName = setName;
		this.crispValue = crispValue;
		
		for(int i=0;i<elements.size();i++) {
			this.elements.add(i,elements.get(i));
		}
		elements.clear();
		
	}
	
	
	public FuzzySet() {
		
	}

	
	public int getSetCount() {
		return setCount;
	}
	public void setSetCount(int setCount) {
		this.setCount = setCount;
	}
	public String getSetName() {
		return setName;
	}
	public void setSetName(String setName) {
		this.setName = setName;
	}
	public int getCrispValue() {
		return crispValue;
	}
	public void setCrispValue(int crispValue) {
		this.crispValue = crispValue;
	}
	public List<LinguisticElement> getElements() {
		return elements;
	}
	public void setElements(List<LinguisticElement> elements) {
		for(int i=0;i<elements.size();i++) {
			this.elements.add(i,elements.get(i));
			
		}
		elements.clear();
	}
	
	
	
}
