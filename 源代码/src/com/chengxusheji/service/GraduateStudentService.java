package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.SpecialInfo;
import com.chengxusheji.po.GradeInfo;
import com.chengxusheji.po.GraduateStudent;

import com.chengxusheji.mapper.GraduateStudentMapper;
@Service
public class GraduateStudentService {

	@Resource GraduateStudentMapper graduateStudentMapper;
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

    /*添加考研学生记录*/
    public void addGraduateStudent(GraduateStudent graduateStudent) throws Exception {
    	graduateStudentMapper.addGraduateStudent(graduateStudent);
    }

    /*按照查询条件分页查询考研学生记录*/
    public ArrayList<GraduateStudent> queryGraduateStudent(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth,String province,String schoolName,String specialName,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_graduateStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_graduateStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null  && !specialObj.getSpecialNo().equals(""))  where += " and t_graduateStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_graduateStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_graduateStudent.yearMonth like '%" + yearMonth + "%'";
    	if(!province.equals("")) where = where + " and t_graduateStudent.province like '%" + province + "%'";
    	if(!schoolName.equals("")) where = where + " and t_graduateStudent.schoolName like '%" + schoolName + "%'";
    	if(!specialName.equals("")) where = where + " and t_graduateStudent.specialName like '%" + specialName + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return graduateStudentMapper.queryGraduateStudent(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<GraduateStudent> queryGraduateStudent(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth,String province,String schoolName,String specialName) throws Exception  { 
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_graduateStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_graduateStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null && !specialObj.getSpecialNo().equals(""))  where += " and t_graduateStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_graduateStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_graduateStudent.yearMonth like '%" + yearMonth + "%'";
    	if(!province.equals("")) where = where + " and t_graduateStudent.province like '%" + province + "%'";
    	if(!schoolName.equals("")) where = where + " and t_graduateStudent.schoolName like '%" + schoolName + "%'";
    	if(!specialName.equals("")) where = where + " and t_graduateStudent.specialName like '%" + specialName + "%'";
    	return graduateStudentMapper.queryGraduateStudentList(where);
    }

    /*查询所有考研学生记录*/
    public ArrayList<GraduateStudent> queryAllGraduateStudent()  throws Exception {
        return graduateStudentMapper.queryGraduateStudentList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth,String province,String schoolName,String specialName) throws Exception {
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_graduateStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_graduateStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null && !specialObj.getSpecialNo().equals(""))  where += " and t_graduateStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_graduateStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_graduateStudent.yearMonth like '%" + yearMonth + "%'";
    	if(!province.equals("")) where = where + " and t_graduateStudent.province like '%" + province + "%'";
    	if(!schoolName.equals("")) where = where + " and t_graduateStudent.schoolName like '%" + schoolName + "%'";
    	if(!specialName.equals("")) where = where + " and t_graduateStudent.specialName like '%" + specialName + "%'";
        recordNumber = graduateStudentMapper.queryGraduateStudentCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取考研学生记录*/
    public GraduateStudent getGraduateStudent(int id) throws Exception  {
        GraduateStudent graduateStudent = graduateStudentMapper.getGraduateStudent(id);
        return graduateStudent;
    }

    /*更新考研学生记录*/
    public void updateGraduateStudent(GraduateStudent graduateStudent) throws Exception {
        graduateStudentMapper.updateGraduateStudent(graduateStudent);
    }

    /*删除一条考研学生记录*/
    public void deleteGraduateStudent (int id) throws Exception {
        graduateStudentMapper.deleteGraduateStudent(id);
    }

    /*删除多条考研学生信息*/
    public int deleteGraduateStudents (String ids) throws Exception {
    	String _ids[] = ids.split(",");
    	for(String _id: _ids) {
    		graduateStudentMapper.deleteGraduateStudent(Integer.parseInt(_id));
    	}
    	return _ids.length;
    }
}
