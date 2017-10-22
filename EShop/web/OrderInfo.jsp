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
                    <td height="60"><img src="images/icon_order_view.gif" width="150" height="29"/></td>
                </tr>

                <tr>
                    <td align="center">
                        <table width="94%" border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">

                            <tr bgcolor="#F7F3F7" class="text">
                                <td width="220" height="26" align="right">订单编号：</td>
                                <td>&nbsp;${order.orderid}</td>
                            </tr>
                            <tr bgcolor="#F7F3F7" class="text">
                                <td width="220" height="26" align="right">金额：</td>
                                <td>&nbsp;￥${order.ordermoney}</td>
                            </tr>
                            <tr bgcolor="#F7F3F7" class="text">
                                <td width="220" height="26" align="right">下单日期 ：</td>
                                <td>&nbsp;${order.orderdate}</td>
                            </tr>
                            <tr bgcolor="#F7F3F7" class="text">
                                <td width="220" height="26" align="right">会员级别：</td>
                                <td>&nbsp;${order.lvname}</td>
                            </tr>
                            <tr bgcolor="#F7F3F7" class="text">
                                <td width="220" height="26" align="right">会员优惠：</td>
                                <td>&nbsp;${order.precent*10}折</td>
                            </tr>
                            <tr bgcolor="#F7F3F7" class="text">
                                <td width="220" height="26" align="right">收货人姓名：</td>
                                <td>&nbsp;${order.ordername}</td>
                            </tr>
                            <tr bgcolor="#F7F3F7" class="text">
                                <td width="220" height="26" align="right">收货人联系电话：</td>
                                <td>&nbsp;${order.orderphone}</td>
                            </tr>
                            <tr bgcolor="#F7F3F7" class="text">
                                <td width="220" height="26" align="right">收货人邮编：</td>
                                <td>&nbsp;${order.orderzip}</td>
                            </tr>
                            <tr bgcolor="#F7F3F7" class="text">
                                <td width="220" height="26" align="right">收货人详细地址：</td>
                                <td>&nbsp;${order.orderaddress}</td>
                            </tr>
                            <tr bgcolor="#F7F3F7" class="text">
                                <td height="26" colspan="2" align="center" bgcolor="#FFFFFF" class="itemTitle">订单购物明细表
                                </td>
                            </tr>
                            <tr bgcolor="#F7F3F7" class="text">
                                <td height="26" colspan="2" align="center" bgcolor="#FFFFFF">
                                    <table cellspacing="1" cellpadding="0" width="100%" border="0" bgcolor="#F7F3F7">
                                        <tr height="26">
                                            <td class="blackTitle" align="center">商品名称</td>
                                            <td class="blackTitle" align="center">市场价</td>
                                            <td class="blackTitle" align="center">会员价</td>
                                            <td class="blackTitle" align="center">数量</td>
                                            <td class="blackTitle" align="center">金额</td>
                                        </tr>
                                        <c:if test="${!empty order.infoList}">
                                            <c:forEach var="info" items="${order.infoList}">
                                                <tr class="text" align="center" bgcolor="#FFFFFF">
                                                    <td>&nbsp;${info.proname}</td>
                                                    <td>￥${info.price}</td>
                                                    <td>￥${info.money/info.pronum}</td>
                                                    <td>${info.pronum}</td>
                                                    <td>￥${info.money}</td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                        <tr height="10" bgcolor="#F7F3F7">
                                            <td colspan="5"></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr bgcolor="#F7F3F7" class="text">
                                <td height="40" colspan="2" align="center" bgcolor="#FFFFFF" valign="bottom">
                                    <a href="javascript:history.back();">
                                        <span class="redText">返回</span>
                                    </a>
                                </td>
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