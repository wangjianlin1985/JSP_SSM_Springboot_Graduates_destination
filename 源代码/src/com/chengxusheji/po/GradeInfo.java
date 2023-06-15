package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class GradeInfo {
    /*记录id*/
    private Integer gradeId;
    public Integer getGradeId(){
        return gradeId;
    }
    public void setGradeId(Integer gradeId){
        this.gradeId = gradeId;
    }

    /*年级名称*/
    @NotEmpty(message="年级名称不能为空")
    private String gradeName;
    public String getGradeName() {
        return gradeName;
    }
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonGradeInfo=new JSONObject(); 
		jsonGradeInfo.accumulate("gradeId", this.getGradeId());
		jsonGradeInfo.accumulate("gradeName", this.getGradeName());
		return jsonGradeInfo;
    }}