import java.util.ArrayList;
import java.util.HashMap;

public class Calculations {
	
	public Calculations() {
		
	}
	/***
	 * Calculate the max, min and average of an arraylist with type double.
	 * @param arraylist
	 * @return return an hashmap with index of max, min and average.
	 */
	
	public HashMap<String, Double> findMaxMinAverageOfArrayList(ArrayList<Double> arraylist) {
		
		HashMap<String, Double> values = new HashMap<String, Double>();
		values.put("max", -1000.00);
		values.put("min", 1000.00);
		values.put("average", 0.00);
		
		double sum = 0.0;
		
		for(int i = 0; i < arraylist.size(); i++) {
			if(arraylist.get(i) > values.get("max")) {
				values.put("max", arraylist.get(i));
			}
			if(arraylist.get(i) < values.get("min")) {
				values.put("min", arraylist.get(i));
			}
			sum += arraylist.get(i);
		}
		values.put("average", sum / arraylist.size());
		
		return values;
	}

}
