package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class EntrepreneurStudent {
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

    /*创业名称*/
    @NotEmpty(message="创业名称不能为空")
    private String entreName;
    public String getEntreName() {
        return entreName;
    }
    public void setEntreName(String entreName) {
        this.entreName = entreName;
    }

    /*公司性质*/
    @NotEmpty(message="公司性质不能为空")
    private String companyType;
    public String getCompanyType() {
        return companyType;
    }
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    /*公司人数*/
    @NotEmpty(message="公司人数不能为空")
    private String personNumber;
    public String getPersonNumber() {
        return personNumber;
    }
    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonEntrepreneurStudent=new JSONObject(); 
		jsonEntrepreneurStudent.accumulate("id", this.getId());
		jsonEntrepreneurStudent.accumulate("studentNumber", this.getStudentNumber());
		jsonEntrepreneurStudent.accumulate("studentName", this.getStudentName());
		jsonEntrepreneurStudent.accumulate("specialObj", this.getSpecialObj().getSpecialName());
		jsonEntrepreneurStudent.accumulate("specialObjPri", this.getSpecialObj().getSpecialNo());
		jsonEntrepreneurStudent.accumulate("gradeObj", this.getGradeObj().getGradeName());
		jsonEntrepreneurStudent.accumulate("gradeObjPri", this.getGradeObj().getGradeId());
		jsonEntrepreneurStudent.accumulate("yearMonth", this.getYearMonth());
		jsonEntrepreneurStudent.accumulate("areaInfo", this.getAreaInfo());
		jsonEntrepreneurStudent.accumulate("entreName", this.getEntreName());
		jsonEntrepreneurStudent.accumulate("companyType", this.getCompanyType());
		jsonEntrepreneurStudent.accumulate("personNumber", this.getPersonNumber());
		return jsonEntrepreneurStudent;
    }}