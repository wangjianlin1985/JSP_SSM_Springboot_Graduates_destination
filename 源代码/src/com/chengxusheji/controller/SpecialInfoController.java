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
import com.chengxusheji.service.SpecialInfoService;
import com.chengxusheji.po.SpecialInfo;

//SpecialInfo管理控制层
@Controller
@RequestMapping("/SpecialInfo")
public class SpecialInfoController extends BaseController {

    /*业务层对象*/
    @Resource SpecialInfoService specialInfoService;

	@InitBinder("specialInfo")
	public void initBinderSpecialInfo(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("specialInfo.");
	}
	/*跳转到添加SpecialInfo视图*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute(new SpecialInfo());
		return "SpecialInfo_add";
	}

	/*客户端ajax方式提交添加专业信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@Validated SpecialInfo specialInfo, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
		boolean success = false;
		if (br.hasErrors()) {
			message = "输入信息不符合要求！";
			writeJsonResponse(response, success, message);
			return ;
		}
		if(specialInfoService.getSpecialInfo(specialInfo.getSpecialNo()) != null) {
			message = "专业编号已经存在！";
			writeJsonResponse(response, success, message);
			return ;
		}
        specialInfoService.addSpecialInfo(specialInfo);
        message = "专业添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	/*ajax方式按照查询条件分页查询专业信息*/
	@RequestMapping(value = { "/list" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void list(String specialNo,String specialName,String bornDate,Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if (specialNo == null) specialNo = "";
		if (specialName == null) specialName = "";
		if (bornDate == null) bornDate = "";
		if(rows != 0)specialInfoService.setRows(rows);
		List<SpecialInfo> specialInfoList = specialInfoService.querySpecialInfo(specialNo, specialName, bornDate, page);
	    /*计算总的页数和总的记录数*/
	    specialInfoService.queryTotalPageAndRecordNumber(specialNo, specialName, bornDate);
	    /*获取到总的页码数目*/
	    int totalPage = specialInfoService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = specialInfoService.getRecordNumber();
        response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象
		JSONObject jsonObj=new JSONObject();
		jsonObj.accumulate("total", recordNumber);
		JSONArray jsonArray = new JSONArray();
		for(SpecialInfo specialInfo:specialInfoList) {
			JSONObject jsonSpecialInfo = specialInfo.getJsonObject();
			jsonArray.put(jsonSpecialInfo);
		}
		jsonObj.accumulate("rows", jsonArray);
		out.println(jsonObj.toString());
		out.flush();
		out.close();
	}

	/*ajax方式按照查询条件分页查询专业信息*/
	@RequestMapping(value = { "/listAll" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void listAll(HttpServletResponse response) throws Exception {
		List<SpecialInfo> specialInfoList = specialInfoService.queryAllSpecialInfo();
        response.setContentType("text/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for(SpecialInfo specialInfo:specialInfoList) {
			JSONObject jsonSpecialInfo = new JSONObject();
			jsonSpecialInfo.accumulate("specialNo", specialInfo.getSpecialNo());
			jsonSpecialInfo.accumulate("specialName", specialInfo.getSpecialName());
			jsonArray.put(jsonSpecialInfo);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	/*前台按照查询条件分页查询专业信息*/
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(String specialNo,String specialName,String bornDate,Integer currentPage, Model model, HttpServletRequest request) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		if (specialNo == null) specialNo = "";
		if (specialName == null) specialName = "";
		if (bornDate == null) bornDate = "";
		List<SpecialInfo> specialInfoList = specialInfoService.querySpecialInfo(specialNo, specialName, bornDate, currentPage);
	    /*计算总的页数和总的记录数*/
	    specialInfoService.queryTotalPageAndRecordNumber(specialNo, specialName, bornDate);
	    /*获取到总的页码数目*/
	    int totalPage = specialInfoService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = specialInfoService.getRecordNumber();
	    request.setAttribute("specialInfoList",  specialInfoList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("specialNo", specialNo);
	    request.setAttribute("specialName", specialName);
	    request.setAttribute("bornDate", bornDate);
		return "SpecialInfo/specialInfo_frontquery_result"; 
	}

     /*前台查询SpecialInfo信息*/
	@RequestMapping(value="/{specialNo}/frontshow",method=RequestMethod.GET)
	public String frontshow(@PathVariable String specialNo,Model model,HttpServletRequest request) throws Exception {
		/*根据主键specialNo获取SpecialInfo对象*/
        SpecialInfo specialInfo = specialInfoService.getSpecialInfo(specialNo);

        request.setAttribute("specialInfo",  specialInfo);
        return "SpecialInfo/specialInfo_frontshow";
	}

	/*ajax方式显示专业修改jsp视图页*/
	@RequestMapping(value="/{specialNo}/update",method=RequestMethod.GET)
	public void update(@PathVariable String specialNo,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键specialNo获取SpecialInfo对象*/
        SpecialInfo specialInfo = specialInfoService.getSpecialInfo(specialNo);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONObject jsonSpecialInfo = specialInfo.getJsonObject();
		out.println(jsonSpecialInfo.toString());
		out.flush();
		out.close();
	}

	/*ajax方式更新专业信息*/
	@RequestMapping(value = "/{specialNo}/update", method = RequestMethod.POST)
	public void update(@Validated SpecialInfo specialInfo, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
    	boolean success = false;
		if (br.hasErrors()) { 
			message = "输入的信息有错误！";
			writeJsonResponse(response, success, message);
			return;
		}
		try {
			specialInfoService.updateSpecialInfo(specialInfo);
			message = "专业更新成功!";
			success = true;
			writeJsonResponse(response, success, message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "专业更新失败!";
			writeJsonResponse(response, success, message); 
		}
	}
    /*删除专业信息*/
	@RequestMapping(value="/{specialNo}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable String specialNo,HttpServletRequest request) throws UnsupportedEncodingException {
		  try {
			  specialInfoService.deleteSpecialInfo(specialNo);
	            request.setAttribute("message", "专业删除成功!");
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", "专业删除失败!");
				return "error";

	        }

	}

	/*ajax方式删除多条专业记录*/
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void delete(String specialNos,HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		String message = "";
    	boolean success = false;
        try { 
        	int count = specialInfoService.deleteSpecialInfos(specialNos);
        	success = true;
        	message = count + "条记录删除成功";
        	writeJsonResponse(response, success, message);
        } catch (Exception e) { 
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
	}

	/*按照查询条件导出专业信息到Excel*/
	@RequestMapping(value = { "/OutToExcel" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void OutToExcel(String specialNo,String specialName,String bornDate, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
        if(specialNo == null) specialNo = "";
        if(specialName == null) specialName = "";
        if(bornDate == null) bornDate = "";
        List<SpecialInfo> specialInfoList = specialInfoService.querySpecialInfo(specialNo,specialName,bornDate);
        ExportExcelUtil ex = new ExportExcelUtil();
        String title = "SpecialInfo信息记录"; 
        String[] headers = { "专业编号","专业名称","开办日期"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<specialInfoList.size();i++) {
        	SpecialInfo specialInfo = specialInfoList.get(i); 
        	dataset.add(new String[]{specialInfo.getSpecialNo(),specialInfo.getSpecialName(),specialInfo.getBornDate()});
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
			response.setHeader("Content-disposition","attachment; filename="+"SpecialInfo.xls");//filename是下载的xls的名，建议最好用英文 
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
