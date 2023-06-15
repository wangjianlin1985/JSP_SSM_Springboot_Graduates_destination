package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.EntrepreneurStudent;

public interface EntrepreneurStudentMapper {
	/*添加创业学生信息*/
	public void addEntrepreneurStudent(EntrepreneurStudent entrepreneurStudent) throws Exception;

	/*按照查询条件分页查询创业学生记录*/
	public ArrayList<EntrepreneurStudent> queryEntrepreneurStudent(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有创业学生记录*/
	public ArrayList<EntrepreneurStudent> queryEntrepreneurStudentList(@Param("where") String where) throws Exception;

	/*按照查询条件的创业学生记录数*/
	public int queryEntrepreneurStudentCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条创业学生记录*/
	public EntrepreneurStudent getEntrepreneurStudent(int id) throws Exception;

	/*更新创业学生记录*/
	public void updateEntrepreneurStudent(EntrepreneurStudent entrepreneurStudent) throws Exception;

	/*删除创业学生记录*/
	public void deleteEntrepreneurStudent(int id) throws Exception;

}
