package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class ServantStudent {
    /*记录id*/
    private Integer id;
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    /*学号*/
    @NotEmpty(message="学号不能为空")
    private String studentNumber;
    public String getStudentNumber() {
        return studentNumber;
    }
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    /*姓名*/
    @NotEmpty(message="姓名不能为空")
    private String studentName;
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /*专业*/
    private SpecialInfo specialObj;
    public SpecialInfo getSpecialObj() {
        return specialObj;
    }
    public void setSpecialObj(SpecialInfo specialObj) {
        this.specialObj = specialObj;
    }

    /*年级*/
    private GradeInfo gradeObj;
    public GradeInfo getGradeObj() {
        return gradeObj;
    }
    public void setGradeObj(GradeInfo gradeObj) {
        this.gradeObj = gradeObj;
    }

    /*年月份*/
    @NotEmpty(message="年月份不能为空")
    private String yearMonth;
    public String getYearMonth() {
        return yearMonth;
    }
    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    /*地区*/
    @NotEmpty(message="地区不能为空")
    private String areaInfo;
    public String getAreaInfo() {
        return areaInfo;
    }
    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    /*就业单位*/
    @NotEmpty(message="就业单位不能为空")
    private String danwei;
    public String getDanwei() {
        return danwei;
    }
    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    /*职位*/
    @NotEmpty(message="职位不能为空")
    private String position;
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    /*收入*/
    @NotEmpty(message="收入不能为空")
    private String shouru;
    public String getShouru() {
        return shouru;
    }
    public void setShouru(String shouru) {
        this.shouru = shouru;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonServantStudent=new JSONObject(); 
		jsonServantStudent.accumulate("id", this.getId());
		jsonServantStudent.accumulate("studentNumber", this.getStudentNumber());
		jsonServantStudent.accumulate("studentName", this.getStudentName());
		jsonServantStudent.accumulate("specialObj", this.getSpecialObj().getSpecialName());
		jsonServantStudent.accumulate("specialObjPri", this.getSpecialObj().getSpecialNo());
		jsonServantStudent.accumulate("gradeObj", this.getGradeObj().getGradeName());
		jsonServantStudent.accumulate("gradeObjPri", this.getGradeObj().getGradeId());
		jsonServantStudent.accumulate("yearMonth", this.getYearMonth());
		jsonServantStudent.accumulate("areaInfo", this.getAreaInfo());
		jsonServantStudent.accumulate("danwei", this.getDanwei());
		jsonServantStudent.accumulate("position", this.getPosition());
		jsonServantStudent.accumulate("shouru", this.getShouru());
		return jsonServantStudent;
    }}