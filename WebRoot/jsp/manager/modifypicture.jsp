<%@ page  pageEncoding="utf-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">

	
	<script type="text/javascript">                                           
			$(function() {
				$("#upfm").form("load","${pageContext.request.contextPath }/picture/selectById?id="+uid);
				
				$("#adddialog").linkbutton({
					onClick:function(){
						$("#upfm").form("submit",{
							url:"${pageContext.request.contextPath }/picture/modifypicture",
							//表单提交前的操作，如果返回false则停止提交
							onSubmit:function(){
								return $("#upfm").form("validate");							
							},
							//表单提交成功后的操作
							success:function(){
								$("#modifypicture").dialog("close"),
								$.messager.show({
	    							title:"系 统 提 示",
	    							msg:"修改成功！"
	    						});
	    						$("#tb1").datagrid("load");
							}
						});
					}
				});
				
			});
		</script>
		<form id="upfm" action="${pageContext.request.contextPath }/picture/modifypicture" enctype="multipart/form-data" method="post">
			<input type="hidden" name="id"/>
			状态:激活<input type="radio" value="true" name="status"/>未激活<input type="radio" value="false" name="status"/><br/>
			图片描述:<input id="upip2" name="description"/><br/>
			<a href="javascript:void(0)" id="adddialog">修改</a>
		</form>
</html>