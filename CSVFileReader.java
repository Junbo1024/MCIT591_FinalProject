import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class CSVFileReader {

	public CSVFileReader() {

	}

	public HashMap<Integer, AAPL> AAPLData;

	public HashMap<Integer, AAPL> APPLCSVFileReader(String fileName) {

		File csvFile = new File(fileName);

		AAPLData = new HashMap<Integer, AAPL>();

		try {
			Scanner in = new Scanner(csvFile);

			in.nextLine();

			int sampleNumber = 0;

			while (in.hasNextLine()) {
				
				String csvRowData = in.nextLine();
				String[] columnData = csvRowData.split(",");

				String Date = columnData[0];
				String Open = columnData[1];
				String High = columnData[2];
				String Low = columnData[3];
				String Close = columnData[4];
				String Adj_Close = columnData[5];
				String Volumn = columnData[6];

				AAPL f = new AAPL(Date, Open, High, Low, Close, Adj_Close, Volumn);

				AAPLData.put(sampleNumber, f);
				sampleNumber++;

			}

			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("There is an error in file reader.");
			e.printStackTrace();
		}

		return AAPLData;
	}

}
