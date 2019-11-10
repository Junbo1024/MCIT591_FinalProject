import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Team_5_Answers {

	private String fileName;

	public Team_5_Answers(String fileName) {
		this.fileName = fileName;
	}

	public HashMap<Integer, AAPL> getData(String fileName) {
		CSVFileReader CFR = new CSVFileReader();
		HashMap<Integer, AAPL> AAPLData = CFR.APPLCSVFileReader(fileName);
		return AAPLData;
	}

	public String Q1() {

		HashMap<Integer, AAPL> data = new HashMap<Integer, AAPL>();
		ArrayList<Double> highValue = new ArrayList<Double>();
		ArrayList<Double> lowValue = new ArrayList<Double>();

		data = getData(fileName);

		for (int key : data.keySet()) {

			AAPL targetData = data.get(key);
			double high = Double.parseDouble(targetData.getHigh());
			double low = Double.parseDouble(targetData.getLow());
			highValue.add(high);
			lowValue.add(low);

		}

		double max = 0.00;

		for (int i = 0; i < highValue.size(); i++) {

			if (max < highValue.get(i)) {
				max = highValue.get(i);

			}
		}

		double min = max;

		for (int i = 0; i < lowValue.size(); i++) {

			if (min > lowValue.get(i)) {
				min = lowValue.get(i);

			}
		}

		double total = 0;
		for (int i = 0; i < highValue.size(); i++) {
			total = highValue.get(i) + lowValue.get(i);
		}

		double average = total / (highValue.size() + lowValue.size());
		String Q1Answer = String.valueOf(max) + "," + String.valueOf(min) + "," + String.valueOf(average);
		System.out.println(Q1Answer);

		return Q1Answer;

	}

	public void Q2() {

		CSVFileReader filereader = new CSVFileReader();
		Calculations cal = new Calculations();
		filereader.APPLCSVFileReader(fileName);

		ArrayList<Double> dayOneClose = new ArrayList<Double>();
		ArrayList<Double> dailyReturn = new ArrayList<Double>();

		// get all the adj_close values into dayOneClose, starts at day 0
		int i = 0;
		while (i < filereader.AAPLData.size()) {
			dayOneClose.add(Double.valueOf(filereader.AAPLData.get(i).getAdj_Close()));
			i++;
		}

		// get all the adj_close values into dayTwoClose, starts at day 1
		ArrayList<Double> dayTwoClose = new ArrayList<Double>(dayOneClose);
		dayTwoClose.remove(0);

		// (dayTwoClose - dayOneClose) / dayOneClose = daily return
		for (int j = 0; j < dayTwoClose.size(); j++) {
			dailyReturn.add((dayTwoClose.get(j) - dayOneClose.get(j)) / dayOneClose.get(j) * 100);
		}

		HashMap<String, Double> vall = new HashMap<String, Double>();
		vall = cal.findMaxMinAverageOfArrayList(dailyReturn);

		System.out.println(vall.get("max") + " " + vall.get("min") + " " + vall.get("average"));

	}

	public void Q3() {
		
		String year = fileName.substring(5, 9);

		HashMap<Integer, AAPL> data = new HashMap<Integer, AAPL>();
		ArrayList<Double> openValue = new ArrayList<Double>();
		ArrayList<Double> closeValue = new ArrayList<Double>();
		ArrayList<String> dateValue = new ArrayList<String>();
		ArrayList<Double> monthReturn = new ArrayList<Double>();

		double yearReturnPercent = 0.00;
		double yearOpenValue = 0.00;
		double yearCloseValue = 0.00;

		data = getData(fileName);

		for (int key : data.keySet()) {

			AAPL targetData = data.get(key);
			double open = Double.parseDouble(targetData.getOpen());
			double close = Double.parseDouble(targetData.getAdj_Close());
			String date = targetData.getDate();

			openValue.add(open);
			closeValue.add(close);
			dateValue.add(date);

		}

		// Year return value.
		yearOpenValue = openValue.get(0);
		yearCloseValue = closeValue.get(closeValue.size() - 1);
		yearReturnPercent = (yearCloseValue - yearOpenValue) / yearOpenValue + 1;

		System.out.println("Year Return is " + yearReturnPercent + ".");

		ArrayList<String> monthData = new ArrayList<String>();
		ArrayList<String> dayData = new ArrayList<String>();

		for (int i = 0; i < dateValue.size(); i++) {

			String[] column = dateValue.get(i).split("-");
			monthData.add(column[1]);
			dayData.add(column[2]);

		}

		ArrayList<String> targetMonth = new ArrayList<String>();
		ArrayList<Integer> monthFreqency = new ArrayList<Integer>();

		// Get Month Information - Month and day amount in the Month.
		Set<String> set = new HashSet<String>(monthData);
		for (String r : set) {
			targetMonth.add(r);
			monthFreqency.add(Collections.frequency(monthData, r));
		}

		// Get Month Start Date and End Date.
		int dayInMonth = 0;
		ArrayList<Integer> monthStartLocation = new ArrayList<Integer>();
		ArrayList<Integer> monthEndedLocation = new ArrayList<Integer>();
		ArrayList<String> monthStartDate = new ArrayList<String>();
		ArrayList<String> monthEndedDate = new ArrayList<String>();
		ArrayList<Double> monthStartValue = new ArrayList<Double>();
		ArrayList<Double> monthEndedValue = new ArrayList<Double>();

		for (int i = 0; i < monthFreqency.size(); i++) {
			monthStartLocation.add(dayInMonth);
			dayInMonth += monthFreqency.get(i);
			monthEndedLocation.add(dayInMonth - 1);
		}

		for (int i = 0; i < targetMonth.size(); i++) {
			monthStartDate.add(year + "-" + targetMonth.get(i) + "-" + dayData.get(monthStartLocation.get(i)));
			monthEndedDate.add(year + "-" + targetMonth.get(i) + "-" + dayData.get(monthEndedLocation.get(i)));
		}

		for (int i = 0; i < monthStartDate.size(); i++) {
			for (int j = 0; j < dateValue.size(); j++) {
				if ((monthStartDate.get(i)).equals(dateValue.get(j))) {
					monthStartValue.add(openValue.get(j));
				}
			}
		}

		for (int i = 0; i < monthEndedDate.size(); i++) {
			for (int j = 0; j < dateValue.size(); j++) {
				if ((monthEndedDate.get(i)).equals(dateValue.get(j))) {
					monthEndedValue.add(closeValue.get(j));
				}
			}
		}

		for (int i = 0; i < monthStartValue.size(); i++) {
			monthReturn.add((monthEndedValue.get(i) - monthStartValue.get(i)) / monthStartValue.get(i) + 1);
		}

		String[] monthInYear = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		ArrayList<String> month = new ArrayList<String>();
		for (int i = 0; i < monthInYear.length; i++) {
			month.add(monthInYear[i]);
		}

		for (int i = 0; i < targetMonth.size(); i++) {
			System.out.println(
					month.get(Integer.parseInt(targetMonth.get(i)) - 1) + "'s return rate is " + monthReturn.get(i));
		}
	}

	public void Q4() {

	}

	public void Q5() {

	}

	public double Q6() {
		double sum = 0.0;
		boolean tradeOpen = false;
		double openPrice = 0.0;
		double closePrice = 0.0;
		
		CSVFileReader filereader = new CSVFileReader();
		Calculations cal = new Calculations();
		filereader.APPLCSVFileReader(fileName);
		ArrayList<Double> arrayOf20 = new ArrayList<Double>();
		
		int i = 0;
		while(i < filereader.AAPLData.size()) {
			
			if(arrayOf20.size() < 20) {
				arrayOf20.add(Double.valueOf(filereader.AAPLData.get(i).getAdj_Close()));
			}
			else {
				arrayOf20.add(Double.valueOf(filereader.AAPLData.get(i).getAdj_Close()));
				
				double max = cal.findMaxMinAverageOfArrayList(arrayOf20).get("max");
				double min = cal.findMaxMinAverageOfArrayList(arrayOf20).get("min");
				
				if(Double.valueOf(filereader.AAPLData.get(i).getAdj_Close()) == max) {
					if(tradeOpen) {
						closePrice = max;
						sum += closePrice - openPrice;
						tradeOpen = false;
					}
				}
				if(Double.valueOf(filereader.AAPLData.get(i).getAdj_Close()) == min) {
					if(tradeOpen) {
						closePrice = min;
						sum += openPrice - closePrice;
						tradeOpen = false;
					}
					
					else {
						openPrice = min;
						tradeOpen = true;
					}
				}
				
				arrayOf20.remove(0);
			}
			i++;
		}
		return sum;

	}

	public void Q7() {

	}

	public void Q8() {

	}

}
