package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.OtherStudent;

public interface OtherStudentMapper {
	/*添加其他学生信息*/
	public void addOtherStudent(OtherStudent otherStudent) throws Exception;

	/*按照查询条件分页查询其他学生记录*/
	public ArrayList<OtherStudent> queryOtherStudent(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有其他学生记录*/
	public ArrayList<OtherStudent> queryOtherStudentList(@Param("where") String where) throws Exception;

	/*按照查询条件的其他学生记录数*/
	public int queryOtherStudentCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条其他学生记录*/
	public OtherStudent getOtherStudent(int id) throws Exception;

	/*更新其他学生记录*/
	public void updateOtherStudent(OtherStudent otherStudent) throws Exception;

	/*删除其他学生记录*/
	public void deleteOtherStudent(int id) throws Exception;

}
