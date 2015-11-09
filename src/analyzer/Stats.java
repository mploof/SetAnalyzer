package analyzer;

import java.util.ArrayList;

public class Stats {
	
	double mean;
	double stdDev;
	ArrayList<Integer> list;
	
	public Stats(ArrayList<Integer> list){
		this.list = list;
		findStdDev();
	}
	
	private void findMean(){						
		for(int i : list){
			mean += i;			
		}
		mean /= list.size();		
	}
	
	private void findStdDev(){
		
		findMean();
		double sum = 0;
		
		for(int i : list){			
			sum += Math.pow(((float) i - mean), 2);			
		}
		
		stdDev = Math.sqrt( (1.0/list.size()) * sum );		
	}
	
	public float getMean(){
		return (float) mean;
	}
	
	public float getStdDev(){
		return (float) stdDev;
	}

}
