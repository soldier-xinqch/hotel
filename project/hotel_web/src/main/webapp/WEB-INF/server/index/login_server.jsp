<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>${companyName} 欢迎您</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta http-equiv="Cache-Control" content="no-transform" />
		<meta name="renderer" content="webkit">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- 记得更改 网址信息 -->
		<meta name="mobile-agent" content="format=html5;url=http://m.xinqch.icoc.me/"/>
		
		<meta name="keywords" content=""/>
		<meta name="description" content=""/>
		<link type="text/css" rel="stylesheet" href="../public/css/main.css">
		<!-- 网页刷新进度条  -->
		<link type="text/css" rel="stylesheet" href="../public/css/nprogress.css">
		<link type="text/css" rel="stylesheet" href="../public/css/normalize.css">
		<link type="text/css" rel="stylesheet" href="../plugins/bootstrap-3.3.0/css/bootstrap.min.css ">
		<!--  图标字体库和CSS框架  -->
		<link rel="stylesheet" href="../plugins/font-awesome-4.6.3/css/font-awesome.css" />
		<link rel="stylesheet" href="../public/css/ace/ace.min.css" />
		<link rel="stylesheet" href="../public/css/ace/ace-rtl.min.css" />
		<link rel="stylesheet" href="../public/css/ace/ace-skins.min.css" />
		<link rel="stylesheet" href="../public/css/mycustom.css" />
		<link rel="stylesheet" href="../plugins/lobibox/lobibox.css" />
		<style style="text/css">
		.toolbar a{
			text-decoration:none;
		}
		.fa-user,.fa-lock{
			position: absolute;
			line-height: 30px;
			padding: 0 3px;
			z-index: 2;
			font-size: 17px;
			left: 5px;
			color: #909090;
		}
		.login-layout{
			background-image: url("../public/images/bg1.jpg");
			background-attachment:fixed;
		}
		/* div.glass{ 
			background-image:url(./glass.jpg); 
			background-attachment:fixed;
			
			position:absolute; 
			top:100px; 
			left:200px; 
			width:300px; 
			height:200px; 
			overflow:hidden; 
		}  */
		</style>
	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<%-- <div class="center">
								<h1>
									<span class="white">${systemName}(系统名称)</span>
								</h1>
							</div> --%>
							<div class="position-relative" style="top: 200px;">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h3 class="header blue lighter bigger">
												<i class="icon-coffee green"></i>
												宏业世纪 一体化平台
											</h3>
											<form id="login_form" action="../login/login" method="post">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-left">
															<i class="fa fa-user"></i>
															<input type="text" name="username" class="form-control" placeholder="请输入用户名……" />
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-left">
															<i class="fa fa-lock"></i>
															<input type="password" name="password" class="form-control" placeholder="请输入密码……" />
															
														</span>
													</label>
													<label class="block clearfix ">
														<span class="block input-icon input-icon-left" >
															<input type="text" name="verufucatCode"  style="width: 60%" placeholder="请输入验证码……" />
															<img id="validImage" style="width: 35%;margin-left: 5px;" alt="验证码图片"  
															   onclick="reloadVerifyCode();"	src="<%=request.getContextPath()%>/login/verificat">
														</span>
													</label>
													<div class="space"></div>

													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" class="ace" />
															<span class="lbl"> 记住我</span>
														</label>
														<!-- <button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="icon-key"></i>
															登录
														</button> -->
													</div>

													<div class="space-4"></div>
													<div class="toolbar clearfix" style="text-align: center;">
														<button type="button" id="submitBtn" class="col-lg-12 pull-right btn btn-sm btn-primary">
															<i class="icon-key"></i>
															登录
														</button>
													</div>
												</fieldset>
											</form>

											<!-- <div class="social-or-login center">
												<span class="bigger-110">快捷登录</span>
											</div>

											<div class="social-login center">
												<a class="btn btn-primary">
													<i class="icon-facebook"></i>
												</a>

												<a class="btn btn-info">
													<i class="icon-twitter"></i>
												</a>

												<a class="btn btn-danger">
													<i class="icon-google-plus"></i>
												</a>
											</div> -->
										</div><!-- /widget-main -->

										<!-- <div class="toolbar clearfix">
											<div>
												<a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">
													<i class="icon-hand-left"></i>
													忘记密码
												</a>
											</div>

											<div>
												<a href="#" onclick="show_box('signup-box'); return false;" class="user-signup-link">
													注册
													<i class="icon-hand-right"></i>
												</a>
											</div>
										</div> -->
									</div><!-- /widget-body -->
								</div><!-- /login-box -->

								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="fa fa-key"></i>
												忘记密码
											</h4>

											<div class="space-6"></div>
											<p>
												请输入您的邮箱地址
											</p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<i class="fa fa-envelope"></i>
															<input type="email" class="form-control font-indentation" />
														</span>
													</label>

													<div class="clearfix">
														<button type="button" class="width-35 pull-right btn btn-sm btn-danger">
															<i class="icon-lightbulb"></i>
															找回密码
														</button>
													</div>
												</fieldset>
											</form>
										</div><!-- /widget-main -->

										<div class="toolbar center">
											<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
												返回登录
												<i class="icon-hand-right"></i>
											</a>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /forgot-box -->

								<div id="signup-box" class="signup-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">
												<i class="icon-group blue"></i>
												注册
											</h4>

											<div class="space-6"></div>
											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="请输入注册邮箱……" />
															<i class="icon-envelope"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="请输入用户名……" />
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="请输入密码……" />
															<i class="icon-lock"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="请再次输入密码……" />
															<i class="icon-retweet"></i>
														</span>
													</label>

													<label class="block">
														<input type="checkbox" class="ace" />
														<span class="lbl">
															同意服务
															<a href="#">服务详细</a>
														</span>
													</label>

													<div class="space-24"></div>

													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="icon-refresh"></i>
															信息重置
														</button>

														<button type="button" class="width-65 pull-right btn btn-sm btn-success">
															注册
															<i class="icon-arrow-right icon-on-right"></i>
														</button>
													</div>
												</fieldset>
											</form>
										</div>

										<div class="toolbar center">
											<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
												<i class="icon-hand-left"></i>
												返回登录
											</a>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /signup-box -->
							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->

		<!-- basic scripts -->
		<script type="text/javascript" src="../public/js/jquery-1.9.1/jquery.js"></script>
		<script src="../plugins/lobibox/lobibox.js"></script> 
		<!-- <script src="/plugins/jquery-mobile/jquery.mobile-1.4.5.js"></script> -->
		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			function show_box(id) {
			 $('.widget-box.visible').removeClass('visible');
			 $('#'+id).addClass('visible');
			}
			$("#validImage").bind('click',function(){
				$("#validImage").attr('src','<%=request.getContextPath()%>/login/verificat?timestamp='+new Date());
			});
			$("#submitBtn").bind('click',function(){
				 $.ajax({
          		   type: "POST",
          		   url:$("#login_form").attr('action'),
          		   data: $("#login_form").serialize(),
          		   success: function(data){
          			   if('success' == data.type){
          				   Lobibox.notify("success", {
          		        		size: 'mini',
          		        		position: 'center top',
          		        		/* title: '登陆提示', */
          		        		msg: data.message
          		        	});
          				 setTimeout(window.location.href="../home/index", 3000);
          			   }else{
          				   Lobibox.notify("error", {
          						size: 'mini',
          		        		/* height:'300px', */
          		        		position: 'center top',
          		        		/* title: '登陆提示', */
          		        		msg: data.message
          		        	});
          			   }
          		   }
          		});
			});
		</script>
</body>
</html>
