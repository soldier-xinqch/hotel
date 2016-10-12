    $(function () {
    	//首先定义表格选项
    	var tabId = $("#attendanceSearch_grid");
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
    	                { title: "员工姓名",dataIndx:"staff_name", width: '5%', dataType: "Integer", align: "center",
    	                	filter: { type: 'textbox', condition: 'begin', listeners: ['change'] }
    	                },
    	        		{ title: "所属部门",dataIndx:"org_name", width: '6%', dataType: "string", align: "center",
    	        			filter: { type: 'textbox', condition: 'begin', listeners: ['change'] }
    	                },
    	        		{ title: "平常加班",dataIndx:"a_rest", width: '5%', dataType: "string", align: "center" },
    	        		{ title: "假日加班",dataIndx:"b_rest", width: '5%', dataType: "string", align: "center" },
    	        		{ title: "节日加班",dataIndx:"c_rest", width: '5%', dataType: "string", align: "center" },
    	        		{ title: "迟到",dataIndx:"b_rest", width: '2%', dataType: "string", align: "center" },
    	        		{ title: "早退",dataIndx:"e_rest", width: '2%', dataType: "string", align: "center" },
    	        		{ title: "倒休",dataIndx:"f_rest", width: '2%', dataType: "string", align: "center" },
    	        		{ title: "年假",dataIndx:"year_day", width: '2%', dataType: "string", align: "center" },
    	        		{ title: "事假",dataIndx:"g_rest", width: '2%', dataType: "string", align: "center" },
    	        		{ title: "病假",dataIndx:"h_rest", width: '2%', dataType: "string", align: "center" },
    	        		{ title: "探亲假",dataIndx:"i_rest", width: '4%', dataType: "string", align: "center" },
    	        		{ title: "工伤假",dataIndx:"j_rest", width: '4%', dataType: "string", align: "center" },
    	        		{ title: "产假",dataIndx:"k_rest", width: '2%', dataType: "string", align: "center" },
    	        		{ title: "婚假",dataIndx:"l_rest", width: '2%', dataType: "string", align: "center" },
    	        		{ title: "丧假",dataIndx:"m_rest", width: '2%', dataType: "string", align: "center" },
    	        		{ title: "旷工",dataIndx:"n_rest", width: '2%', dataType: "string", align: "center" },
    	        		{ title: "法定节日",dataIndx:"o_rest", width: '5%', dataType: "string", align: "center" },
    	        		{ title: "公出",dataIndx:"p_rest", width: '2%', dataType: "string", align: "center" },
    	        		{ title: "应上工时",dataIndx:"q_rest", width: '5%', dataType: "string", align: "center" },
    	        		{ title: "实上工时",dataIndx:"r_rest", width: '5%', dataType: "string", align: "center" },
    	        		{ title: "应上天数",dataIndx:"s_rest", width: '5%', dataType: "string", align: "center" },
    	        		{ title: "实上天数",dataIndx:"t_rest", width: '5%', dataType: "string", align: "center" },
    	        		{ title: "存休",dataIndx:"keep_day", width: '5%', dataType: "string", align: "center" }
    	        	];
    	var tab_toolbar = {
                items: [
                    {
						type:'button',
						label: '查询',
						listener: function(){
							this.option('filterModel.header', !this.option('filterModel.header'));
							this.refresh();
						},
						cls: 'btn btn-sm btn-danger'
					},
					{type: 'separator' },
                    {
                        type: 'button',
                        label: "导出",
                        cls:'btn btn-sm',
                        listeners: [{"click": function (evt) {
                        	tab_export();
                            }
                        }]
                    }
                ]
            };
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
        function tab_export() {
          	var delMsg = '<div class="dialog-cls"><form id="export_from" class="form-horizontal" role="form">'+$("#attendanceSearch_export_dialog").html()+'</form></div>';
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
    