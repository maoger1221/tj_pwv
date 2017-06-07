package tj.pwv.utils.SWV;

public class PWV {
	private int year;
	private  int doy;//年积日
	private  int hour;
	private  int min;
	private  int sec;
	
	private  double pwv;
	private  double zwd;
	private  double gradNS;
	private  double gradEW;

	public PWV(int year, int doy, int hour, int min, int sec, double pwv, double zwd, double gradNS, double gradEW) {
		super();
		this.year = year;
		this.doy = doy;
		this.hour = hour;
		this.min = min;
		this.sec = sec;
		this.pwv = pwv;
		this.zwd = zwd;
		this.gradNS = gradNS;
		this.gradEW = gradEW;
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

	public double getPwv() {
		return pwv;
	}

	public void setPwv(double pwv) {
		this.pwv = pwv;
	}

	public double getZwd() {
		return zwd;
	}

	public void setZwd(double zwd) {
		this.zwd = zwd;
	}

	public double getGradNS() {
		return gradNS;
	}

	public void setGradNS(double gradNS) {
		this.gradNS = gradNS;
	}

	public double getGradEW() {
		return gradEW;
	}

	public void setGradEW(double gradEW) {
		this.gradEW = gradEW;
	}

	
}
