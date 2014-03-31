<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="/create-game" method="POST">
    <div>
        <label for="name"></label>
        <input type="text" name="name" id="name" required="required">
    </div>
    <div>
        <button type="submit">Create</button>
    </div>
</form>
<c:choose>
    <c:when test="${not empty games}">
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="game" items="${games}">
                <tr>
                    <td><a href="/game/${game.id}">${game.name}</a></td>
                    <td>${game.status}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <div>No games created yet</div>
    </c:otherwise>
</c:choose>
</body>
</html>
