package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.SpecialInfo;
import com.chengxusheji.po.GradeInfo;
import com.chengxusheji.po.ArmyStudent;

import com.chengxusheji.mapper.ArmyStudentMapper;
@Service
public class ArmyStudentService {

	@Resource ArmyStudentMapper armyStudentMapper;
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

    /*添加参军学生记录*/
    public void addArmyStudent(ArmyStudent armyStudent) throws Exception {
    	armyStudentMapper.addArmyStudent(armyStudent);
    }

    /*按照查询条件分页查询参军学生记录*/
    public ArrayList<ArmyStudent> queryArmyStudent(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth,String areaInfo,String junqu,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_armyStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_armyStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null  && !specialObj.getSpecialNo().equals(""))  where += " and t_armyStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_armyStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_armyStudent.yearMonth like '%" + yearMonth + "%'";
    	if(!areaInfo.equals("")) where = where + " and t_armyStudent.areaInfo like '%" + areaInfo + "%'";
    	if(!junqu.equals("")) where = where + " and t_armyStudent.junqu like '%" + junqu + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return armyStudentMapper.queryArmyStudent(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<ArmyStudent> queryArmyStudent(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth,String areaInfo,String junqu) throws Exception  { 
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_armyStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_armyStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null && !specialObj.getSpecialNo().equals(""))  where += " and t_armyStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_armyStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_armyStudent.yearMonth like '%" + yearMonth + "%'";
    	if(!areaInfo.equals("")) where = where + " and t_armyStudent.areaInfo like '%" + areaInfo + "%'";
    	if(!junqu.equals("")) where = where + " and t_armyStudent.junqu like '%" + junqu + "%'";
    	return armyStudentMapper.queryArmyStudentList(where);
    }

    /*查询所有参军学生记录*/
    public ArrayList<ArmyStudent> queryAllArmyStudent()  throws Exception {
        return armyStudentMapper.queryArmyStudentList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth,String areaInfo,String junqu) throws Exception {
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_armyStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_armyStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null && !specialObj.getSpecialNo().equals(""))  where += " and t_armyStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_armyStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_armyStudent.yearMonth like '%" + yearMonth + "%'";
    	if(!areaInfo.equals("")) where = where + " and t_armyStudent.areaInfo like '%" + areaInfo + "%'";
    	if(!junqu.equals("")) where = where + " and t_armyStudent.junqu like '%" + junqu + "%'";
        recordNumber = armyStudentMapper.queryArmyStudentCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取参军学生记录*/
    public ArmyStudent getArmyStudent(int id) throws Exception  {
        ArmyStudent armyStudent = armyStudentMapper.getArmyStudent(id);
        return armyStudent;
    }

    /*更新参军学生记录*/
    public void updateArmyStudent(ArmyStudent armyStudent) throws Exception {
        armyStudentMapper.updateArmyStudent(armyStudent);
    }

    /*删除一条参军学生记录*/
    public void deleteArmyStudent (int id) throws Exception {
        armyStudentMapper.deleteArmyStudent(id);
    }

    /*删除多条参军学生信息*/
    public int deleteArmyStudents (String ids) throws Exception {
    	String _ids[] = ids.split(",");
    	for(String _id: _ids) {
    		armyStudentMapper.deleteArmyStudent(Integer.parseInt(_id));
    	}
    	return _ids.length;
    }
}
