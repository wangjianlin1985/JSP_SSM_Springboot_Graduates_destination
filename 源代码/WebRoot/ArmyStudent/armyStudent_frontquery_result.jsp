<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%> 
<%@ page import="com.chengxusheji.po.ArmyStudent" %>
<%@ page import="com.chengxusheji.po.GradeInfo" %>
<%@ page import="com.chengxusheji.po.SpecialInfo" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    List<ArmyStudent> armyStudentList = (List<ArmyStudent>)request.getAttribute("armyStudentList");
    //获取所有的gradeObj信息
    List<GradeInfo> gradeInfoList = (List<GradeInfo>)request.getAttribute("gradeInfoList");
    //获取所有的specialObj信息
    List<SpecialInfo> specialInfoList = (List<SpecialInfo>)request.getAttribute("specialInfoList");
    int currentPage =  (Integer)request.getAttribute("currentPage"); //当前页
    int totalPage =   (Integer)request.getAttribute("totalPage");  //一共多少页
    int recordNumber =   (Integer)request.getAttribute("recordNumber");  //一共多少记录
    String studentNumber = (String)request.getAttribute("studentNumber"); //学号查询关键字
    String studentName = (String)request.getAttribute("studentName"); //姓名查询关键字
    SpecialInfo specialObj = (SpecialInfo)request.getAttribute("specialObj");
    GradeInfo gradeObj = (GradeInfo)request.getAttribute("gradeObj");
    String yearMonth = (String)request.getAttribute("yearMonth"); //年月份查询关键字
    String areaInfo = (String)request.getAttribute("areaInfo"); //地区查询关键字
    String junqu = (String)request.getAttribute("junqu"); //参军军区查询关键字
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
<title>参军学生查询</title>
<link href="<%=basePath %>plugins/bootstrap.css" rel="stylesheet">
<link href="<%=basePath %>plugins/bootstrap-dashen.css" rel="stylesheet">
<link href="<%=basePath %>plugins/font-awesome.css" rel="stylesheet">
<link href="<%=basePath %>plugins/animate.css" rel="stylesheet">
<link href="<%=basePath %>plugins/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
</head>
<body style="margin-top:70px;">
<div class="container">
<jsp:include page="../header.jsp"></jsp:include>
	<div class="row"> 
		<div class="col-md-9 wow fadeInDown" data-wow-duration="0.5s">
			<div>
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
			    	<li><a href="<%=basePath %>index.jsp">首页</a></li>
			    	<li role="presentation" class="active"><a href="#armyStudentListPanel" aria-controls="armyStudentListPanel" role="tab" data-toggle="tab">参军学生列表</a></li>
			    	<li role="presentation" ><a href="<%=basePath %>ArmyStudent/armyStudent_frontAdd.jsp" style="display:none;">添加参军学生</a></li>
				</ul>
			  	<!-- Tab panes -->
			  	<div class="tab-content">
				    <div role="tabpanel" class="tab-pane active" id="armyStudentListPanel">
				    		<div class="row">
				    			<div class="col-md-12 top5">
				    				<div class="table-responsive">
				    				<table class="table table-condensed table-hover">
				    					<tr class="success bold"><td>序号</td><td>记录id</td><td>学号</td><td>姓名</td><td>专业</td><td>年级</td><td>年月份</td><td>地区</td><td>参军军区</td><td>职位</td><td>收入</td><td>操作</td></tr>
				    					<% 
				    						/*计算起始序号*/
				    	            		int startIndex = (currentPage -1) * 5;
				    	            		/*遍历记录*/
				    	            		for(int i=0;i<armyStudentList.size();i++) {
					    	            		int currentIndex = startIndex + i + 1; //当前记录的序号
					    	            		ArmyStudent armyStudent = armyStudentList.get(i); //获取到参军学生对象
 										%>
 										<tr>
 											<td><%=currentIndex %></td>
 											<td><%=armyStudent.getId() %></td>
 											<td><%=armyStudent.getStudentNumber() %></td>
 											<td><%=armyStudent.getStudentName() %></td>
 											<td><%=armyStudent.getSpecialObj().getSpecialName() %></td>
 											<td><%=armyStudent.getGradeObj().getGradeName() %></td>
 											<td><%=armyStudent.getYearMonth() %></td>
 											<td><%=armyStudent.getAreaInfo() %></td>
 											<td><%=armyStudent.getJunqu() %></td>
 											<td><%=armyStudent.getPositionName() %></td>
 											<td><%=armyStudent.getShouru() %></td>
 											<td>
 												<a href="<%=basePath  %>ArmyStudent/<%=armyStudent.getId() %>/frontshow"><i class="fa fa-info"></i>&nbsp;查看</a>&nbsp;
 												<a href="#" onclick="armyStudentEdit('<%=armyStudent.getId() %>');" style="display:none;"><i class="fa fa-pencil fa-fw"></i>编辑</a>&nbsp;
 												<a href="#" onclick="armyStudentDelete('<%=armyStudent.getId() %>');" style="display:none;"><i class="fa fa-trash-o fa-fw"></i>删除</a>
 											</td> 
 										</tr>
 										<%}%>
				    				</table>
				    				</div>
				    			</div>
				    		</div>

				    		<div class="row">
					            <div class="col-md-12">
						            <nav class="pull-left">
						                <ul class="pagination">
						                    <li><a href="#" onclick="GoToPage(<%=currentPage-1 %>,<%=totalPage %>);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
						                     <%
						                    	int startPage = currentPage - 5;
						                    	int endPage = currentPage + 5;
						                    	if(startPage < 1) startPage=1;
						                    	if(endPage > totalPage) endPage = totalPage;
						                    	for(int i=startPage;i<=endPage;i++) {
						                    %>
						                    <li class="<%= currentPage==i?"active":"" %>"><a href="#"  onclick="GoToPage(<%=i %>,<%=totalPage %>);"><%=i %></a></li>
						                    <%  } %> 
						                    <li><a href="#" onclick="GoToPage(<%=currentPage+1 %>,<%=totalPage %>);"><span aria-hidden="true">&raquo;</span></a></li>
						                </ul>
						            </nav>
						            <div class="pull-right" style="line-height:75px;" >共有<%=recordNumber %>条记录，当前第 <%=currentPage %>/<%=totalPage %> 页</div>
					            </div>
				            </div> 
				    </div>
				</div>
			</div>
		</div>
	<div class="col-md-3 wow fadeInRight">
		<div class="page-header">
    		<h1>参军学生查询</h1>
		</div>
		<form name="armyStudentQueryForm" id="armyStudentQueryForm" action="<%=basePath %>ArmyStudent/frontlist" class="mar_t15">
			<div class="form-group">
				<label for="studentNumber">学号:</label>
				<input type="text" id="studentNumber" name="studentNumber" value="<%=studentNumber %>" class="form-control" placeholder="请输入学号">
			</div>






			<div class="form-group">
				<label for="studentName">姓名:</label>
				<input type="text" id="studentName" name="studentName" value="<%=studentName %>" class="form-control" placeholder="请输入姓名">
			</div>






            <div class="form-group">
            	<label for="specialObj_specialNo">专业：</label>
                <select id="specialObj_specialNo" name="specialObj.specialNo" class="form-control">
                	<option value="">不限制</option>
	 				<%
	 				for(SpecialInfo specialInfoTemp:specialInfoList) {
	 					String selected = "";
 					if(specialObj!=null && specialObj.getSpecialNo()!=null && specialObj.getSpecialNo().equals(specialInfoTemp.getSpecialNo()))
 						selected = "selected";
	 				%>
 				 <option value="<%=specialInfoTemp.getSpecialNo() %>" <%=selected %>><%=specialInfoTemp.getSpecialName() %></option>
	 				<%
	 				}
	 				%>
 			</select>
            </div>
            <div class="form-group">
            	<label for="gradeObj_gradeId">年级：</label>
                <select id="gradeObj_gradeId" name="gradeObj.gradeId" class="form-control">
                	<option value="0">不限制</option>
	 				<%
	 				for(GradeInfo gradeInfoTemp:gradeInfoList) {
	 					String selected = "";
 					if(gradeObj!=null && gradeObj.getGradeId()!=null && gradeObj.getGradeId().intValue()==gradeInfoTemp.getGradeId().intValue())
 						selected = "selected";
	 				%>
 				 <option value="<%=gradeInfoTemp.getGradeId() %>" <%=selected %>><%=gradeInfoTemp.getGradeName() %></option>
	 				<%
	 				}
	 				%>
 			</select>
            </div>
			<div class="form-group">
				<label for="yearMonth">年月份:</label>
				<input type="text" id="yearMonth" name="yearMonth" value="<%=yearMonth %>" class="form-control" placeholder="请输入年月份">
			</div>






			<div class="form-group">
				<label for="areaInfo">地区:</label>
				<input type="text" id="areaInfo" name="areaInfo" value="<%=areaInfo %>" class="form-control" placeholder="请输入地区">
			</div>






			<div class="form-group">
				<label for="junqu">参军军区:</label>
				<input type="text" id="junqu" name="junqu" value="<%=junqu %>" class="form-control" placeholder="请输入参军军区">
			</div>






            <input type=hidden name=currentPage value="<%=currentPage %>" />
            <button type="submit" class="btn btn-primary">查询</button>
        </form>
	</div>

		</div>
	</div> 
