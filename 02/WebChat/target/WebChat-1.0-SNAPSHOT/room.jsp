<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- META -->
        <meta http-equiv="refresh" content="15">
        <!-- TITLE -->
        <title>Room page</title>
        <!-- CSS -->
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <!-- Custom CSS -->
        <link type="text/css" rel="stylesheet" href="css/chatter.css">
    </head>

    <body class="bg-light">
        <jsp:include page="components/banner.jsp" />
        <main>
            <div class="container mt-5">
                <div class="h-100 p-5 rounded-3 bg-white">
                    <!-- ROOM TITLE and BUTTONS -->
                    <div class="border-bottom pb-3">
                        <div class="row">
                            <div class="col-md-8">
                                <h2 class="inline-element me-2"><i class="far fa-file-alt me-3"></i>${room.getName()}</h2>
                                <p class="inline-element fw-light text-muted">created by ${room.getAuthor()}</p>
                            </div>
                            <div class="col-md-4 text-end">
                                <button onClick="window.location.reload();" class="btn btn-secondary inline-element m-1">Reload</button>
                                <a href="/home">
                                    <button type="button" class="btn btn-secondary inline-element m-1">Leave room</button>
                                </a>
                            </div>
                        </div>
                    </div>
                    <!-- MESSAGE FORM -->
                    <div class="my-5">
                        <form action="/RoomServlet" method="POST" class="my-3">
                            <div class="form-floating">
                                <textarea name="message" class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px" required></textarea>
                                <label for="floatingTextarea2">What are your thoughts?</label>
                            </div>
                            <input type="hidden" id="roomID" name="roomID" value="${roomID}">
                            <button type="submit" class="my-3 btn btn-warning">Comment</button>
                        </form>
                    </div>

                    <!-- MESSAGES -->
                    <div class="my-5">
                        <c:if test="${(room.getMessages()).isEmpty()}">
                            <div class="row message-box">
                                <p>There are no messages. Write yours.</p>
                            </div>
                        </c:if>
                        <c:if test="${!((room.getMessages()).isEmpty())}">
                            <c:forEach items="${room.getMessages()}" var="message">
                                <div class="row message-box">
                                    <div class="col-md-1">
                                        <h2 class="text-muted"><i class="far fa-comments"></i></h2>
                                    </div>
                                    <div class="col-md">
                                        <div>
                                            <p class="inline-element"><b>${message.getAuthor()}</b></p>
                                            <span>·</span>
                                            <p class="muted inline-element">${message.getFormattedTime()}</p>
                                        </div>
                                        <p>${message.getText()}</p>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </main>

        <!-- JAVASCRIPT -->
        <!-- Boostrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js" integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/" crossorigin="anonymous"></script>
        <!-- Fontawesome JS -->
        <script src="https://kit.fontawesome.com/782fba089f.js" crossorigin="anonymous"></script>

    </body>
</html>
