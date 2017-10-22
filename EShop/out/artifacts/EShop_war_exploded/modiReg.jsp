<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
<title>欢迎使用电子商城系统</title>
<link href="CSS/stylesheet.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
  <script type="text/javascript" src="../js/jquery-validate.js"></script>
  <script type="text/javascript" src="js/memreg.js"></script>
</head>
<body class="body">
<table width="780" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" style="border:1px; border-style:solid; border-color:#888888">
  <tr>
    <td width="20">&nbsp;</td>
    <TD height="50" align="right" valign="bottom">
		<IMG src="images/icon_login.gif" align=absMiddle> 
		<INPUT id="qKey" name="qKey" value="商品关键字" "> 
		<SELECT id="category" name="category">
			<option value="0">所有商品</option>
		</SELECT>
		<A href=""><IMG src="images/icon_search.gif" align="absMiddle" border="0"></A>
    </TD>
    <td width="20">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>
	<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TR align="center">
          <TD valign="top" width="9"><IMG src="images/icon02.gif"></TD>
          <TD class="header_menu" align="middle">
		  	<A href="/def?action=load"><span class="whiteTitle">商城首页</span></A>
		  </TD>
          <TD background="images/Bule_06.gif" width="2"></TD>
          <TD class="header_menu" align="middle">
		  	<A href="/chk?action=chk&name=cart"><span class="whiteTitle">购物车管理</span></A>
		  </TD>
          <TD background="images/Bule_06.gif" width="2"></TD>
          <TD class="header_menu" align="middle">
		  	<A href="/chk?action=chk&name=order"><span class="whiteTitle">订单管理</span></A>
		  </TD>
          <TD background="images/Bule_06.gif" width="2"></TD>
          <TD class="header_menu" align="middle">
		  	<A href="/lword?action=list"><span class="whiteTitle">顾客留言</span></A>
		  </TD>
          <TD background="images/Bule_06.gif" width="2"></TD>
          <TD class="header_menu" align="middle">
		  	<A href="/chk?action=chk&name=modi"><span class="whiteTitle">修改用户资料</span></A>
		  </TD>
          <TD vAlign=top width=7><IMG src="images/icon07.gif"></TD>
        </TR>
    </TABLE></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>
	<form id="regform" action="/mem?action=update&id=${mBean.memid}" method="post" style="margin:0px;" >
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
      <tr>
        <td height="30" colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td height="40" colspan="2"><img src="images/EditUser_01.gif" /></td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right">会员级别
          ：</td>
        <td height="26" class="text">${mBean.lvname}</td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right">真实姓名
          ：</td>
        <td height="26"><input type="text" name="memberName" size="30" class="textBox" value="${mBean.memname}" /></td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right">登录帐号
          ：</td>
        <td height="26"><input type="text" name="loginName" size="30" class="textBox" value="${mBean.memlogin}" onblur="chkLoginName()"/></td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right">登录密码
          ：</td>
        <td height="26"><input type="password" name="loginPwd" id="loginPwd" size="30" class="textBox"/></td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right">核对密码
          ：</td>
        <td height="26"><input name="reLoginPwd" type="password" class="textBox" id="reLoginPwd"  size="30"/></td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right">联系电话
          ：</td>
        <td height="26"><input type="text" name="phone" size="30" class="textBox" value="${mBean.memphone}" /></td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right">联系地址
          ：</td>
        <td height="26"><input type="text" name="address" size="30" class="textBox" value="${mBean.memaddress}" /></td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right">邮政编码
          ：</td>
        <td height="26"><input type="text" name="zip" size="30" class="textBox" value="${mBean.memzip}" /></td>
      </tr>
      <tr bgcolor="#F7F3F7">
        <td width="260" height="26" class="text" align="right">电子邮箱
          ：</td>
        <td height="26"><input type="text" name="email" size="30" class="textBox" value="${mBean.email}" /></td>
      </tr>
      <tr>
        <td height="40" colspan="2" align="center">
			<input class="C_Input" type="submit" value="保存" onclick="chkReg()"/>
	   </td>
      </tr>
    </table>
	</form> 
	</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td height="30">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td height="30" bgcolor="#4282CE" class="whiteText" align="center">
		本电子商城系统仅供学习交流使用，未经授权严禁用于商业用途！！	</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td height="20">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>