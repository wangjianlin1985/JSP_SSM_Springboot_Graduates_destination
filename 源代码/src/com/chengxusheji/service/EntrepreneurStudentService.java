package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.SpecialInfo;
import com.chengxusheji.po.GradeInfo;
import com.chengxusheji.po.EntrepreneurStudent;

import com.chengxusheji.mapper.EntrepreneurStudentMapper;
@Service
public class EntrepreneurStudentService {

	@Resource EntrepreneurStudentMapper entrepreneurStudentMapper;
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

    /*添加创业学生记录*/
    public void addEntrepreneurStudent(EntrepreneurStudent entrepreneurStudent) throws Exception {
    	entrepreneurStudentMapper.addEntrepreneurStudent(entrepreneurStudent);
    }

    /*按照查询条件分页查询创业学生记录*/
    public ArrayList<EntrepreneurStudent> queryEntrepreneurStudent(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth,String areaInfo,String entreName,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_entrepreneurStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_entrepreneurStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null  && !specialObj.getSpecialNo().equals(""))  where += " and t_entrepreneurStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_entrepreneurStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_entrepreneurStudent.yearMonth like '%" + yearMonth + "%'";
    	if(!areaInfo.equals("")) where = where + " and t_entrepreneurStudent.areaInfo like '%" + areaInfo + "%'";
    	if(!entreName.equals("")) where = where + " and t_entrepreneurStudent.entreName like '%" + entreName + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return entrepreneurStudentMapper.queryEntrepreneurStudent(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<EntrepreneurStudent> queryEntrepreneurStudent(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth,String areaInfo,String entreName) throws Exception  { 
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_entrepreneurStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_entrepreneurStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null && !specialObj.getSpecialNo().equals(""))  where += " and t_entrepreneurStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_entrepreneurStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_entrepreneurStudent.yearMonth like '%" + yearMonth + "%'";
    	if(!areaInfo.equals("")) where = where + " and t_entrepreneurStudent.areaInfo like '%" + areaInfo + "%'";
    	if(!entreName.equals("")) where = where + " and t_entrepreneurStudent.entreName like '%" + entreName + "%'";
    	return entrepreneurStudentMapper.queryEntrepreneurStudentList(where);
    }

    /*查询所有创业学生记录*/
    public ArrayList<EntrepreneurStudent> queryAllEntrepreneurStudent()  throws Exception {
        return entrepreneurStudentMapper.queryEntrepreneurStudentList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth,String areaInfo,String entreName) throws Exception {
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_entrepreneurStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_entrepreneurStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null && !specialObj.getSpecialNo().equals(""))  where += " and t_entrepreneurStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_entrepreneurStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_entrepreneurStudent.yearMonth like '%" + yearMonth + "%'";
    	if(!areaInfo.equals("")) where = where + " and t_entrepreneurStudent.areaInfo like '%" + areaInfo + "%'";
    	if(!entreName.equals("")) where = where + " and t_entrepreneurStudent.entreName like '%" + entreName + "%'";
        recordNumber = entrepreneurStudentMapper.queryEntrepreneurStudentCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取创业学生记录*/
    public EntrepreneurStudent getEntrepreneurStudent(int id) throws Exception  {
        EntrepreneurStudent entrepreneurStudent = entrepreneurStudentMapper.getEntrepreneurStudent(id);
        return entrepreneurStudent;
    }

    /*更新创业学生记录*/
    public void updateEntrepreneurStudent(EntrepreneurStudent entrepreneurStudent) throws Exception {
        entrepreneurStudentMapper.updateEntrepreneurStudent(entrepreneurStudent);
    }

    /*删除一条创业学生记录*/
    public void deleteEntrepreneurStudent (int id) throws Exception {
        entrepreneurStudentMapper.deleteEntrepreneurStudent(id);
    }

    /*删除多条创业学生信息*/
    public int deleteEntrepreneurStudents (String ids) throws Exception {
    	String _ids[] = ids.split(",");
    	for(String _id: _ids) {
    		entrepreneurStudentMapper.deleteEntrepreneurStudent(Integer.parseInt(_id));
    	}
    	return _ids.length;
    }
}
