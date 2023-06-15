package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.UnderemployedStudent;

public interface UnderemployedStudentMapper {
	/*添加未就业学生信息*/
	public void addUnderemployedStudent(UnderemployedStudent underemployedStudent) throws Exception;

	/*按照查询条件分页查询未就业学生记录*/
	public ArrayList<UnderemployedStudent> queryUnderemployedStudent(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有未就业学生记录*/
	public ArrayList<UnderemployedStudent> queryUnderemployedStudentList(@Param("where") String where) throws Exception;

	/*按照查询条件的未就业学生记录数*/
	public int queryUnderemployedStudentCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条未就业学生记录*/
	public UnderemployedStudent getUnderemployedStudent(int id) throws Exception;

	/*更新未就业学生记录*/
	public void updateUnderemployedStudent(UnderemployedStudent underemployedStudent) throws Exception;

	/*删除未就业学生记录*/
	public void deleteUnderemployedStudent(int id) throws Exception;

}
