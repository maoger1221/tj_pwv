package tj.pwv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tj.pwv.pojo.DrawingCheckBox;
import tj.pwv.pojo.ViewObject;
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
		ViewObject vo = new ViewObject();
		if (drawingbox.contains("mwrz")) {
			vo.set("iwvdata",dataService.getDbDrawingMwrZ(date));
		}
		if (drawingbox.contains("pwv")) {
			vo.set("pwvdata",dataService.getDbDrawingPWV(date,""));
		}
		return vo;
	}

}
