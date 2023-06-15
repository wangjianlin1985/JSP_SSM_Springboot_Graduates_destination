$(function () {
	$("#gradeInfo_gradeName").validatebox({
		required : true, 
		missingMessage : '请输入年级名称',
	});

	//单击添加按钮
	$("#gradeInfoAddButton").click(function () {
		//验证表单 
		if(!$("#gradeInfoAddForm").form("validate")) {
			$.messager.alert("错误提示","你输入的信息还有错误！","warning");
		} else {
			$("#gradeInfoAddForm").form({
			    url:"GradeInfo/add",
			    onSubmit: function(){
					if($("#gradeInfoAddForm").form("validate"))  { 
	                	$.messager.progress({
							text : "正在提交数据中...",
						}); 
	                	return true;
	                } else {
	                    return false;
	                }
			    },
			    success:function(data){
			    	$.messager.progress("close");
                    //此处data={"Success":true}是字符串
                	var obj = jQuery.parseJSON(data); 
                    if(obj.success){ 
                        $.messager.alert("消息","保存成功！");
                        $("#gradeInfoAddForm").form("clear");
                    }else{
                        $.messager.alert("消息",obj.message);
                    }
			    }
			});
			//提交表单
			$("#gradeInfoAddForm").submit();
		}
	});

	//单击清空按钮
	$("#gradeInfoClearButton").click(function () { 
		$("#gradeInfoAddForm").form("clear"); 
	});
});
