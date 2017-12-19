<%@ page  pageEncoding="utf-8"%>
	
<html>
	<body>	
	<script type="text/javascript">
		var uid;
		$(function(){
			$("#tb1").datagrid({
    			fitColumns:true,
    			url:"${pageContext.request.contextPath }/picture/showAll",
				pagination:true,
    			pageSize:6,
    			pageList:[3,4,5,6,7],
    			pageNumber:1,
    			striped:true,
    			toolbar:"#datagridBtns",
    			pagePosition:"both"
    		});
				
			$("#addp").linkbutton({
					iconCls:'icon-add',
					onClick:function(){
						$("#addpicture").dialog("open");
					}
			});
			$("#updp").linkbutton({
					iconCls:'icon-edit',
					onClick:function(){
						var rows = $("#tb1").datagrid("getSelections");
						if(rows.length==1){
							uid = rows[0].id;
							$("#modifypicture").dialog("open");
						}else{
							alert("请选择一个");
						}
					}
			});
			$("#redp").linkbutton({
					iconCls:'icon-cancel',
					onClick:function(){
						// 获取选中的多行
	    				var rows = $("#tb1").datagrid("getSelections");
	    				var ids = new Array();
	    				var name = new Array();
	    				for(var i=0; i<rows.length; i++){
	    					ids[i] = rows[i].id;
	    					name[i] = rows[i].name;
	    				}
	    				// 发ajax请求，实现 批量 （控制器需要数组 url?ids=10&ids=11）
	    				 $.get("${pageContext.request.contextPath}/picture/deletepicture",
	    					"ids="+ids+"&name="+name,
	    					function(){
	    						$.messager.show({
	    							title:"系统提示",
	    							msg:"删除成功"
	    						});
	    						$("#tb1").datagrid("reload");
	    					},
	    					"text");
					}
			});
			$("#modifypicture").dialog({
					title:"修改用户",
					width:500,
					height:300,
					closed:true,
					modal:true,
					href:"${pageContext.request.contextPath }/jsp/manager/modifypicture.jsp",
			});
			
			$("#addpicture").dialog({
				title:"上传图片",
				width:500,
				height:300,
				closed:true,
				modal:true,
				href:"${pageContext.request.contextPath }/jsp/manager/addpicture.jsp",
			});
			
		});
		
		/* //自定义操作列
		function operation(val,row,idx){
			return "<a href='javascript:void(0)' onclick='deletepicture(\""+row.id+"\")'>删除</a>|<a href='javascript:void(0)' onclick='updatepicture(\""+row.id+"\")'>修改</a>";
		} */
		function imgpreview(val,row,idx){
			return "<img src='"+row.url+"'style='width:100;heigth:80'/>";
		}
		
		function deletepicture(id){
			$.get("${pageContext.request.contextPath}/picture/delete",
				"id="+id,
				function(){
					//从新加载表格
					$("#tb1").datagrid("reload");
				});
		}
		function updatepicture(id){
			$.get("${pageContext.request.contextPath}/picture/delete",
				"id="+id,
				function(){
					//从新加载表格
					$("#tb1").datagrid("reload");
				});
		}
		
	</script>
  	<table id="tb1">
  		<thead>
  			<tr>
  				<th data-options="field:'aa',checkbox:true"></th>
  				<th data-options="field:'aaa',width:100,formatter:imgpreview,align:'center',title:'图片预览'"></th>
  				<th data-options="field:'id',width:100,title:'图片id'"></th>
  				<th data-options="field:'name',width:100,title:'图片名字'"></th>
  				<th data-options="field:'url',width:100,title:'图片路径'"></th>
  				<th data-options="field:'status',width:100,title:'图片状态'"></th>
  				<th data-options="field:'upload_time',width:100,title:'上传时间'"></th>
  				<th data-options="field:'description',width:100,title:'图片描述'"></th>
  				<!-- <th data-options="field:'xxx',width:100,formatter:operation,title:'操作'"></th> -->
  			</tr>
  		</thead>
  	</table>
	<div id="datagridBtns">
		<a href="javascript:void(0)" id="addp">上传图片</a>
		<a href="javascript:void(0)" id="redp">删除图片</a>
		<a href="javascript:void(0)" id="updp">修改图片</a>
	</div>
	<div id="addpicture"></div>
	<div id="modifypicture"></div>
	</body>
</html>