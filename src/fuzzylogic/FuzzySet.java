package fuzzylogic;

import java.util.ArrayList;
import java.util.List;

public class FuzzySet {

	private int setCount; //number of linguistic variables in the fuzzy set.
	private String setName; // Ex: Temperature , Cover
	private float crispValue;
	private List<LinguisticElement>elements = new ArrayList<>(setCount); //each element contains --> (name(freezing) ,type(trapezoidal), range(0,0,30,50))
	private float centroid;

	public FuzzySet(int setCount, String setName, int crispValue, List<LinguisticElement> elements) {
		super();
		this.setCount = setCount;
		this.setName = setName;
		this.crispValue = crispValue;
		
		for(int i = 0; i < elements.size(); i++) {
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
	
	public float getCrispValue() {
		return crispValue;
	}
	
	public void setCrispValue(float f) {
		this.crispValue = f;
	}
	
	public List<LinguisticElement> getElements() {
		return elements;
	}
	
	public void setElements(List<LinguisticElement> elements) {
		for(int i = 0; i < elements.size(); i++) {
			this.elements.add(i,elements.get(i));
			
		}
		elements.clear();
	}
	
	public float getCentroid() {
		return centroid;
	}

	public void setCentroid(float centroid) {
		this.centroid = centroid;
	}
}
