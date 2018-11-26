package fuzzylogic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FuzzyToolBox {

	private int setNum; //number of sets in the fuzzy toolbox.
	private List<FuzzySet>fuzzySet = new ArrayList<>(setNum);
	private FuzzySet outputSet = new FuzzySet(); //fuzzy set of the output , membershipvalue in the linguistic element contains the inference output.
	private int numOfRules = 0;
	private List<InferenceRule> inferenceRules;
	private float result = 0;
	private Map<String, Float> table = new HashMap<>();		//to map between linguistic element name and its membership value
		
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

	

	void readFile(String filepath) {
		FileReader filereader;
		BufferedReader in ;
		try {
			filereader = new FileReader(filepath);
			in = new BufferedReader(filereader);
			String[] y;
			
			setNum = Integer.parseInt(in.readLine());
			
			for(int i = 0; i < setNum; i++) {
				FuzzySet fuzzyVal = new FuzzySet();
				List<LinguisticElement>lingArray = new ArrayList<>();
				
				y = in.readLine().split("\\s+");
				fuzzyVal.setSetName(y[0]);
				fuzzyVal.setCrispValue(Float.parseFloat(y[1]));
			    fuzzyVal.setSetCount(Integer.parseInt(in.readLine()));
				
				for(int j = 0; j < fuzzyVal.getSetCount(); j++) {
					LinguisticElement ling = new LinguisticElement();
					y = in.readLine().split("\\s+");
					ling.setName(y[0]); 
					ling.setType(y[1]);
					
					if(ling.getType().equals("trapezoidal")) { //get ready for 4 points right there.
						y = in.readLine().split("\\s+");
						List<Float>range = new ArrayList<Float>(4);
						
						for(int p = 0; p < 4; p++)
							range.add(Float.parseFloat(y[p]));
						
						ling.setRange(range);
						lingArray.add(ling);
					}
					else if(ling.getType().equals("triangle")) { // 3 points it is then.
						y = in.readLine().split("\\s+");
						List<Float>range = new ArrayList<Float>(3);
						
						for(int p = 0; p < 3; p++)
							range.add(Float.parseFloat(y[p]));
						
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
			for(int i = 0; i < outputSet.getSetCount(); i++) {
				LinguisticElement ling = new LinguisticElement();
				
				y = in.readLine().split("\\s+");
				ling.setName(y[0]); 
				ling.setType(y[1]);
				
				if(ling.getType().equals("trapezoidal")) { //get ready for 4 points right there.
					y = in.readLine().split("\\s+");
					List<Float>range = new ArrayList<Float>(4);
					
					for(int p = 0; p < 4; p++)
						range.add(Float.parseFloat(y[p]));
					
					ling.setRange(range);
					lingArray.add(ling);
				}
				
				else if(ling.getType().equals("triangle")) { // 3 points it is then.
					y = in.readLine().split("\\s+");
					List<Float>range = new ArrayList<Float>(3);
					
					for(int p = 0; p < 3; p++)
						range.add(Float.parseFloat(y[p]));
					
					ling.setRange(range);
					lingArray.add(ling);
				}
				
			}
			outputSet.setElements(lingArray);
			
			String[] z;
			numOfRules = Integer.parseInt(in.readLine());
			inferenceRules = new ArrayList<InferenceRule>(numOfRules); //intializing array of inference rules.
			
			for(int i = 0; i < numOfRules; i++) {
				InferenceRule element = new InferenceRule(); //creating inference rule element.
				
				z = in.readLine().split("\\s+");
				 
				element.setNumOfPremise(Integer.parseInt(z[0]));
				
				for(int r = 1; r < z.length - 1; r++) { // loop to pass on premises.
					if(z[r].equals("AND")) {
						element.getType().add("AND");
					}
					
					else if(z[r].equals("OR")) {
						element.getType().add("OR");
					}
					
					else { // then it's a premise	
						element.getPremise().add(z[r]);
					}
				}
				element.setOutput(z[z.length-1]);
			
				 inferenceRules.add(element); //adding the inference rule element to the array of the rules.
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
		//System.out.println("Fuzzification Step : ");
	
		LinguisticElement element = new LinguisticElement();
		for(int i = 0; i < setNum; i++) {
			for(int j = 0; j < fuzzySet.get(i).getElements().size(); j++) {
				element.setName(fuzzySet.get(i).getElements().get(j).getName());
				element.setType(fuzzySet.get(i).getElements().get(j).getType());
				element.setRange(fuzzySet.get(i).getElements().get(j).getRange());
				element.setMembershipValue(fuzzySet.get(i).getElements().get(j).getMembershipValue());
				
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
				fuzzySet.get(i).getElements().get(j).setMembershipValue(element.getMembershipValue());
				table.put(element.getName(), element.getMembershipValue());
				
				//System.out.println(element.getName() + "     " + element.getMembershipValue());
				
			}
		}
		//System.out.println();
	}
	
	void InferenceBASIC() { //only 2 premises
		
		for(int i=0 ; i <inferenceRules.size();i++) {  
			String outputLingElem = inferenceRules.get(i).getOutput().split("=")[1]; //Ex: returns "fast".
	          
			for(int j=0;j<inferenceRules.get(i).getType().size();j++) { //type loop , type index 0 -- > take premises 0 & 1	 
		         String lingElem1 = inferenceRules.get(i).getPremise().get(j).split("=")[1]; // "warm"
		         String lingElem2 = inferenceRules.get(i).getPremise().get(j+1).split("=")[1]; // "sunny"
		         float x =0 , y = 0; //carries the membership values
				 
		         
				 if(inferenceRules.get(i).getType().get(j).equals("AND")) {
					 
				          for( int t = 0; t < fuzzySet.size(); t++) {
				        	  for(int l=0 ;l < fuzzySet.get(t).getElements().size();l++) {
				        		  
				        		    if(fuzzySet.get(t).getElements().get(l).getName().equals(lingElem1))
				        		    	x =  fuzzySet.get(t).getElements().get(l).getMembershipValue();
				        		    
				        		    else if(fuzzySet.get(t).getElements().get(l).getName().equals(lingElem2))
				        		    	y= fuzzySet.get(t).getElements().get(l).getMembershipValue();
				        	  }
				        	  
				        	  
				          }
				          
				          for(int s = 0; s < outputSet.getElements().size(); s++)
				        	  if(outputSet.getElements().get(s).getName().equals(outputLingElem))
				        		  outputSet.getElements().get(s).setMembershipValue(Math.min(x,y));
					}
				 
				 else if (inferenceRules.get(i).getType().get(j).equals("OR")) {
					 
					 for( int t = 0; t < fuzzySet.size(); t++) {
			        	  for(int l = 0; l < fuzzySet.get(t).getElements().size(); l++) {
			        		  
			        		    if(fuzzySet.get(t).getElements().get(l).getName().equals(lingElem1))
			        		    	x = fuzzySet.get(t).getElements().get(l).getMembershipValue();
			        		    
			        		    else if(fuzzySet.get(t).getElements().get(l).getName().equals(lingElem2))
			        		    	y= fuzzySet.get(t).getElements().get(l).getMembershipValue();
			        	  }
			        	  
			        	  
			          }
			          for(int s = 0; s < outputSet.getElements().size(); s++)
			        	  if(outputSet.getElements().get(s).getName().equals(outputLingElem))
			        		  outputSet.getElements().get(s).setMembershipValue(Math.max(x,y)); 
				 }
			 }
			
		}
		
		
	}
	
	void Inference() {
		//System.out.println("Hardcore Infrenece:");
		for(int i = 0; i < inferenceRules.size(); i++) {
			List <Float> values = new ArrayList<>();
			List <String> operators = new ArrayList<>();
			String premiseLingElem = new String();
			String outputLingElem = inferenceRules.get(i).getOutput().split("=")[1]; //Ex: returns "fast"
			
			for(int j = 0; j < inferenceRules.get(i).getPremise().size(); j++) {
				premiseLingElem = inferenceRules.get(i).getPremise().get(j).split("=")[1];
        		if(premiseLingElem.contains("!")) {
        			values.add(1 - table.get(premiseLingElem));
        		}
        		else {
        			values.add(table.get(premiseLingElem));
        			//System.out.println(table.get(outputLingElem));
        		}
        	}
			
			
        	for(int j = 0; j < inferenceRules.get(i).getType().size(); j++) {
        		operators.add(inferenceRules.get(i).getType().get(j));
        	}
        	//System.out.println("Before: "  + values);
        	for(int j = 0; j < operators.size(); j++) {
        		if(operators.get(j).equals("AND")) {
        			float x = Math.min(values.get(j), values.get(j + 1));
        			values.set(j, x);
        			values.remove(j + 1);
        			operators.remove(j);
        			j = 0;
        		}
        	}
        	
        	//System.out.println("After: "  + values);
        	for(int j = 0; j < operators.size(); j++) {
        		if(operators.get(j).equals("OR")) {
        			float x = Math.max(values.get(j), values.get(j + 1));
        			values.set(j, x);
        			values.remove(j + 1);
        			operators.remove(j);
        			j = 0;
        		}
        	}
        	
        	
        	table.put(inferenceRules.get(i).getOutput().split("=")[1], values.get(0));
        	//System.out.println(values);
        	
        	for(int s = 0; s < outputSet.getElements().size(); s++) {
        		if(outputSet.getElements().get(s).getName().equals(outputLingElem)) {
        			outputSet.getElements().get(s).setMembershipValue(values.get(0));
        			//System.out.println(outputSet.getElements().get(s).getName() + " " + outputSet.getElements().get(s).getMembershipValue());
        		}
        	}
        	
        		 
        }
		
		//Set<String>hashKeys = table.keySet();
//		System.out.println("HardCore Inference: ");
//		for(int s = 0; s < table.size(); s++)
//			
		//System.out.println();
	}
	
	void defuzzification() {
		
		float denominator = 0;
		
		for(int i = 0; i < outputSet.getSetCount(); i++) { //setting the centroid of the output fuzzy set.
			
			if(outputSet.getElements().get(i).getType().equals("triangle")) {
				outputSet.setCentroid(outputSet.getElements().get(i).getRangeByIndex(1));
				result += (outputSet.getCentroid() * outputSet.getElements().get(i).getMembershipValue());
				denominator += outputSet.getElements().get(i).getMembershipValue();
			}
			
			else if (outputSet.getElements().get(i).getType().equals("trapezoidal")){
				
				if(i == 0) { //trapezoidal in the beginning
	
					outputSet.setCentroid(outputSet.getElements().get(i).getRangeByIndex(2));
					
					result += (outputSet.getCentroid() * outputSet.getElements().get(i).getMembershipValue());
					denominator += outputSet.getElements().get(i).getMembershipValue();
					
				}
				else if (i == (outputSet.getSetCount() - 1)) //trapezoidal in the end
				{
					outputSet.setCentroid(outputSet.getElements().get(i).getRangeByIndex(1));
					
					result += (outputSet.getCentroid() * outputSet.getElements().get(i).getMembershipValue());
					denominator += outputSet.getElements().get(i).getMembershipValue();
				}
				else { // somewhere in the middle
					float point1 = outputSet.getElements().get(i).getRangeByIndex(1);
					float point2 = outputSet.getElements().get(i).getRangeByIndex(2);
					outputSet.setCentroid((float) ((point1 + point2)/2.0));
					
					result += (outputSet.getCentroid() * outputSet.getElements().get(i).getMembershipValue());
					denominator += outputSet.getElements().get(i).getMembershipValue();
					
				}
				
				
			}
			
			
		}
		
		//weighted mean
		result /= denominator;
		
		//System.out.println("Defuzzification Step : " + result);
		//System.out.println();
		
	}
	
	
	
	void show() {
		
		
		System.out.println("Fuzzification Step : ");
		
		for(int i=0;i<fuzzySet.size();i++) {
			for(int j=0;j<fuzzySet.get(i).getElements().size();j++) {
				System.out.println(fuzzySet.get(i).getElements().get(j).getName() + "     " + fuzzySet.get(i).getElements().get(j).getMembershipValue());
			}
		}
		
		System.out.println();
		
		System.out.println("Hardcore Inference : ");
		
		for(int i=0;i<outputSet.getElements().size();i++) {
			
			System.out.println(outputSet.getElements().get(i).getName() + "     " + outputSet.getElements().get(i).getMembershipValue());
		}
		
		System.out.println();
		
		System.out.println("Defuzzification Step : ");
		System.out.println(result);
		
	}
	
	
		
	
	
}
