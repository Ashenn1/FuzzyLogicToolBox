package fuzzylogic;

import java.util.ArrayList;
import java.util.List;

public class LinguisticElement {

	

	private String name; // Ex : (Freezing , warm , cool).
	private String type; // Ex : (triangle , trapezoidal)
	private List<Float>Range = new ArrayList<>(); // Ex: (30 50 70) 3 or 4 points depending on it being triangular or trapezoidal.
	private float membershipValue = -1; //after fuzzification, this is the membership ship value of the crisp value 
	
	public LinguisticElement(String name, String type, List<Float> range) {
		super();
		this.name = name;
		this.type = type;
		Range = range;
	}
	
	
	public LinguisticElement() {
		
	}

	public float getMembershipValue() {
		return membershipValue;
	}


	public void setMembershipValue(float membershipValue) {
		this.membershipValue = membershipValue;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public float getRangeByIndex(int i) {
		return Range.get(i);
	}
	
	public List<Float> getRange() {
		return Range;
	}
	
	public void setRange(List<Float> range) {
		for(int i=0;i<range.size();i++) {
			Range.add(i, range.get(i));
		}
		
	}
	
	
	
	
	
	
	
}
