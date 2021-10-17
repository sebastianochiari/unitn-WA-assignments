<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- META -->
        <!-- TITLE -->
        <title>WebChat - Home</title>
        <!-- CSS -->
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <!-- Custom CSS -->
        <link type="text/css" rel="stylesheet" href="/css/chatter.css">
    </head>

    <body>
        <jsp:include page="components/banner.jsp" />
        <main>
            <div class="container mt-5">
                <div class="row align-items-md-stretch">
                    <div class="col-md-6">
                        <div class="h-100 p-5 bg-light rounded-3">
                            <h2>Rooms</h2>
                            <c:if test="${!areThereRooms}">
                                <p class="lead">Sorry, no rooms are available, but you can create one.</p>
                            </c:if>
                            <c:if test="${areThereRooms}">
                                <p class="lead">Enter in a room, or create one.</p>
                                <div class="list-group">
                                    <c:forEach items="${rooms}" var="room">
                                        <a href="/room?name=${room.getName()}" class="list-group-item list-group-item-action">${room.getName()}</a>
                                    </c:forEach>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="h-100 p-5 bg-light rounded-3">
                            <h2>Create a room</h2>
                            <p class="lead">Enter your room name.</p>
                            <form action="/home" method="POST">
                                <div class="form-floating mb-3">
                                    <input name="roomName" type="text" class="form-control" id="roomName" placeholder="Friday For Future" required>
                                    <label for="roomName">Room name</label>
                                </div>
                                <button type="submit" class="btn btn-primary text-end">Create</button>
                            </form>
                        </div>
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