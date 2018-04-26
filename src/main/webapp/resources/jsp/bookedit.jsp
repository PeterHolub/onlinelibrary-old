<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/WEB-INF/jspf/left_menu.jspf" %>
<html>
<head>
    <title>BookEdit</title>
</head>
<body>
<div align="center">

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

        <h1>Book Edit Page</h1>
        <c:forEach items="${bookEditList}" var="bookDetails">
        <form method="post" action="BookUpdate?updateId=<c:out value="${bookDetails.id}"/>" enctype="multipart/form-data">
            <table border="0">

                <tr><img src="${pageContext.servletContext.contextPath}/ShowImage?imageId=<c:out value="${bookDetails.id}"/>" height="250"
                         width="190" alt="Cover"/>
                </tr>


                <tr>
                    <td>Book Name:</td>
                    <td><input type="text" required name="book name" size="30" value="<c:out value="${bookDetails.name}"/>"/></td>
                </tr>
                <tr>
                    <td>Author Name:</td>
                    <td>
                        <select name="author name" select id="author name">

                            <c:forEach var="author" items="${listAuthor.rows}">
                                <option value=
                                        <c:out value="${author.id}"/>><c:out value="${author.authorname}"/></option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Genre name:</td>
                    <td>
                        <select name="genre" select id="genre">

                            <c:forEach var="genre" items="${listGenre.rows}">
                                <option value=
                                        <c:out value="${genre.id}"/>><c:out value="${genre.name}"/></option>
                            </c:forEach>
                        </select>

                    </td>
                </tr>

                <tr>
                    <td>Description:</td>
                    <td><textarea maxlength="600" rows="10" cols="45" name="description" required >
                    <c:out value="${bookDetails.description}"/>
                </textarea></td>
                </tr>
                <tr>
                    <td>Book image: </td>
                    <td><input type="file" accept=".jpg, .png" name="image" size="50"/></td>
                </tr>
                <tr>
                    <td>Book Upload: </td>
                    <td><input type="file" accept=".pdf"name="content" size="50"/></td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" value="Finish Edit">
                    </td>
                </tr>
            </table>
        </form>

        <script>

            function setSelectedIndex(s, valsearch) {
                for (i = 0; i < s.options.length; i++) {
                    if (s.options[i].value == valsearch) {
                        s.options[i].selected = true;
                        break;

                    }
                }
                return;
            }

            setSelectedIndex(document.getElementById("author name"), "<c:out value="${author_id}"/>");
            setSelectedIndex(document.getElementById("genre"), "<c:out value="${genre_id}"/>");

        </script>
        </c:forEach>
    </div>
</body>
</html>
