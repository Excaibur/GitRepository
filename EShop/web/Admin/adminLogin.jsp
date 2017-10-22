<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
<title>电子商城系统--后台管理</title>
<link href="../CSS/stylesheet.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="../js/login.js"></script>
	<script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
</head>
<body>
<form id="login" action="/login?action=admin" method="post" >
	<table width="551" height="350" border="0" align="center" cellpadding="0" cellspacing="0" background="../images/login_back.jpg">
	  <tr>
		<td height="203">&nbsp;</td>
	  </tr>
	  <tr>
		<td><table width="80%"  border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
			<td width="50" class="blackTitle">
				帐 号：
			</td>
			<td>
				<input type="text" name="loginName" size="15" styleClass="textBox"/>
			</td>
			<td width="50" class="blackTitle">
				密 码：
			</td>
			<td>
				<input type="password" name="loginPwd" size="15" styleClass="textBox"/>
			</td>
              <td width="80">
                  <div align="right">
                      <img src="../images/login_button.jpg" border="0" onclick="login()">
                  </div>
              </td>
		  </tr>
		</table></td>
	  </tr>
	</table>
</form>
</body>
</html>
