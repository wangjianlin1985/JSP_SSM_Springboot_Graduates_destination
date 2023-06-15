package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.ServantStudent;

public interface ServantStudentMapper {
	/*添加公务员学生信息*/
	public void addServantStudent(ServantStudent servantStudent) throws Exception;

	/*按照查询条件分页查询公务员学生记录*/
	public ArrayList<ServantStudent> queryServantStudent(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有公务员学生记录*/
	public ArrayList<ServantStudent> queryServantStudentList(@Param("where") String where) throws Exception;

	/*按照查询条件的公务员学生记录数*/
	public int queryServantStudentCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条公务员学生记录*/
	public ServantStudent getServantStudent(int id) throws Exception;

	/*更新公务员学生记录*/
	public void updateServantStudent(ServantStudent servantStudent) throws Exception;

	/*删除公务员学生记录*/
	public void deleteServantStudent(int id) throws Exception;

}
