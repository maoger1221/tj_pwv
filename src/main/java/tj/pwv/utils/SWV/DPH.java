package tj.pwv.utils.SWV;

public class DPH {	
	private double azi;
	private double elev;
	private int epoch;
	private int prn;
	
	public DPH(double azi, double elev, int epoch) {
		super();
		this.azi = azi;
		this.elev = elev;
		this.epoch = epoch;
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

	public int getEpoch() {
		return epoch;
	}

	public void setEpoch(int epoch) {
		this.epoch = epoch;
	}
	
	public int getPrn() {
		return prn;
	}

	public void setPrn(int prn) {
		this.prn = prn;
	}

}
