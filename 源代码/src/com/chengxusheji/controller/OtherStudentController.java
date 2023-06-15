package com.chengxusheji.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.chengxusheji.utils.ExportExcelUtil;
import com.chengxusheji.utils.UserException;
import com.chengxusheji.service.OtherStudentService;
import com.chengxusheji.po.OtherStudent;
import com.chengxusheji.service.GradeInfoService;
import com.chengxusheji.po.GradeInfo;
import com.chengxusheji.service.SpecialInfoService;
import com.chengxusheji.po.SpecialInfo;

//OtherStudent管理控制层
@Controller
@RequestMapping("/OtherStudent")
public class OtherStudentController extends BaseController {

    /*业务层对象*/
    @Resource OtherStudentService otherStudentService;

    @Resource GradeInfoService gradeInfoService;
    @Resource SpecialInfoService specialInfoService;
	@InitBinder("specialObj")
	public void initBinderspecialObj(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("specialObj.");
	}
	@InitBinder("gradeObj")
	public void initBindergradeObj(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("gradeObj.");
	}
	@InitBinder("otherStudent")
	public void initBinderOtherStudent(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("otherStudent.");
	}
	/*跳转到添加OtherStudent视图*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute(new OtherStudent());
		/*查询所有的GradeInfo信息*/
		List<GradeInfo> gradeInfoList = gradeInfoService.queryAllGradeInfo();
		request.setAttribute("gradeInfoList", gradeInfoList);
		/*查询所有的SpecialInfo信息*/
		List<SpecialInfo> specialInfoList = specialInfoService.queryAllSpecialInfo();
		request.setAttribute("specialInfoList", specialInfoList);
		return "OtherStudent_add";
	}

	/*客户端ajax方式提交添加其他学生信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@Validated OtherStudent otherStudent, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
		boolean success = false;
		if (br.hasErrors()) {
			message = "输入信息不符合要求！";
			writeJsonResponse(response, success, message);
			return ;
		}
        otherStudentService.addOtherStudent(otherStudent);
        message = "其他学生添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	/*ajax方式按照查询条件分页查询其他学生信息*/
	@RequestMapping(value = { "/list" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void list(String studentNumber,String studentName,@ModelAttribute("specialObj") SpecialInfo specialObj,@ModelAttribute("gradeObj") GradeInfo gradeObj,String yearMonth,Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if (studentNumber == null) studentNumber = "";
		if (studentName == null) studentName = "";
		if (yearMonth == null) yearMonth = "";
		if(rows != 0)otherStudentService.setRows(rows);
		List<OtherStudent> otherStudentList = otherStudentService.queryOtherStudent(studentNumber, studentName, specialObj, gradeObj, yearMonth, page);
	    /*计算总的页数和总的记录数*/
	    otherStudentService.queryTotalPageAndRecordNumber(studentNumber, studentName, specialObj, gradeObj, yearMonth);
	    /*获取到总的页码数目*/
	    int totalPage = otherStudentService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = otherStudentService.getRecordNumber();
        response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象
		JSONObject jsonObj=new JSONObject();
		jsonObj.accumulate("total", recordNumber);
		JSONArray jsonArray = new JSONArray();
		for(OtherStudent otherStudent:otherStudentList) {
			JSONObject jsonOtherStudent = otherStudent.getJsonObject();
			jsonArray.put(jsonOtherStudent);
		}
		jsonObj.accumulate("rows", jsonArray);
		out.println(jsonObj.toString());
		out.flush();
		out.close();
	}

	/*ajax方式按照查询条件分页查询其他学生信息*/
	@RequestMapping(value = { "/listAll" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void listAll(HttpServletResponse response) throws Exception {
		List<OtherStudent> otherStudentList = otherStudentService.queryAllOtherStudent();
        response.setContentType("text/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for(OtherStudent otherStudent:otherStudentList) {
			JSONObject jsonOtherStudent = new JSONObject();
			jsonOtherStudent.accumulate("id", otherStudent.getId());
			jsonArray.put(jsonOtherStudent);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	/*前台按照查询条件分页查询其他学生信息*/
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(String studentNumber,String studentName,@ModelAttribute("specialObj") SpecialInfo specialObj,@ModelAttribute("gradeObj") GradeInfo gradeObj,String yearMonth,Integer currentPage, Model model, HttpServletRequest request) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		if (studentNumber == null) studentNumber = "";
		if (studentName == null) studentName = "";
		if (yearMonth == null) yearMonth = "";
		List<OtherStudent> otherStudentList = otherStudentService.queryOtherStudent(studentNumber, studentName, specialObj, gradeObj, yearMonth, currentPage);
	    /*计算总的页数和总的记录数*/
	    otherStudentService.queryTotalPageAndRecordNumber(studentNumber, studentName, specialObj, gradeObj, yearMonth);
	    /*获取到总的页码数目*/
	    int totalPage = otherStudentService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = otherStudentService.getRecordNumber();
	    request.setAttribute("otherStudentList",  otherStudentList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("studentNumber", studentNumber);
	    request.setAttribute("studentName", studentName);
	    request.setAttribute("specialObj", specialObj);
	    request.setAttribute("gradeObj", gradeObj);
	    request.setAttribute("yearMonth", yearMonth);
	    List<GradeInfo> gradeInfoList = gradeInfoService.queryAllGradeInfo();
	    request.setAttribute("gradeInfoList", gradeInfoList);
	    List<SpecialInfo> specialInfoList = specialInfoService.queryAllSpecialInfo();
	    request.setAttribute("specialInfoList", specialInfoList);
		return "OtherStudent/otherStudent_frontquery_result"; 
	}

     /*前台查询OtherStudent信息*/
	@RequestMapping(value="/{id}/frontshow",method=RequestMethod.GET)
	public String frontshow(@PathVariable Integer id,Model model,HttpServletRequest request) throws Exception {
		/*根据主键id获取OtherStudent对象*/
        OtherStudent otherStudent = otherStudentService.getOtherStudent(id);

        List<GradeInfo> gradeInfoList = gradeInfoService.queryAllGradeInfo();
        request.setAttribute("gradeInfoList", gradeInfoList);
        List<SpecialInfo> specialInfoList = specialInfoService.queryAllSpecialInfo();
        request.setAttribute("specialInfoList", specialInfoList);
        request.setAttribute("otherStudent",  otherStudent);
        return "OtherStudent/otherStudent_frontshow";
	}

	/*ajax方式显示其他学生修改jsp视图页*/
	@RequestMapping(value="/{id}/update",method=RequestMethod.GET)
	public void update(@PathVariable Integer id,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键id获取OtherStudent对象*/
        OtherStudent otherStudent = otherStudentService.getOtherStudent(id);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONObject jsonOtherStudent = otherStudent.getJsonObject();
		out.println(jsonOtherStudent.toString());
		out.flush();
		out.close();
	}

	/*ajax方式更新其他学生信息*/
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public void update(@Validated OtherStudent otherStudent, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
    	boolean success = false;
		if (br.hasErrors()) { 
			message = "输入的信息有错误！";
			writeJsonResponse(response, success, message);
			return;
		}
		try {
			otherStudentService.updateOtherStudent(otherStudent);
			message = "其他学生更新成功!";
			success = true;
			writeJsonResponse(response, success, message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "其他学生更新失败!";
			writeJsonResponse(response, success, message); 
		}
	}
    /*删除其他学生信息*/
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Integer id,HttpServletRequest request) throws UnsupportedEncodingException {
		  try {
			  otherStudentService.deleteOtherStudent(id);
	            request.setAttribute("message", "其他学生删除成功!");
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", "其他学生删除失败!");
				return "error";

	        }

	}

	/*ajax方式删除多条其他学生记录*/
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void delete(String ids,HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		String message = "";
    	boolean success = false;
        try { 
        	int count = otherStudentService.deleteOtherStudents(ids);
        	success = true;
        	message = count + "条记录删除成功";
        	writeJsonResponse(response, success, message);
        } catch (Exception e) { 
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
	}

	/*按照查询条件导出其他学生信息到Excel*/
	@RequestMapping(value = { "/OutToExcel" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void OutToExcel(String studentNumber,String studentName,@ModelAttribute("specialObj") SpecialInfo specialObj,@ModelAttribute("gradeObj") GradeInfo gradeObj,String yearMonth, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
        if(studentNumber == null) studentNumber = "";
        if(studentName == null) studentName = "";
        if(yearMonth == null) yearMonth = "";
        List<OtherStudent> otherStudentList = otherStudentService.queryOtherStudent(studentNumber,studentName,specialObj,gradeObj,yearMonth);
        ExportExcelUtil ex = new ExportExcelUtil();
        String title = "OtherStudent信息记录"; 
        String[] headers = { "学号","姓名","专业","年级","年月份"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<otherStudentList.size();i++) {
        	OtherStudent otherStudent = otherStudentList.get(i); 
        	dataset.add(new String[]{otherStudent.getStudentNumber(),otherStudent.getStudentName(),otherStudent.getSpecialObj().getSpecialName(),otherStudent.getGradeObj().getGradeName(),otherStudent.getYearMonth()});
        }
        /*
        OutputStream out = null;
		try {
			out = new FileOutputStream("C://output.xls");
			ex.exportExcel(title,headers, dataset, out);
		    out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		OutputStream out = null;//创建一个输出流对象 
		try { 
			out = response.getOutputStream();//
			response.setHeader("Content-disposition","attachment; filename="+"OtherStudent.xls");//filename是下载的xls的名，建议最好用英文 
			response.setContentType("application/msexcel;charset=UTF-8");//设置类型 
			response.setHeader("Pragma","No-cache");//设置头 
			response.setHeader("Cache-Control","no-cache");//设置头 
			response.setDateHeader("Expires", 0);//设置日期头  
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			ex.exportExcel(rootPath,title,headers, dataset, out);
			out.flush();
		} catch (IOException e) { 
			e.printStackTrace(); 
		}finally{
			try{
				if(out!=null){ 
					out.close(); 
				}
			}catch(IOException e){ 
				e.printStackTrace(); 
			} 
		}
    }
}
