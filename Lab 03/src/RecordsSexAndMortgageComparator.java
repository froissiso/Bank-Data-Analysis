import java.util.Comparator;

/**
 * Class used to compare two objects BankRecords by their sex and mortgage.
 * 
 * @author Francisco Rois Siso
 *
 */
public class RecordsSexAndMortgageComparator implements Comparator<BankRecords>{

	@Override
	public int compare(BankRecords rec1, BankRecords rec2) {
		// if different sex
		if(!rec1.getSex().equals(rec2.getSex()))
			return rec1.getSex().compareTo(rec2.getSex());
		// if equal sex
		else
			return rec2.getMortgage().compareTo(rec1.getMortgage());
	}

}
