<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/include/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>排班管理</title>
		<%@ include file="/common/include/head_meta.jsp"%>
		
		<link rel='stylesheet' href='/plugins/fullcalendar-3.0.0/fullcalendar.css' />
		<script src='/plugins/fullcalendar-3.0.0/lib/moment.min.js'></script>
		<script src='/plugins/fullcalendar-3.0.0/fullcalendar.js'></script>
		<script src='/plugins/fullcalendar-3.0.0/locale-all.js'></script>
		
		<style type="text/css">
		.ui-button{
			margin: 5 0px;
		}
		</style>
	</head>

	<body>
		<%-- <%@ include file="/common/include/head_title.jsp"%> --%>
		<jsp:include page="/home/title" />
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<%--<%@ include file="/common/include/left_menu.jsp"%> --%>
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
								排班管理
								<small>
									<i class="icon-double-angle-right"></i>
									 查看
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-lg-12">
								<!-- PAGE CONTENT BEGINS -->
								<div id='calendar'></div>
								
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
				<div id="schedule_dialog" class="dialog-model"><%@ include file="schedule_dialog.jsp"%></div>
			</div><!-- /.main-container-inner -->
			<%@ include file="/common/include/plugins.jsp"%>
		</div><!-- /.main-container -->
		<script type="text/javascript">
			<%@ include file="attendanceArrange.js"%>
		</script>
</body>
</html>

