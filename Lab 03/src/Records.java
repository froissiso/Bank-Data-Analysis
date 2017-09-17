import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The class Records has methods for data processing and analysis of the data read and stored in BankRecords.java.
 * The class Records extends BankRecords, therefore it inherits its methods and variables. 
 * 
 * @author Francisco Rois Siso
 *
 */

public class Records extends BankRecords{

	/**
	 * Constructor with no input parameters.
	 */
	public Records(){}
	
	/**
	 * method getAverageIncome goes through the list of bank records and calculate the total average income
	 * @return averageIncome, as a double
	 */
	public double getAverageIncome(){
		double averageIncome = 0.0;
		for(BankRecords b:super.bankRecords_list){
			averageIncome += b.getIncome();
		}
		averageIncome /= super.bankRecords_list.size();
		
		return averageIncome;
	}

	/**
	 * method getMinAgesPerLocation sorts the list of bank records by region and then creates a map with the minimum age for each of them.
	 * @return map_mins a map with the regions as key and the minimum age as values, as a Map<String,Integer>
	 */
	public Map<String,Integer> getMinAgesPerLocation(){
		Map<String,Integer> map_mins = new HashMap<String,Integer>();
		// sort the BankRecords list by region and age
		Collections.sort(super.bankRecords_list,new RecordsRegionAndAgeComparator());
		// sort possible regions for info displaying purposes
		Collections.sort(super.possible_regions);


		for(BankRecords b: super.bankRecords_list){
			// insert in map the first age value found for each region, which is the minimum
			if(!map_mins.containsKey(b.getRegion())){
				// insert minimum in map
				map_mins.put(b.getRegion(), b.getAge());
			}
		}
		return map_mins;
	}
	
	/**
	 * method getMaxAgesPerLocation sorts the list of bank records by region and then creates a map with the maximum age for each of them.
	 * @return map_maxs a map with the regions as key and the maximum age as values, as a Map<String,Integer>
	 */
	public Map<String,Integer> getMaxAgesPerLocation(){
		Map<String,Integer> map_maxs = new HashMap<String,Integer>();
		// sort the BankRecords list by region and age
		Collections.sort(super.bankRecords_list,new RecordsRegionAndAgeComparator());
		// sort possible regions for info displaying purposes
		Collections.sort(super.possible_regions);

		// go through the sorted list, but this time backwards, so the first values found are the maximums
		for(int i = super.bankRecords_list.size()-1 ; i>=0 ; i--){
			BankRecords b = super.bankRecords_list.get(i);
			if(!map_maxs.containsKey(b.getRegion())){
				// insert in map the only the first age value found for each region, which is the maximum
				map_maxs.put(b.getRegion(), b.getAge());
			}
		}
		return map_maxs;
	}
		
	/**
	 * method getNumberOfFemalesWithMortgages sorts the list of bank records by sex and mortgage. Then it goes through the sorted list
	 * and counts the number of females with mortgage.
	 * @return count the number of females with a mortgage, as an int
	 */
	public int getNumberOfFemalesWithMortgages(){
		// sort list by sex and mortgage
		Collections.sort(super.bankRecords_list,new RecordsSexAndMortgageComparator());
		// go through the sorted list only until a record not female and without mortgage is found
		int count = 0;
		int e = 0;
		while(super.bankRecords_list.get(e).getSex().equals("FEMALE") && super.bankRecords_list.get(e).getMortgage().equals("YES")){
			e++;
			count ++;
		}
		return count;
	}
	
	/**
	 * method getNumberOfMalesWithCarAndOneChildPerLocation first sorts the list of bank records by sex, region, car and number of children.
	 * Then the method goes through the collection and counts the number of males with car and one child, per region.
	 * @return map a map with the regions as key and the number of males with car and 1 child as value for each of the regions
	 */
	public Map<String,Integer> getNumberOfMalesWithCarAndOneChildPerLocation(){
		// sort list by sex, location, car and number of child
		Collections.sort(super.bankRecords_list, new RecordsSexRegionCarAndNChildComparator());
		// map to store number of specified elements (value) per region (key)
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		int i = 0;
		int count = 0;
		// while it is a man and he has car
		while(i < super.bankRecords_list.size() && super.bankRecords_list.get(i).getSex().equals("MALE") && super.bankRecords_list.get(i).getCar().equals("YES")){
			// if he has 1 child, increment the count
			if(super.bankRecords_list.get(i).getChildren() == 1){
				count++;
			}
			
			// if index in bound
			if((i+1) < super.bankRecords_list.size()) {
				// if i is the last item of the certain region, put the count and the region into the map and restart the count for the next region
				if(!super.bankRecords_list.get(i).getRegion().equals(super.bankRecords_list.get(i+1).getRegion())){
					map.put(super.bankRecords_list.get(i).getRegion(), count);
					count = 0;
				}
			}
			// move to the next element in the collection	
			i++;
		}
		return map;
	}
}
