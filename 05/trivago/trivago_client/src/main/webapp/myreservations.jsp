<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Trivago - My Reservations</title>
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
        <div class="container mt-5">
            <h3>My Reservations</h3>
            <c:if test="${!(thereAreReservations)}">
            <p class="mt-4">
                Oh, that's unfortunate.
                <br>
                We found <b>no reservation</b> under the name "${name}".
            </p>
            </c:if>
            <c:if test="${thereAreReservations}">
            <div class="reservations">
                <c:forEach items="${reservations}" var="reservation">
                    <div class="reservation-item mb-2">
                        <h2 class="reservation-header">
                            Reservation - ${reservation.getAccommodationName()}
                            <c:if test="${reservation.getAccommodationType() == 'APARTMENT'}">
                                apartment
                            </c:if>
                            <c:if test="${reservation.getAccommodationType() == 'HOTEL'}">
                                hotel
                            </c:if>
                        </h2>
                        <div class="reservation-body">
                            <c:if test="${reservation.getAccommodationType() == 'HOTEL'}">
                                You reserved <strong>${reservation.getPlaces_reserved()} places</strong>
                                <br>
                            </c:if>
                            from <strong>${reservation.getStartDate().toString()}</strong> to <strong>${reservation.getEndDate().toString()}</strong>
                            <br>
                            <br>
                            total price: <strong>${reservation.getTotal_price()}€</strong>
                        </div>
                    </div>
                </c:forEach>
            </div>
            </c:if>
        </div>
    </main>

    <!-- BOOTSTRAP JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <!-- FONT AWESOME -->
    <script src="https://kit.fontawesome.com/782fba089f.js" crossorigin="anonymous"></script>
</body>
</html>
