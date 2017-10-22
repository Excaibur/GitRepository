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
            <IMG src="images/icon_login.gif" align="absmiddle">
            <INPUT id="qKey" name="qKey" value="${name}">
            <select id="category" name="category">
                <c:if test="${!empty type}">
                    <c:if test="${typeid==0}"><option value="${typeid}">所有商品</option></c:if>
                    <c:forEach items="${type}" var="type">
                        <c:choose>
                            <c:when test="${type.typeid==typeid}">
                                <option value="${type.typeid}" selected>${type.typename}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${type.typeid}">${type.typename}</option>
                            </c:otherwise>
                        </c:choose>
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
                    <td valign="top"><br>
                        <TABLE width="96%" border=0 align="center" cellPadding=0 cellSpacing=0 class="C_Goods_Title">
                            <TR>
                                <TD><IMG hspace=5 src="images/NewGoods_04.gif"></TD>
                            </TR>
                        </TABLE>
                        <table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#F7F3F7">
                            <tr bgcolor="#F7F3F7" valign="middle" align="center">
                                <td height="30" class="blackTitle">商品图片</td>
                                <td height="30" class="blackTitle">商品基本信息</td>
                                <td height="30" class="blackTitle">商品描述</td>
                                <td height="30" class="blackTitle">基本操作</td>
                            </tr>
                            <c:if test="${!empty list}">
                                <c:forEach items="${list}" var="pro">
                                    <tr valign="middle" bgcolor="#FFFFFF">
                                        <td width="100" align="center">
                                            <a href="/def?action=selById&id=${pro.proid}" target=_blank>
                                                <img src="${pro.propic}" width="60" height="60" border="1"> </a>
                                        </td>
                                        <td width="160" class="text">
                                            <a href="/def?action=selById&id=${pro.proid}" target=_blank>
                                                <span class="blueText">${pro.proname}</span></a><br>
                                            市场价： ￥${pro.price}<br>
                                            生产厂家：${pro.profactory}<br>
                                        </td>
                                        <td class="text">${pro.prodesc}</td>
                                        <td width="100">
                                            <a href="/def?action=selById&id=${pro.proid}"><img src="images/icon_car.gif" border=0></a><br>
                                            <a href="javascript:buy('${pro.proid}','${mBean.memid}')"><img src="images/icon_buy.gif" border=0></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" height="2" bgcolor="#F7F3F7"></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="4" height="30" bgcolor="#F7F3F7" class="text" align="center">
                                    <a href="javascript:changePage('1','${count}')" class="blueText"><span class="blueText">首页</span></a>&nbsp;
                                    <a href="javascript:changePage('${curpage-1}','${count}')" class="blueText"><span class="blueText">上一页</span></a>&nbsp;
                                    <a href="javascript:changePage('${curpage+1}','${count}')" class="blueText"><span class="blueText">下一页</span></a>&nbsp;
                                    <a href="javascript:changePage('${count}','${count}')" class="blueText"><span class="blueText">末页</span></a>　
                                    去第<input type="text" id="willGoPage" name="willGoPage" class="control" size="2">
                                    页<input type="button" class="button" id="go" value="GO" name="go" onclick="goPage()"> 　
                                    第<span class="redText">${curpage}</span>页/共<span class="redText">${num}</span>页　
                                    总数<span class="redText">${count}</span>
                                </td>
                            </tr>
                        </table>
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