<div id="armyStudentEditDialog" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><i class="fa fa-edit"></i>&nbsp;参军学生信息编辑</h4>
      </div>
      <div class="modal-body" style="height:450px; overflow: scroll;">
      	<form class="form-horizontal" name="armyStudentEditForm" id="armyStudentEditForm" enctype="multipart/form-data" method="post"  class="mar_t15">
		  <div class="form-group">
			 <label for="armyStudent_id_edit" class="col-md-3 text-right">记录id:</label>
			 <div class="col-md-9"> 
			 	<input type="text" id="armyStudent_id_edit" name="armyStudent.id" class="form-control" placeholder="请输入记录id" readOnly>
			 </div>
		  </div> 
		  <div class="form-group">
		  	 <label for="armyStudent_studentNumber_edit" class="col-md-3 text-right">学号:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="armyStudent_studentNumber_edit" name="armyStudent.studentNumber" class="form-control" placeholder="请输入学号">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="armyStudent_studentName_edit" class="col-md-3 text-right">姓名:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="armyStudent_studentName_edit" name="armyStudent.studentName" class="form-control" placeholder="请输入姓名">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="armyStudent_specialObj_specialNo_edit" class="col-md-3 text-right">专业:</label>
		  	 <div class="col-md-9">
			    <select id="armyStudent_specialObj_specialNo_edit" name="armyStudent.specialObj.specialNo" class="form-control">
			    </select>
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="armyStudent_gradeObj_gradeId_edit" class="col-md-3 text-right">年级:</label>
		  	 <div class="col-md-9">
			    <select id="armyStudent_gradeObj_gradeId_edit" name="armyStudent.gradeObj.gradeId" class="form-control">
			    </select>
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="armyStudent_yearMonth_edit" class="col-md-3 text-right">年月份:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="armyStudent_yearMonth_edit" name="armyStudent.yearMonth" class="form-control" placeholder="请输入年月份">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="armyStudent_areaInfo_edit" class="col-md-3 text-right">地区:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="armyStudent_areaInfo_edit" name="armyStudent.areaInfo" class="form-control" placeholder="请输入地区">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="armyStudent_junqu_edit" class="col-md-3 text-right">参军军区:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="armyStudent_junqu_edit" name="armyStudent.junqu" class="form-control" placeholder="请输入参军军区">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="armyStudent_positionName_edit" class="col-md-3 text-right">职位:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="armyStudent_positionName_edit" name="armyStudent.positionName" class="form-control" placeholder="请输入职位">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="armyStudent_shouru_edit" class="col-md-3 text-right">收入:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="armyStudent_shouru_edit" name="armyStudent.shouru" class="form-control" placeholder="请输入收入">
			 </div>
		  </div>
		</form> 
	    <style>#armyStudentEditForm .form-group {margin-bottom:5px;}  </style>
      </div>
      <div class="modal-footer"> 
      	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      	<button type="button" class="btn btn-primary" onclick="ajaxArmyStudentModify();">提交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<jsp:include page="../footer.jsp"></jsp:include> 
