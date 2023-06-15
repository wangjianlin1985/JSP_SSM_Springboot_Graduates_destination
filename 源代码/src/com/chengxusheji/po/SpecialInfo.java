package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class SpecialInfo {
    /*专业编号*/
    @NotEmpty(message="专业编号不能为空")
    private String specialNo;
    public String getSpecialNo(){
        return specialNo;
    }
    public void setSpecialNo(String specialNo){
        this.specialNo = specialNo;
    }

    /*专业名称*/
    @NotEmpty(message="专业名称不能为空")
    private String specialName;
    public String getSpecialName() {
        return specialName;
    }
    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }

    /*专业介绍*/
    @NotEmpty(message="专业介绍不能为空")
    private String specialDesc;
    public String getSpecialDesc() {
        return specialDesc;
    }
    public void setSpecialDesc(String specialDesc) {
        this.specialDesc = specialDesc;
    }

    /*开办日期*/
    @NotEmpty(message="开办日期不能为空")
    private String bornDate;
    public String getBornDate() {
        return bornDate;
    }
    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonSpecialInfo=new JSONObject(); 
		jsonSpecialInfo.accumulate("specialNo", this.getSpecialNo());
		jsonSpecialInfo.accumulate("specialName", this.getSpecialName());
		jsonSpecialInfo.accumulate("specialDesc", this.getSpecialDesc());
		jsonSpecialInfo.accumulate("bornDate", this.getBornDate().length()>19?this.getBornDate().substring(0,19):this.getBornDate());
		return jsonSpecialInfo;
    }}