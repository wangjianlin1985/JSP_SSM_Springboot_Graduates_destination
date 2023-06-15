package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.SpecialInfo;

public interface SpecialInfoMapper {
	/*添加专业信息*/
	public void addSpecialInfo(SpecialInfo specialInfo) throws Exception;

	/*按照查询条件分页查询专业记录*/
	public ArrayList<SpecialInfo> querySpecialInfo(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有专业记录*/
	public ArrayList<SpecialInfo> querySpecialInfoList(@Param("where") String where) throws Exception;

	/*按照查询条件的专业记录数*/
	public int querySpecialInfoCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条专业记录*/
	public SpecialInfo getSpecialInfo(String specialNo) throws Exception;

	/*更新专业记录*/
	public void updateSpecialInfo(SpecialInfo specialInfo) throws Exception;

	/*删除专业记录*/
	public void deleteSpecialInfo(String specialNo) throws Exception;

}
