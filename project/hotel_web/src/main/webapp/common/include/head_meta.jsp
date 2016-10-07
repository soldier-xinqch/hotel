<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"  %>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Cache-Control" content="no-transform" />
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- 记得更改 网址信息 -->
<meta name="mobile-agent" content="format=html5;url=http://m.xinqch.icoc.me/"/>

<meta name="keywords" content=""/>
<meta name="description" content=""/>


<!-- <link rel="icon" type="image/png" href="/Content/images/favicon.ico"/> -->
<link type="text/css" rel="stylesheet" href="/plugins/jquery-ui-1.12.0/jquery-ui.css">
<!-- <link type="text/css" rel="stylesheet" href="/public/css/jquery-ui-1.12.0/themes/base/theme.css"> -->
<!-- 网页刷新进度条  -->
<link type="text/css" rel="stylesheet" href="/public/css/nprogress.css">
<link type="text/css" rel="stylesheet" href="/public/css/normalize.css">
<link type="text/css" rel="stylesheet" href="/plugins/bootstrap-3.3.0/css/bootstrap.min.css ">
<!-- 下拉框  -->
<link type="text/css" rel="stylesheet" href="/plugins/select2/select2.css">
<!--  图标字体库和CSS框架  -->
<link rel="stylesheet" href="/plugins/font-awesome-4.6.3/font-awesome.css" />
<link rel="stylesheet" href="/plugins/font-awesome-4.6.3/css/font-awesome.css" />
<link rel="stylesheet" href="/plugins/font-awesome-4.6.3/font-awesome-animation.css" />
<!-- jquery 动态表格 -->
<link rel="stylesheet" href="/plugins/paramquery/pqgrid.min.css" />
<link rel="stylesheet" href="/plugins/paramquery/pqgrid.ui.min.css" />
<link rel="stylesheet" href="/plugins/paramquery/pqgrid.bootstrap.min.css" />
<link rel="stylesheet" href="/plugins/paramquery/pqSelect/pqselect.min.css" />

<link rel="stylesheet" href="/public/css/ace/ace.min.css" />
<link rel="stylesheet" href="/public/css/ace/ace-rtl.min.css" />
<link rel="stylesheet" href="/public/css/ace/ace-skins.min.css" />
<link rel="stylesheet" href="/plugins/lobibox/lobibox.css" />
<link rel="stylesheet" href="/plugins/switch/bootstrap-switch.css" />
<link rel="stylesheet" type="text/css" href="/plugins/clockpicker/bootstrap-clockpicker.min.css">
<link rel="stylesheet" type="text/css" href="/plugins/bootstrap-dateTimepicker/css/bootstrap-datetimepicker.css">

<link rel="stylesheet" href="/public/css/mycustom.css" />
<!--
<link rel="stylesheet" href="/plugins/bootstrap-treeview/bootstrap-treeview.min.css" >
  -->



<script type="text/javascript" src="/public/js/jquery-1.9.1/jquery.js"></script>
<script type="text/javascript" src="/public/js/nprogress.js"></script>
<script type="text/javascript" src="/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/plugins/select2/select2.js"></script>
<script type="text/javascript" src="/plugins/jquery-ui-1.12.0/jquery-ui.js"></script>
<script src="/plugins/bootstrap-dateTimepicker/js/bootstrap-datetimepicker.js"></script> 
<script src="/plugins/bootstrap-dateTimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script> 

<script type="application/javascript" src="/plugins/switch/bootstrap-switch.js"></script>
<script src="/public/js/html5shiv.js"></script>
<script src="/public/js/respond.min.js"></script>
<script src="/public/js/my.js"></script>


<script src="/public/js/ace/ace-extra.min.js"></script>
<script src="/public/js/ace/ace-elements.min.js"></script>
<script src="/public/js/ace/ace.min.js"></script>

<script type="application/javascript" src="/plugins/paramquery/pqgrid.min.js"></script>
<script type="application/javascript" src="/plugins/paramquery/jsZip-2.5.0/jszip.min.js"></script>
<script src="/plugins/paramquery/pqSelect/pqselect.min.js"></script>
<script type="application/javascript" src="/plugins/paramquery/localize/pq-localize-zh.js"></script>
<script src="/public/js/jquery.ui.touch-punch.min.js"></script>
<script src="/plugins/bootbox/bootbox.js"></script>
<script src="/plugins/lobibox/lobibox.js"></script> 
<script src="/plugins/bootstrap-checkbox/js/bootstrap-checkbox.js"></script> 
<script src="/plugins/clockpicker/bootstrap-clockpicker.min.js"></script> 
<script src="/public/js/date.js"></script>

<!-- 
<script src="/plugins/bootstrap-treeview/bootstrap-treeview.min.js"></script>
<script type="text/javascript" src="/public/js/browser.js"></script>
<script src="/public/js/jquery.easypiechart.js"></script>
<script src="/public/js/jquery.slimscroll.min.js"></script>
<script src="/public/js/typeahead.bundle.js"></script>
<script src="/public/js/jquery.sparkline.min.js"></script>  -->
<script type="text/javascript">
	$(function() {
		/* 页面加载进度条  */
		NProgress.start();
		$(document).ready(function() {
			NProgress.done();
		});
		$.fn.modal.Constructor.prototype.enforceFocus =function(){};
    	$("#logout").bind("click",function(){
    		 $.ajax({
          		   type: "POST",
          		   url:"/login/logout",
          		  /*  data: $("#login_form").serialize(), */
          		   success: function(data){
          			   if('success' == data.type){
          				   Lobibox.notify("success", {
          						size: 'mini',
          		        		position: 'center top',
          		        		/* title: '登陆提示', */
          		        		msg: data.message
          		        	});
          				 setTimeout(window.location.href="/login/index", 3000);
          			   }else{
          				   Lobibox.notify("error", {
          					 	size: 'mini',
          		        		position: 'center top',
          		        		/* title: '登陆提示', */
          		        		msg: data.message
          		        	});
          			   }
          		   }
          		});
    	});
    });
	</script>

<style type="text/css">
.select2-search{
	font-size: 16px;
	left:-12px;
}
.ui-icon{
	margin-top:0.5em;
	position: static;
}
.nav-list > li{
	border-top:1px solid #fcfcfc;
	border-bottom:1px solid #E5E5E5;
}
</style>