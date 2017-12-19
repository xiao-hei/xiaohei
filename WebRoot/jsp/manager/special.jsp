<%@page pageEncoding="utf-8"%>
	
	<body>	
	<script type="text/javascript">
		var uid;
		$(function(){
			$("#tb2").treegrid({
    			fitColumns:true,
    			url:"${pageContext.request.contextPath }/special/showAll",
    			idField:"id",
    			treeField:"name",
    			animate:true,
				pagination:true,
    			pageSize:7,
    			pageList:[3,4,5,6,7],
    			pageNumber:1,
    			striped:true,
    			rownumber:true,
    			toolbar:"#treegridBtns",
    			pagePosition:"both"
    		});
				
			$("#addsp").linkbutton({
					iconCls:'icon-add',
					onClick:function(){
						$("#addSpecial").dialog("open");
					}
			});
			$("#addauo").linkbutton({
					iconCls:'icon-add',
					onClick:function(){
						$("#addAudio").dialog("open");
					}
			});
			$("#upauo").linkbutton({
					iconCls:'icon-edit',
					onClick:function(){
						var rows = $("#tb2").datagrid("getSelections");
						if(rows.length==1){
							uid = rows[0].id;
							$("#modifypicture").dialog("open");
						}else{
							alert("请选择一个");
						}
					}
			});
			$("#resp").linkbutton({
					iconCls:'icon-cancel',
					onClick:function(){
						// 获取选中的多行
	    				var rows = $("#tb2").treegrid("getSelections");
	    				var ids = new Array();
	    				var name = new Array();
	    				for(var i=0; i<rows.length; i++){
	    					ids[i] = rows[i].id;
	    					name[i] = rows[i].cover_name;
	    				}
	    				// 发ajax请求，实现 批量 （控制器需要数组 url?ids=10&ids=11）
	    				 $.get("${pageContext.request.contextPath}/special/deletespecial",
	    					"ids="+ids+"&name="+name,
	    					function(){
	    						$.messager.show({
	    							title:"系统提示",
	    							msg:"删除成功"
	    						});
	    						$("#tb2").treegrid("reload");
	    					},
	    					"text");
					}
			});
			$("#modifyAudio").dialog({
					title:"修改用户",
					width:500,
					height:300,
					closed:true,
					modal:true,
					href:"${pageContext.request.contextPath }/jsp/manager/modifypicture.jsp",
			});
			
			$("#addAudio").dialog({
				title:"创建专辑",
				width:500,
				height:300,
				closed:true,
				modal:true,
				href:"${pageContext.request.contextPath }/jsp/manager/addspecial.jsp",
			});
			$("#addAudio").dialog({
				title:"上传音频",
				width:500,
				height:300,
				closed:true,
				modal:true,
				href:"${pageContext.request.contextPath }/jsp/manager/addaudio.jsp",
			});
			
		});
		
		/* //自定义操作列
		function operation(val,row,idx){
			return "<a href='javascript:void(0)' onclick='deletepicture(\""+row.id+"\")'>删除</a>|<a href='javascript:void(0)' onclick='updatepicture(\""+row.id+"\")'>修改</a>";
		} */
		function coverpreview(val,row,idx){
			if(row.type == "1"){
				return "<img src='"+row.cover_url+"'style='width:100;heigth:80'/>";
			}else{
				return "<audio src='"+row.audio_url+"' controls></audio>"
			}
		}
		
		function deletepicture(id){
			$.get("${pageContext.request.contextPath}/special/delete",
				"id="+id,
				function(){
					//从新加载表格
					$("#tb2").datagrid("reload");
				});
		}
		function updatepicture(id){
			$.get("${pageContext.request.contextPath}/special/delete",
				"id="+id,
				function(){
					//从新加载表格
					$("#tb2").datagrid("reload");
				});
		}
		
	</script>
  	<table id="tb2">
  		<thead>
  			<tr>
  			  	<th data-options="field:'aa',checkbox:true"></th>
  				<th data-options="field:'aaa',width:100,formatter:coverpreview,align:'center',title:'封面预览'"></th>
  				<th data-options="field:'id',width:100,title:'专辑id'"></th>
  				<th data-options="field:'name',width:100,title:'专辑名字'"></th>
  				<th data-options="field:'cover_name',width:100,title:'封面图片名字'"></th>
  				<th data-options="field:'author',width:100,title:'播音者'"></th>
  				<th data-options="field:'cover_url',width:100,title:'封面路径'"></th>
  				<th data-options="field:'upload_time',width:100,title:'创建时间'"></th>
  				<th data-options="field:'synopsis',width:100,title:'专辑简介'"></th>
  				<!-- <th data-options="field:'xxx',width:100,formatter:operation,title:'操作'"></th> -->
  			</tr>
  		</thead>
  	</table>
	<div id="treegridBtns">
		<a href="javascript:void(0)" id="addsp">创建专辑</a>
		<a href="javascript:void(0)" id="addauo">上传音频</a>
		<a href="javascript:void(0)" id="resp">删除</a>
		<a href="javascript:void(0)" id="upauo">修改</a>
	</div>
	<div id="addSpecial"></div>
	<div id="addAudio"></div>
	<div id="modifyspecial"></div>
	</body>
