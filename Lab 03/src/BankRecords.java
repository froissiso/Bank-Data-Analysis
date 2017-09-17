import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * The class BankRecords extends the abstract class Client and implements its methods readData, processData
 * and printData. The purposes of these methods are reading clients' information from a certain csv file, convert
 * each line into an object with the correct parameters and then print information from several of the records.
 * 
 * @author Francisco Rois Siso
 *
 */
public class BankRecords extends Client {

	// default file from which the reading is done if not other file is specified.
	final static String DEFAULT_FILE = "bank-Detail.csv";
	
	// list of objects BankRecords to store all the clients with their information.
	List<BankRecords> bankRecords_list = new ArrayList<>();
	// list of lists of Strings. Each of the lists is a line (corresponding to a client) and each String is a client's parameter
	static List<List<String>> listOfLines = new ArrayList<>();
	
	// list with all the possible values for the field "region" in the Bank Records
	List <String> possible_regions = new ArrayList<>();
	
	// parameters of the client
	private String id;
	private int age;
	private String sex;
	private String region;
	private double income;
	private String married;
	private int children;
	private String car;
	private String save_act;
	private String current_act;
	private String mortgage;
	private String pep;
	
	/**
	 * Constructor without input parameters.
	 */
	public BankRecords(){}
	
	/**
	 * Constructor with all the parameters of the client.
	 * @param id Identifier as String
	 * @param age Age as int
	 * @param sex Sex as String. It can be FEMALE or MALE.
	 * @param region Region as String. It can be INNER_CITY, TOWN, RURAL or SUBURBAN.
	 * @param income Income as double.
	 * @param married Shows if the client is married or not, as a String. It can be NO or YES
	 * @param children The number of children that the client has, as an int. It can be 0,1,2 or 3.
	 * @param car Shows if the client has car or not, as a String. It can be NO or YES.
	 * @param save_act
	 * @param current_act
	 * @param mortgage Shows if the client has mortgage or not. It can be NO or YES
	 * @param pep Shows if the client is a "Politically Exposed Person"
	 */
	public BankRecords(String id, int age, String sex, 
			String region, double income, String married,
			int children, String car, String save_act,
			String current_act, String mortgage, String pep){
		this.id = id;
		this.age = age;
		this.sex = sex;
		this.region = region;
		this.age = age;
		this.income = income;
		this.married = married;
		this.children = children;
		this.car = car;
		this.save_act = save_act;
		this.current_act = current_act;
		this.mortgage = mortgage;
		this.pep = pep;
	}
	
	/**
	 * Getters and setters for the parameters of the client.
	 */
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public String getMarried() {
		return married;
	}
	public void setMarried(String married) {
		this.married = married;
	}
	public int getChildren() {
		return children;
	}
	public void setChildren(int children) {
		this.children = children;
	}
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public String getSave_act() {
		return save_act;
	}
	public void setSave_act(String save_act) {
		this.save_act = save_act;
	}
	public String getCurrent_act() {
		return current_act;
	}
	public void setCurrent_act(String current_act) {
		this.current_act = current_act;
	}
	public String getMortgage() {
		return mortgage;
	}
	public void setMortgage(String mortgage) {
		this.mortgage = mortgage;
	}
	public String getPep() {
		return pep;
	}
	public void setPep(String pep) {
		this.pep = pep;
	}
	
	/**
	 *  Override method toString in order to show the proper String with the parameters of the bank record
	 *  @return String The chain of parameters of the bank record.
	 */
	@Override
	public String toString() {
		return "BankRecords [id=" + id + ", age=" + age + ", sex=" + sex
				+ ", region=" + region + ", income=" + income + ", married="
				+ married + ", children=" + children + ", car=" + car
				+ ", save_act=" + save_act + ", current_act=" + current_act
				+ ", mortgage=" + mortgage + ", pep=" + pep + "]";
	}
	
