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
import com.chengxusheji.service.EntrepreneurStudentService;
import com.chengxusheji.po.EntrepreneurStudent;
import com.chengxusheji.service.GradeInfoService;
import com.chengxusheji.po.GradeInfo;
import com.chengxusheji.service.SpecialInfoService;
import com.chengxusheji.po.SpecialInfo;

//EntrepreneurStudent管理控制层
@Controller
@RequestMapping("/EntrepreneurStudent")
public class EntrepreneurStudentController extends BaseController {

    /*业务层对象*/
    @Resource EntrepreneurStudentService entrepreneurStudentService;

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
	@InitBinder("entrepreneurStudent")
	public void initBinderEntrepreneurStudent(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("entrepreneurStudent.");
	}
	/*跳转到添加EntrepreneurStudent视图*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute(new EntrepreneurStudent());
		/*查询所有的GradeInfo信息*/
		List<GradeInfo> gradeInfoList = gradeInfoService.queryAllGradeInfo();
		request.setAttribute("gradeInfoList", gradeInfoList);
		/*查询所有的SpecialInfo信息*/
		List<SpecialInfo> specialInfoList = specialInfoService.queryAllSpecialInfo();
		request.setAttribute("specialInfoList", specialInfoList);
		return "EntrepreneurStudent_add";
	}

	/*客户端ajax方式提交添加创业学生信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@Validated EntrepreneurStudent entrepreneurStudent, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
		boolean success = false;
		if (br.hasErrors()) {
			message = "输入信息不符合要求！";
			writeJsonResponse(response, success, message);
			return ;
		}
        entrepreneurStudentService.addEntrepreneurStudent(entrepreneurStudent);
        message = "创业学生添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	/*ajax方式按照查询条件分页查询创业学生信息*/
	@RequestMapping(value = { "/list" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void list(String studentNumber,String studentName,@ModelAttribute("specialObj") SpecialInfo specialObj,@ModelAttribute("gradeObj") GradeInfo gradeObj,String yearMonth,String areaInfo,String entreName,Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if (studentNumber == null) studentNumber = "";
		if (studentName == null) studentName = "";
		if (yearMonth == null) yearMonth = "";
		if (areaInfo == null) areaInfo = "";
		if (entreName == null) entreName = "";
		if(rows != 0)entrepreneurStudentService.setRows(rows);
		List<EntrepreneurStudent> entrepreneurStudentList = entrepreneurStudentService.queryEntrepreneurStudent(studentNumber, studentName, specialObj, gradeObj, yearMonth, areaInfo, entreName, page);
	    /*计算总的页数和总的记录数*/
	    entrepreneurStudentService.queryTotalPageAndRecordNumber(studentNumber, studentName, specialObj, gradeObj, yearMonth, areaInfo, entreName);
	    /*获取到总的页码数目*/
	    int totalPage = entrepreneurStudentService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = entrepreneurStudentService.getRecordNumber();
        response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象
		JSONObject jsonObj=new JSONObject();
		jsonObj.accumulate("total", recordNumber);
		JSONArray jsonArray = new JSONArray();
		for(EntrepreneurStudent entrepreneurStudent:entrepreneurStudentList) {
			JSONObject jsonEntrepreneurStudent = entrepreneurStudent.getJsonObject();
			jsonArray.put(jsonEntrepreneurStudent);
		}
		jsonObj.accumulate("rows", jsonArray);
		out.println(jsonObj.toString());
		out.flush();
		out.close();
	}

	/*ajax方式按照查询条件分页查询创业学生信息*/
	@RequestMapping(value = { "/listAll" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void listAll(HttpServletResponse response) throws Exception {
		List<EntrepreneurStudent> entrepreneurStudentList = entrepreneurStudentService.queryAllEntrepreneurStudent();
        response.setContentType("text/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for(EntrepreneurStudent entrepreneurStudent:entrepreneurStudentList) {
			JSONObject jsonEntrepreneurStudent = new JSONObject();
			jsonEntrepreneurStudent.accumulate("id", entrepreneurStudent.getId());
			jsonArray.put(jsonEntrepreneurStudent);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	/*前台按照查询条件分页查询创业学生信息*/
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(String studentNumber,String studentName,@ModelAttribute("specialObj") SpecialInfo specialObj,@ModelAttribute("gradeObj") GradeInfo gradeObj,String yearMonth,String areaInfo,String entreName,Integer currentPage, Model model, HttpServletRequest request) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		if (studentNumber == null) studentNumber = "";
		if (studentName == null) studentName = "";
		if (yearMonth == null) yearMonth = "";
		if (areaInfo == null) areaInfo = "";
		if (entreName == null) entreName = "";
		List<EntrepreneurStudent> entrepreneurStudentList = entrepreneurStudentService.queryEntrepreneurStudent(studentNumber, studentName, specialObj, gradeObj, yearMonth, areaInfo, entreName, currentPage);
	    /*计算总的页数和总的记录数*/
	    entrepreneurStudentService.queryTotalPageAndRecordNumber(studentNumber, studentName, specialObj, gradeObj, yearMonth, areaInfo, entreName);
	    /*获取到总的页码数目*/
	    int totalPage = entrepreneurStudentService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = entrepreneurStudentService.getRecordNumber();
	    request.setAttribute("entrepreneurStudentList",  entrepreneurStudentList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("studentNumber", studentNumber);
	    request.setAttribute("studentName", studentName);
	    request.setAttribute("specialObj", specialObj);
	    request.setAttribute("gradeObj", gradeObj);
	    request.setAttribute("yearMonth", yearMonth);
	    request.setAttribute("areaInfo", areaInfo);
	    request.setAttribute("entreName", entreName);
	    List<GradeInfo> gradeInfoList = gradeInfoService.queryAllGradeInfo();
	    request.setAttribute("gradeInfoList", gradeInfoList);
	    List<SpecialInfo> specialInfoList = specialInfoService.queryAllSpecialInfo();
	    request.setAttribute("specialInfoList", specialInfoList);
		return "EntrepreneurStudent/entrepreneurStudent_frontquery_result"; 
	}

     /*前台查询EntrepreneurStudent信息*/
	@RequestMapping(value="/{id}/frontshow",method=RequestMethod.GET)
	public String frontshow(@PathVariable Integer id,Model model,HttpServletRequest request) throws Exception {
		/*根据主键id获取EntrepreneurStudent对象*/
        EntrepreneurStudent entrepreneurStudent = entrepreneurStudentService.getEntrepreneurStudent(id);

        List<GradeInfo> gradeInfoList = gradeInfoService.queryAllGradeInfo();
        request.setAttribute("gradeInfoList", gradeInfoList);
        List<SpecialInfo> specialInfoList = specialInfoService.queryAllSpecialInfo();
        request.setAttribute("specialInfoList", specialInfoList);
        request.setAttribute("entrepreneurStudent",  entrepreneurStudent);
        return "EntrepreneurStudent/entrepreneurStudent_frontshow";
	}

	/*ajax方式显示创业学生修改jsp视图页*/
	@RequestMapping(value="/{id}/update",method=RequestMethod.GET)
	public void update(@PathVariable Integer id,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键id获取EntrepreneurStudent对象*/
        EntrepreneurStudent entrepreneurStudent = entrepreneurStudentService.getEntrepreneurStudent(id);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONObject jsonEntrepreneurStudent = entrepreneurStudent.getJsonObject();
		out.println(jsonEntrepreneurStudent.toString());
		out.flush();
		out.close();
	}

	/*ajax方式更新创业学生信息*/
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public void update(@Validated EntrepreneurStudent entrepreneurStudent, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
    	boolean success = false;
		if (br.hasErrors()) { 
			message = "输入的信息有错误！";
			writeJsonResponse(response, success, message);
			return;
		}
		try {
			entrepreneurStudentService.updateEntrepreneurStudent(entrepreneurStudent);
			message = "创业学生更新成功!";
			success = true;
			writeJsonResponse(response, success, message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "创业学生更新失败!";
			writeJsonResponse(response, success, message); 
		}
	}
    /*删除创业学生信息*/
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Integer id,HttpServletRequest request) throws UnsupportedEncodingException {
		  try {
			  entrepreneurStudentService.deleteEntrepreneurStudent(id);
	            request.setAttribute("message", "创业学生删除成功!");
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", "创业学生删除失败!");
				return "error";

	        }

	}

	/*ajax方式删除多条创业学生记录*/
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void delete(String ids,HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		String message = "";
    	boolean success = false;
        try { 
        	int count = entrepreneurStudentService.deleteEntrepreneurStudents(ids);
        	success = true;
        	message = count + "条记录删除成功";
        	writeJsonResponse(response, success, message);
        } catch (Exception e) { 
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
	}

	/*按照查询条件导出创业学生信息到Excel*/
	@RequestMapping(value = { "/OutToExcel" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void OutToExcel(String studentNumber,String studentName,@ModelAttribute("specialObj") SpecialInfo specialObj,@ModelAttribute("gradeObj") GradeInfo gradeObj,String yearMonth,String areaInfo,String entreName, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
        if(studentNumber == null) studentNumber = "";
        if(studentName == null) studentName = "";
        if(yearMonth == null) yearMonth = "";
        if(areaInfo == null) areaInfo = "";
        if(entreName == null) entreName = "";
        List<EntrepreneurStudent> entrepreneurStudentList = entrepreneurStudentService.queryEntrepreneurStudent(studentNumber,studentName,specialObj,gradeObj,yearMonth,areaInfo,entreName);
        ExportExcelUtil ex = new ExportExcelUtil();
        String title = "EntrepreneurStudent信息记录"; 
        String[] headers = { "学号","姓名","专业","年级","年月份","地区","创业名称","公司性质","公司人数"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<entrepreneurStudentList.size();i++) {
        	EntrepreneurStudent entrepreneurStudent = entrepreneurStudentList.get(i); 
        	dataset.add(new String[]{entrepreneurStudent.getStudentNumber(),entrepreneurStudent.getStudentName(),entrepreneurStudent.getSpecialObj().getSpecialName(),entrepreneurStudent.getGradeObj().getGradeName(),entrepreneurStudent.getYearMonth(),entrepreneurStudent.getAreaInfo(),entrepreneurStudent.getEntreName(),entrepreneurStudent.getCompanyType(),entrepreneurStudent.getPersonNumber()});
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
			response.setHeader("Content-disposition","attachment; filename="+"EntrepreneurStudent.xls");//filename是下载的xls的名，建议最好用英文 
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
