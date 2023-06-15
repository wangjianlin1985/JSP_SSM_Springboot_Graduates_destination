package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.SpecialInfo;
import com.chengxusheji.po.GradeInfo;
import com.chengxusheji.po.ServantStudent;

import com.chengxusheji.mapper.ServantStudentMapper;
@Service
public class ServantStudentService {

	@Resource ServantStudentMapper servantStudentMapper;
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

    /*添加公务员学生记录*/
    public void addServantStudent(ServantStudent servantStudent) throws Exception {
    	servantStudentMapper.addServantStudent(servantStudent);
    }

    /*按照查询条件分页查询公务员学生记录*/
    public ArrayList<ServantStudent> queryServantStudent(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth,String areaInfo,String danwei,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_servantStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_servantStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null  && !specialObj.getSpecialNo().equals(""))  where += " and t_servantStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_servantStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_servantStudent.yearMonth like '%" + yearMonth + "%'";
    	if(!areaInfo.equals("")) where = where + " and t_servantStudent.areaInfo like '%" + areaInfo + "%'";
    	if(!danwei.equals("")) where = where + " and t_servantStudent.danwei like '%" + danwei + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return servantStudentMapper.queryServantStudent(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<ServantStudent> queryServantStudent(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth,String areaInfo,String danwei) throws Exception  { 
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_servantStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_servantStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null && !specialObj.getSpecialNo().equals(""))  where += " and t_servantStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_servantStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_servantStudent.yearMonth like '%" + yearMonth + "%'";
    	if(!areaInfo.equals("")) where = where + " and t_servantStudent.areaInfo like '%" + areaInfo + "%'";
    	if(!danwei.equals("")) where = where + " and t_servantStudent.danwei like '%" + danwei + "%'";
    	return servantStudentMapper.queryServantStudentList(where);
    }

    /*查询所有公务员学生记录*/
    public ArrayList<ServantStudent> queryAllServantStudent()  throws Exception {
        return servantStudentMapper.queryServantStudentList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(String studentNumber,String studentName,SpecialInfo specialObj,GradeInfo gradeObj,String yearMonth,String areaInfo,String danwei) throws Exception {
     	String where = "where 1=1";
    	if(!studentNumber.equals("")) where = where + " and t_servantStudent.studentNumber like '%" + studentNumber + "%'";
    	if(!studentName.equals("")) where = where + " and t_servantStudent.studentName like '%" + studentName + "%'";
    	if(null != specialObj &&  specialObj.getSpecialNo() != null && !specialObj.getSpecialNo().equals(""))  where += " and t_servantStudent.specialObj='" + specialObj.getSpecialNo() + "'";
    	if(null != gradeObj && gradeObj.getGradeId()!= null && gradeObj.getGradeId()!= 0)  where += " and t_servantStudent.gradeObj=" + gradeObj.getGradeId();
    	if(!yearMonth.equals("")) where = where + " and t_servantStudent.yearMonth like '%" + yearMonth + "%'";
    	if(!areaInfo.equals("")) where = where + " and t_servantStudent.areaInfo like '%" + areaInfo + "%'";
    	if(!danwei.equals("")) where = where + " and t_servantStudent.danwei like '%" + danwei + "%'";
        recordNumber = servantStudentMapper.queryServantStudentCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取公务员学生记录*/
    public ServantStudent getServantStudent(int id) throws Exception  {
        ServantStudent servantStudent = servantStudentMapper.getServantStudent(id);
        return servantStudent;
    }

    /*更新公务员学生记录*/
    public void updateServantStudent(ServantStudent servantStudent) throws Exception {
        servantStudentMapper.updateServantStudent(servantStudent);
    }

    /*删除一条公务员学生记录*/
    public void deleteServantStudent (int id) throws Exception {
        servantStudentMapper.deleteServantStudent(id);
    }

    /*删除多条公务员学生信息*/
    public int deleteServantStudents (String ids) throws Exception {
    	String _ids[] = ids.split(",");
    	for(String _id: _ids) {
    		servantStudentMapper.deleteServantStudent(Integer.parseInt(_id));
    	}
    	return _ids.length;
    }
}
