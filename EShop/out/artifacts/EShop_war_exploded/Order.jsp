<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>欢迎使用电子商城系统</title>
    <link href="CSS/stylesheet.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="js/order.js"></script>
    <script type="text/javascript" src="js/def.js"></script>
    <script type="text/javascript" src="js/jquery-validate.js"></script>
</head>
<body class="body">
<table width="780" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"
       style="border:1px; border-style:solid; border-color:#888888">
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
            </select>
            <A href="javascript:selLike()"><IMG src="images/icon_search.gif" align="absmiddle" border="0"></A>
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
                        <A href="/def?action=load"><span class="whiteTitle">商城首页</span></A></TD>
                    <TD background="images/Bule_06.gif" width="2"></TD>
                    <TD class="header_menu" align="middle">
                        <A href="/chk?action=chk&name=cart"><span class="whiteTitle">购物车管理</span></A></TD>
                    <TD background="images/Bule_06.gif" width="2"></TD>
                    <TD class="header_menu" align="middle">
                        <A href="/chk?action=chk&name=order"><span class="whiteTitle">订单管理</span></A></TD>
                    <TD background="images/Bule_06.gif" width="2"></TD>
                    <TD class="header_menu" align="middle">
                        <A href="/lword?action=list"><span class="whiteTitle">顾客留言</span></A></TD>
                    <TD background="images/Bule_06.gif" width="2"></TD>
                    <TD class="header_menu" align="middle">
                        <A href="/chk?action=chk&name=modi"><span class="whiteTitle">修改用户资料</span></A></TD>
                    <TD vAlign=top width=7><IMG src="images/icon07.gif"></TD>
                </TR>
            </TABLE>
        </td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td height="60"><img src="images/icon_order.gif"/></td>
                </tr>

                <tr>
                    <td align="center">
                        <table cellspacing="1" cellpadding="0" width="94%" border="0" bgcolor="#F7F3F7">
                            <tr height="26">
                                <td class="blackTitle" align="center">订单编号</td>
                                <td class="blackTitle" align="center">金额</td>
                                <td class="blackTitle" align="center">下单日期</td>
                                <td class="blackTitle" align="center">订单状态</td>
                                <td class="blackTitle" align="center">编 辑</td>
                            </tr>
                            <c:if test="${!empty list}">
                                <c:forEach items="${list}" var="list">
                                    <tr height="26" class="text" align="center" bgcolor="#FFFFFF">
                                        <td>${list.orderid}</td>
                                        <td class="redText">￥${list.ordermoney}</td>
                                        <td>${list.orderdate}</td>
                                        <td>
                                            <c:if test="${list.orderstatus==0}">已下单，未受理</c:if>
                                            <c:if test="${list.orderstatus==1}">已受理，处理中</c:if>
                                            <c:if test="${list.orderstatus==2}">处理完毕</c:if>
                                            <c:if test="${list.orderstatus==3}">结单</c:if>
                                        </td>
                                        <td>
                                            <a href="/myorder?action=selId&oid=${list.orderid}">
                                                <span class="blueText">查看订单</span>
                                            </a>&nbsp;
                                            <c:if test="${list.orderstatus==3}">
                                                <a href="javascript:chkDel(${list.orderid})">
                                                    <span class="blueText">删除订单</span>
                                                </a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="5">&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="20">
                    <td colspan="5"></td>
                </tr>

                <tr align="right">
                    <td height="20">&nbsp;</td>
                </tr>
            </table>
        </td>
        <td>&nbsp;</td>
    </tr>

    <tr>
        <td>&nbsp;</td>
        <td height="30" bgcolor="#4282CE" class="whiteText" align="center">
            本电子商城系统仅供学习交流使用，未经授权严禁用于商业用途！！
        </td>
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