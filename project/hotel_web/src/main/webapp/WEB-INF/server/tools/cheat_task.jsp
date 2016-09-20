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
						<!-- TODO  -->
						<div class="row">
							<div class="col-sm-9">
								<div class="space"></div>

								<div id="calendar"></div>
							</div>

							<div class="col-sm-3">
								<div class="widget-box transparent">
									<div class="widget-header">
										<h4>Draggable events</h4>
									</div>

									<div class="widget-body">
										<div class="widget-main no-padding">
											<div id="external-events">
												<div class="external-event label-grey" data-class="label-grey">
													<i class="icon-move"></i>
													My Event 1
												</div>

												<div class="external-event label-success" data-class="label-success">
													<i class="icon-move"></i>
													My Event 2
												</div>

												<div class="external-event label-danger" data-class="label-danger">
													<i class="icon-move"></i>
													My Event 3
												</div>

												<div class="external-event label-purple" data-class="label-purple">
													<i class="icon-move"></i>
													My Event 4
												</div>

												<div class="external-event label-yellow" data-class="label-yellow">
													<i class="icon-move"></i>
													My Event 5
												</div>

												<div class="external-event label-pink" data-class="label-pink">
													<i class="icon-move"></i>
													My Event 6
												</div>

												<div class="external-event label-info" data-class="label-info">
													<i class="icon-move"></i>
													My Event 7
												</div>

												<label>
													<input type="checkbox" class="ace ace-checkbox" id="drop-remove" />
													<span class="lbl"> Remove after drop</span>
												</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
			</div><!-- /.main-container-inner -->
		</div><!-- /.main-container -->
		<script src="/public/js/js/fullcalendar.min.js"></script>
	<script type="text/javascript">
		 <%@ include file="cheat_task.js"%> 
	</script>
</body>
</html>

