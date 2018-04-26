<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<%@include file="/WEB-INF/jspf/left_menu.jspf" %>

<sql:setDataSource
        var="library"
        driver="org.sqlite.JDBC"
        url="jdbc:sqlite:./db/onlinelibrary.db"
/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Online Library</title>
</head>
<body>
<c:if test="${userRole == 'admin'}">
    <div align="right" style="margin-right:78px">
        <form action="${pageContext.servletContext.contextPath}/resources/jsp/bookupload.jsp">
            <input type="submit" value="Book Upload" class="myButton"/>
        </form>
    </div>
</c:if>

<div class="book_list">
    <h5 style="text-align: left; margin-top:20px;">Books found:<c:out value="${currentBookList.size()}"/>
    </h5>

    <script>
        function ConfirmDelete() {
            var x = confirm("Are you sure you want to delete?");
            if (x)
                return true;
        }
    </script>

    <c:forEach var="book" items="${currentBookList}">

        <div class="book_info">
            <div class="book_title">
                <p><c:out value="${book.name}"/></p>
            </div>
            <div class="book_image">
                <img src="${pageContext.servletContext.contextPath}/ShowImage?imageId=<c:out value="${book.id}"/>"
                     height="250" width="190" alt="Cover"/>
            </div>
            <div class="buttons">

                <form action="${pageContext.servletContext.contextPath}/BookRead" method="post">
                    <button class="myButton" type="submit" name="readId"
                            value="<c:out value="${book.id}"/>">Read
                    </button>
                </form>

                <c:if test="${(userRole == 'admin')||(userRole == 'user')}">
                    <form action="${pageContext.servletContext.contextPath}/BookDownload" method="post">
                        <button class="myButton" type="submit" name="downloadId"
                                value="<c:out value="${book.id}"/>">Download
                        </button>
                    </form>

                    <sql:query var="favorite" dataSource="${library}">
                        SELECT * FROM favorites WHERE book_id=(<c:out value="${book.id}"/>) AND user_id='<c:out
                            value="${username}"/>'
                    </sql:query>
                    <c:choose>
                        <c:when test="${favorite.rowCount == 0}">

                            <form action="${pageContext.servletContext.contextPath}/AddToFavorites" method="post">
                                <input type="hidden" name="username" value="<c:out value="${username}"/>"/>
                                <input type="hidden" name="bookId" value="<c:out value="${book.id}"/>"/>
                                <input type="submit" value="Add to Favorites" class="favoriteButton">
                            </form>
                        </c:when>
                        <c:otherwise>

                            <form action="${pageContext.servletContext.contextPath}/DeleteFromFavorites" method="post">
                                <input type="hidden" name="username" value="<c:out value="${username}"/>"/>
                                <input type="hidden" name="bookId" value="<c:out value="${book.id}"/>"/>
                                <input type="submit" value="Remove from Favorites" class="favoriteButton">
                            </form>

                        </c:otherwise>
                    </c:choose>
                </c:if>

                <c:if test="${userRole == 'admin'}">
                    <form action="${pageContext.servletContext.contextPath}/BookEdit" method="post">
                        <button class="myButton" type="submit" name="editId"
                                value="<c:out value="${book.id}"/>">Edit
                        </button>
                    </form>
                    <form action="${pageContext.servletContext.contextPath}/BookDelete" method="post">
                        <button class="myButton" Onclick="return ConfirmDelete();" type="submit" name="deleteId"
                                value="<c:out value="${book.id}"/>">Delete
                        </button>
                    </form>
                </c:if>
                <form action="${pageContext.servletContext.contextPath}/BookDetails" method="post">
                    <button class="myButton" type="submit" name="showDetailsId"
                            value="<c:out value="${book.id}"/>">See book details
                    </button>
                </form>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>