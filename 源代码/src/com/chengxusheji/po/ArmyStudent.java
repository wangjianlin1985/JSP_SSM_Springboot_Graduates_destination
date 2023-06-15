package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class ArmyStudent {
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

    /*参军军区*/
    @NotEmpty(message="参军军区不能为空")
    private String junqu;
    public String getJunqu() {
        return junqu;
    }
    public void setJunqu(String junqu) {
        this.junqu = junqu;
    }

    /*职位*/
    @NotEmpty(message="职位不能为空")
    private String positionName;
    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
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
    	JSONObject jsonArmyStudent=new JSONObject(); 
		jsonArmyStudent.accumulate("id", this.getId());
		jsonArmyStudent.accumulate("studentNumber", this.getStudentNumber());
		jsonArmyStudent.accumulate("studentName", this.getStudentName());
		jsonArmyStudent.accumulate("specialObj", this.getSpecialObj().getSpecialName());
		jsonArmyStudent.accumulate("specialObjPri", this.getSpecialObj().getSpecialNo());
		jsonArmyStudent.accumulate("gradeObj", this.getGradeObj().getGradeName());
		jsonArmyStudent.accumulate("gradeObjPri", this.getGradeObj().getGradeId());
		jsonArmyStudent.accumulate("yearMonth", this.getYearMonth());
		jsonArmyStudent.accumulate("areaInfo", this.getAreaInfo());
		jsonArmyStudent.accumulate("junqu", this.getJunqu());
		jsonArmyStudent.accumulate("positionName", this.getPositionName());
		jsonArmyStudent.accumulate("shouru", this.getShouru());
		return jsonArmyStudent;
    }}