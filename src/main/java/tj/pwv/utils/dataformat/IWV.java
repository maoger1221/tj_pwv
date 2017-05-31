package tj.pwv.utils.dataformat;

public class IWV {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int min;
	private int sec;
	
	private int rainflag;
	
	private double iwv;
	private double elev;
	private double azi;
	
	private int type;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getSec() {
		return sec;
	}
	public void setSec(int sec) {
		this.sec = sec;
	}
	public int getRainflag() {
		return rainflag;
	}
	public void setRainflag(int rainflag) {
		this.rainflag = rainflag;
	}
	public double getIwv() {
		return iwv;
	}
	public void setIwv(double iwv) {
		this.iwv = iwv;
	}
	public double getElev() {
		return elev;
	}
	public void setElev(double elev) {
		this.elev = elev;
	}
	public double getAzi() {
		return azi;
	}
	public void setAzi(double azi) {
		this.azi = azi;
	}
	public IWV(int year, int month, int day, int hour, int min, int sec, int rainflag, double iwv, double elev,
			double azi,int type) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.min = min;
		this.sec = sec;
		this.rainflag = rainflag;
		this.iwv = iwv;
		this.elev = elev;
		this.azi = azi;
		this.type = type;
	}
	@Override
	public String toString() {
		return year +"-"+month+"-"+day+ ";" + hour + ":" +min + ":"
				+ sec + " " + rainflag + " " + String.format("%.2f", iwv) + " " + elev + " " + azi;
	}
	
	
	
}
