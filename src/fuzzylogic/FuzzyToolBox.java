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
	public FuzzySet outputSet;
	
	
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
					else { // 3 points it is then.
						
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
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File is not found.");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("Problem with input/output.");
			e.printStackTrace();
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
		
	}
	
	
	
}