<script src="<%=basePath %>plugins/jquery.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap.js"></script>
<script src="<%=basePath %>plugins/wow.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>plugins/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jsdate.js"></script>
<script>
var basePath = "<%=basePath%>";
/*跳转到查询结果的某页*/
function GoToPage(currentPage,totalPage) {
    if(currentPage==0) return;
    if(currentPage>totalPage) return;
    document.armyStudentQueryForm.currentPage.value = currentPage;
    document.armyStudentQueryForm.submit();
}

/*可以直接跳转到某页*/
function changepage(totalPage)
{
    var pageValue=document.armyStudentQueryForm.pageValue.value;
    if(pageValue>totalPage) {
        alert('你输入的页码超出了总页数!');
        return ;
    }
    document.armyStudentQueryForm.currentPage.value = pageValue;
    documentarmyStudentQueryForm.submit();
}

/*弹出修改参军学生界面并初始化数据*/
function armyStudentEdit(id) {
	$.ajax({
		url :  basePath + "ArmyStudent/" + id + "/update",
		type : "get",
		dataType: "json",
		success : function (armyStudent, response, status) {
			if (armyStudent) {
				$("#armyStudent_id_edit").val(armyStudent.id);
				$("#armyStudent_studentNumber_edit").val(armyStudent.studentNumber);
				$("#armyStudent_studentName_edit").val(armyStudent.studentName);
				$.ajax({
					url: basePath + "SpecialInfo/listAll",
					type: "get",
					success: function(specialInfos,response,status) { 
						$("#armyStudent_specialObj_specialNo_edit").empty();
						var html="";
		        		$(specialInfos).each(function(i,specialInfo){
		        			html += "<option value='" + specialInfo.specialNo + "'>" + specialInfo.specialName + "</option>";
		        		});
		        		$("#armyStudent_specialObj_specialNo_edit").html(html);
		        		$("#armyStudent_specialObj_specialNo_edit").val(armyStudent.specialObjPri);
					}
				});
				$.ajax({
					url: basePath + "GradeInfo/listAll",
					type: "get",
					success: function(gradeInfos,response,status) { 
						$("#armyStudent_gradeObj_gradeId_edit").empty();
						var html="";
		        		$(gradeInfos).each(function(i,gradeInfo){
		        			html += "<option value='" + gradeInfo.gradeId + "'>" + gradeInfo.gradeName + "</option>";
		        		});
		        		$("#armyStudent_gradeObj_gradeId_edit").html(html);
		        		$("#armyStudent_gradeObj_gradeId_edit").val(armyStudent.gradeObjPri);
					}
				});
				$("#armyStudent_yearMonth_edit").val(armyStudent.yearMonth);
				$("#armyStudent_areaInfo_edit").val(armyStudent.areaInfo);
				$("#armyStudent_junqu_edit").val(armyStudent.junqu);
				$("#armyStudent_positionName_edit").val(armyStudent.positionName);
				$("#armyStudent_shouru_edit").val(armyStudent.shouru);
				$('#armyStudentEditDialog').modal('show');
			} else {
				alert("获取信息失败！");
			}
		}
	});
}

