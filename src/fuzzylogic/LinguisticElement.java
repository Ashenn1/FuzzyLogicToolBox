package fuzzylogic;

import java.util.ArrayList;
import java.util.List;

public class LinguisticElement {

	

	public String name; // Ex : (Freezing , warm , cool).
	public String type; // Ex : (Triangular , Trapezoidal)
	public List<Integer>Range = new ArrayList<>(); // Ex: (30 50 70) 3 or 4 points depending on it being triangular or trapezoidal.
	
	public LinguisticElement(String name, String type, List<Integer> range) {
		super();
		this.name = name;
		this.type = type;
		Range = range;
	}
	
	
	public LinguisticElement() {
		
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
	public List<Integer> getRange() {
		return Range;
	}
	
	public void setRange(List<Integer> range) {
		for(int i=0;i<range.size();i++) {
			Range.add(i, range.get(i));
		}
		
	}
	
	
	
	
	
	
	
}
