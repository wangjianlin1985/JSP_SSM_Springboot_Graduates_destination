package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.GetJobStudent;

public interface GetJobStudentMapper {
	/*添加就业学生信息*/
	public void addGetJobStudent(GetJobStudent getJobStudent) throws Exception;

	/*按照查询条件分页查询就业学生记录*/
	public ArrayList<GetJobStudent> queryGetJobStudent(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有就业学生记录*/
	public ArrayList<GetJobStudent> queryGetJobStudentList(@Param("where") String where) throws Exception;

	/*按照查询条件的就业学生记录数*/
	public int queryGetJobStudentCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条就业学生记录*/
	public GetJobStudent getGetJobStudent(int id) throws Exception;

	/*更新就业学生记录*/
	public void updateGetJobStudent(GetJobStudent getJobStudent) throws Exception;

	/*删除就业学生记录*/
	public void deleteGetJobStudent(int id) throws Exception;

}
