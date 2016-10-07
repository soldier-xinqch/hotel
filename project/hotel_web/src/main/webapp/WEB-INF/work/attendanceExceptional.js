    $(function () {
    	//首先定义表格选项
    	var tabId = $("#attendanceExceptional_grid");
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
    	                { title: "员工编号",dataIndx:"staffNo", width: '10%', dataType: "Integer", align: "center",
    	                	filter: { type: 'textbox', condition: 'begin', listeners: ['change'] }
    	                },
    	                { title: "员工姓名",dataIndx:"staffName", width: '11%', dataType: "Integer", align: "center",
    	                	filter: { type: 'textbox', condition: 'begin', listeners: ['change'] }
    	                },
    	        		{ title: "所属部门",dataIndx:"orgName", width: '11%', dataType: "string", align: "center",
    	        			filter: { type: 'textbox', condition: 'begin', listeners: ['change'] }
    	                },
    	        		{ title: "考勤日期",dataIndx:"time", width: '11%', dataType: "date", align: "center" },
    	        		{ title: "异常类型",dataIndx:"time", width: '11%', dataType: "string", align: "center" },
    	        		{ title: "班次",dataIndx:"time", width: '11%', dataType: "string", align: "center" },
    	        		{ title: "班次类型",dataIndx:"time", width: '11%', dataType: "string", align: "center" },
    	        		{ title: "刷卡1",dataIndx:"brush1", width: '7%', dataType: "string", align: "center" },
    	        		{ title: "刷卡2",dataIndx:"brush2", width: '7%', dataType: "string", align: "center" },
    	        		{ title: "刷卡3",dataIndx:"brush3", width: '7%', dataType: "string", align: "center" },
    	        		{ title: "刷卡4",dataIndx:"brush4", width: '7%', dataType: "string", align: "center" },
    	        		{ title: "刷卡5",dataIndx:"brush5", width: '7%', dataType: "string", align: "center" },
    	        		{ title: "刷卡6",dataIndx:"brush6", width: '7%', dataType: "string", align: "center" },
    	        		{ title: "刷卡7",dataIndx:"brush7", width: '7%', dataType: "string", align: "center" },
    	        		{ title: "刷卡8",dataIndx:"brush8", width: '7%', dataType: "string", align: "center" }
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
                        	 window.open("exportWorkType");
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
    });    
    