package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class GraduateStudent {
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

    /*省份*/
    @NotEmpty(message="省份不能为空")
    private String province;
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    /*考研学校*/
    @NotEmpty(message="考研学校不能为空")
    private String schoolName;
    public String getSchoolName() {
        return schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /*考研专业*/
    @NotEmpty(message="考研专业不能为空")
    private String specialName;
    public String getSpecialName() {
        return specialName;
    }
    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonGraduateStudent=new JSONObject(); 
		jsonGraduateStudent.accumulate("id", this.getId());
		jsonGraduateStudent.accumulate("studentNumber", this.getStudentNumber());
		jsonGraduateStudent.accumulate("studentName", this.getStudentName());
		jsonGraduateStudent.accumulate("specialObj", this.getSpecialObj().getSpecialName());
		jsonGraduateStudent.accumulate("specialObjPri", this.getSpecialObj().getSpecialNo());
		jsonGraduateStudent.accumulate("gradeObj", this.getGradeObj().getGradeName());
		jsonGraduateStudent.accumulate("gradeObjPri", this.getGradeObj().getGradeId());
		jsonGraduateStudent.accumulate("yearMonth", this.getYearMonth());
		jsonGraduateStudent.accumulate("province", this.getProvince());
		jsonGraduateStudent.accumulate("schoolName", this.getSchoolName());
		jsonGraduateStudent.accumulate("specialName", this.getSpecialName());
		return jsonGraduateStudent;
    }}