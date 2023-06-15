$(function () {
	$.ajax({
		url : "GradeInfo/" + $("#gradeInfo_gradeId_edit").val() + "/update",
		type : "get",
		data : {
			//gradeId : $("#gradeInfo_gradeId_edit").val(),
		},
		beforeSend : function () {
			$.messager.progress({
				text : "正在获取中...",
			});
		},
		success : function (gradeInfo, response, status) {
			$.messager.progress("close");
			if (gradeInfo) { 
				$("#gradeInfo_gradeId_edit").val(gradeInfo.gradeId);
				$("#gradeInfo_gradeId_edit").validatebox({
					required : true,
					missingMessage : "请输入记录id",
					editable: false
				});
				$("#gradeInfo_gradeName_edit").val(gradeInfo.gradeName);
				$("#gradeInfo_gradeName_edit").validatebox({
					required : true,
					missingMessage : "请输入年级名称",
				});
			} else {
				$.messager.alert("获取失败！", "未知错误导致失败，请重试！", "warning");
			}
		}
	});

	$("#gradeInfoModifyButton").click(function(){ 
		if ($("#gradeInfoEditForm").form("validate")) {
			$("#gradeInfoEditForm").form({
			    url:"GradeInfo/" +  $("#gradeInfo_gradeId_edit").val() + "/update",
			    onSubmit: function(){
					if($("#gradeInfoEditForm").form("validate"))  {
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
                	var obj = jQuery.parseJSON(data);
                    if(obj.success){
                        $.messager.alert("消息","信息修改成功！");
                        location.href="frontlist";
                    }else{
                        $.messager.alert("消息",obj.message);
                    } 
			    }
			});
			//提交表单
			$("#gradeInfoEditForm").submit();
		} else {
			$.messager.alert("错误提示","你输入的信息还有错误！","warning");
		}
	});
});
