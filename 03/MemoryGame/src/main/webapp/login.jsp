<%--
  Created by IntelliJ IDEA.
  User: sebac
  Date: 25/10/21
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>MemoryGame - Welcome</title>
        <!-- CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <body>
        <main class="container my-5">
            <h3>Welcome, unknown friend!</h3>
            <form action="/login" method="POST">
                <div class="row g-3 align-items-center">
                    <div class="col-auto">
                        <label for="username" class="col-form-label">Enter your name: </label>
                    </div>
                    <div class="col-auto">
                        <input name="username" type="text" id="username" class="form-control" aria-describedby="text" required>
                    </div>
                </div>
                <div class="row g-3 align-items-center">
                    <div class="col-auto">
                        <button type="submit" class="btn btn-secondary">Let's go</button>
                    </div>
                </div>
            </form>
        </main>
    </body>
</html>
