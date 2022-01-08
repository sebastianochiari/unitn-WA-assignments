<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <!-- TITLE -->
    <title>Trivago - Home</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Custom CSS -->
    <link type="text/css" rel="stylesheet" href="../css/trivago.css">
    <!-- FONT -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Ubuntu">
</head>
<body>
    <jsp:include page="components/banner.jsp" />

    <main>
        <jsp:include page="components/search-form.jsp" />

        <div class="container">
            <div class="search-section mt-5">

                <div class="alert alert-success alert-dismissible fade show" id="successModal" role="alert" style="display: none">
                    Your booking was completed successfully. Enjoy your holidays!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

                <div class="alert alert-danger alert-dismissible fade show"  id="errorModal" role="alert" style="display: none">
                    Ops, something went wrong with your booking operation. Maybe, somebody stole the last spots.
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

                <c:if test="${sessionScope.searchPerformed}">
                    <c:if test="${sessionScope.accommodations.isEmpty()}">
                        <h5>We are sorry.</h5>
                        <p>It seems there are no available accommodations with your search parameters.</p>
                    </c:if>
                    <c:if test="${!(sessionScope.accommodations.isEmpty())}">
                        <h5>Your results</h5>
                        <div class="row row-cols-1 row-cols-md-2 g-4">
                            <c:forEach items="${sessionScope.accommodations}" var="accommodation">
                                <div class="col">
                                    <div class="card my-2">
                                        <div class="card-header">
                                            <c:if test="${accommodation.getAccommodationType() == 'APARTMENT'}">
                                                Apartment
                                            </c:if>
                                            <c:if test="${accommodation.getAccommodationType() == 'HOTEL'}">
                                                Hotel
                                            </c:if>
                                        </div>
                                        <div class="card-body row g-0">
                                            <div class="col-md-8">
                                                <h4 class="card-title">
                                                        ${accommodation.getAccommodationName()}
                                                            <c:if test="${accommodation.getAccommodationType() == 'HOTEL'}">
                                                                <small>
                                                                    <c:forEach var="i" begin="1" end="${accommodation.getStars()}" step="1" varStatus="status">
                                                                        ⭐
                                                                    </c:forEach>
                                                                </small>
                                                            </c:if>
                                                </h4>
                                                <div class="my-2">
                                                    <h5 class="m-0">
                                                        ${accommodation.getPrice()}€ per night
                                                        <c:if test="${accommodation.getAccommodationType() == 'HOTEL'}">
                                                            <small class="text-muted">(per person)</small>
                                                        </c:if>
                                                    </h5>
                                                    <p class="card-text">
                                                        <small class="text-muted">
                                                            <c:if test="${accommodation.getAccommodationType() == 'APARTMENT'}">
                                                                +${accommodation.getExtra()}€ <i>final cleaning</i>
                                                            </c:if>
                                                            <c:if test="${accommodation.getAccommodationType() == 'HOTEL'}">
                                                                ${accommodation.getPrice() + accommodation.getExtra()}€ with <i>half board</i>
                                                            </c:if>
                                                        </small>
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="col-md-4 text-end">
                                                <h4 class="card-title" style="color: #198754">
                                                    <c:if test="${accommodation.getAccommodationType() == 'HOTEL'}">
                                                    <small style="font-size: medium;">
                                                        starting at
                                                    </small>
                                                        <br>
                                                    </c:if>
                                                    <b>
                                                        <c:if test="${accommodation.getAccommodationType() == 'APARTMENT'}">
                                                            ${(accommodation.getPrice() * sessionScope.days) + accommodation.getExtra()}
                                                        </c:if>
                                                        <c:if test="${accommodation.getAccommodationType() == 'HOTEL'}">
                                                            ${accommodation.getPrice() * sessionScope.days * sessionScope.guests}
                                                        </c:if>
                                                    </b>
                                                    €
                                                </h4>
                                                <form method="POST" action="/CheckoutServlet">
                                                    <input type="hidden" id="accommodationType" name="accommodationType" value="${accommodation.getAccommodationType()}">
                                                    <input type="hidden" id="accommodationID" name="accommodationID" value="${accommodation.getAccommodationID()}">
                                                    <button type="submit" class="btn btn-outline-success">Book now</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                </c:if>
            </div>
        </div>
    </main>

    <!-- BOOTSTRAP JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <!-- FONT AWESOME -->
    <script src="https://kit.fontawesome.com/782fba089f.js" crossorigin="anonymous"></script>
    <!-- CUSTOM JS -->
    <script type="text/javascript">

        function updateTransactionStatus() {
            var xhttp = new XMLHttpRequest();
            xhttp.open("GET", "/BookingServlet", true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.responseType = "json";
            xhttp.send();
        }

        function checkTransactionStatus() {
            let sessionStatus = '<%= session.getAttribute("transactionStatus")%>';
            toggleMessage(sessionStatus);
            updateTransactionStatus();

        }

        function toggleMessage(status) {
            if(status !== null) {
                let modal = null;
                if(status === "succeeded") {
                    modal = document.getElementById("successModal");
                } else if(status === "failed") {
                    modal = document.getElementById("errorModal");
                }
                modal.style.display = "block";
            }
        }

        window.onload = checkTransactionStatus;

    </script>
</body>
</html>