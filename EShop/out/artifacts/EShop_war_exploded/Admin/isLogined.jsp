<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty name}">
	<jsp:forward page="/Admin/adminLogin.jsp"/>
</c:if>

