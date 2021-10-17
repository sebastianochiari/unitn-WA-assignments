<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Admin - User Page</title>
        <!-- CSS -->
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <!-- Custom CSS -->
        <link type="text/css" rel="stylesheet" href="/css/chatter.css">
    </head>
    <body>
        <jsp:include page="../components/banner.jsp" />
        <main>
            <div class="container mt-5">
                <h2>User Management</h2>
                <div class="row align-items-md-stretch mt-3">
                    <div class="col-md-6">
                        <div class="h-100 p-5 bg-light rounded-3">
                            <h4>User List</h4>
                            <table class="table mt-3">
                                <thead class="thead-light">
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Username</th>
                                    <th scope="col">Password</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var = "i" begin = "1" end = "${users.size()}">
                                    <tr>
                                        <th scope="row">${i}</th>
                                        <td>${(users.get(i - 1)).getUsername()}</td>
                                        <td>${(users.get(i - 1)).getPassword()}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="h-100 p-5 bg-light rounded-3">
                            <h4>Add a new user</h4>
                            <form action="/admin" method="POST" class="mt-3">
                                <div class="form-floating mb-3">
                                    <input name="username" type="text" class="form-control" id="username" placeholder="mariorossi" required>
                                    <label for="username">Username</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input name="password" type="password" class="form-control" id="password" placeholder="password" required>
                                    <label for="password">Password</label>
                                </div>
                                <button type="submit" class="btn btn-primary text-end">Add user</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
