<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>欢迎使用电子商城系统</title>
<link href="CSS/stylesheet.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="js/def.js"></script>
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
            <c:if test="${!empty type}">
                <c:forEach items="${type}" var="type">
                    <option value="${type.typeid}">${type.typename}</option>
                </c:forEach>
            </c:if>
		</SELECT>
		<A href="javascript:selLike()"><IMG src="images/icon_search.gif" align="absMiddle" border="0"></A>
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
		  	<A href=""><span class="whiteTitle">购物车管理</span></A>
		  </TD>
          <TD background="images/Bule_06.gif" width="2"></TD>
          <TD class="header_menu" align="middle">
		  	<A href=""><span class="whiteTitle">订单管理</span></A>
		  </TD>
          <TD background="images/Bule_06.gif" width="2"></TD>
          <TD class="header_menu" align="middle">
		  	<A href="/lword?action=list"><span class="whiteTitle">顾客留言</span></A>
		  </TD>
          <TD background="images/Bule_06.gif" width="2"></TD>
          <TD class="header_menu" align="middle">
		  	<A href=""><span class="whiteTitle">修改用户资料</span></A>
		  </TD>
          <TD vAlign=top width=7><IMG src="images/icon07.gif"></TD>
        </TR>
    </TABLE></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>
		<table width="94%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
		  <tr>
			<td height="80" colspan="2"><IMG src="images/icon_goods.gif" width="150" height="29"></td>
		  </tr>
		  <tr>
			<td colspan="2"></td>
		  </tr>
		  <tr>
			<td height="40" colspan="2" align="left" class="text">
					<img src="${pBean.propic}" border="0" align="left">
				&nbsp;商品类别：${pBean.typename}<br>
				&nbsp;商品名称：${pBean.proname}<br>
				&nbsp;商品型号：${pBean.promodel}<br>
				&nbsp;市场价：￥${pBean.price}<br>
					&nbsp;特 价：￥${pBean.sprice}<br>
								
				&nbsp;生产厂家：${pBean.profactory}<br>
				&nbsp;出厂日期：${pBean.facdate}<br>
				&nbsp;商品描述：${pBean.prodesc}<br>
			</td>
		  </tr>
		  <tr>
			<td colspan="2" height="40" align="center">
				<a href="javascript:buy('${pBean.proid}','${mBean.memid}')"><img src="images/icon_buy.gif" border=0></a>
			</td>
		  </tr>
		</table>
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