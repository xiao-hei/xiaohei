<%@page pageEncoding="utf-8"%>

	<script type="text/javascript">
			$(function() {
				$("#adddialog").linkbutton({
					onClick:function(){
						$("#fm1").form("submit",{
							url:"${pageContext.request.contextPath }/picture/uploadpicture",
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
	    						$("#tb1").datagrid("load");
							}
						});
					}
					
				});
				
			});
		</script>
		<form id="fm1" action="${pageContext.request.contextPath }/picture/uploadpicture" enctype="multipart/form-data" method="post">
			选择上传的文件:<input type="file" multiple accept="image/png,image/jpeg" name="file1"/><br/>
			图片描述:<input id="ip1" name="description"/><br/>
			<a href="javascript:void(0)" id="adddialog">上传</a>
		</form>
