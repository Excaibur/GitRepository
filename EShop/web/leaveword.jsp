<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>欢迎使用电子商城系统</title>
    <link href="CSS/stylesheet.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="js/jquery-validate.js"></script>
    <script type="text/javascript" src="js/def.js"></script>
    <script type="text/javascript" src="js/word.js"></script>
</head>
<body class="body">
<table width="780" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"
       style="border:1px; border-style:solid; border-color:#888888">
    <tr>
        <td width="20">&nbsp;</td>
        <TD height="50" align="right" valign="bottom">
            <IMG src="images/icon_login.gif" align=absMiddle>
            <INPUT id="qKey" name="qKey" value="商品关键字" ">
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
                    <td align="center">
                        <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
                            <tr>
                                <td height="40"><img src="images/icon_liuyan.gif"/></td>
                            </tr>
                            <tr>
                                <td height="26" align="center">
                                    <c:if test="${!empty list}">
                                        <c:forEach items="${list}" var="word">
                                            <table width="90%" border="0" cellpadding="4" cellspacing="0"
                                                   style="border:1px; border-color:black; border-style:solid;">
                                                <tr bgcolor="#F7F3F7">
                                                    <td class="text"><span class="blackTitle">留言标题</span>：${word.wtitle}
                                                    </td>
                                                    <td align="right" class="text">
                                                        <span class="blackTitle">顾客姓名</span>：${word.memlogin}&nbsp;
                                                        <span class="blackTitle">留言时间</span>：${word.wdate}
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="text" colspan="2">${word.wcontent}</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2" bgcolor="#F7F3F7" class="blackTitle">管理员回复：</td>
                                                </tr>
                                                <tr>
                                                    <td class="text" colspan="2">${word.wmcontent}</td>
                                                </tr>
                                            </table>
                                            <table cellpadding="0" cellspacing="0">
                                                <tr height="10">
                                                    <td></td>
                                                </tr>
                                            </table>
                                        </c:forEach>
                                    </c:if>
                                </td>
                            </tr>
                            <tr bgcolor="#F7F3F7">
                                <td height="26" bgcolor="#FFFFFF" align="center" class="text">
                                    <a href="/lword?action=list&curpage=1" class="blueText"><span
                                            class="blueText">首页</span></a>&nbsp;
                                    <a href="/lword?action=list&curpage=${curpage-1}" class="blueText"><span
                                            class="blueText">上一页</span></a>&nbsp;
                                    <a href="/lword?action=list&curpage=${curpage+1}" class="blueText"><span
                                            class="blueText">下一页</span></a>&nbsp;
                                    <a href="/lword?action=list&curpage=${endpage}" class="blueText"><span
                                            class="blueText">末页</span></a>　
                                    去第<input type="text" id="willGoPage" name="willGoPage" class="control" size="2">
                                    页<input type="button" class="button" id="go" value="GO" name="go" onclick="goPage()"> 　
                                    第<span class="redText">${page}</span>页/共<span class="redText">${endpage}</span>页　
                                    总数<span class="redText">${num}</span>
                                </td>
                            </tr>
                            <tr bgcolor="#F7F3F7">
                                <td height="26" bgcolor="#FFFFFF">
                                    <form id="form1" action="/lword?action=add" method="post" style="margin:0px;">
                                        <table width="94%" border="0" align="center" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td colspan="2"><img src="images/icon_LeaveWord.gif" width="140"
                                                                     height="45"/></td>
                                            </tr>
                                            <tr>
                                                <td colspan="2">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td height="30" align="right" class="blackTitle">留言标题：</td>
                                                <td height="30"><input type="text" id="wordTitle" name="wordTitle"
                                                                       class="textBox" size="61"/></td>
                                            </tr>
                                            <tr>
                                                <td align="right" class="blackTitle">留言内容：</td>
                                                <td><textarea id="content" name="content" rows="4" cols="60"
                                                              class="textBox"></textarea></td>
                                            </tr>
                                            <tr>
                                                <c:if test="${empty mBean}">
                                                    <td>&nbsp;您还未登录，<a href="index.htm">点击登录</a></td>
                                                </c:if>
                                                <c:if test="${!empty mBean}">
                                                    <td height="35"><input type="button" class="C_Input"
                                                                           onClick="checkForm()" value="提交留言"/></td>
                                                </c:if>
                                            </tr>
                                        </table>
                                    </form>
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