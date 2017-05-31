package tj.pwv.utils.dataformat;

public class PWV {
	private int year;
	private int doy;// 年积日
	private int hour;
	private int min;
	private int sec;

	private double tzen;
	private double wzen;
	private double szen;

	private double pwv;
	private double spwv;

	private double press;
	private double temp;
	private double zhd;

	private double gradNS;
	private double sNS;
	private double gradEW;
	private double sEW;

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

	public double getTzen() {
		return tzen;
	}

	public void setTzen(double tzen) {
		this.tzen = tzen;
	}

	public double getWzen() {
		return wzen;
	}

	public void setWzen(double wzen) {
		this.wzen = wzen;
	}

	public double getSzen() {
		return szen;
	}

	public void setSzen(double szen) {
		this.szen = szen;
	}

	public double getPwv() {
		return pwv;
	}

	public void setPwv(double pwv) {
		this.pwv = pwv;
	}

	public double getSpwv() {
		return spwv;
	}

	public void setSpwv(double spwv) {
		this.spwv = spwv;
	}

	public double getPress() {
		return press;
	}

	public void setPress(double press) {
		this.press = press;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getZhd() {
		return zhd;
	}

	public void setZhd(double zhd) {
		this.zhd = zhd;
	}

	public double getGradNS() {
		return gradNS;
	}

	public void setGradNS(double gradNS) {
		this.gradNS = gradNS;
	}

	public double getsNS() {
		return sNS;
	}

	public void setsNS(double sNS) {
		this.sNS = sNS;
	}

	public double getGradEW() {
		return gradEW;
	}

	public void setGradEW(double gradEW) {
		this.gradEW = gradEW;
	}

	public double getsEW() {
		return sEW;
	}

	public void setsEW(double sEW) {
		this.sEW = sEW;
	}

	public PWV(int year, int doy, int hour, int min, int sec, double tzen, double wzen, double szen, double pwv,
			double spwv, double press, double temp, double zhd, double gradNS, double sNS, double gradEW, double sEW) {
		super();
		this.year = year;
		this.doy = doy;
		this.hour = hour;
		this.min = min;
		this.sec = sec;
		this.tzen = tzen;
		this.wzen = wzen;
		this.szen = szen;
		this.pwv = pwv;
		this.spwv = spwv;
		this.press = press;
		this.temp = temp;
		this.zhd = zhd;
		this.gradNS = gradNS;
		this.sNS = sNS;
		this.gradEW = gradEW;
		this.sEW = sEW;
	}

	@Override
	public String toString() {
		String mintemp = "00" + min;
		String sectemp = "00" + sec;
		return doy2day(year, doy) + ";" + hour + ":" + mintemp.substring(mintemp.length() - 2) + ":"
				+ sectemp.substring(sectemp.length() - 2) + " " + tzen + " " + wzen + " " + szen + " " + pwv + " "
				+ spwv + " " + press + " " + temp + " " + zhd + " " + gradNS + " " + sNS + " " + gradEW
				+ " " + sEW;
	}

	private String doy2day(int year, int doy) {
		int flag = 0;
		String temp;
		if (year % 4 == 0)
			flag = 1;
		if (doy <= 31) {
			temp = "00" + doy;
			return year + "-01" + "-" + temp.substring(temp.length() - 2);
		} else if (doy <= 59 + flag) {
			temp = "00" + (doy - 31);
			return year + "-02" + "-" + temp.substring(temp.length() - 2);
		} else if (doy <= 90 + flag) {
			temp = "00" + (doy - 59 - flag);
			return year + "-03" + "-" + temp.substring(temp.length() - 2);
		} else if (doy <= 120 + flag) {
			temp = "00" + (doy - 90 - flag);
			return year + "-04" + "-" + temp.substring(temp.length() - 2);
		} else if (doy <= 151 + flag) {
			temp = "00" + (doy - 120 - flag);
			return year + "-05" + "-" + temp.substring(temp.length() - 2);
		} else if (doy <= 181 + flag) {
			temp = "00" + (doy - 151 - flag);
			return year + "-06" + "-" + temp.substring(temp.length() - 2);
		} else if (doy <= 212 + flag) {
			temp = "00" + (doy - 181 - flag);
			return year + "-07" + "-" + temp.substring(temp.length() - 2);
		} else if (doy <= 243 + flag) {
			temp = "00" + (doy - 212 - flag);
			return year + "-08" + "-" + temp.substring(temp.length() - 2);
		} else if (doy <= 273 + flag) {
			temp = "00" + (doy - 243 - flag);
			return year + "-09" + "-" + temp.substring(temp.length() - 2);
		} else if (doy <= 304 + flag) {
			temp = "00" + (doy - 273 - flag);
			return year + "-10" + "-" + temp.substring(temp.length() - 2);
		} else if (doy <= 334 + flag) {
			temp = "00" + (doy - 304 - flag);
			return year + "-11" + "-" + temp.substring(temp.length() - 2);
		} else if (doy <= 365 + flag) {
			temp = "00" + (doy - 334 - flag);
			return year + "-12" + "-" + temp.substring(temp.length() - 2);
		}
		return "";
	}
}
