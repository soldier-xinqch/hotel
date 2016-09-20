<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>${modelName} 性能监控</title>
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
								性能监控
								<small>
									<i class="icon-double-angle-right"></i>
									对数据执行以及网站访问等性能进行监控查看
								</small>
							</h1>
						</div><!-- /.page-header -->
						<!-- TODO  -->    
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
										<div class="tabbable">
											<ul class="nav nav-tabs" id="myTab">
												<li class="active">
													<a data-toggle="tab" href="#home">
														<i class="green icon-home bigger-110"></i>
														数据执行监控查询
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#profile">
														运行监控查询
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#dataSearch">
														数据库登陆
													</a>
												</li>
											</ul>

											<div class="tab-content">
												<div id="home" class="tab-pane in active">
													
												</div>

												<div id="profile" class="tab-pane embed-responsive embed-responsive-16by9">
													<iframe src="/monitoring" class="iframe_cls" frameborder="0" id="h2-database-frame" width="100%" onload="this.height=600" ></iframe>
												</div>
												<div id="dataSearch" class="tab-pane embed-responsive embed-responsive-16by9">
													<iframe src="/console" class="iframe_cls" frameborder="0"  id="h2-database-frame" width="100%" onload="this.height=600" ></iframe>
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
		function iFrameHeight() {   
			var ifm= document.getElementsByClassName('iframe_cls');
			var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;   
			if(ifm != null && subWeb != null) {
		 	  var scrollheight = subWeb.body.scrollHeight;
		 	  ifm.height= 600 >scrollheight?600:scrollheight; 
			}   
		}   
		window.setInterval("iFrameHeight()", 600);
	</script>
</body>
</html>

