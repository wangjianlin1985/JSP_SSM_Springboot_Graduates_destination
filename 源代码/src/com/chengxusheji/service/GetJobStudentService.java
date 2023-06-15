package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.SpecialInfo;
import com.chengxusheji.po.GradeInfo;
import com.chengxusheji.po.GetJobStudent;

import com.chengxusheji.mapper.GetJobStudentMapper;
@Service
public class GetJobStudentService {

	@Resource GetJobStudentMapper getJobStudentMapper;
    /*每页显示记录数目*/
    private int rows = 10;;
    public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

    /*保存查询后总的页数*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*保存查询到的总记录数*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*添加就业学生记录*/
    public void addGetJobStudent(GetJobStudent getJobStudent) throws Exception {
    	getJobStudentMapper.addGetJobStudent(getJobStudent);
    }

    /*按照查询条件分页查询就业学生记录*/
    public ArrayList<GetJobStudent> queryGetJobStudent(String studentNumber,String name,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_getJobStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!name.equals("")) where = where + " and t_getJobStudent.name like '%" + name + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null  && !specialObj.getSpecialNo().equals(""))  where += " and t_getJobStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_getJobStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_getJobStudent.yearMonth like '%" + yearMonth + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return getJobStudentMapper.queryGetJobStudent(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<GetJobStudent> queryGetJobStudent(String studentNumber,String name,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth) throws Exception  { 
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_getJobStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!name.equals("")) where = where + " and t_getJobStudent.name like '%" + name + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null && !specialObj.getSpecialNo().equals(""))  where += " and t_getJobStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_getJobStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_getJobStudent.yearMonth like '%" + yearMonth + "%'";
    	return getJobStudentMapper.queryGetJobStudentList(where);
    }

    /*查询所有就业学生记录*/
    public ArrayList<GetJobStudent> queryAllGetJobStudent()  throws Exception {
        return getJobStudentMapper.queryGetJobStudentList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(String studentNumber,String name,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth) throws Exception {
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_getJobStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!name.equals("")) where = where + " and t_getJobStudent.name like '%" + name + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null && !specialObj.getSpecialNo().equals(""))  where += " and t_getJobStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_getJobStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_getJobStudent.yearMonth like '%" + yearMonth + "%'";
        recordNumber = getJobStudentMapper.queryGetJobStudentCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取就业学生记录*/
    public GetJobStudent getGetJobStudent(int id) throws Exception  {
        GetJobStudent getJobStudent = getJobStudentMapper.getGetJobStudent(id);
        return getJobStudent;
    }

    /*更新就业学生记录*/
    public void updateGetJobStudent(GetJobStudent getJobStudent) throws Exception {
        getJobStudentMapper.updateGetJobStudent(getJobStudent);
    }

    /*删除一条就业学生记录*/
    public void deleteGetJobStudent (int id) throws Exception {
        getJobStudentMapper.deleteGetJobStudent(id);
    }

    /*删除多条就业学生信息*/
    public int deleteGetJobStudents (String ids) throws Exception {
    	String _ids[] = ids.split(",");
    	for(String _id: _ids) {
    		getJobStudentMapper.deleteGetJobStudent(Integer.parseInt(_id));
    	}
    	return _ids.length;
    }
}
