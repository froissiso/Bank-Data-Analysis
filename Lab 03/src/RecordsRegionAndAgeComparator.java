import java.util.Comparator;


/**
 * Class used to compare two objects BankRecords by their region and age.
 * 
 * @author Francisco Rois Siso
 *
 */
public class RecordsRegionAndAgeComparator implements Comparator<BankRecords>{

	@Override
	public int compare(BankRecords rec1, BankRecords rec2) {
		// if different region
		if(!rec1.getRegion().equals(rec2.getRegion())){
			return rec1.getRegion().compareTo(rec2.getRegion());
		}
		// if equal region
		else{
			return rec1.getAge()-rec2.getAge();
		}
	}

	
}
