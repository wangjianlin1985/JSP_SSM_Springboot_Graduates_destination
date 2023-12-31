﻿package com.chengxusheji.controller;

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
import com.chengxusheji.service.GradeInfoService;
import com.chengxusheji.po.GradeInfo;

//GradeInfo管理控制层
@Controller
@RequestMapping("/GradeInfo")
public class GradeInfoController extends BaseController {

    /*业务层对象*/
    @Resource GradeInfoService gradeInfoService;

	@InitBinder("gradeInfo")
	public void initBinderGradeInfo(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("gradeInfo.");
	}
	/*跳转到添加GradeInfo视图*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute(new GradeInfo());
		return "GradeInfo_add";
	}

	/*客户端ajax方式提交添加年级信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@Validated GradeInfo gradeInfo, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
		boolean success = false;
		if (br.hasErrors()) {
			message = "输入信息不符合要求！";
			writeJsonResponse(response, success, message);
			return ;
		}
        gradeInfoService.addGradeInfo(gradeInfo);
        message = "年级添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	/*ajax方式按照查询条件分页查询年级信息*/
	@RequestMapping(value = { "/list" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void list(Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if(rows != 0)gradeInfoService.setRows(rows);
		List<GradeInfo> gradeInfoList = gradeInfoService.queryGradeInfo(page);
	    /*计算总的页数和总的记录数*/
	    gradeInfoService.queryTotalPageAndRecordNumber();
	    /*获取到总的页码数目*/
	    int totalPage = gradeInfoService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = gradeInfoService.getRecordNumber();
        response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象
		JSONObject jsonObj=new JSONObject();
		jsonObj.accumulate("total", recordNumber);
		JSONArray jsonArray = new JSONArray();
		for(GradeInfo gradeInfo:gradeInfoList) {
			JSONObject jsonGradeInfo = gradeInfo.getJsonObject();
			jsonArray.put(jsonGradeInfo);
		}
		jsonObj.accumulate("rows", jsonArray);
		out.println(jsonObj.toString());
		out.flush();
		out.close();
	}

	/*ajax方式按照查询条件分页查询年级信息*/
	@RequestMapping(value = { "/listAll" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void listAll(HttpServletResponse response) throws Exception {
		List<GradeInfo> gradeInfoList = gradeInfoService.queryAllGradeInfo();
        response.setContentType("text/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for(GradeInfo gradeInfo:gradeInfoList) {
			JSONObject jsonGradeInfo = new JSONObject();
			jsonGradeInfo.accumulate("gradeId", gradeInfo.getGradeId());
			jsonGradeInfo.accumulate("gradeName", gradeInfo.getGradeName());
			jsonArray.put(jsonGradeInfo);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	/*前台按照查询条件分页查询年级信息*/
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(Integer currentPage, Model model, HttpServletRequest request) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		List<GradeInfo> gradeInfoList = gradeInfoService.queryGradeInfo(currentPage);
	    /*计算总的页数和总的记录数*/
	    gradeInfoService.queryTotalPageAndRecordNumber();
	    /*获取到总的页码数目*/
	    int totalPage = gradeInfoService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = gradeInfoService.getRecordNumber();
	    request.setAttribute("gradeInfoList",  gradeInfoList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
		return "GradeInfo/gradeInfo_frontquery_result"; 
	}

     /*前台查询GradeInfo信息*/
	@RequestMapping(value="/{gradeId}/frontshow",method=RequestMethod.GET)
	public String frontshow(@PathVariable Integer gradeId,Model model,HttpServletRequest request) throws Exception {
		/*根据主键gradeId获取GradeInfo对象*/
        GradeInfo gradeInfo = gradeInfoService.getGradeInfo(gradeId);

        request.setAttribute("gradeInfo",  gradeInfo);
        return "GradeInfo/gradeInfo_frontshow";
	}

	/*ajax方式显示年级修改jsp视图页*/
	@RequestMapping(value="/{gradeId}/update",method=RequestMethod.GET)
	public void update(@PathVariable Integer gradeId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键gradeId获取GradeInfo对象*/
        GradeInfo gradeInfo = gradeInfoService.getGradeInfo(gradeId);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONObject jsonGradeInfo = gradeInfo.getJsonObject();
		out.println(jsonGradeInfo.toString());
		out.flush();
		out.close();
	}

	/*ajax方式更新年级信息*/
	@RequestMapping(value = "/{gradeId}/update", method = RequestMethod.POST)
	public void update(@Validated GradeInfo gradeInfo, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
    	boolean success = false;
		if (br.hasErrors()) { 
			message = "输入的信息有错误！";
			writeJsonResponse(response, success, message);
			return;
		}
		try {
			gradeInfoService.updateGradeInfo(gradeInfo);
			message = "年级更新成功!";
			success = true;
			writeJsonResponse(response, success, message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "年级更新失败!";
			writeJsonResponse(response, success, message); 
		}
	}
    /*删除年级信息*/
	@RequestMapping(value="/{gradeId}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Integer gradeId,HttpServletRequest request) throws UnsupportedEncodingException {
		  try {
			  gradeInfoService.deleteGradeInfo(gradeId);
	            request.setAttribute("message", "年级删除成功!");
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", "年级删除失败!");
				return "error";

	        }

	}

	/*ajax方式删除多条年级记录*/
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void delete(String gradeIds,HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		String message = "";
    	boolean success = false;
        try { 
        	int count = gradeInfoService.deleteGradeInfos(gradeIds);
        	success = true;
        	message = count + "条记录删除成功";
        	writeJsonResponse(response, success, message);
        } catch (Exception e) { 
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
	}

	/*按照查询条件导出年级信息到Excel*/
	@RequestMapping(value = { "/OutToExcel" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void OutToExcel( Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
        List<GradeInfo> gradeInfoList = gradeInfoService.queryGradeInfo();
        ExportExcelUtil ex = new ExportExcelUtil();
        String title = "GradeInfo信息记录"; 
        String[] headers = { "记录id","年级名称"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<gradeInfoList.size();i++) {
        	GradeInfo gradeInfo = gradeInfoList.get(i); 
        	dataset.add(new String[]{gradeInfo.getGradeId() + "",gradeInfo.getGradeName()});
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
			response.setHeader("Content-disposition","attachment; filename="+"GradeInfo.xls");//filename是下载的xls的名，建议最好用英文 
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
