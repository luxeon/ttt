<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="../partials/header.jsp" />
<form:form action="/" method="POST" commandName="newGame">
    <div>
        <form:label path="name">Name*</form:label>
        <form:input path="name" required="required" />
    </div>
    <div>
        <form:errors path="name" />
    </div>
    <div>
        <button type="submit">Create</button>
    </div>
</form:form>
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
<c:import url="../partials/footer.jsp" />