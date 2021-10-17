<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- META -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- TITLE -->
        <title>Login</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <!-- Custom CSS -->
        <link type="text/css" rel="stylesheet" href="css/chatter.css">
    </head>
    <body class="text-center h-100 login-container">
        <div class="form-signin mt-5">
            <form class="mt-5" action="/login" method="POST">
                <h1 class="h3 mb-3 fw-normal">Please, login</h1>
                <c:if test="${login eq 'error'}">
                    <div class="my-3">
                        <small class="form-text text-error">
                            Ops, something went wrong. Try again.
                        </small>
                    </div>
                </c:if>
                <div class="form-floating">
                    <input name="username" type="text" class="form-control" id="username" placeholder="mariorossi" required>
                    <label for="username">Username</label>
                </div>
                <div class="form-floating">
                    <input name="password" type="password" class="form-control" id="password" placeholder="Password" required>
                    <label for="password">Password</label>
                </div>
                <!--
                <div class="checkbox mb-3">
                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div>
                -->
                <button class="w-100 btn btn-lg btn-primary" type="submit">Login</button>
            </form>
            <p class="mt-5 mb-3 text-muted">Web Architectures - <i>assignment 2</i></p>
        </div>

        <!-- JAVASCRIPT -->
        <!-- Boostrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js" integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/" crossorigin="anonymous"></script>

    </body>
</html>
