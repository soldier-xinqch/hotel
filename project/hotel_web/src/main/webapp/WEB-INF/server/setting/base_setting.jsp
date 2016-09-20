<%@page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${modelName}基础信息设置</title>
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
								基础信息管理
								<small>
									<i class="icon-double-angle-right"></i>
									展示了系统基本信息和操作的日志信息
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
														基础信息列表
														<span class="badge badge-danger">4</span>
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#profile">
														日志信息列表
														<span class="badge badge-danger">4</span>
													</a>
												</li>
											</ul>

											<div class="tab-content">
												<div id="home" class="tab-pane in active">
													<div class="alert alert-info">
														<i class="fa fa-bell"></i>
														基础信息为空，请添加基础信息
														<button class="close" data-dismiss="alert">
															<i class="fa fa-remove"></i>
														</button>
													</div>
													<div class="">
														<div id="base_info_tab" style="margin:auto;"></div>
													</div>
												</div>

												<div id="profile" class="tab-pane">
													<div class="alert alert-info">
														<i class="fa fa-bell"></i>
														日志信息为空，只有在操作基础信息后才会生成日志信息
														<button class="close" data-dismiss="alert">
															<i class="fa fa-remove"></i>
														</button>
													<table id="grid-table"></table>
													<div id="grid-pager"></div>
													</div> 
												</div>
											</div>
									</div><!-- /span -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
			</div><!-- /.main-container-inner -->
		</div><!-- /.main-container -->
		
		

	<script type="text/javascript">
		<%@ include file="base_setting.js"%>
	</script>
</body>
</html>

