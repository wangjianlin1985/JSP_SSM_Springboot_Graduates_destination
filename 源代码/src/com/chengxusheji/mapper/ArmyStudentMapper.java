package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.ArmyStudent;

public interface ArmyStudentMapper {
	/*添加参军学生信息*/
	public void addArmyStudent(ArmyStudent armyStudent) throws Exception;

	/*按照查询条件分页查询参军学生记录*/
	public ArrayList<ArmyStudent> queryArmyStudent(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有参军学生记录*/
	public ArrayList<ArmyStudent> queryArmyStudentList(@Param("where") String where) throws Exception;

	/*按照查询条件的参军学生记录数*/
	public int queryArmyStudentCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条参军学生记录*/
	public ArmyStudent getArmyStudent(int id) throws Exception;

	/*更新参军学生记录*/
	public void updateArmyStudent(ArmyStudent armyStudent) throws Exception;

	/*删除参军学生记录*/
	public void deleteArmyStudent(int id) throws Exception;

}
