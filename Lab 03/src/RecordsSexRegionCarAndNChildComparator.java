import java.util.Comparator;

/**
 * Class used to compare two objects BankRecords by their sex, region, car and number of children.
 * 
 * @author Francisco Rois Siso
 *
 */
public class RecordsSexRegionCarAndNChildComparator implements Comparator<BankRecords> {

	@Override
	public int compare(BankRecords rec1,BankRecords rec2){
		// if different sex
		if(!rec1.getSex().equals(rec2.getSex())){
			return rec2.getSex().compareTo(rec1.getSex());
		}
		else{
			// if equal sex but different car
			if(!rec1.getCar().equals(rec2.getCar())){
				return rec2.getCar().compareTo(rec1.getCar());
			}
			else{
				// if equal sex, equal car but different region
				if(!rec1.getRegion().equals(rec2.getRegion())){
					return rec1.getRegion().compareTo(rec2.getRegion());
				}
				// if equal sex, equal car, equal region, then compare by number of children
				else{
					return rec1.getChildren()-rec2.getChildren();
				}
			}
		}
	}
}
