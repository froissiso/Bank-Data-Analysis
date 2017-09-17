import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/*
Program to perform data analysis from a csv file with records from clients of a bank. After the data is retrieved and stored, the following
information is extracted from the data analysis:
- Overall average income.
- Max and min ages per location.
- Total number of females with mortgage.
- Number of males with both car and 1 child per location.

For efficiency purposes, the list of bank records is sorted in different ways during the program performance.
The results are shown in a text file.

- Programmer: Francisco Rois Siso
- Date: 02/24/2017
- Source File Name: Records_Test.java
- Lab 3
- ITMD510 Object-Oriented Application Development

 */

/**
 * The purpose of this class is test the class Records by creating an object and calling the different methods of the class.
 * Then the information obtained is printed into a text file.
 * 
 * @author Francisco Rois Siso
 *
 */
public class Records_Test {
	
	final static String FILENAME = "bankrecords.txt";
	static BufferedWriter bw = null;
	static FileWriter fw = null;

	public static void main(String[] args) {

		// create a new object Records, which will be used all along the program
		Records rec = new Records();
		// read data from the default csv file and process it (storage)
		rec.readData("default");

		// variable for program performance measurement
		long startTime = System.nanoTime();
		
		// String content is created in order to add the information required to print on the text file. The info is also displayed in console.
		String content = "";
		
		// add average income
		content += "Average Income: "+rec.getAverageIncome()+"\n";
		
		// add max and min ages per region. map_mins and map_maxs store the min and max ages per location, respectively.
		Map<String,Integer> map_mins = rec.getMinAgesPerLocation();
		Map<String,Integer> map_maxs = rec.getMaxAgesPerLocation();
		
		for(String s: rec.possible_regions){
			content += s + " region max age: "+ map_maxs.get(s)+ " ";			
		}
		content += "\n";
		
		for(String r: rec.possible_regions){
			content += r + " region min age: "+ map_mins.get(r)+ " ";
		}

		// add total number of females with mortgage
		int numFemMort = rec.getNumberOfFemalesWithMortgages();
		
		content += "\n";
		content += "#Females with mortgage: " + numFemMort;
		
		// add number of males with car and 1 child, per region. map_malesCar1Child stores the information after data analysis.
		Map<String,Integer> map_malesCar1Child = rec.getNumberOfMalesWithCarAndOneChildPerLocation();
		
		content += "\n";
		for(String t: rec.possible_regions){
			content += t + " region males with car & 1 child: " + map_malesCar1Child.get(t);
			content += "\n";
		}
		
		// add name, date and time at the end of the file, for lab submission purposes
		content += showDateAndTime();
		
		// create FileWriter and BufferedWriter in order to write the content into the text file specified
		try {
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(content);
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				// close FileWriter and BufferedWriter
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			} 

		}
		
		// info also displayed in console
		System.out.println("\n--> Information successfully printed in file " + FILENAME +":");
		System.out.print(content);
		
		// code used for time measuring purposes. Program performance time
		System.out.println("\n\n");
		System.out.println(String.valueOf(rec.getAverageIncome()));
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns"); 
	}
	
	
	/**
	 * showDateAndTime allows to show the current date and time for lab submission purposes
	 */
	static String showDateAndTime(){
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		return("\n\nCur dt=" + timeStamp + "\nProgrammed by Francisco Rois Siso\n");
	}
}
