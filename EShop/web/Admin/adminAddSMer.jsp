<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>电子商城系统--后台管理</title>
    <link href="../CSS/stylesheet.css" rel="stylesheet" type="text/css">

    <style type="text/css">
        <!--
        body {
            background-color: lightgrey;
        }
        -->
    </style>
    <script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="../js/jquery-validate.js"></script>
    <script type="text/javascript" src="../js/chkPro.js"></script>
    <script type="text/javascript" src="../js/showdate.js"></script>
</head>
<body>
<form id="proform" action="/pro?action=add" enctype="multipart/form-data" method="post">
    <table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr height="40">
            <td colspan="2" class="itemTitle" align="center">
                新增商品
            </td>
        </tr>
        <tr height="24">
            <td width="160" align="right">商品类别：</td>
            <td><select name="category" styleClass="textBox">
                <c:if test="${!empty type}">
                    <c:forEach items="${type}" var="type">
                        <option value="${type.typeid}">${type.typename}</option>
                    </c:forEach>
                </c:if>
            </select></td>
        </tr>
        <tr height="24">
            <td valign="top"  align="right">商品名称：</td>
            <td><input type="text" name="merName" size="41" styleClass="textBox"/></td>
        </tr>
        <tr height="24">
            <td width="160" align="right">商品型号：</td>
            <td><input type="text" name="merModel" size="41" styleClass="textBox"/></td>
        </tr>
        <tr height="24">
            <td valign="top"  align="right">商品图片：</td>
            <td><input type="file" name="picture"  styleClass="textBox"/></td>
        </tr>
        <tr height="24">
            <td width="160" align="right">市场价：</td>
            <td><input type="text" name="price" size="41" styleClass="textBox"/></td>
        </tr>
        <tr height="24">
            <td valign="top"  align="right">有无特价：</td>
            <td>
                <input type="radio" name="special" value="0" onclick="changePrice()"/>无&nbsp;&nbsp;
                <input type="radio" name="special" value="1" checked onclick="changePrice()"/>有
            </td>
        </tr>
        <tr height="24">
            <td width="160" align="right">特 价：</td>
            <td><input type="text" name="sprice" size="41" styleClass="textBox"/></td>
        </tr>
        <tr height="24">
            <td valign="top"  align="right">商品描述：</td>
            <td><textarea name="merDesc" cols="40" rows="10" styleClass="textBox"></textarea></td>
        </tr>
        <tr height="24">
            <td width="160" align="right">生产厂家：</td>
            <td><input type="text" name="manufacturer" size="41" styleClass="textBox"/></td>
        </tr>
        <tr height="24">
            <td valign="top"  align="right">出厂日期：</td>
            <td><input type="text" id="leaveFactoryDate"  name="leaveFactoryDate" size="41" readonly="true" onclick="Calendar('leaveFactoryDate')" styleClass="textBox"/></td>
        </tr>
        <tr height="24">
            <td colspan="2" align="center">
                <input type="reset" value="重填">
                <input type="submit" value="提交" onclick="chkAddPro()">
            </td>
        </tr>

    </table>
</form>

</body>
</html>