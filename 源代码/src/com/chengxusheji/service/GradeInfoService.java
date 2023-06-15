package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.GradeInfo;

import com.chengxusheji.mapper.GradeInfoMapper;
@Service
public class GradeInfoService {

	@Resource GradeInfoMapper gradeInfoMapper;
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

    /*添加年级记录*/
    public void addGradeInfo(GradeInfo gradeInfo) throws Exception {
    	gradeInfoMapper.addGradeInfo(gradeInfo);
    }

    /*按照查询条件分页查询年级记录*/
    public ArrayList<GradeInfo> queryGradeInfo(int currentPage) throws Exception { 
     	String where = "where 1=1";
    	int startIndex = (currentPage-1) * this.rows;
    	return gradeInfoMapper.queryGradeInfo(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<GradeInfo> queryGradeInfo() throws Exception  { 
     	String where = "where 1=1";
    	return gradeInfoMapper.queryGradeInfoList(where);
    }

    /*查询所有年级记录*/
    public ArrayList<GradeInfo> queryAllGradeInfo()  throws Exception {
        return gradeInfoMapper.queryGradeInfoList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber() throws Exception {
     	String where = "where 1=1";
        recordNumber = gradeInfoMapper.queryGradeInfoCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取年级记录*/
    public GradeInfo getGradeInfo(int gradeId) throws Exception  {
        GradeInfo gradeInfo = gradeInfoMapper.getGradeInfo(gradeId);
        return gradeInfo;
    }

    /*更新年级记录*/
    public void updateGradeInfo(GradeInfo gradeInfo) throws Exception {
        gradeInfoMapper.updateGradeInfo(gradeInfo);
    }

    /*删除一条年级记录*/
    public void deleteGradeInfo (int gradeId) throws Exception {
        gradeInfoMapper.deleteGradeInfo(gradeId);
    }

    /*删除多条年级信息*/
    public int deleteGradeInfos (String gradeIds) throws Exception {
    	String _gradeIds[] = gradeIds.split(",");
    	for(String _gradeId: _gradeIds) {
    		gradeInfoMapper.deleteGradeInfo(Integer.parseInt(_gradeId));
    	}
    	return _gradeIds.length;
    }
}
