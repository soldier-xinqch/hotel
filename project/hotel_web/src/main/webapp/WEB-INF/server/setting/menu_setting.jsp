<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>${modelName} 菜单权限设置</title>
		<%@ include file="/common/include/head_meta.jsp"%>
		<link href="/public/css/fuelux/fuelux.min.css" rel="stylesheet"/>
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
								菜单设置
								<small>
									<i class="icon-double-angle-right"></i>
									对左侧菜单进行添加或修改
								</small>
							</h1>
						</div><!-- /.page-header -->
						<!-- TODO  -->    
						<ul id="tree"></ul>
						
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
			</div><!-- /.main-container-inner -->
		</div><!-- /.main-container -->

	<%-- <script type="application/javascript" src="/public/js/fuelux/js/fuelux.tree.min.js"></script>
	<script type="application/javascript" src="/public/js/fuelux/data/fuelux.tree-sampledata.js"></script>
	<script type="text/javascript">
		<%@ include file="menu_setting.js"%>
	</script> --%>
    <script src="/public/js/fuelux/js/fuelux.min.js"></script>
    <script type="text/javascript">
    $(function(){
    	
    });
    
    </script>
</body>
</html>

