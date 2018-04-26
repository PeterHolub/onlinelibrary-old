<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<%@include file="/WEB-INF/jspf/left_menu.jspf" %>

<div  style="padding-left: 360px">
    <h2>Use genre menu or search</h2>

    <c:if test="${userRole == 'admin'}">
        <div align="right" style="margin-right:78px">
            <form action="${pageContext.servletContext.contextPath}/resources/jsp/bookupload.jsp">
                <input type="submit" value="Book Upload" class="myButton"/>
            </form>
        </div>
    </c:if>

</div>