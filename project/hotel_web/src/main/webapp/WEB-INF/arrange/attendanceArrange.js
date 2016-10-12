$(document).ready(function() {
	$("#select2").pqSelect({    
	    deselect: false
	}).pqSelect( 'close' );
    $('#calendar').fullCalendar({
    	locale: 'zh-cn',
    	weekends: true,
    	//日历高度  
        height: $(window).height(),
    	//16:9 1.7  4:3  1.3
        aspectRatio:1.7,
        handleWindowResize:true,
        timezone: 'Asia/Shanghai',//设置时区
        //theme: true,//true 时日历主题可随 jQuery ui 的主题变化
    	header:{
    		left:'prev,next today',
    		center: 'title',
    	    right:  'month,agendaWeek,agendaDay,list'
    	},
    	buttonText:{
    		prev:'上个月',
    		next:'下个月',
    		list:'排班'
    	},
    	//日历上显示全天的文本  
        allDayText: '全天',  
        //允许用户通过单击或拖动选择日历中的对象，包括天和时间。  
        selectable: true,  
        //当点击或拖动选择时间时，显示默认加载的提示信息，该属性只在周/天视图里可用。  
        selectHelper: true,  
        //当点击页面日历以外的位置时，自动取消当前的选中状态。  
        unselectAuto: true,  
        //用来设置日历中的日程是否可以编辑.  可编辑是指可以移动, 改变大小等.
        editable:true,
        //所有的event可以拖动, 必须editable = true
        disableDragging:true,
        //diableResizing: Boolean, 默认false, 所有的event可以改变大小, 必须editable = true
        diableResizing:true,
        //dragRevertDuration: 拖动恢复的时间, 默认500毫秒, 表示一个不成功的拖动之后, 控件回复到原始位置的时间.
        dragRevertDuration:500,
        eventStartEditable:true,
    	/* views: {
            agendaFourDay: {
                type: 'agenda',
                duration: { days: 4 },
                buttonText: '4 day'
            }
        }, */
    	dayClick: add,
//    	events: {
//    		url: '/myfeed.php',
//    		type: 'POST',
//    		data: {
//    			custom_param1: 'something',
//    			custom_param2: 'somethingelse'
//    		},
//    		error: function() {
//    			alert('there was an error while fetching events!');
//    		},
//    		color: 'yellow', // a non-ajax option
//    		textColor: 'black' // a non-ajax option
//    	},
//    	id
//    	allDay
//    	url
//    	className
//    	editable
//    	startEditable
//    	durationEditable
//    	source
//    	color
//    	backgroundColor
//    	borderColor
//    	textColor
    	
    	events:function(start, end, timezone, callback) {//ajax请求数据并显示在响应时间段内
    		$.ajax({
    	 		   type: "GET",
    	 		   url: "pageData",
    	 		   data: $("#schedule_from").serialize(),
    	 		   success: function(data){
    	 			   if('success' == data.type){
    	 				  var datas = data.list;
    	 				  var events = [];
    	 				  if(datas.length>0){
    	 					 for (var i = 0; i < datas.length; i++) {
    	 						 var id = datas[i].id;
    	 	                     var title = datas[i].orgName+datas[i].scheduleMsg;
    	 	                     var evtstart = datas[i].startTime;
    	 	                     var evtend = datas[i].endTime;
    	 	                     events.push({
    	 	                    	 id:id,
    	 	                         title:title,
    	 	                         start:moment(evtstart).format("YYYY-MM-DD HH:mm:ss"),
    	 	                         end:moment(evtend).format("YYYY-MM-DD HH:mm:ss"),
    	 	                     });
    	 					 }
    	 					callback(events);
    	 				  }
    	 			   }else{
//    	 				   Lobibox.notify("error", {
//    	 		        		icon: false,
//    	 		        		height:'300px',
//    	 		        		title: '加载提示',
//    	 		        		msg: '加载失败，请检查添加的数据'
//    	 		        	});
    	 			   }
    	 		   }
    	 		});
    	},
//    	events: [
//				{
//				id:'123456',	
//				title: 'All Day Event',
//				start: new Date(),
//				color:'black',
//				borderColor:'blue',
//				editable:true,
//				allDay:false
//				},
//				{
//				title: 'Long Event',
//				start: new Date(),
//				url:'www.baidu.com',
//				end: new Date(new Date().getTime()+86400000)
//				}
//		],
    	eventClick : function(event, jsEvent, view) {
//    		alert(event.id);
//    		alert(event.title);
//    		alert(event.start);
//    		alert(event.end);
    		scheduleModifyDialog(event);
    	}
	});
    $('#calendar').fullCalendar('option', 'timezone','Asia/Shanghai');
    

    function add(date, allDay, jsEvent, view) {
    	// 此處可以進行彈窗、後台通信等處理
    	// 本例僅在日曆中添加一個新日程
//    	$('#calendar').fullCalendar('renderEvent',
//	    	{
//	    	title: '日程' + new Date().getTime(),
//	    	start: new Date(),
//	    	allDay: true
//	    	});
    	scheduleDialog(jsEvent.id);
    }
    
    function scheduleDialog() {
        var addMessage = '<div class="dialog-cls"><form id="schedule_from" class="form-horizontal" role="form">'+$("#schedule_dialog").html()+'</form></div>';
        bootbox.dialog({ 
        	title: "排班安排", 
        	message:addMessage,
        	animate: true,
	    	buttons: {
	             success: {
	                 label: "保存",
	                 className: "btn btn-sm btn-success",
	                 callback: function () {
	                	//部门名称 班次
	                	var orgName = $("#schedule_from").find("select[name='orgId'] option:selected").text();
	                	var scheduleMsg = $("#schedule_from").find("select[name='scheduleMsg'] option:selected").text();
	                	var startTime = $("#schedule_from input[name=startTime]").val();
	                	var endTime = $("#schedule_from input[name=endTime]").val();
 	                	 $.ajax({
	                		   type: "POST",
	                		   url: "addSchedule",
	                		   data: $("#schedule_from").serialize(),
	                		   success: function(data){
	                			   if('success' == data.type){
	                				   Lobibox.notify("success", {
	                		        		icon: false,
	                		        		height:'300px',
	                		        		title: '添加提示',
	                		        		msg: '添加成功'
	                		        	});
	                				   $('#calendar').fullCalendar('renderEvent',{
		           					    	title:orgName+'-' +scheduleMsg,
		           					    	start:moment(startTime).format("YYYY-MM-DD HH:mm:ss"),
		           					    	end:moment(endTime).format("YYYY-MM-DD HH:mm:ss"),
	//	           					    	color:'blue'
	           				    		});
	                				   $('#calendar').fullCalendar('refetchEvents'); 
	                			   }else{
	                				   Lobibox.notify("error", {
	                		        		icon: false,
	                		        		height:'300px',
	                		        		title: '添加提示',
	                		        		msg: '添加失败，请检查添加的数据'
	                		        	});
	                			   }
	                		   }
	                		});
	                 }
	             },
	             canle:{
	            	 label: "取消",
	                 className: "btn btn-sm btn-grey",
	                 callback: function () {
	                	 
	                 }
	             }
	    	}
//	    	callback:setTimeout(selectInput,200)
        });
        $('#schedule_from .row').find(" .form-group div").find("select").pqSelect({ 
    		checkbox: true,
    		width:'270px'
    	});
        $('.datepicker').datetimepicker({
			language: 'zh-CN',
			format: 'yyyy-mm-dd hh:ii:ss',
	        autoclose: true,
	        todayBtn: true,
	        pickerPosition: "bottom-left"
        });
    }
    
    function scheduleModifyDialog(event) {
        var addMessage = '<div class="dialog-cls"><form id="schedule_from" class="form-horizontal" role="form">'+$("#schedule_dialog").html()+'</form></div>';
        bootbox.dialog({ 
        	title: "排班安排", 
        	message:addMessage,
        	animate: true,
	    	buttons: {
	             success: {
	                 label: "保存",
	                 className: "btn btn-sm btn-success",
	                 callback: function () {
	                	//部门名称 班次
	                	var orgName = $("#schedule_from").find("select[name='orgId'] option:selected").text();
	                	var scheduleMsg = $("#schedule_from").find("select[name='scheduleMsg'] option:selected").text();
	                	var startTime = $("#schedule_from input[name=startTime]").val();
	                	var endTime = $("#schedule_from input[name=endTime]").val();
 	                	 $.ajax({
	                		   type: "POST",
	                		   url: "modifySchedule",
	                		   data: $("#schedule_from").serialize(),
	                		   success: function(data){
	                			   if('success' == data.type){
	                				   Lobibox.notify("success", {
	                		        		icon: false,
	                		        		height:'300px',
	                		        		title: '添加提示',
	                		        		msg: '添加成功'
	                		        	});
	                				   $('#calendar').fullCalendar('refetchEvents');
	                			   }else{
	                				   Lobibox.notify("error", {
	                		        		icon: false,
	                		        		height:'300px',
	                		        		title: '添加提示',
	                		        		msg: '添加失败，请检查添加的数据'
	                		        	});
	                			   }
	                		   }
	                		});
	                 }
	             },
	             confirm: {
	                 label: '删除',
	                 className: 'btn btn-sm btn-danger ',
	                 callback: function () {
	                	 $.ajax({
	                		   type: "POST",
	                		   url: "deleteSchedule",
	                		   data: 'id='+event.id,
	                		   success: function(data){
	                			   if('success' == data.type){
	                				   Lobibox.notify("success", {
	                		        		icon: false,
	                		        		height:'300px',
	                		        		title: '添加提示',
	                		        		msg: '添加成功'
	                		        	});
	                				   $('#calendar').fullCalendar('removeEvents',event.id); 
	                				   $('#calendar').fullCalendar('refetchEvents');
	                			   }else{
	                				   Lobibox.notify("error", {
	                		        		icon: false,
	                		        		height:'300px',
	                		        		title: '添加提示',
	                		        		msg: '添加失败，请检查添加的数据'
	                		        	});
	                			   }
	                		   }
	                		});
	                	 
	                 }
	             },
	             canle:{
	            	 label: "取消",
	                 className: "btn btn-sm btn-grey",
	                 callback: function () {
	                	 
	                 }
	             }
	    	}
//	    	callback:setTimeout(selectInput,200)
        });
      	$("#schedule_from").find("input[name='id']").val(event.id);
        $('#schedule_from .row').find(" .form-group div").find("select").pqSelect({ 
    		checkbox: true,
    		width:'270px'
    	});
        $('.datepicker').datetimepicker({
			language: 'zh-CN',
			format: 'yyyy-mm-dd hh:ii:ss',
	        autoclose: true,
	        todayBtn: true,
	        pickerPosition: "bottom-left"
        });
    }
});