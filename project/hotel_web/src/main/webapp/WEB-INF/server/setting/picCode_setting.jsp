<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>${modelName} 图码生成</title>
		<%@ include file="/common/include/head_meta.jsp"%>
	<style type="text/css">
	
	.btn.btn-app{
		font-size: 14px;
	}
	.fontSize{
		font-size: 18px;
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
								图码生成
								<small>
									<i class="icon-double-angle-right"></i>
									将信息生成图码或将图码转换成数据信息
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
														生成图码
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#profile">
														解析图码
													</a>
												</li>
											</ul>

											<div class="col-xs-12 tab-content">
												<div id="home" class="tab-pane in active">
													<!-- 图码生成区域  -->
													<div class='col-sm-3 fontSize' >
														<div class="row">
															<div class="col-xs-10" style="margin-top: 10px;">
																<div class="form-group" >
																	<label for="inputEmail3" class="control-label fontSize">链接地址</label>
																   	<div >
																    	<input type="email" class="form-control" id="" placeholder="Email">
																   	</div>
																 </div>
															</div>
															<div class="col-xs-2" style="padding-top: 15px;padding-left: 2px;">
																<button class="btn btn-app btn-light btn-xs">
																<i class="icon-print bigger-160"></i>
																生成
																</button>
															</div>
													     </div>
													     <div class="row">
														 	<div class="panel panel-default" >
																<div class="panel-heading" ><i class="fa fa-qrcode" style="margin-right: 5px;"></i>生成二维码</div>
															  	<div class="panel-body">
															   	<div class="widget-main">
																	<img id="encoderImgId" class="img-responsive" width="356px" height="356px" src="/public/images/default.png" cache="false">
																</div>
															  	</div>
															</div>
													     </div>
													</div>
													<!-- 内容编辑区域  -->
													<div class='col-sm-8' style="border:1px solid blue">
														<!-- 加载编辑器的容器 -->
										  				<script id="container" name="content" type="text/plain">
                  											这里写你的初始化内容
  											 			</script>
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
	
    
    <!-- 配置文件 -->
    <script type="text/javascript" src="/public/js/ueediter/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="/public/js/ueediter/ueditor.all.js"></script>
    <!-- 引入语言  -->
    <script type="text/javascript" src="/public/js/ueediter/lang/zh-cn/zh-cn.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container');
    </script>

	<script type="text/javascript">
		<%-- <%@ include file="menu_setting.js"%> --%>
	</script>
</body>
</html>

