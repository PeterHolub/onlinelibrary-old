<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Enter</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/style_index.css" rel="stylesheet"
          type="text/css">
</head>

<body>
<%session.invalidate(); %>

<div class="main">

    <div class="content">
        <p class="title"><span class="text">
           <a href="${pageContext.servletContext.contextPath}/resources/jsp/main.jsp"><img
                   src="${pageContext.servletContext.contextPath}/resources/images/library.jpg" alt="Logo"
                   name="logo"/></a>
               </span></p>
        <h1 align="center" style="color:#00688f">Welcome to online library</h1>

    </div>

    <div class="login_div" >
        <p class="title">Enter your login & password:</p>

        <form class="login_form"
              action="${pageContext.servletContext.contextPath}/Authentication" method="post">
            <table align="center" border="0">

                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="username" required value="" size="16"/></td>
                </tr>
                <tr>
                    <td> Password:</td>
                    <td><input type="text" name="password" required value="" size="16"/></td>
                </tr>

            </table>
            <h3 style="color: #eb1a24"> <c:out value="${ErrorLogin}"/></h3>
            <div align="left" style="margin-left:130px">
            <input type="submit" value="Enter" class="enterButton" >
            </div>
        </form>

    </div>

    <div style="clear:both;">
        <h4 style="text-align: center">© Peter Holub, 2018</h4>
    </div>
</div>


</body>
</html>
