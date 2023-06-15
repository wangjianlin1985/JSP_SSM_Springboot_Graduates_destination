package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class GetJobStudent {
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
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    private String companyName;
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /*行业*/
    @NotEmpty(message="行业不能为空")
    private String hangye;
    public String getHangye() {
        return hangye;
    }
    public void setHangye(String hangye) {
        this.hangye = hangye;
    }

    /*职位名称*/
    @NotEmpty(message="职位名称不能为空")
    private String positionName;
    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    /*单位性质*/
    @NotEmpty(message="单位性质不能为空")
    private String companyType;
    public String getCompanyType() {
        return companyType;
    }
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    /*单位联系人电话*/
    @NotEmpty(message="单位联系人电话不能为空")
    private String conectInfo;
    public String getConectInfo() {
        return conectInfo;
    }
    public void setConectInfo(String conectInfo) {
        this.conectInfo = conectInfo;
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
    	JSONObject jsonGetJobStudent=new JSONObject(); 
		jsonGetJobStudent.accumulate("id", this.getId());
		jsonGetJobStudent.accumulate("studentNumber", this.getStudentNumber());
		jsonGetJobStudent.accumulate("name", this.getName());
		jsonGetJobStudent.accumulate("specialObj", this.getSpecialObj().getSpecialName());
		jsonGetJobStudent.accumulate("specialObjPri", this.getSpecialObj().getSpecialNo());
		jsonGetJobStudent.accumulate("gradeObj", this.getGradeObj().getGradeName());
		jsonGetJobStudent.accumulate("gradeObjPri", this.getGradeObj().getGradeId());
		jsonGetJobStudent.accumulate("yearMonth", this.getYearMonth());
		jsonGetJobStudent.accumulate("areaInfo", this.getAreaInfo());
		jsonGetJobStudent.accumulate("companyName", this.getCompanyName());
		jsonGetJobStudent.accumulate("hangye", this.getHangye());
		jsonGetJobStudent.accumulate("positionName", this.getPositionName());
		jsonGetJobStudent.accumulate("companyType", this.getCompanyType());
		jsonGetJobStudent.accumulate("conectInfo", this.getConectInfo());
		jsonGetJobStudent.accumulate("shouru", this.getShouru());
		return jsonGetJobStudent;
    }}