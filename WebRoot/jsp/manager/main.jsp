<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="util.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>驰名法洲首页</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/back/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/back/themes/icon.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath }/back/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/back/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		function selectTab(title, iconCls, href) {
			//tabs 组件 添加
			//判断title所对应的tab是否存在  如果存在 则选中 否则添加
			if ($('#tt').tabs('exists', title)) {
				//选中
				$('#tt').tabs('select', title);
			} else {
	
				$('#tt').tabs('add', {
					title : title,
					iconCls:iconCls,
					href:"${pageContext.request.contextPath}/jsp/manager/"+href,
					selected : true,
					closable:true
				});
			}
		}
	
		$(function() {
			$.post(
			"${pageContext.request.contextPath}/menu/menujson",
			function(data){
			//遍历获得一级菜单  遍历data集合  index下标   obj循环变量
			$.each(data,function(index,obj){
			var content = "";
			//遍历obj.list 获得content   i： obj.list 下标   o：obj.list 的循环变量 
			$.each(obj.children,function(i,o){
				//content  linkbutton		selectTab(萝卜兔)						  iconCls:"icon-ok"
				content += "<a onclick='selectTab(\""+o.name+"\",\""+o.iconCls+"\",\""+o.href+"\")' class='easyui-linkbutton' data-options='iconCls:\""+o.iconCls+"\"' style='width:100%'>"+o.name+"</a>";
			})
			$('#aa').accordion('add', {
				title : obj.name,
				content : content,
				selected : false
			});
			});
			
			},
			"json"
			);
			
	
		});
	</script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:true,height:100" style="background-color:#5C160C">
		<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">持名法州后台管理系统</div>
		<div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">
			欢迎您:${sessionScope.successlogin.username } &nbsp;<a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="#"class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a>
		</div>
	</div>

	<div data-options="region:'south',split:true,height:100" style="background:#5C160C">
		<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育</div>
	</div>
                      
	<div data-options="region:'west',title:'导航菜单',split:true,width:100" style="width:180px">
		<div id="aa" class="easyui-accordion" data-options="fit:true"></div>
	</div>

	<div data-options="region:'center'">
		<div id="tt" class="easyui-tabs"
			data-options="fit:true,narrow:true,pill:true">
			<div title="主页" data-options="iconCls:'icon-neighbourhood',"
				style="background-image:url(${pageContext.request.contextPath }/back/img/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
		</div>
	</div>
</body>
</html>