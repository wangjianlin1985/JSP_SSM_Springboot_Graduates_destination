package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class UnderemployedStudent {
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

    /*原因*/
    @NotEmpty(message="原因不能为空")
    private String reason;
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonUnderemployedStudent=new JSONObject(); 
		jsonUnderemployedStudent.accumulate("id", this.getId());
		jsonUnderemployedStudent.accumulate("studentNumber", this.getStudentNumber());
		jsonUnderemployedStudent.accumulate("studentName", this.getStudentName());
		jsonUnderemployedStudent.accumulate("specialObj", this.getSpecialObj().getSpecialName());
		jsonUnderemployedStudent.accumulate("specialObjPri", this.getSpecialObj().getSpecialNo());
		jsonUnderemployedStudent.accumulate("gradeObj", this.getGradeObj().getGradeName());
		jsonUnderemployedStudent.accumulate("gradeObjPri", this.getGradeObj().getGradeId());
		jsonUnderemployedStudent.accumulate("yearMonth", this.getYearMonth());
		jsonUnderemployedStudent.accumulate("reason", this.getReason());
		return jsonUnderemployedStudent;
    }}