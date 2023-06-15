package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.SpecialInfo;
import com.chengxusheji.po.GradeInfo;
import com.chengxusheji.po.OtherStudent;

import com.chengxusheji.mapper.OtherStudentMapper;
@Service
public class OtherStudentService {

	@Resource OtherStudentMapper otherStudentMapper;
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

    /*添加其他学生记录*/
    public void addOtherStudent(OtherStudent otherStudent) throws Exception {
    	otherStudentMapper.addOtherStudent(otherStudent);
    }

    /*按照查询条件分页查询其他学生记录*/
    public ArrayList<OtherStudent> queryOtherStudent(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_otherStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_otherStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null  && !specialObj.getSpecialNo().equals(""))  where += " and t_otherStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_otherStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_otherStudent.yearMonth like '%" + yearMonth + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return otherStudentMapper.queryOtherStudent(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<OtherStudent> queryOtherStudent(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth) throws Exception  { 
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_otherStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_otherStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null && !specialObj.getSpecialNo().equals(""))  where += " and t_otherStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_otherStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_otherStudent.yearMonth like '%" + yearMonth + "%'";
    	return otherStudentMapper.queryOtherStudentList(where);
    }

    /*查询所有其他学生记录*/
    public ArrayList<OtherStudent> queryAllOtherStudent()  throws Exception {
        return otherStudentMapper.queryOtherStudentList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth) throws Exception {
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_otherStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_otherStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null && !specialObj.getSpecialNo().equals(""))  where += " and t_otherStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_otherStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_otherStudent.yearMonth like '%" + yearMonth + "%'";
        recordNumber = otherStudentMapper.queryOtherStudentCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取其他学生记录*/
    public OtherStudent getOtherStudent(int id) throws Exception  {
        OtherStudent otherStudent = otherStudentMapper.getOtherStudent(id);
        return otherStudent;
    }

    /*更新其他学生记录*/
    public void updateOtherStudent(OtherStudent otherStudent) throws Exception {
        otherStudentMapper.updateOtherStudent(otherStudent);
    }

    /*删除一条其他学生记录*/
    public void deleteOtherStudent (int id) throws Exception {
        otherStudentMapper.deleteOtherStudent(id);
    }

    /*删除多条其他学生信息*/
    public int deleteOtherStudents (String ids) throws Exception {
    	String _ids[] = ids.split(",");
    	for(String _id: _ids) {
    		otherStudentMapper.deleteOtherStudent(Integer.parseInt(_id));
    	}
    	return _ids.length;
    }
}