/*删除参军学生信息*/
function armyStudentDelete(id) {
	if(confirm("确认删除这个记录")) {
		$.ajax({
			type : "POST",
			url : basePath + "ArmyStudent/deletes",
			data : {
				ids : id,
			},
			success : function (obj) {
				if (obj.success) {
					alert("删除成功");
					$("#armyStudentQueryForm").submit();
					//location.href= basePath + "ArmyStudent/frontlist";
				}
				else 
					alert(obj.message);
			},
		});
	}
}

/*ajax方式提交参军学生信息表单给服务器端修改*/
function ajaxArmyStudentModify() {
	$.ajax({
		url :  basePath + "ArmyStudent/" + $("#armyStudent_id_edit").val() + "/update",
		type : "post",
		dataType: "json",
		data: new FormData($("#armyStudentEditForm")[0]),
		success : function (obj, response, status) {
            if(obj.success){
                alert("信息修改成功！");
                $("#armyStudentQueryForm").submit();
            }else{
                alert(obj.message);
            } 
		},
		processData: false,
		contentType: false,
	});
}

$(function(){
	/*小屏幕导航点击关闭菜单*/
    $('.navbar-collapse a').click(function(){
        $('.navbar-collapse').collapse('hide');
    });
    new WOW().init();

})
</script>
</body>
</html>

