package tj.pwv.utils.SWV;

public class SWV {
	private int year;
	private int doy;
	private int hour;
	private int min;
	private int sec;
	private double swv;
	private double azi;
	private double elev;

	public SWV(int year, int doy, int hour, int min, int sec, double swv, double azi, double elev) {
		super();
		this.year = year;
		this.doy = doy;
		this.hour = hour;
		this.min = min;
		this.sec = sec;
		this.swv = swv;
		this.azi = azi;
		this.elev = elev;
	}

	public double getAzi() {
		return azi;
	}

	public void setAzi(double azi) {
		this.azi = azi;
	}

	public double getElev() {
		return elev;
	}

	public void setElev(double elev) {
		this.elev = elev;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDoy() {
		return doy;
	}

	public void setDoy(int doy) {
		this.doy = doy;
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

	public double getSwv() {
		return swv;
	}

	public void setSwv(double swv) {
		this.swv = swv;
	}

	public static double calSWV(double mw, double pwv, double pi, double md, double gNw, double gEw, double azi) {
		return mw * pwv + pi * (md * (gNw * Math.cos(azi * Math.PI / 180) + gEw * Math.sin(azi * Math.PI / 180)));
	}

	@Override
	public String toString() {
		// return year + " " + doy + " " + hour + " " + min + " " + sec + " " +
		// String.format("%.2f", swv);
		// Formatter formatter = new Formatter(System.out);
		return String.format("%5d %6d %6d %6d %6d %6.2f %10.4f %10.4f", year, doy, hour, min, sec, swv, elev, azi);
	}

}
