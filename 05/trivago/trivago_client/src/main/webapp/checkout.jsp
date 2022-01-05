<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Trivago - Homepage</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Custom CSS -->
    <link type="text/css" rel="stylesheet" href="/css/trivago.css">
    <!-- FONT -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Ubuntu">
</head>

<body class="bg-light">

<main>
    <div class="container" style="max-width: 960px">
        <div class="py-5">
            <h2 class="text-center">Confirm booking</h2>
            <form action="/BookingServlet" method="POST">
                <div class="row g-5 my-1">
                    <div class="col-5">
                        <h4 class="d-flex justify-content-between align-items-center mb-3">
                            Your reservation
                        </h4>
                        <div class="card g-3">
                            <div class="card-body">
                                <h4 class="card-title">
                                    ${accommodation.getAccommodationName()}
                                </h4>
                                <p class="card-text" >
                                    <c:if test="${accommodation.getAccommodationType() == 'HOTEL'}">
                                        <small>
                                            <c:forEach var="i" begin="1" end="${accommodation.getStars()}" step="1" varStatus="status">
                                                ⭐
                                            </c:forEach>
                                        </small>
                                    </c:if>
                                    <small>
                                        <c:if test="${accommodation.getAccommodationType() == 'HOTEL'}">
                                            Hotel
                                        </c:if>
                                        <c:if test="${accommodation.getAccommodationType() == 'APARTMENT'}">
                                            Apartment
                                        </c:if>
                                    </small>
                                </p>
                                <p class="card-text">
                                    <i class="far fa-calendar-alt me-2"></i>
                                    ${startDate} - ${endDate}
                                </p>
                                <c:if test="${accommodation.getAccommodationType() == 'HOTEL'}">
                                    <p class="card-text">
                                        <i class="fas fa-user-friends me-2"></i>
                                        ${guests} guests
                                    </p>
                                    <div class="form-check my-2">
                                        <input class="form-check-input" type="checkbox" value="yes" id="halfBoard" name="halfBoard" onclick="changePrice()">
                                        <label class="form-check-label" for="halfBoard">
                                            Extra half board
                                        </label>
                                    </div>
                                </c:if>
                                <h2 class="card-title text-end" style="color: #198754; font-weight: bold">
                                    <div id="price">
                                        145€
                                    </div>
                                    <div id="price-with-extra" style="display: none">
                                        180€
                                    </div>
                                </h2>
                            </div>
                        </div>
                    </div>
                    <div class="col-7">
                        <h4 class="d-flex justify-content-between align-items-center mb-3">
                            Billing address
                        </h4>
                        <div class="row g-3">
                            <div class="col-sm-6">
                                <label for="firstName" class="form-label">First name</label>
                                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="" value="" required>
                            </div>

                            <div class="col-sm-6">
                                <label for="lastName" class="form-label">Last name</label>
                                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="" value="" required>
                            </div>
                        </div>
                        <div class="row g-3 my-1">
                            <div class="col-md-12">
                                <label for="cc-number" class="form-label">Credit card number</label>
                                <input type="text" class="form-control" id="cc-number" name="creditCard" placeholder="" required>
                                <div class="invalid-feedback">
                                    Credit card number is required
                                </div>
                            </div>
                        </div>
                        <hr class="my-4">
                        <input type="hidden" id="accommodationType" name="accommodationType" value="${accommodation.getAccommodationType()}">
                        <input type="hidden" id="accommodationID" name="accommodationID" value="${accommodation.getAccommodationID()}">
                        <input type="hidden" id="startDate" name="startDate" value="${startDate}">
                        <input type="hidden" id="endDate" name="endDate" value="${endDate}">
                        <input type="hidden" id="guests" name="guests" value="${guests}">

                        <button class="w-100 btn btn-primary btn-lg" type="submit">Book your accommodation</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</main>

<!-- BOOTSTRAP JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- FONT AWESOME -->
<script src="https://kit.fontawesome.com/782fba089f.js" crossorigin="anonymous"></script>
<!-- CUSTOM JS -->
<script type="text/javascript">
    function changePrice() {
        const x = document.getElementById("price");
        const y = document.getElementById("price-with-extra");
        if(y.style.display === "none") {
            x.style.display = "none";
            y.style.display = "block";
        } else {
            x.style.display = "block";
            y.style.display = "none";
        }
    }
</script>
</body>
</html>
