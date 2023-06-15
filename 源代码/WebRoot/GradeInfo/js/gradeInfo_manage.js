var gradeInfo_manage_tool = null; 
$(function () { 
	initGradeInfoManageTool(); //建立GradeInfo管理对象
	gradeInfo_manage_tool.init(); //如果需要通过下拉框查询，首先初始化下拉框的值
	$("#gradeInfo_manage").datagrid({
		url : 'GradeInfo/list',
		fit : true,
		fitColumns : true,
		striped : true,
		rownumbers : true,
		border : false,
		pagination : true,
		pageSize : 5,
		pageList : [5, 10, 15, 20, 25],
		pageNumber : 1,
		sortName : "gradeId",
		sortOrder : "desc",
		toolbar : "#gradeInfo_manage_tool",
		columns : [[
			{
				field : "gradeId",
				title : "记录id",
				width : 70,
			},
			{
				field : "gradeName",
				title : "年级名称",
				width : 140,
			},
		]],
	});

	$("#gradeInfoEditDiv").dialog({
		title : "修改管理",
		top: "50px",
		width : 700,
		height : 515,
		modal : true,
		closed : true,
		iconCls : "icon-edit-new",
		buttons : [{
			text : "提交",
			iconCls : "icon-edit-new",
			handler : function () {
				if ($("#gradeInfoEditForm").form("validate")) {
					//验证表单 
					if(!$("#gradeInfoEditForm").form("validate")) {
						$.messager.alert("错误提示","你输入的信息还有错误！","warning");
					} else {
						$("#gradeInfoEditForm").form({
						    url:"GradeInfo/" + $("#gradeInfo_gradeId_edit").val() + "/update",
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
						    	console.log(data);
			                	var obj = jQuery.parseJSON(data);
			                    if(obj.success){
			                        $.messager.alert("消息","信息修改成功！");
			                        $("#gradeInfoEditDiv").dialog("close");
			                        gradeInfo_manage_tool.reload();
			                    }else{
			                        $.messager.alert("消息",obj.message);
			                    } 
						    }
						});
						//提交表单
						$("#gradeInfoEditForm").submit();
					}
				}
			},
		},{
			text : "取消",
			iconCls : "icon-redo",
			handler : function () {
				$("#gradeInfoEditDiv").dialog("close");
				$("#gradeInfoEditForm").form("reset"); 
			},
		}],
	});
});

function initGradeInfoManageTool() {
	gradeInfo_manage_tool = {
		init: function() {
		},
		reload : function () {
			$("#gradeInfo_manage").datagrid("reload");
		},
		redo : function () {
			$("#gradeInfo_manage").datagrid("unselectAll");
		},
		search: function() {
			$("#gradeInfo_manage").datagrid("load");
		},
		exportExcel: function() {
			$("#gradeInfoQueryForm").form({
			    url:"GradeInfo/OutToExcel",
			});
			//提交表单
			$("#gradeInfoQueryForm").submit();
		},
		remove : function () {
			var rows = $("#gradeInfo_manage").datagrid("getSelections");
			if (rows.length > 0) {
				$.messager.confirm("确定操作", "您正在要删除所选的记录吗？", function (flag) {
					if (flag) {
						var gradeIds = [];
						for (var i = 0; i < rows.length; i ++) {
							gradeIds.push(rows[i].gradeId);
						}
						$.ajax({
							type : "POST",
							url : "GradeInfo/deletes",
							data : {
								gradeIds : gradeIds.join(","),
							},
							beforeSend : function () {
								$("#gradeInfo_manage").datagrid("loading");
							},
							success : function (data) {
								if (data.success) {
									$("#gradeInfo_manage").datagrid("loaded");
									$("#gradeInfo_manage").datagrid("load");
									$("#gradeInfo_manage").datagrid("unselectAll");
									$.messager.show({
										title : "提示",
										msg : data.message
									});
								} else {
									$("#gradeInfo_manage").datagrid("loaded");
									$("#gradeInfo_manage").datagrid("load");
									$("#gradeInfo_manage").datagrid("unselectAll");
									$.messager.alert("消息",data.message);
								}
							},
						});
					}
				});
			} else {
				$.messager.alert("提示", "请选择要删除的记录！", "info");
			}
		},
		edit : function () {
			var rows = $("#gradeInfo_manage").datagrid("getSelections");
			if (rows.length > 1) {
				$.messager.alert("警告操作！", "编辑记录只能选定一条数据！", "warning");
			} else if (rows.length == 1) {
				$.ajax({
					url : "GradeInfo/" + rows[0].gradeId +  "/update",
					type : "get",
					data : {
						//gradeId : rows[0].gradeId,
					},
					beforeSend : function () {
						$.messager.progress({
							text : "正在获取中...",
						});
					},
					success : function (gradeInfo, response, status) {
						$.messager.progress("close");
						if (gradeInfo) { 
							$("#gradeInfoEditDiv").dialog("open");
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
			} else if (rows.length == 0) {
				$.messager.alert("警告操作！", "编辑记录至少选定一条数据！", "warning");
			}
		},
	};
}
