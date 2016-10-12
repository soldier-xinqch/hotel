<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>考勤单管理</title>
		<%@ include file="/common/include/head_meta.jsp"%>
		<style type="text/css">
			.ui-dialog *
{
    font-family:Tahoma;
    font-size:11px;
}
.ui-dialog form#crud-form
{
    margin-top:20px;
}
.ui-dialog form#crud-form input
{
    width:230px;
    overflow:visible;/*fix for IE*/
}
.ui-dialog form#crud-form td.label
{
    font-weight:bold;padding-right:5px;
}
div.pq-grid-toolbar-crud
{
    text-align:center;
}
div.pq-grid *
{
    font-size:14px;    
}
tr.pq-grid-row td
{
    color:#888;
}
tr.pq-row-edit > td
{
    color:#000;
}
tr.pq-row-delete
{
    text-decoration:line-through;         
}
tr.pq-row-delete td
{
    background-color:pink;   
}
.pq-pager .pq-page-placeholder{
	vertical-align:middle;
}
.ui-button{
	margin: 5 0px;
}
		</style>
	</head>

	<body>
		<jsp:include page="/home/title" />
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<%-- <%@ include file="/common/include/left_menu.jsp"%> --%>
				<jsp:include page="/menu/index/${menuKey}" />
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">首页</a>
							</li>
							<li class="active">控制台</li>
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->
					</div>

					<div class="page-content">
						<div class="page-header">
							<h1>
								考勤单管理
								<small>
									<i class="icon-double-angle-right"></i>
									 查看
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="col-lg-2">
									<div class="panel panel-default">
									   <div class="panel-heading">
									          部门结构
									   </div>
									   <div class="panel-body">
									     <ul id="zTree" class="ztree" ></ul>
									   </div>
									</div>
									
								</div>
								<div class='col-lg-10'>
									<div class="searchDiv">
										<form class="form-inline">
										  <div class="form-group">
										    <label for="exampleInputName2">姓名</label>
										    <input type="text" class="form-control" id="exampleInputName2" placeholder="请输入员工姓名">
										  </div>
										   <div class="form-group">
										    <label for="exampleInputEmail2">员工编号</label>
										    <input type="email" class="form-control" id="exampleInputEmail2" placeholder="请输入员工的电话号">
										  </div>
										  <div class="form-group">
										    <label for="exampleInputEmail2">工作日期</label>
										    <input type="email" class="form-control" id="exampleInputEmail2" placeholder="请输入员工的身份证号">
										  </div>
										   <div class="form-group">
										    <label for="exampleInputEmail2">TO</label>
										    <input type="email" class="form-control" id="exampleInputEmail2" placeholder="请输入员工的身份证号">
										  </div>
										  <button type="submit" class="btn btn-sm btn-primary">查询</button>
										</form>
									</div>
									 <div id="attendanceRecord_grid"></div>
								</div>
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				
			</div><!-- /.main-container-inner -->
			<%@ include file="/common/include/plugins.jsp"%>
		</div><!-- /.main-container -->
		
<!--  dialog  -->
<div id="attendanceRecord_dialog" class="dialog-model"><%@ include file="attendanceRecord_dialog.jsp"%></div>
<div id="attendanceRecord_export_dialog" class="dialog-model"><%@ include file="attendanceRecord_export_dialog.jsp"%></div>
		<script type="text/javascript">
			<%@ include file="attendanceRecord.js"%>
		</script>
</body>
</html>

