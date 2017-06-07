package tj.pwv.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tj.pwv.pojo.*;
import tj.pwv.service.DataService;
import tj.pwv.service.FileService;


@Controller
public class DataController {
	@Autowired
	private DataService dataService;
	@Autowired
	private FileService fileService;
	//获取分页数据
	@RequestMapping(value="/getdbdata", method=RequestMethod.POST)
	@ResponseBody
	public Object getDbData(String date,String elev,String azi,String iwv,String swv,String prn,Integer pageNum,String type,HttpSession httpSession){
		if(type == null || type.equals("")){
			type = (String) httpSession.getAttribute("type");
		}else{
			httpSession.setAttribute("type", type);
		}
		if(type.equals("mwr2d")){
			List<Mwr2d> dbData = dataService.getMwr2d(date,elev,azi,iwv,pageNum,httpSession);
			return dbData;
		}else if(type.equals("mwrz")){
			List<MwrZenit> dbData = dataService.getMwrZ(date,elev,azi,iwv,pageNum,httpSession);
			return dbData;
		}else if (type.equals("swv")){
			List<Swv> dbData = dataService.getSwv(date,elev,azi,swv,prn,pageNum,httpSession);
			return dbData;
		}
		return null;
	}
	@RequestMapping("/getTotalpages")
	@ResponseBody
	public String getTotalPages(HttpSession httpSession){
		return String.valueOf(httpSession.getAttribute("totalpages"));
	}
	@RequestMapping(value="/clearSession")
	@ResponseBody
	public String clearSession(HttpSession httpSession){
		httpSession.setAttribute("pageNum", 1);
		httpSession.setAttribute("date", "");
		httpSession.setAttribute("elev", "");
		httpSession.setAttribute("azi", "");
		httpSession.setAttribute("iwv", "");
		httpSession.setAttribute("totalpages", "");
		httpSession.setAttribute("type", "");
		return "success";
	}


    @RequestMapping(value="/getAnalysis", method=RequestMethod.POST)
    @ResponseBody
    public Object getAnalysis(String date,String drawingbox,String ownfilename,String selectbox,HttpSession httpSession){
        ViewObject vo = new ViewObject();

        if (drawingbox.contains("after")) {
        	vo = dataService.getDbDrawingPWV(date,ownfilename,selectbox);
        }
		if (drawingbox.contains("before")) {
			vo.set("pwv_before",dataService.getDbDrawingPWV(date,ownfilename));
		}

		User user = (User)httpSession.getAttribute("user");
		Date now = new Date();
		String filename = String.valueOf(now.getTime()) + user.getUsername() + selectbox ;
		String path=DATAPOSTION + filename;
		if (vo.getObjs().get("pwv_before") != null){
			vo.set("pwv_before_name",filename + "pwv_before");
			fileService.createFile(path + "pwv_before" + FILESUFFIX,vo.getObjs().get("pwv_before"),"pwv_before");
		}
		if (vo.getObjs().get("pwv_after") != null){
			vo.set("pwv_after_name",filename + "pwv_after");
			fileService.createFile(path + "pwv_after" + FILESUFFIX,vo.getObjs().get("pwv_after"),"pwv_after");
		}
		if (vo.getObjs().get("r") != null){
			vo.set("r_name",filename + "r");
			fileService.createFile(path + "r" + FILESUFFIX,vo.getObjs().get("r"),"r");
		}
		if (vo.getObjs().get("sigma") != null){
			vo.set("sigma_name",filename + "sigma");
			fileService.createFile(path + "sigma" + FILESUFFIX,vo.getObjs().get("sigma"),"sigma");
		}
		//文件太大了，写入很慢
		/*if (vo.getObjs().get("y") != null){
			vo.set("y_name",filename + "y" + FILESUFFIX);
			fileService.createFile(path + "y" + FILESUFFIX,vo.getObjs().get("y"),"y");
		}*/
		if (vo.getObjs().get("predict") != null){
			vo.set("predict_name",filename + "predict");
			fileService.createFile(path + "predict" + FILESUFFIX,vo.getObjs().get("predict"),"predict");
		}
        return vo;
    }



	private static final String DATAPOSTION = "E:\\JetBrains\\IdeaProjects\\tj_pwv\\src\\main\\resources\\downlfiles\\";
	private static final String DATAPOSTION1 = "E:\\JetBrains\\IdeaProjects\\tj_pwv\\src\\main\\resources\\upload\\";
	private static final String FILESUFFIX = ".txt";
    //下载文件
	@RequestMapping(value = "/download/{filename}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@PathVariable("filename") String filename) throws IOException {
		String filename1 = filename + FILESUFFIX;
		String path=DATAPOSTION + filename1;
		File file=new File(path);
		HttpHeaders headers = new HttpHeaders();
		String fileName2=new String(filename1.getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName2);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);//ie浏览器用这个方法不能下载,将HttpStatus.created改为HttpStatus.OK
	}

	//上传文件
	@RequestMapping(value = "/fileUpload")
	@ResponseBody
	public Object fileUpload(@RequestParam("file") MultipartFile file) {
		ViewObject vo = new ViewObject();
		// 判断文件是否为空
		if (!file.isEmpty()) {
			if(file.getSize() > 1024 * 1024 * 2){
				return "file is bigger than 2MB";
			}
			try {
				// 文件保存路径
				String filename = new Date().getTime() + file.getOriginalFilename();
				// 转存文件
				file.transferTo(new File(DATAPOSTION1 + filename));

				vo.set("msg","success!!");
				vo.set("filename",filename);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}



}
