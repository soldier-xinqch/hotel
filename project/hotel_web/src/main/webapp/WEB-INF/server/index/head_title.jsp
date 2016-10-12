<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<div class="navbar navbar-default" id="navbar">
	<script type="text/javascript">
		try {
			ace.settings.check('navbar', 'fixed')
		} catch (e) {
		}
	</script>

	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left">
			<a href="#" class="navbar-brand"> <small>
			<!-- <img class="nav-user-photo" src="" alt="" />  -->
			<i class="icon-leaf"></i>
			北京宏业世纪运营管理系统
			</small>
			</a>
			<!-- /.brand -->
		</div>
		<!-- /.navbar-header -->

		<div class="navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li class="light-blue">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle"> 
					<img class="nav-user-photo" src="../public/images/index.png" alt="Jason's Photo" /> 
					<span class="user-info"> <small>您好</small> ${user.realName} </span> <i class="icon-caret-down"></i>
				</a>
				<ul
					class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
					<!-- <li><a href="#"> <i class="icon-cog"></i> Settings
							</a></li>
					-->
					<li><a href="#"> <i class="icon-user"></i> 个人设置
					</a></li> 

					<li class="divider"></li>
				
					<li><a href="#" id="logout"> <i class="icon-off"></i> Logout
					</a></li>
				</ul>
			</li>
			</ul>
			<!-- /.ace-nav -->
		</div>
		<!-- /.navbar-header -->
	</div>
	<!-- /.container -->
</div>