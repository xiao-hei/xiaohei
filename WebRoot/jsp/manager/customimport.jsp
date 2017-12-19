<%@page pageEncoding="utf-8"%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/back/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/back/themes/icon.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath }/back/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/back/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
			$(function() {
				$("#dd").combotree({
					checkbox:true,
					animate:true,
					multiple:true,
					data:[{
						text:"自定义导出",
						iconCls:"icon-edit",
						children:[{
							id:"id",
							text:"编号"
						},{
							id:"name",
							text:"用户名"
						},{
							id:"birthday",
							text:"生日"
						}],
					}]
				});
			
				$("#adddialog").linkbutton({
					onClick:function(){
						$("#fm1").form("submit",{
							url:"${pageContext.request.contextPath }/poi/import",
							//表单提交前的操作，如果返回false则停止提交
							onSubmit:function(){
								return $("#fm1").form("validate");			
							},
							//表单提交成功后的操作
							success:function(){
								$("#addpicture").dialog("close"),
								$.messager.show({
	    							title:"系 统 提 示",
	    							msg:"上传成功！"
	    						});
							}
						});
					}
				});
			});
		</script>
		<form id="fm1" action="${pageContext.request.contextPath }/poi/import" enctype="multipart/form-data" method="post">
			选择上传的文件:<input id="dd"/><br/>
			<a href="javascript:void(0)" id="adddialog">上传</a>
		</form>
