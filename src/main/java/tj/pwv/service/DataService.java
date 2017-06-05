package tj.pwv.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import tj.pwv.pojo.*;

public interface DataService {
	
	List<Mwr2d> getMwr2d(String date,String elev,String azi,String iwv,Integer pageNum,HttpSession httpSession);
	List<MwrZenit> getMwrZ(String date,String elev,String azi,String iwv,Integer pageNum,HttpSession httpSession);
	List<Pwv> getPWV();
	List<MwrZenit30min> getDbDrawingMwrZ(String date);
	List<Pwv> getDbDrawingPWV(String date,String ownfilename);
	ViewObject getDbDrawingPWV(String date,String ownfilename, String selectbox);
}
