<%@page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${modelName}用户权限设置</title>
<%@ include file="/common/include/head_meta.jsp"%>
</head>

<body>
	<%@ include file="/common/include/head_title.jsp"%>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>
		<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
			<%@ include file="/common/include/left_menu.jsp"%>
			<div class="main-content">
				<!-- 右侧内容头部 begin  -->
				<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
				</script>
				<ul class="breadcrumb">
					<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
					<li class="active">控制台</li>
				</ul>
				<!-- .breadcrumb -->
				<div class="nav-search" id="nav-search">
					<form class="form-search">
						<span class="input-icon"> <input type="text"
							placeholder="Search ..." class="nav-search-input"
							id="nav-search-input" autocomplete="off" /> <i
							class="icon-search nav-search-icon"></i>
						</span>
					</form>
				</div>
				<!-- #nav-search -->
			</div>
			<!-- 右侧内容头部 end  -->
					<div class="page-content">
						<div class="page-header">
							<h1>
								用户管理
								<small>
									<i class="icon-double-angle-right"></i>
									展示了用户的基本信息和操作的日志信息
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
										<div class="tabbable">
											<ul class="nav nav-tabs" id="myTab">
												<li class="active">
													<a data-toggle="tab" href="#home">
														<i class="green icon-home bigger-110"></i>
														管理员列表
														<span class="badge badge-danger">4</span>
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#profile">
														成员列表
														<span class="badge badge-danger">4</span>
													</a>
												</li>

												<li class="dropdown">
													<a data-toggle="dropdown" class="dropdown-toggle" href="#">
														用户操作日志 &nbsp;
														<i class="icon-caret-down bigger-110 width-auto"></i>
													</a>
													<ul class="dropdown-menu dropdown-info">
														<li>
															<a data-toggle="tab" href="#dropdown1">管理员操作日志</a>
														</li>

														<li>
															<a data-toggle="tab" href="#dropdown2">成员操作日志</a>
														</li>
													</ul>
												</li>
											</ul>

											<div class="tab-content">
												<div id="home" class="tab-pane in active">
													<div class="alert alert-info">
														<i class="icon-hand-right"></i>
														Please note that demo server is not configured to save the changes, therefore you may get an error message.
														<button class="close" data-dismiss="alert">
															<i class="icon-remove"></i>
														</button>
													</div>
					
													<table id="grid-table"></table>
													<div id="grid-pager"></div>
												</div>

												<div id="profile" class="tab-pane">
													<div class="alert alert-info">
														<i class="icon-hand-right"></i>
														Please note that demo server is not configured to save the changes, therefore you may get an error message.
														<button class="close" data-dismiss="alert">
															<i class="icon-remove"></i>
														</button>
													</div>
					
													<table id="grid-table"></table>
													<div id="grid-pager"></div>
												</div>

												<div id="dropdown1" class="tab-pane">
													<div class="alert alert-info">
														<i class="icon-hand-right"></i>
														Please note that demo server is not configured to save the changes, therefore you may get an error message.
														<button class="close" data-dismiss="alert">
															<i class="icon-remove"></i>
														</button>
													</div>
					
													<table id="grid-table"></table>
													<div id="grid-pager"></div>
												</div>

												<div id="dropdown2" class="tab-pane">
													<div class="alert alert-info">
														<i class="icon-hand-right"></i>
														Please note that demo server is not configured to save the changes, therefore you may get an error message.
														<button class="close" data-dismiss="alert">
															<i class="icon-remove"></i>
														</button>
													</div>
					
													<table id="grid-table"></table>
													<div id="grid-pager"></div>
												</div>
											</div>
									</div><!-- /span -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
			</div><!-- /.main-container-inner -->
		</div><!-- /.main-container -->

	<script type="application/javascript" src="/public/js/jqGrid/jquery.jqGrid.min.js"></script>
	<script type="application/javascript" src="/public/js/jqGrid/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript">
		<%@ include file="user_setting.js"%>
	</script>
</body>
</html>

