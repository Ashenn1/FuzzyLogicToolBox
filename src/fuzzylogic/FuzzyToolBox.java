package fuzzylogic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class FuzzyToolBox {

	

	public int setNum; //number of sets in the fuzzy toolbox.
	public List<FuzzySet>fuzzySet = new ArrayList<>(setNum);
	public FuzzySet outputSet = new FuzzySet(); //fuzzy set of the output.
	public String[] inferenceRules;
	

	
	public int getSetNum() {
		return setNum;
	}

	public void setSetNum(int setNum) {
		this.setNum = setNum;
	}

	public List<FuzzySet> getFuzzySet() {
		return fuzzySet;
	}

	public void setFuzzySet(List<FuzzySet> fuzzySet) {
		this.fuzzySet = fuzzySet;
	}

	public FuzzySet getOutputSet() {
		return outputSet;
	}

	public void setOutputSet(FuzzySet outputSet) {
		this.outputSet = outputSet;
	}

	public String[] getInferenceRule() {
		return inferenceRules;
	}

	public void setInferenceRule(String[] inferenceRule) {
		this.inferenceRules = inferenceRule;
	}

	void readFile(String filepath) {
		FileReader filereader;
		BufferedReader in ;
		try {
			filereader = new FileReader(filepath);
			in = new BufferedReader(filereader);
			
			String[] y;
			
			
			setNum = Integer.parseInt(in.readLine());
			
			for(int i=0;i<setNum;i++) {
				
				FuzzySet fuzzyVal = new FuzzySet();
				List<LinguisticElement>lingArray = new ArrayList<>();
				
				y=in.readLine().split("\\s+");
				fuzzyVal.setSetName(y[0]);
				fuzzyVal.setCrispValue(Integer.parseInt(y[1]));
			   
				fuzzyVal.setSetCount(Integer.parseInt(in.readLine()));
				
				
				for(int j=0;j<fuzzyVal.getSetCount();j++) {
					LinguisticElement ling = new LinguisticElement();
					y=in.readLine().split("\\s+");
					ling.setName(y[0]); 
					ling.setType(y[1]);
					if(ling.getType().equals("trapezoidal")) { //get ready for 4 points right there.
						y=in.readLine().split("\\s+");
						List<Integer>range = new ArrayList(4);
						for(int p=0;p<4;p++)
							range.add(Integer.parseInt(y[p]));
						
						ling.setRange(range);
						lingArray.add(ling);
					}
					else if(ling.getType().equals("triangle")) { // 3 points it is then.
						
						y=in.readLine().split("\\s+");
						List<Integer>range = new ArrayList(3);
						for(int p=0;p<3;p++)
							range.add(Integer.parseInt(y[p]));
						
						ling.setRange(range);
						lingArray.add(ling);
					}
					fuzzyVal.setElements(lingArray);
					
				}
				
				fuzzySet.add(i, fuzzyVal);
				lingArray.clear();
			}
			outputSet.setSetName(in.readLine());
			
			outputSet.setSetCount(Integer.parseInt(in.readLine()));
			List<LinguisticElement>lingArray = new ArrayList<>();
			for(int i=0;i<outputSet.getSetCount();i++) {
				
				LinguisticElement ling = new LinguisticElement();
				y=in.readLine().split("\\s+");
				ling.setName(y[0]); 
				ling.setType(y[1]);
				if(ling.getType().equals("trapezoidal")) 
				{ //get ready for 4 points right there.
					y=in.readLine().split("\\s+");
					List<Integer>range = new ArrayList(4);
					for(int p=0;p<4;p++)
						range.add(Integer.parseInt(y[p]));
					
					ling.setRange(range);
					lingArray.add(ling);
				}
				else if(ling.getType().equals("triangle")) { 
					// 3 points it is then.
					
					y=in.readLine().split("\\s+");
					List<Integer>range = new ArrayList(3);
					for(int p=0;p<3;p++)
						range.add(Integer.parseInt(y[p]));
					
					ling.setRange(range);
					lingArray.add(ling);
				}
				
			}
			outputSet.setElements(lingArray);

			int numOfRules=0;
			String[] z;
			numOfRules = Integer.parseInt(in.readLine());
			for(int i=0;i<numOfRules;i++) {
				List<InferenceRule> rule=new ArrayList<InferenceRule>(numOfRules);
				 z = in.readLine().split("\\s+");
				 rule.get(i).numOfPremise = Integer.parseInt(z[0]);
				 for(int r=1;r<z.length-1;r++) { // loop to pass on premises.
					 if(z[r].equals("AND")) {
						  rule.get(i).type.add("AND");
					 }
					else if(z[r].equals("OR")) {
						rule.get(i).type.add("OR");
					}
						  
					else { // then it's a premise
						rule.get(i).Premise.add(z[r]);
					}
					 
					 }
						 
				 }
			
			lingArray.clear();
			

			
		} catch (FileNotFoundException e) {
			System.out.println("File is not found.");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("Problem with input/output.");
			e.printStackTrace();
		}	
	}
	
	void fuzzification() {
		LinguisticElement element = new LinguisticElement();
		for(int i = 0; i < setNum; i++) {
			for(int j = 0; j < fuzzySet.get(i).elements.size(); j++) {
				element.setName(fuzzySet.get(i).elements.get(j).getName());
				element.setType(fuzzySet.get(i).elements.get(j).getType());
				element.setRange(fuzzySet.get(i).elements.get(j).getRange());
				element.setMembershipValue(fuzzySet.get(i).elements.get(j).getMembershipValue());
				if(element.getType().equals("triangle")) {
					float a = element.getRange().get(0);
					float b = element.getRange().get(1);
					float c = element.getRange().get(2);
					float crispValue = fuzzySet.get(i).getCrispValue();
					
					if(crispValue <= a) {
						element.setMembershipValue(0);
					}
					else if(crispValue >= a && crispValue <= b) {
						element.setMembershipValue((crispValue - a)/ (b - a));
					}
					else if(crispValue >= b && crispValue <= c) {
						element.setMembershipValue((c - crispValue)/ (c - b));
					}
					else if(crispValue >= c) {
						element.setMembershipValue(0);
					}
				}
			
				else if(element.getType().equals("trapezoidal")) {
					float a = element.getRange().get(0);
					float b = element.getRange().get(1);
					float c = element.getRange().get(2);
					float d = element.getRange().get(3);
					float crispValue = fuzzySet.get(i).getCrispValue();
					
					if(crispValue <= a) {
						element.setMembershipValue(0);
					}
					else if(crispValue >= a && crispValue <= b) {
						element.setMembershipValue((crispValue - a)/ (b - a));
					}
					else if(crispValue >= b && crispValue <= c) {
						element.setMembershipValue(1);
					}
					else if(crispValue >= c && crispValue <= d) {
						element.setMembershipValue((d - crispValue) / (d - c));
					}
					else if(d <= crispValue) {
						element.setMembershipValue(0);
					}			
				}
				System.out.println(element.getName());
				System.out.println(element.getMembershipValue());
			}
		}
		
	}
	
	
	
	
	
	
	void show() {
		
		System.out.println("Number of fuzzy sets in this toolbox " + setNum );
		System.out.println("The fuzzy set: " );
		for(int i=0;i<setNum;i++) {
			System.out.println("fuzzy set " + i +" "+"Name: "+ fuzzySet.get(i).getSetName());
			System.out.println("fuzzy set " + i +" "+"crisp value "+ fuzzySet.get(i).getCrispValue());
			for(int j=0;j<fuzzySet.get(i).getElements().size();j++)
			System.out.println("fuzzy set " + i +" "+"elements Name: "+ fuzzySet.get(i).getElements().get(j).getName());
		}
		
		
		System.out.println(outputSet.getSetName());
			
			for(int j=0;j<outputSet.getElements().size();j++) {
				System.out.println("elements Name: "+ outputSet.getElements().get(j).getName());
			    System.out.println(outputSet.getElements().get(j).getRange());	
			}
			
			
		
	}
	
	
	
	
}
