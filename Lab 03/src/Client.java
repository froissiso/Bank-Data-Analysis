/**
 * The class Client provides three abstract methods the bank needs in order to process data from a file: readData, processData and printData.
 * 
 * @author Francisco Rois Siso
 *
 */
public abstract class Client {
	// abstract methods that will be defined in BankRecords.java.
	
	// a String parameter called fileName is included in "readData" in order to be able 
	// to read information from different files in future implementations. 
	// The input String "default" triggers the reading from the default file ("bank-Detail.csv")
	public abstract void readData(String fileName); 
	public abstract void processData();
	public abstract void printData();
}