	/**
	 * readData method reads all the data from the file csv indicated and stores it into an ArrayList.
	 * Then it calls to the method processData in order to continue the flow of actions.
	 * @param fileName String indicating the name of the file to read the data from.
	 */
	public void readData(String fileName){
		// if the contrary is not specified, the reading is done from the default file.
		String file2Read = DEFAULT_FILE;
		if(!fileName.equals(file2Read)&&!fileName.equals("default")){
			file2Read = fileName;
		}
		// separator used between fields in the document.
		final String SEPARATOR = ",";
		// line that will correspond to a client with its data.
		String line = "";
		
		// try to read from file.
		try(BufferedReader br = new BufferedReader(new FileReader(file2Read))){
			// while there are more lines to read, continue reading.
			while((line = br.readLine()) != null){
				// add the new line to the list of lines. The output from the split method is an array, 
				//therefore it is converted into an ArrayList in order to include the line in the list of lines.
				listOfLines.add(Arrays.asList(line.split(SEPARATOR)));
			}	
			// inform the user when the process is correctly finished.
			System.out.println("\n--> File correctly read.");
			// continue the chain of actions by processing the data.
			processData();
			
		} catch(FileNotFoundException e){
			System.out.println("The name of the file specified is not correct.");
		}catch(IOException e){
			System.out.println("Please, introduce a correct file name.");
		}
		
	}
	
	/**
	 * processData method processes the ArrayList from readData and adds the data into each of the bank records objects.
	 * It makes use of the setters defined in this class.
	 * Then it calls the method printData in order to continue the flow of actions.
	 */
	public void processData() {
		// create a new object BankRecords for each line in listOfLines and use setters to add the different features of the clients.
		for(List<String> client_fields: listOfLines){
			BankRecords b = new BankRecords();
			b.setId(client_fields.get(0));
			b.setAge(Integer.parseInt(client_fields.get(1)));
			b.setSex(client_fields.get(2));
			b.setRegion(client_fields.get(3));
			b.setIncome(Double.parseDouble(client_fields.get(4)));
			b.setMarried(client_fields.get(5));
			b.setChildren(Integer.parseInt(client_fields.get(6)));
			b.setCar(client_fields.get(7));
			b.setSave_act(client_fields.get(8));
			b.setCurrent_act(client_fields.get(9));
			b.setMortgage(client_fields.get(10));
			b.setPep(client_fields.get(11));

			// once the client's features are correctly set, the client is added to the list of bank records.
			bankRecords_list.add(b);
			if(!possible_regions.contains(b.getRegion()))
				possible_regions.add(b.getRegion());
		}
		// inform the user of successful processing.
		System.out.println("\n--> Data from file correctly processed.");
		// print on screen date and time for lab submission purposes.
		//showDateAndTime();
		// continue the chain of actions by printing data.
		//printData();
	}
	
	/**
	 * printData method prints the first 25 records for various fields to the console.
	 * It makes use of the getters defined in this class.
	 * The records printed are ID, AGE, SEX, REGION, INCOME and MORTGAGE.
	 */
	public void printData() {
		// Inform the user of the data to be printed on screen.
		System.out.println("\n--> Here is some data from the first 25 clients in the document:");
		
		// take the first 25 clients from the list of bank records and print information from them
		for(int i = 0;i<25;i++){
			BankRecords brec = bankRecords_list.get(i);
			
			// build String chain in order to print it.
			String chain_client= "\n\t>>>>>   CLIENT " + (i+1) + "   <<<<<\n"
					+ "\t-------------------------" + "\n"
					+ "\tID:\t\t" + brec.getId() + "\n"
					+ "\tAGE:\t\t" + brec.getAge() + "\n"
					+ "\tSEX:\t\t" + brec.getSex() + "\n"
					+ "\tREGION:\t\t" + brec.getRegion() + "\n"
					+ "\tINCOME:\t\t$" + brec.getIncome() + "\n"
					+ "\tMORTGAGE:\t" + brec.getMortgage() + "\n"
					+ "\n\n";
			
			// once the chain is built, print it on screen.
			System.out.print(chain_client);
		}
	}
	
	/**
	 * showDateAndTime allows to show the current date and time for lab submission purposes
	 */
	static void showDateAndTime(){
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println("\n\nCur dt=" + timeStamp + "\nProgrammed by Francisco Rois Siso\n");
	}
}