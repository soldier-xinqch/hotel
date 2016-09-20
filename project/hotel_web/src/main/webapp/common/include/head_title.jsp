<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<img class="nav-user-photo" src="" alt="Jason's Photo" /> 
			这里输入公司名称或者是系统名称用el表达式动态传进来
			</small>
			</a>
			<!-- /.brand -->
		</div>
		<!-- /.navbar-header -->

		<div class="navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li class="grey">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#"> <i class="icon-tasks"></i> 
						<span class="badge badge-grey">4</span>
					</a>
					<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-header"><i class="icon-ok"></i> 4 Tasks to complete</li>
						<li><a href="#"> </a></li>
						<li><a href="#"> </a></li>
						<li><a href="#"> </a></li>
						<li><a href="#"> </a></li>
						<li>
							<a href="#"> See tasks with details<i class="icon-arrow-right"></i> </a>
						</li>
					</ul>
				</li>
				<li class="purple">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#"> <i class="fa fa-warning faa-flash animated"></i> 
					
						<span class="badge badge-important">8</span>
					</a>
					<ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-header"><i class="icon-warning-sign"></i> 8 Notifications</li>
						<li><a href="#"> </a></li>
						<li><a href="#"> <i class="btn btn-xs btn-primary icon-user"></i> Bob just signed up as an editor ... </a></li>
						<li><a href="#"> </a></li>
						<li><a href="#"> </a></li>

						<li><a href="#"> See all notifications <i class="icon-arrow-right"></i> </a></li>
					</ul>
				</li>
				<li class="green">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#"> 
						<i class="icon-comments-alt"></i> 
						
						<span class="badge badge-success">5</span>
					</a>
					<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-header"><i class="icon-envelope-alt"></i> 5 Messages</li>
						<li>
							<a href="#"> 
								<img src="" class="msg-photo" alt="Alex's Avatar" /> 
								<span class="msg-body">
									<span class="msg-title"> <span class="blue">Alex:</span>
											Ciao sociis natoque penatibus et auctor ...
									</span> 
									<span class="msg-time"> 
										<i class="icon-time"></i> <span>a moment ago</span>
									</span>
								</span>
							</a>
						</li>

						<li><a href="#"> <img src="" class="msg-photo" alt="Susan's Avatar" /> 
							<span class="msg-body"> 
								<span class="msg-title">
						 			<span class="blue">Susan:</span> Vestibulum id ligula porta felis euismod ...
								</span> 
								<span class="msg-time"> 
									<i class="icon-time"></i> 
									<span>20 minutes ago</span>
								</span>
							</span>
						</a></li>

						<li>
							<a href="#"> <img src="" class="msg-photo" alt="Bob's Avatar" /> 
								<span class="msg-body">
									<span class="msg-title"> 
										<span class="blue">Bob:</span> Nullam quis risus eget urna mollis ornare ...
									</span> 
									<span class="msg-time"> <i class="icon-time"></i>
										<span>3:15 pm</span>
									</span>
								</span>
							</a>
						</li>
						<li>
							<a href="inbox.html"> See all messages <i class="icon-arrow-right"></i> </a>
						</li>
					</ul>
				</li>
				<li class="light-blue">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle"> 
					<img class="nav-user-photo" src="" alt="Jason's Photo" /> 
					<span class="user-info"> <small>Welcome,</small> Jason </span> <i class="icon-caret-down"></i>
				</a>
				<ul
					class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
					<li><a href="#"> <i class="icon-cog"></i> Settings
					</a></li>

					<li><a href="#"> <i class="icon-user"></i> Profile
					</a></li>

					<li class="divider"></li>

					<li><a href="#"> <i class="icon-off"></i> Logout
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