<%@ page contentType="text/html; charset=utf-8" %>

<html>
<head>
<title>欢迎使用电子商城系统</title>
<link href="CSS/stylesheet.css" rel="stylesheet" type="text/css">
</head>
<body class="body">
<table width="780" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" style="border:1px; border-style:solid; border-color:#888888">
  <tr>
    <td width="20">&nbsp;</td>
    <TD height="50" align="right" valign="bottom">
		<IMG src="images/icon_login.gif" align=absMiddle> 
		<INPUT id="qKey" name="qKey" value="商品关键字"> 
		<SELECT id="category" name="category">
			<option value="0">所有商品</option>
		</SELECT>
		<A href=""><IMG src="images/icon_search.gif" align="absMiddle" border="0"></A>    </TD>
    <td width="20">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>
	<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TR align="center">
          <TD valign="top" width="9"><IMG src="images/icon02.gif"></TD>
          <TD class="header_menu" align="middle">
		  	<A href="/def?action=load"><span class="whiteTitle">商城首页</span></A>		  </TD>
          <TD background="images/Bule_06.gif" width="2"></TD>
          <TD class="header_menu" align="middle">
		  	<A href="/chk?action=chk&name=cart"><span class="whiteTitle">购物车管理</span></A>		  </TD>
          <TD background="images/Bule_06.gif" width="2"></TD>
          <TD class="header_menu" align="middle">
		  	<A href="/chk?action=chk&name=order"><span class="whiteTitle">订单管理</span></A>		  </TD>
          <TD background="images/Bule_06.gif" width="2"></TD>
          <TD class="header_menu" align="middle">
		  	<A href="/lword?action=list"><span class="whiteTitle">顾客留言</span></A>		  </TD>
          <TD background="images/Bule_06.gif" width="2"></TD>
          <TD class="header_menu" align="middle">
		  	<A href="/chk?action=chk&name=modi"><span class="whiteTitle">修改用户资料</span></A>		  </TD>
          <TD vAlign=top width=7><IMG src="images/icon07.gif"></TD>
        </TR>
    </TABLE></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="60"><img src="images/Car_icon_04.gif"></td>
      </tr>
      <tr>
        <td><TABLE cellSpacing=0 cellPadding=0 border=0>
            <tr valign="center">
              <td><img hspace="5" src="images/Car_07.gif" /></td>
              <td class="C_Carbg_Default">查看购物车物品</td>
              <td><img height="39" src="images/Car_15.gif" width="1" /></td>
              <td align="middle"><img hspace="5" src="images/Car_09.gif" /></td>
              <td class="C_Carbg_Default">确认订单信息</td>
              <td><img height="39" src="images/Car_15.gif" width="1" /></td>
              <td align="middle"><img hspace="5" src="images/Car_11.gif" /></td>
              <td class="C_Carbg_Current">订单提交成功</td>
              <td><img height="39" src="images/Car_15.gif" width="1" /></td>
            </tr>
        </TABLE></td>
      </tr>
      <tr>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
            <tr bgcolor="#F7F3F7" class="text">
              <td width="240" rowspan="4" align="center" bgcolor="#FFFFFF"><IMG hspace=5  src="images/Car_icon_10.gif"></td>
              <td height="26">&nbsp;恭喜您，订单提交成功！</td>
            </tr>
            <tr bgcolor="#F7F3F7" class="text">
              <td height="26">&nbsp;订单编号：${oid}</td>
            </tr>
            <tr bgcolor="#F7F3F7" class="text">
              <td height="26">&nbsp;总金额：${total}</td>
            </tr>
            <tr bgcolor="#F7F3F7" class="text">
			  			
              <td height="26">&nbsp;下单日期：${date}</td>
            </tr>
        </table></td>
      </tr>
      <tr align="right">
        <td height="20">&nbsp;</td>
      </tr>
    </table></td>
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