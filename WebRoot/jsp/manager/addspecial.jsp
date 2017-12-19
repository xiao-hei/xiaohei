<%@page  pageEncoding="utf-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">

	
	<script type="text/javascript">
			$(function() {
				$("#addcover").linkbutton({
					onClick:function(){
						$("#cofm").form("submit",{
							url:"${pageContext.request.contextPath }/special/uploadspecial",
							//表单提交前的操作，如果返回false则停止提交
							onSubmit:function(){
								return $("#cofm").form("validate");	
							},
							//表单提交成功后的操作
							success:function(){
								$("#addSpecial").dialog("close"),
								$.messager.show({
	    							title:"系 统 提 示",
	    							msg:"上传成功！"
	    						});
	    						$("#tb2").treegrid("load");
							}
						});
					}
				});
				
			});
		</script>
		<form id="cofm" enctype="multipart/form-data" method="post">
			选择上传的文件:<input type="file" multiple accept="image/png,image/jpeg" name="file1"/><br/>
			专辑名:<input id="couerip1" name="name"/><br/>
			播 音 者:<input id="couerip1" name="author"/><br/>
			图片描述:<input id="couerip2" name="synopsis"/><br/>
			<a href="javascript:void(0)" id="addcover">上传</a>
		</form>
</html>