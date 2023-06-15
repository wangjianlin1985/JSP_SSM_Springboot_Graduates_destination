<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gradeInfo.css" />
<div id="gradeInfoAddDiv">
	<form id="gradeInfoAddForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">年级名称:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="gradeInfo_gradeName" name="gradeInfo.gradeName" style="width:200px" />

			</span>

		</div>
		<div class="operation">
			<a id="gradeInfoAddButton" class="easyui-linkbutton">添加</a>
			<a id="gradeInfoClearButton" class="easyui-linkbutton">重填</a>
		</div> 
	</form>
</div>
<script src="${pageContext.request.contextPath}/GradeInfo/js/gradeInfo_add.js"></script> 
