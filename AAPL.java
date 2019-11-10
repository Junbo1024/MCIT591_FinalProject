
public class AAPL {
	
	private String Date;
	private String Open;
	private String High;
	private String Low;
	private String Close;
	private String Adj_Close;
	private String Volumn;
	
	public AAPL (String currDate, String currOpen, String currHigh, String currLow, String currClose, String currAdj_Close, String currVolumn){ 
		
		this.Date = currDate;
		this.Open = currOpen;
		this.High = currHigh;
		this.Low = currLow;
		this.Close = currClose;
		this.Adj_Close = currAdj_Close;
		this.Volumn = currVolumn;
		
	}

	public String getDate() {
		return Date;
	}

	public String getOpen() {
		return Open;
	}

	public String getHigh() {
		return High;
	}
	
	public String getLow() {
		return Low;
	}
	
	public String getClose() {
		return Close;
	}

	public String getAdj_Close() {
		return Adj_Close;
	}

	public String getVolumn() {
		return Volumn;
	}
	
}
