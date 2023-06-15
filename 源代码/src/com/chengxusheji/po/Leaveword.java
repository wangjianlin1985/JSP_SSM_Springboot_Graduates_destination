package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class Leaveword {
    /*留言id*/
    private Integer learveId;
    public Integer getLearveId(){
        return learveId;
    }
    public void setLearveId(Integer learveId){
        this.learveId = learveId;
    }

    /*标题*/
    @NotEmpty(message="标题不能为空")
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    /*留言内容*/
    @NotEmpty(message="留言内容不能为空")
    private String content;
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    /*留言时间*/
    @NotEmpty(message="留言时间不能为空")
    private String leaveTime;
    public String getLeaveTime() {
        return leaveTime;
    }
    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    /*学生*/
    private Student studentObj;
    public Student getStudentObj() {
        return studentObj;
    }
    public void setStudentObj(Student studentObj) {
        this.studentObj = studentObj;
    }

    /*回复内容*/
    private String replyContent;
    public String getReplyContent() {
        return replyContent;
    }
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    /*回复时间*/
    private String replyTime;
    public String getReplyTime() {
        return replyTime;
    }
    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonLeaveword=new JSONObject(); 
		jsonLeaveword.accumulate("learveId", this.getLearveId());
		jsonLeaveword.accumulate("title", this.getTitle());
		jsonLeaveword.accumulate("content", this.getContent());
		jsonLeaveword.accumulate("leaveTime", this.getLeaveTime());
		jsonLeaveword.accumulate("studentObj", this.getStudentObj().getName());
		jsonLeaveword.accumulate("studentObjPri", this.getStudentObj().getStudentNumber());
		jsonLeaveword.accumulate("replyContent", this.getReplyContent());
		jsonLeaveword.accumulate("replyTime", this.getReplyTime());
		return jsonLeaveword;
    }}