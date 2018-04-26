<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/WEB-INF/jspf/left_menu.jspf" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Refresh" content="2;url=${pageContext.servletContext.contextPath}/resources/jsp/main.jsp">
    <title>Message</title>
</head>
<body>
<div  style="padding-left: 360px">
    <h3> <c:out value="${Message}"/></h3>
</div>
</body>
</html>