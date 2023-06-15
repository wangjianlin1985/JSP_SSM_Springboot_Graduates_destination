package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.GraduateStudent;

public interface GraduateStudentMapper {
	/*添加考研学生信息*/
	public void addGraduateStudent(GraduateStudent graduateStudent) throws Exception;

	/*按照查询条件分页查询考研学生记录*/
	public ArrayList<GraduateStudent> queryGraduateStudent(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有考研学生记录*/
	public ArrayList<GraduateStudent> queryGraduateStudentList(@Param("where") String where) throws Exception;

	/*按照查询条件的考研学生记录数*/
	public int queryGraduateStudentCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条考研学生记录*/
	public GraduateStudent getGraduateStudent(int id) throws Exception;

	/*更新考研学生记录*/
	public void updateGraduateStudent(GraduateStudent graduateStudent) throws Exception;

	/*删除考研学生记录*/
	public void deleteGraduateStudent(int id) throws Exception;

}
