<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>欢迎使用电子商城系统</title>
    <link href="CSS/stylesheet.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
    <script type="text/javascript" src="js/def.js"></script>
</head>
<body class="body" onload="">
<table width="780" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"
       style="border:1px; border-style:solid; border-color:#888888">
    <tr>
        <td width="20">&nbsp;</td>
        <TD height="50" align="right" valign="bottom">
            <IMG src="images/icon_login.gif" align="absmiddle">
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
            </TABLE>
        </td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="200" valign="top">
                        <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                            <TR>
                                <TD><IMG src="images/Bule_43.gif"></TD>
                            </TR>
                            <TR>
                                <TD class="C_Item_bg">
                                    <form id="login" action="/login?action=def" method="post" style="margin:0px;">
                                        <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                                            <TR>
                                                <TD class="C_login_Title">会员登录</TD>
                                            </TR>
                                            <TR>
                                                <TD>
                                                    <c:if test="${empty mBean}">
                                                    <TABLE cellSpacing=0 cellPadding=0 width="90%" align=center
                                                           border=0>
                                                        <TR height="30">
                                                            <TD class="text">登录帐号：
                                                                <input type="text" name="loginName" size="10"
                                                                       styleClass="textBox"/>
                                                            </TD>
                                                        </TR>
                                                        <TR height="30">
                                                            <TD class="text">登录密码：
                                                                <input type="password" name="loginPwd" size="10"
                                                                       styleClass="textBox"/>
                                                            </TD>
                                                        </TR>
                                                        <TR height="30">
                                                            <TD class="UserRegster" align="right">
                                                                <input type="button" value="注册"
                                                                       onclick="location.href='reg.jsp'">
                                                                <input type="button" value="登录" onclick="login()">
                                                            </TD>
                                                        </TR>
                                                    </TABLE>
                                                    </c:if>
                                                    <c:if test="${!empty mBean}">
                                                    欢迎登录，<font color="red">${mBean.memlogin}</font><br>
                                                    &nbsp;<input type="button" value="注销" onclick="logout()">
                                                    </c:if>
                                            </TR>
                                        </TABLE>
                                    </form>
                                </TD>
                            </TR>
                            <TR>
                                <TD><IMG src="images/Bule_58.gif"></TD>
                            </TR>
                        </TABLE>
                        <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                            <TR>
                                <TD><IMG src="images/Bule_43.gif"></TD>
                            </TR>
                            <TR>
                                <TD class="C_Item_bg" valign="top">
                                    <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                                        <TR>
                                            <TD class="C_Sort_Title">商品类别</TD>
                                        </TR>
                                        <c:if test="${!empty type}">
                                            <c:forEach items="${type}" var="type">
                                                <TR>
                                                    <TD class="text">
                                                        &nbsp;<img src="images/cateIcon.gif" border="0">
                                                        <a href="/def?action=like&category=${type.typeid}">${type.typename}</a>
                                                    </TD>
                                                </TR>
                                            </c:forEach>
                                        </c:if>
                                    </TABLE>
                                </TD>
                            </TR>
                            <TR>
                                <TD><IMG src="images/Bule_58.gif"></TD>
                            </TR>
                        </TABLE>
                    </td>
                    <td width="20">&nbsp;</td>
                    <td valign="top"><br>
                        <TABLE class="C_Goods_Title" cellSpacing=0 cellPadding=0 width="100%" border=0>
                            <TR>
                                <TD><IMG hspace=5 src="images/Icon_TeJia.gif"></TD>
                                <%--<TD>&nbsp;</TD>--%>
                                <TD align="right">
                                    <A href="/def?action=more&pro=spro"><IMG hspace=5 src="images/icon_more.gif" border=0></A>
                                </TD>
                            </TR>
                        </TABLE>
                        <TABLE class="C_Goods_Border" cellSpacing=0 cellPadding=0 width="100%" border=0>
                            <c:if test="${!empty spros}">
                                <c:forEach items="${spros}" var="spro" begin="0" end="${fn:length(spros)}" varStatus="i">
                                    <c:if test="${(i.index)%3==0}"><TR></c:if>
                                    <TD valign="top" width="33%">
                                        <table cellspacing=0 cellpadding=0 width=180 border=0>
                                            <tr>
                                                <td align="TOP">
                                                    <table width="118" height="118" border="0" align="center"
                                                           cellpadding="0" cellspacing="0"
                                                           style="border:1px; border-style:solid; border-color:#888888">
                                                        <tr align="center" valign="middle">
                                                            <td><a href="/def?action=selById&id=${spro.proid}"
                                                                   target=_blank>
                                                                <img src="${spro.propic}" width="100" height="100"
                                                                     border="0"> </a>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr align="center" height="30">
                                                <td><a href="/def?action=selById&id=${spro.proid}"
                                                       target=_blank><span
                                                        class="blueText">${spro.proname}</span></a></td>
                                            </tr>
                                            <tr align="center" height="20">
                                                <td class="text">市场价： ￥${spro.price}</td>
                                            </tr>
                                            <tr align="center" height="20">
                                                <td class="text">特价： ￥${spro.sprice}</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <a href="/def?action=selById&id=${spro.proid}"><img
                                                            src="images/icon_car.gif" border=0></a>
                                                        <%--<a href="/cart?action=add&"></a>--%>
                                                    <img alt="" src="images/icon_buy.gif" border=0
                                                         onclick="buy('${spro.proid}','${mBean.memid}')">
                                                </td>
                                            </tr>
                                        </table>
                                    </TD>
                                    <c:if test="${(i.index)%3==2}"></TR></c:if>
                                </c:forEach>
                            </c:if>
                        </TABLE>
                        <br>
                        <TABLE class=C_Goods_Title cellSpacing=0 cellPadding=0 width="100%" border=0>
                            <TR>
                                <TD><IMG hspace=5 src="images/NewGoods_03.gif"></TD>
                                <%--<TD>&nbsp;</TD>--%>
                                <TD align=right><A href="/def?action=more&pro=pro">
                                    <IMG hspace=5 src="images/icon_more.gif" border=0></A>
                                </TD>
                            </TR>
                        </TABLE>
                        <TABLE class="C_Goods_Border" cellSpacing=0 cellPadding=0 width="100%" border=0>
                            <c:if test="${!empty pros}">
                                <c:forEach items="${pros}" var="pro" varStatus="i">
                                    <c:if test="${(i.index)%3==0}"><TR></c:if>
                                    <TD valign="top" width="33%">
                                        <table cellspacing=0 cellpadding=0 width=180 border=0>
                                            <tr>
                                                <td align="TOP">
                                                    <table width="118" height="118" border="0" align="center"
                                                           cellpadding="0" cellspacing="0"
                                                           style="border:1px; border-style:solid; border-color:#888888">
                                                        <tr align="center" valign="middle">
                                                            <td><a href="/def?action=selById&id=${pro.proid}"
                                                                   target=_blank>
                                                                <img src="${pro.propic}" width="100" height="100"
                                                                     border="0"> </a>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr align="center" height="30">
                                                <td><a href="/def?action=selById&id=${pro.proid}"
                                                       target=_blank><span
                                                        class="blueText">${pro.proname}</span></a></td>
                                            </tr>
                                            <tr align="center" height="20">
                                                <td class="text">市场价： ￥${pro.price}</td>
                                            </tr>
                                            <tr>
                                                <td class=GoodsItem_buy>
                                                    <a href="/def?action=selById&id=${pro.proid}">
                                                        <img src="images/icon_car.gif" border=0></a>
                                                    <a href="javascript:buy('${pro.proid}','${mBean.memid}')">
                                                        <img alt="" src="images/icon_buy.gif" border=0></a>
                                                </td>
                                            </tr>
                                        </table>
                                    </TD>
                                    <c:if test="${(i.index)%3==2}"></TR></c:if>
                                </c:forEach>
                            </c:if>
                        </TABLE>
                    </td>
                </tr>
            </table>
        </td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td colspan="3">&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td height="30" bgcolor="#4282CE" class="whiteText" align="center">
            本电子商城系统仅供学习交流使用，未经授权严禁用于商业用途！！
        </td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td height="20" colspan="3">&nbsp;</td>
    </tr>
</table>

</body>
</html>