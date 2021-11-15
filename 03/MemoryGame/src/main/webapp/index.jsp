<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <title>MemoryGame - Homepage</title>
        <!-- CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <body>
        <main class="container my-5">
            <h3>Welcome <%= session.getAttribute("user")%></h3>
            <hr>
            <h4>Classifica:</h4>
            <c:if test="${emptyScoreboard}">
                <p>Classifica vuota - nessuna partita giocata.</p>
            </c:if>
            <c:if test="${!emptyScoreboard}">
                <c:forEach items="${scoreboard}" var="entry">
                    <p class="my-1">${entry.getValue0()} - ${entry.getValue1()}</p>
                </c:forEach>
            </c:if>
            <hr>
            <a href="/MemoryServlet">
                <button class="btn btn-secondary">Play a game</button>
            </a>
        </main>
    </body>
</html>