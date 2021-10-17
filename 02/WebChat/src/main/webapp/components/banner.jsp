<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page session="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="row">
        <div class="col-md-6">
            <div class="align-items-center justify-content-center justify-content-lg-start">
                <span>
                    Ciao
                    <%= session.getAttribute("user")%>
                </span>
            </div>
        </div>
        <div class="col-md-6">
            <div class="align-items-center justify-content-center justify-content-lg-start text-end">
                <c:if test="${sessionScope.isAdmin}">
                    <form action="/admin" method="GET" class="inline-element">
                        <button type="submit" class="btn btn-warning ms-3">Admin</button>
                    </form>
                </c:if>
                <form action="/logout" method="POST" class="inline-element">
                    <button type="submit" class="btn btn-warning ms-3">Logout</button>
                </form>
            </div>
        </div>
        </div>
    </div>
</header>