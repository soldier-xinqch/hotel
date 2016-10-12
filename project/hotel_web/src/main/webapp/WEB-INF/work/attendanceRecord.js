    $(function () {
    	//首先定义表格选项
    	var tabId = $("#attendanceRecord_grid");
    	var dataModel = {
    			 	cache: true,
//    	            recIndx: "customerid",
//    	            sortIndx: "contactname",
//    			 	sortDir: "up",
    	            location: "remote",
    	            sorting: "local",            
    	            dataType: "JSON",
    	            method: "GET",
    	            url: "pageData",
    	            getData: function (dataJSON) {  
    	                return { curPage: dataJSON.pageNo, totalRecords: dataJSON.total,data: dataJSON.list };
    	            }
    	        };
    	var colModel = [
    	                { title: "员工姓名",dataIndx:"staffName", width: '20%', dataType: "Integer", align: "center",
    	                	filter: { type: 'textbox', condition: 'begin', listeners: ['change'] }
    	                },
    	        		{ title: "所属部门",dataIndx:"orgName", width: '20%', dataType: "string", align: "center",
    	        			filter: { type: 'textbox', condition: 'begin', listeners: ['change'] }
    	                },
    	        		{ title: "工作日期",dataIndx:"workTime", width: '20%', dataType: "data", align: "center" ,
	    	                filter: { type: 'textbox', condition: "between", init: pqDatePicker, listeners: ['change'] },
	           			 render: function (ui) {
            	            var rowData=ui.rowData;
            	            return formatDateTime(rowData.workTime,"yyyy-MM-dd HH:mm:ss");
	           			 }
    	                },
    	        		{ title: "休息日期",dataIndx:"restTime", width: '20%', dataType: "data", align: "center" ,
    	                	 filter: { type: 'textbox', condition: "between", init: pqDatePicker, listeners: ['change'] },
    	        			 render: function (ui) {
    	         	            var rowData=ui.rowData;
    	                     return formatDateTime(rowData.restTime,"yyyy-MM-dd HH:mm:ss");
    	        			 }
    	        		},
    	        		{ title: "考勤类型",dataIndx:"attendanceTypeName", width: '10%', dataType: "string", align: "center" ,
    	        			filter: { type: 'textbox', condition: 'begin', listeners: ['change'] }	
    	        		},
    	        		{ title: "时间（小时）",dataIndx:"num", width: '9%', dataType: "string", align: "center"}
    	        	];
    	var tab_toolbar = {
                items: [
                    { type: 'button', label: '添加', listeners: [{ click: tab_addRow}], cls: 'btn btn-sm btn-success' },
                    { type: 'button', label: '修改', listeners: [{ click:tab_editRow}], cls: 'btn btn-sm btn-primary' },
                    { type: 'button', label: '删除', listeners: [{ click: tab_deleteRow}], cls: 'btn btn-sm btn-danger' },
                    {type: 'separator' },
                    {
						type:'button',
						label: '查询',
						listener: function(){
							this.option('filterModel.header', !this.option('filterModel.header'));
							this.refresh();
						},
						cls: 'btn btn-sm btn-danger'
					},
                    {
						type:'button',
						label: '重置',
						listener: function(){
							this.reset({filter: true});							
						},
						cls: 'btn btn-sm btn-danger'
                    },
                    {type: 'separator' },
                    {
						type:'button',
						label: '导出',
						listener: function(){
							tab_export();						
						},
						cls: 'btn btn-sm '
                    }
                ]
            };
    	function pqDatePicker(ui) {
    	    var $this = $(this);
    	    //.css({ zIndex: 3, position: "relative" })
    	    $this.datetimepicker({
    	    		language: 'zh-CN',
    	    		format: 'yyyy-mm-dd',
    	            yearRange: "-20:+0", //20 years prior to present.
    	            changeYear: true,
    	            changeMonth: true,
    	            autoclose:true,
    	            //showButtonPanel: true,
    	            onClose: function (evt, ui) {
    	                $(this).focus();
    	            }
    	        });
    	}
        var obj = { 
        	title:"菜单信息列表",
//			width: '100%',
			height: "100%",
//            minWidth: 500,
//            height: 400,
            flexHeight: true,
            showTitle:false,
			showButton:true,
			wrap:false,
			track: true,
			resizable: true,//调整网格的居中和垂直
			sortIndx: 0,//排序列或多列的dataIndx。它变成多列排序使用阵列时
            colModel:colModel,
            dataModel:dataModel,
            numberCell:{resizable:true, title: "#"},
            filterModel: { on: true, mode: "AND", header: false, type: 'local' },
            selectionModel: { mode: 'single' },
            selectionModel: { type: 'row' },//选中 row 为整行 cell 为整列 model 为单元格
            pageModel: { type: "remote", rPP: 10, rPPOptions: [10, 20, 50, 100] },//定义表尾分页参数 local为本地数据，remote 为远程数据
            scrollModel: { autoFit: false },
            collapsible: { on: true, collapsed: false },
            complete:function(event, ui){
            }
        };
        obj.columnTemplate = {            
            title: function (ui) {
            	ss();
            	alert(1);
                return ui.column.Title + " (" + ui.column.width + ")";
            }
        };
        obj.toolbar = tab_toolbar;
        var $grid = tabId.pqGrid(obj);
        $grid.pqGrid("option", $.paramquery.pqGrid.regional['zh']);            
        $grid.find(".pq-pager").pqPager("option", $.paramquery.pqPager.regional['zh']);   
        //load shipregion and shipvia dropdowns in first load event.   
        $("#sl_width").change(function (evt) {
            var val = $(this).val();
            $grid.pqGrid('option', 'width', val)
                    .pqGrid('refresh');
        });
        $("#sl_margin").change(function (evt) {
            var val = $(this).val();
            $grid.css('margin', val).pqGrid('refresh');
        });
        //edit Row
        function tab_editRow() {
            var rowIndx = getRowIndx();
            if (rowIndx != null) {
                var row = $grid.pqGrid('getRowData', {rowIndx: rowIndx});
                var updateDialogMsg = '<div"><form id="update_tab_from" class="form-horizontal" role="form">'+$("#attendanceRecord_dialog").html()+'</form></div>';
                bootbox.dialog({
                	title: "修改菜单", 
                	message:updateDialogMsg,
                	animate: true,
        	    	buttons: {
        	             success: {
        	                 label: "保存",
        	                 className: "btn btn-sm btn-success",
        	                 callback: function () {
         	                	 $.ajax({
        	                		   type: "POST",
        	                		   url: "modifyAttendance",
        	                		   data: $("#update_tab_from").serialize(),
        	                		   success: function(data){
        	                			   if('success' == data.type){
        	                				   Lobibox.notify("success", {
        	                		        		icon: false,
        	                		        		height:'300px',
        	                		        		title: '修改提示',
        	                		        		msg: '修改成功'
        	                		        	});
        	                				   $grid.pqGrid( "refreshDataAndView" )
        	                			   }else{
        	                				   Lobibox.notify("error", {
        	                		        		icon: false,
        	                		        		height:'300px',
        	                		        		title: '修改提示',
        	                		        		msg: '修改失败，请检查修改的数据'
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
        	    	},
//        	    	callback:setTimeout(updateMeuns(row),500)
                });
                updateMeuns(row);
                
            }
        }
        function updateMeuns(row){
        	$('#update_tab_from select[name=orgId]').find("option[value="+row.orgId+"-"+row.orgName+"]").attr("selected",true);
        	$('#update_tab_from select[name=staffId]').find("option[value="+row.staffId+"-"+row.staffName+"]").attr("selected",true);
        	$('#update_tab_from select[name=attendanceTypeId]').find("option[value="+row.attendanceTypeId+"-"+row.attendanceTypeName+"]").attr("selected",true);
        	
        	$('#update_tab_from select[name=staffId]').pqSelect({
        		checkbox: false, //adds checkbox to options   
        		width:'270px'
        	}).pqSelect('close');  
        	$('#update_tab_from select[name=orgId]').pqSelect({
        		checkbox: false, //adds checkbox to options   
        		width:'270px'
        	}).pqSelect('close');  
        	$('#update_tab_from select[name=attendanceTypeId]').pqSelect({
        		checkbox: false, //adds checkbox to options   
        		width:'270px'
        	}).pqSelect('close');  
        	$('#update_tab_from input[name=workTime]').val(formatDateTime(row.workTime,"yyyy-MM-dd HH:mm:ss"));
        	$('#update_tab_from input[name=restTime]').val(formatDateTime(row.restTime,"yyyy-MM-dd HH:mm:ss"));
        	$('#update_tab_from input[name=num]').val(row.num);
        	$('#update_tab_from input[name=id]').val(row.id);
        	$('.datepicker').datetimepicker({
    			language: 'zh-CN',
    			format: 'yyyy-mm-dd hh:ii:ss',
    	        autoclose: true,
    	        todayBtn: true,
    	        pickerPosition: "bottom-left"
//    	            startDate: "2013-02-14 10:00",
//    	            minuteStep: 10
        	});
        }
        //append Row
        function tab_addRow() {
            var $frm = $("form#crud-form");
            $frm.find("input").val("");
            var addMessage = '<div class="dialog-cls"><form id="add_tab_from" class="form-horizontal" role="form">'+$("#attendanceRecord_dialog").html()+'</form></div>';
            bootbox.dialog({ 
            	title: "新增菜单", 
            	message:addMessage,
            	animate: true,
    	    	buttons: {
    	             success: {
    	                 label: "保存",
    	                 className: "btn btn-sm btn-success",
    	                 callback: function () {
     	                	 $.ajax({
    	                		   type: "POST",
    	                		   url: "addAttendance",
    	                		   data: $("#add_tab_from").serialize(),
    	                		   success: function(data){
    	                			   if('success' == data.type){
    	                				   Lobibox.notify("success", {
    	                		        		icon: false,
    	                		        		height:'300px',
    	                		        		title: '新增提示',
    	                		        		msg: '添加成功'
    	                		        	});
    	                				   $grid.pqGrid( "refreshDataAndView" )
    	                			   }else{
    	                				   Lobibox.notify("error", {
    	                		        		icon: false,
    	                		        		height:'300px',
    	                		        		title: '新增提示',
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
    	    	},
    	    	callback:setTimeout(selectInput,0)
            });
            $("#popup-dialog-crud").dialog("open");
        }
        function selectInput(){
        	$('#add_tab_from .row').find(" .form-group div").find("select").pqSelect({ 
        		selectClsType:'',
        		deselect: false,
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
        function formatRepoProvince(repo) {
            if (repo.loading) return repo.text;
            var markup = "<div>"+repo.name+"</div>";
            return markup;
        }
        //delete Row.
        function tab_deleteRow() {
            var rowIndx = getRowIndx();
            if (rowIndx != null) {
            	var row = $grid.pqGrid('getRowData', {rowIndx: rowIndx});
            	var delMsg = '<form id="del_tab_from" class="form-horizontal" role="form"><input type="hidden" class="form-control input-xlarge" name="id" value="'+row.id+'"></form>';
            	bootbox.dialog({
            		  message: "您确定要删除这条信息么"+delMsg,
            		  title: "删除基础信息",
            		  buttons: {
            		    success: {
            		      label: "确定",
            		      className: "btn btn-sm btn-success",
            		      callback: function() {
            		    	 var add_tab_data=$("#del_tab_from input[type=hidden]").map(function(){
       	                		  return ($(this).attr("name")+'='+$(this).val());
       	                		}).get().join("&") ;
            		    	  $.ajax({
    	                		   type: "POST",
    	                		   url: "deleteAttendance",
    	                		   data: add_tab_data,
    	                		   success: function(data){
    	                			   if('success' == data.type){
    	                				   $grid.pqGrid("deleteRow", { rowIndx: rowIndx });
    	                	               $grid.pqGrid("setSelection", { rowIndx: rowIndx });
    	                				   Lobibox.notify("success", {
    	                		        		icon: false,
    	                		        		height:'300px',
    	                		        		title: '删除提示',
    	                		        		msg: '删除成功'
    	                		        	});
    	                			   }else{
    	                				   Lobibox.notify("error", {
    	                		        		icon: false,
    	                		        		height:'300px',
    	                		        		title: '删除提示',
    	                		        		msg: '删除失败，请检查删除的数据'
    	                		        	});
    	                			   }
    	                			   $grid.pqGrid( "refreshDataAndView" )
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
            		});
            }
        }
        function getRowIndx() {
            var arr = $grid.pqGrid("selection", { type: 'row', method: 'getSelection' });
            if (arr && arr.length > 0) {
                return arr[0].rowIndx;                                
            }
            else {
            	Lobibox.notify("info", {
            		//size: 'mini',
            		icon: false,
            		//width:'',
            		height:'300px',
            		title: '点击提示',
            		msg: '请选择一行数据'
            		});
                return null;
            }
        }
        function tab_export() {
          	var delMsg = '<div class="dialog-cls"><form id="export_from" class="form-horizontal" role="form">'+$("#attendanceRecord_export_dialog").html()+'</form></div>';
          	bootbox.dialog({
          		  message: delMsg,
          		  title: "导出列表",
          		  buttons: {
          		    success: {
          		      label: "导出",
          		      className: "btn btn-sm btn-success",
          		      callback: function() {
          		    	  window.open("exportExcel?"+$("#export_from").serialize());
          		      }
          		    },
          		    canle:{
     	            	 label: "取消",
     	                 className: "btn btn-sm btn-grey",
     	                 callback: function () {
     	                 }
          		    }
          		  }
          		});
          	$('#export_from .row').find(" .form-group div").find("select").pqSelect({ 
          		selectallText:'全选',
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
    