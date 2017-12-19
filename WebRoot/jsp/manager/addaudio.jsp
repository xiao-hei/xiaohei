 <%@page  pageEncoding="utf-8"%>

	<script type="text/javascript">
			$(function() {
				$("#addcover").linkbutton({
					onClick:function(){
						$("#aufm").form("submit",{
							url:"${pageContext.request.contextPath }/audio/uploadaudio",
							//表单提交前的操作，如果返回false则停止提交
							onSubmit:function(){
								return $("#aufm").form("validate");	
							},
							//表单提交成功后的操作
							success:function(){
								$("#addAudio").dialog("close"),
								$.messager.show({
	    							title:"系 统 提 示",
	    							msg:"上传成功！"
	    						});
	    						$("#tb2").treegrid("load");
							}
						});
					}
				});
				
				$("#auip3").combobox({
					editable:false,
					url:"${pageContext.request.contextPath }/special/queryAll",
					valueField:"id",
					textField:"name",
					select:"1",
				});
			});
		</script>
		<form id="aufm" enctype="multipart/form-data" method="post">
			选择上传的文件:<input type="file" multiple accept="audio/mpeg" name="file1"/><br/>
			音频名:<input id="auip1" name="name"/><br/>
			播 音 者:<input id="auip2" name="author"/><br/>
			所属专辑:<textarea id="auip3" name="special_id"></textarea><br/>
			<a href="javascript:void(0)" id="addcover">上传</a>
		</form>
