package tj.pwv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tj.pwv.pojo.DrawingCheckBox;
import tj.pwv.service.DataService;

@Controller
public class DrawingController {
	@Autowired
	private DataService dataService;
	

	@RequestMapping("/getPWV")
	@ResponseBody
	public Object getPWV(){
		return dataService.getPWV();
	}
	@RequestMapping(value="/getDbDrawing", method=RequestMethod.POST)
	@ResponseBody
	public Object getDbDrawing(String date,String drawingbox){
		if(drawingbox.contains("mwrz") && drawingbox.contains("pwv")){
			return new DrawingCheckBox(dataService.getDbDrawingMwrZ(date),dataService.getDbDrawingPWV(date));
		}else if ("mwrz".equals(drawingbox)) {
			return dataService.getDbDrawingMwrZ(date);
		}else if ("pwv".equals(drawingbox)) {
			return dataService.getDbDrawingPWV(date);
		}
		return null;
	}
}
