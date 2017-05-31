package tj.pwv.pojo;

import java.util.ArrayList;
import java.util.List;

public class DrawingCheckBox {
	private List<MwrZenit30min> iwvdata = new ArrayList<MwrZenit30min>();
	private List<Pwv> pwvdata = new ArrayList<Pwv>();
	public List<MwrZenit30min> getIwvdata() {
		return iwvdata;
	}
	public void setIwvdata(List<MwrZenit30min> iwvdata) {
		this.iwvdata = iwvdata;
	}
	public List<Pwv> getPwvdata() {
		return pwvdata;
	}
	public void setPwvdata(List<Pwv> pwvdata) {
		this.pwvdata = pwvdata;
	}
	public DrawingCheckBox(List<MwrZenit30min> iwvdata, List<Pwv> pwvdata) {
		super();
		this.iwvdata = iwvdata;
		this.pwvdata = pwvdata;
	}
}
