package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.GradeInfo;

public interface GradeInfoMapper {
	/*添加年级信息*/
	public void addGradeInfo(GradeInfo gradeInfo) throws Exception;

	/*按照查询条件分页查询年级记录*/
	public ArrayList<GradeInfo> queryGradeInfo(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有年级记录*/
	public ArrayList<GradeInfo> queryGradeInfoList(@Param("where") String where) throws Exception;

	/*按照查询条件的年级记录数*/
	public int queryGradeInfoCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条年级记录*/
	public GradeInfo getGradeInfo(int gradeId) throws Exception;

	/*更新年级记录*/
	public void updateGradeInfo(GradeInfo gradeInfo) throws Exception;

	/*删除年级记录*/
	public void deleteGradeInfo(int gradeId) throws Exception;

}
