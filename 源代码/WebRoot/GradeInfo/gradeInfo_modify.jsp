<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_user_logstate.jsp"/>
<!DOCTYPE html>
<html>
<head>
<title>修改页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gradeInfo.css" />
</head>
<body style="margin:0px; font-size:14px; background-image:url(../images/bg.jpg); background-position:bottom; background-repeat:repeat;">
<div id="gradeInfo_editDiv">
	<form id="gradeInfoEditForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">记录id:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="gradeInfo_gradeId_edit" name="gradeInfo.gradeId" value="<%=request.getParameter("gradeId") %>" style="width:200px" />
			</span>
		</div>

		<div>
			<span class="label">年级名称:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="gradeInfo_gradeName_edit" name="gradeInfo.gradeName" style="width:200px" />

			</span>

		</div>
		<div class="operation">
			<a id="gradeInfoModifyButton" class="easyui-linkbutton">更新</a> 
		</div>
	</form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js" ></script>
<script src="${pageContext.request.contextPath}/GradeInfo/js/gradeInfo_modify.js"></script> 
</body>
</html>
