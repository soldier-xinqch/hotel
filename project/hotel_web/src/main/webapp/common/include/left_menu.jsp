<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- sidebar  BEGIN -->
<a class="menu-toggler" id="menu-toggler" href="#">
	<span class="menu-text"></span>
</a>
<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'fixed')
		} catch (e) {
		}
	</script>

	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success">
				<i class="icon-signal"></i>
			</button>

			<button class="btn btn-info">
				<i class="icon-pencil"></i>
			</button>

			<button class="btn btn-warning">
				<i class="icon-group"></i>
			</button>

			<button class="btn btn-danger">
				<i class="icon-cogs"></i>
			</button>
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span> <span class="btn btn-info"></span>

			<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
		</div>
	</div>
	<!-- #sidebar-shortcuts -->

	<ul class="nav nav-list">
		<li class="active">
			<a href="index.html"> <i class="fa fa-home"></i> <span class="menu-text"> 控制台 </span>
			</a>
		</li>

		<li>
			<a href="typography.html"> <i class="icon-text-width"></i>
					<span class="menu-text"> 企业管理 </span>
			</a>
		</li>

		<li>
			<a href="#" class="dropdown-toggle"> <i
				class="fa fa-user"></i> <span class="menu-text">人员信息 </span> <b
				class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li><a href="elements.html"> <i
						class="icon-double-angle-right"></i> 部门管理
				</a></li>

				<li><a href="buttons.html"> <i
						class="icon-double-angle-right"></i> 员工管理
				</a></li>

				<li><a href="treeview.html"> <i
						class="icon-double-angle-right"></i> 离职人员管理
				</a></li>
			</ul>
		</li>

		<li><a href="#" class="dropdown-toggle"> <i class="icon-list"></i>
				<span class="menu-text">考勤管理 </span> <b class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu">
				<li><a href="tables.html"> <i
						class="icon-double-angle-right"></i> 刷卡查询
				</a></li>

				<li><a href="jqgrid.html"> <i
						class="icon-double-angle-right"></i>异常查询
				</a></li>
				<li><a href="jqgrid.html"> <i
						class="icon-double-angle-right"></i>迟退查询
				</a></li>
				<li><a href="jqgrid.html"> <i
						class="icon-double-angle-right"></i>综合查询
				</a></li>
				<li><a href="jqgrid.html"> <i
						class="icon-double-angle-right"></i>排班管理
				</a></li>
				<li><a href="jqgrid.html"> <i
						class="icon-double-angle-right"></i>考勤管理
				</a></li>
				<li><a href="jqgrid.html"> <i
						class="icon-double-angle-right"></i>班次设置
				</a></li>
				<li><a href="jqgrid.html"> <i
						class="icon-double-angle-right"></i>类别设置
				</a></li>
			</ul></li>

		<li>
			<a href="#" class="dropdown-toggle"> <i class="icon-edit"></i>
				<span class="menu-text"> 餐厅管理 </span> <b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li><a href="form-elements.html"> <i
						class="icon-double-angle-right"></i> 开餐时间
				</a></li>

				<li><a href="form-wizard.html"> <i
						class="icon-double-angle-right"></i>刷卡查询
				</a></li>

				<li><a href="wysiwyg.html"> <i
						class="icon-double-angle-right"></i> 餐次管理
				</a></li>
			</ul></li>

		<li><a href="widgets.html"> <i class="icon-list-alt"></i> <span
				class="menu-text"> 节水管理 </span>
			</a>
			<ul class="submenu">
				<li><a href="form-elements.html"> <i
						class="icon-double-angle-right"></i> 用户卡管理
				</a></li>

				<li><a href="form-wizard.html"> <i
						class="icon-double-angle-right"></i>人员类型管理
				</a></li>

				<li><a href="wysiwyg.html"> <i
						class="icon-double-angle-right"></i> 开户查询
				</a></li>
			</ul>
		</li>

		<li><a href="calendar.html"> <i class="icon-calendar"></i> <span
				class="menu-text">门禁管理 <span
					class="badge badge-transparent tooltip-error"
					title="2&nbsp;Important&nbsp;Events"> <i
						class="icon-warning-sign red bigger-130"></i>
				</span>
			</span>
		</a>
			<ul class="submenu">
				<li><a href="form-elements.html"> <i
						class="icon-double-angle-right"></i>出入门管理
				</a></li>

				<li><a href="form-wizard.html"> <i
						class="icon-double-angle-right"></i>门禁授权
				</a></li>

				<li><a href="wysiwyg.html"> <i
						class="icon-double-angle-right"></i> 刷卡记录查询
				</a></li>
			</ul>
		</li>
		<li><a href="#" class="dropdown-toggle"> <i class="icon-tag"></i>
				<span class="menu-text"> 系统管理 </span> <b
				class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu">
				<li><a href="profile.html"> <i
						class="icon-double-angle-right"></i> 资源分类
				</a></li>

				<li><a href="inbox.html"> <i
						class="icon-double-angle-right"></i> 资源管理
				</a></li>

				<li><a href="pricing.html"> <i
						class="icon-double-angle-right"></i> 模块管理
				</a></li>

				<li><a href="invoice.html"> <i
						class="icon-double-angle-right"></i>角色管理
				</a></li>

				<li><a href="timeline.html"> <i
						class="icon-double-angle-right"></i>用户管理
				</a></li>

				<li><a href="login.html"> <i
						class="icon-double-angle-right"></i>个人资料
				</a></li>
				<li><a href="login.html"> <i
						class="icon-double-angle-right"></i>缓存管理
				</a></li>
				<li><a href="login.html"> <i
						class="icon-double-angle-right"></i>系统设置
				</a></li>
			</ul></li>
	</ul>
	<!-- /.nav-list -->
	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
			data-icon2="icon-double-angle-right"></i>
	</div>
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed')
		} catch (e) {
		}
	</script>
</div>
<!-- sidebar END  -->