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
            <select id="category" name="category">
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
                    <td height="60"><img src="images/Car_icon_04.gif"/></td>
                </tr>
                <tr>
                    <td>
                        <table cellspacing="0" cellpadding="0" border="0">
                            <tr valign="center">
                                <td><img hspace="5" src="images/Car_07.gif"/></td>
                                <td class="C_Carbg_Current">查看购物车物品</td>
                                <td><img height="39" src="images/Car_15.gif" width="1"/></td>
                                <td align="middle"><img hspace="5" src="images/Car_09.gif"/></td>
                                <td class="C_Carbg_Default">确认订单信息</td>
                                <td><img height="39" src="images/Car_15.gif" width="1"/></td>
                                <td align="middle"><img hspace="5" src="images/Car_11.gif"/></td>
                                <td class="C_Carbg_Default">订单提交成功</td>
                                <td><img height="39" src="images/Car_15.gif" width="1"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <table cellspacing="1" cellpadding="0" width="94%" border="0" bgcolor="#F7F3F7">
                            <tr height="26">
                                <td class="blackTitle" align="center">商品名称</td>
                                <td class="blackTitle" align="center">市场价</td>
                                <td class="blackTitle" align="center">会员价</td>
                                <td class="blackTitle" align="center">数量</td>
                                <td class="blackTitle" align="center">金额</td>
                                <td class="blackTitle" align="center">删除</td>
                            </tr>

                            <c:if test="${!empty requestScope.prolist}">
                                <c:forEach items="${prolist}" var="pro">
                                    <tr class="text" align="center" bgcolor="#FFFFFF">
                                        <td>
                                            &nbsp;<a href="/def?action=selById&id=${pro.proid}" target="_blank">
                                            <span class="blueText">${pro.proname}</span></a>
                                        </td>
                                        <td>￥${pro.price}</td>
                                        <c:choose>
                                            <c:when test="${pro.sprice==0.0}">
                                                <td>￥<span id="price${pro.proid}">${pro.price*pro.precent}</span></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>￥<span id="money${pro.proid}">${pro.sprice*pro.precent}</span></td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td><input type="text" class="textBox" name="num" onChange="changeNum('${mBean.memid}','${pro.proid}')" value="${pro.pronum}" size="4"/></td>
                                        <td>￥<span id="money${pro.proid}">${pro.money}</span></td>
                                        <td><input onClick="chkDel('${pro.cid}')" type="image" src="images/delete_01.gif" border="0"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="6" class="Order_Total"><img hspace="5" src="images/me03.gif" align="absmiddle"/>
                                    总金额<span id="totalMoney">${requestScope.totalmoney}</span>(不包括配送费用)
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="20">
                    <td colspan="5"></td>
                </tr>
                <tr align="right">
                    <td>
                        <input type="image" src="images/Car_icon_01.gif" style="BORDER: 0px;WIDTH: 126px; HEIGHT: 39px;"
                               onClick="clearCart('${mBean.memid}')">
                        <img style="CURSOR: hand" onClick="continueBuy()" src="images/Car_icon_02.gif"/>
                        <img src="images/Car_icon_03.gif" onClick="next('${mBean.lvid}')" border="0" style="CURSOR: hand"/>
                    </td>
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