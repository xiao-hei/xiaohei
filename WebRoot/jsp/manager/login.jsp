<%@ page  pageEncoding="utf-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理中心</title>
    
	<link rel="icon" href="${pageContext.request.contextPath}/back/img/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/common.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/login.css" type="text/css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/back/script/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/back/script/common.js"></script>
	<script type="text/javascript">
		$(function(){
			
			
			
		});
	</script>
</head>
<body>
	
		<div class="login">
			<form id="loginForm" action="${pageContext.request.contextPath}/manager/login" method="post" >
				
				<table>
					<tbody>
						<tr>
							<td width="190" rowspan="2" align="center" valign="bottom">
								<img src="${pageContext.request.contextPath}/back/img/header_logo.gif" alt="购物"/>
							</td>
							<th>
								用户名:
							</th>
							<td>
								<input type="text"  name="username" class="text" maxlength="20"/>
							</td>
					  </tr>
					  <tr>
							<th>
								密&nbsp;&nbsp;&nbsp;码:
							</th>
							<td>
								<input type="password" name="password" class="text" maxlength="20" autocomplete="off"/>
							</td>
					  </tr>
					
						<tr>
							<td>&nbsp;</td>
							<th>验证码:</th>
							<td>
								<input type="text" id="enCode" name="code" class="text captcha" maxlength="5" autocomplete="off"/>
								<img alt="验证码" id="img1" onclick="document.getElementById('img1').src = '${pageContext.request.contextPath }/manager/code?'+(new Date()).getTime()" src="${pageContext.request.contextPath }/manager/code"><br/>
							</td>
						</tr>					
			
					<tr>
						<td>&nbsp;</td>
						<th>&nbsp;</th>
						<td>
							<font style="color: red">${sessionScope.errologin }</font></br>
							<input type="button" class="homeButton" value="" onclick="location.href='/'"><input type="submit" class="loginButton" value="登录">
						</td>
					</tr>
				</tbody></table>
				<div class="powered">COPYRIGHT © 2005-2013.</div>
				<div class="link">
					<a href="">前台首页</a> |
					<a href="">官方网站</a> |
					<a href="">交流论坛</a> |
					<a href="">关于我们</a> |
					<a href="">联系我们</a> |
					<a href="">授权查询</a>
				</div>
			</form>
		</div>
</body>
</html>