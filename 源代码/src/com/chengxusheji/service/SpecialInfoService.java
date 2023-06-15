package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.SpecialInfo;

import com.chengxusheji.mapper.SpecialInfoMapper;
@Service
public class SpecialInfoService {

	@Resource SpecialInfoMapper specialInfoMapper;
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

    /*添加专业记录*/
    public void addSpecialInfo(SpecialInfo specialInfo) throws Exception {
    	specialInfoMapper.addSpecialInfo(specialInfo);
    }

    /*按照查询条件分页查询专业记录*/
    public ArrayList<SpecialInfo> querySpecialInfo(String specialNo,String specialName,String bornDate,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(!specialNo.equals("")) where = where + " and t_specialInfo.specialNo like '%" + specialNo + "%'";
    	if(!specialName.equals("")) where = where + " and t_specialInfo.specialName like '%" + specialName + "%'";
    	if(!bornDate.equals("")) where = where + " and t_specialInfo.bornDate like '%" + bornDate + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return specialInfoMapper.querySpecialInfo(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<SpecialInfo> querySpecialInfo(String specialNo,String specialName,String bornDate) throws Exception  { 
     	String where = "where 1=1";
    	if(!specialNo.equals("")) where = where + " and t_specialInfo.specialNo like '%" + specialNo + "%'";
    	if(!specialName.equals("")) where = where + " and t_specialInfo.specialName like '%" + specialName + "%'";
    	if(!bornDate.equals("")) where = where + " and t_specialInfo.bornDate like '%" + bornDate + "%'";
    	return specialInfoMapper.querySpecialInfoList(where);
    }

    /*查询所有专业记录*/
    public ArrayList<SpecialInfo> queryAllSpecialInfo()  throws Exception {
        return specialInfoMapper.querySpecialInfoList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(String specialNo,String specialName,String bornDate) throws Exception {
     	String where = "where 1=1";
    	if(!specialNo.equals("")) where = where + " and t_specialInfo.specialNo like '%" + specialNo + "%'";
    	if(!specialName.equals("")) where = where + " and t_specialInfo.specialName like '%" + specialName + "%'";
    	if(!bornDate.equals("")) where = where + " and t_specialInfo.bornDate like '%" + bornDate + "%'";
        recordNumber = specialInfoMapper.querySpecialInfoCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取专业记录*/
    public SpecialInfo getSpecialInfo(String specialNo) throws Exception  {
        SpecialInfo specialInfo = specialInfoMapper.getSpecialInfo(specialNo);
        return specialInfo;
    }

    /*更新专业记录*/
    public void updateSpecialInfo(SpecialInfo specialInfo) throws Exception {
        specialInfoMapper.updateSpecialInfo(specialInfo);
    }

    /*删除一条专业记录*/
    public void deleteSpecialInfo (String specialNo) throws Exception {
        specialInfoMapper.deleteSpecialInfo(specialNo);
    }

    /*删除多条专业信息*/
    public int deleteSpecialInfos (String specialNos) throws Exception {
    	String _specialNos[] = specialNos.split(",");
    	for(String _specialNo: _specialNos) {
    		specialInfoMapper.deleteSpecialInfo(_specialNo);
    	}
    	return _specialNos.length;
    }
}
