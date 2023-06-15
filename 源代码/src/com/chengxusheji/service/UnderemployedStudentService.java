package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.SpecialInfo;
import com.chengxusheji.po.GradeInfo;
import com.chengxusheji.po.UnderemployedStudent;

import com.chengxusheji.mapper.UnderemployedStudentMapper;
@Service
public class UnderemployedStudentService {

	@Resource UnderemployedStudentMapper underemployedStudentMapper;
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

    /*添加未就业学生记录*/
    public void addUnderemployedStudent(UnderemployedStudent underemployedStudent) throws Exception {
    	underemployedStudentMapper.addUnderemployedStudent(underemployedStudent);
    }

    /*按照查询条件分页查询未就业学生记录*/
    public ArrayList<UnderemployedStudent> queryUnderemployedStudent(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_underemployedStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_underemployedStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null  && !specialObj.getSpecialNo().equals(""))  where += " and t_underemployedStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_underemployedStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_underemployedStudent.yearMonth like '%" + yearMonth + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return underemployedStudentMapper.queryUnderemployedStudent(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<UnderemployedStudent> queryUnderemployedStudent(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth) throws Exception  { 
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_underemployedStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_underemployedStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null && !specialObj.getSpecialNo().equals(""))  where += " and t_underemployedStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_underemployedStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_underemployedStudent.yearMonth like '%" + yearMonth + "%'";
    	return underemployedStudentMapper.queryUnderemployedStudentList(where);
    }

    /*查询所有未就业学生记录*/
    public ArrayList<UnderemployedStudent> queryAllUnderemployedStudent()  throws Exception {
        return underemployedStudentMapper.queryUnderemployedStudentList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth) throws Exception {
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_underemployedStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_underemployedStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null && !specialObj.getSpecialNo().equals(""))  where += " and t_underemployedStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_underemployedStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_underemployedStudent.yearMonth like '%" + yearMonth + "%'";
        recordNumber = underemployedStudentMapper.queryUnderemployedStudentCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取未就业学生记录*/
    public UnderemployedStudent getUnderemployedStudent(int id) throws Exception  {
        UnderemployedStudent underemployedStudent = underemployedStudentMapper.getUnderemployedStudent(id);
        return underemployedStudent;
    }

    /*更新未就业学生记录*/
    public void updateUnderemployedStudent(UnderemployedStudent underemployedStudent) throws Exception {
        underemployedStudentMapper.updateUnderemployedStudent(underemployedStudent);
    }

    /*删除一条未就业学生记录*/
    public void deleteUnderemployedStudent (int id) throws Exception {
        underemployedStudentMapper.deleteUnderemployedStudent(id);
    }

    /*删除多条未就业学生信息*/
    public int deleteUnderemployedStudents (String ids) throws Exception {
    	String _ids[] = ids.split(",");
    	for(String _id: _ids) {
    		underemployedStudentMapper.deleteUnderemployedStudent(Integer.parseInt(_id));
    	}
    	return _ids.length;
    }
}
