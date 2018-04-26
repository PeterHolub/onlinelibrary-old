<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/WEB-INF/jspf/left_menu.jspf" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Book Upload</title>
</head>
<body>

<sql:setDataSource
        var="library"
        driver="org.sqlite.JDBC"
        url="jdbc:sqlite:./db/onlinelibrary.db"
/>
<sql:query var="listAuthor" dataSource="${library}">
    SELECT * FROM author;
</sql:query>
<sql:query var="listGenre" dataSource="${library}">
    SELECT * FROM genre;
</sql:query>

<div align="center">

    <h1 style="color:#00688f">Book Upload Page</h1>
    <form method="post" action="${pageContext.servletContext.contextPath}/BookUpload" enctype="multipart/form-data">
        <table border="0">

            <tr>
                <td>Book Name:</td>
                <td><input type="text" required name="book name" size="30"/></td>
            </tr>
            <tr>
                <td>Author Name:</td>
                <td>
                    <select name="author name">

                    <c:forEach var="author" items="${listAuthor.rows}">
                        <option value=<c:out value="${author.id}"/> ><c:out value="${author.authorname}"/></option>
                    </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Genre name:</td>
                <td>
                    <select name="genre">

                        <c:forEach var="genre" items="${listGenre.rows}">
                            <option value=<c:out value="${genre.id}"/> ><c:out value="${genre.name}"/></option>
                        </c:forEach>
                    </select>

                </td>
            </tr>

            <tr>
                <td>Description:</td>
                <td><textarea maxlength="600" rows="10" cols="45"
                              name="description" required ></textarea></td>
            </tr>
            <tr>
                <td>Book image: </td>
                <td><input type="file"  required accept=".jpg, .png" name="image" size="50"/></td>
            </tr>
            <tr>
                <td>Book Upload: </td>
                <td><input type ="file" required  accept=".pdf"name="content" size="50"/></td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" value="UPLOAD">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>