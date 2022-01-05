<nav class="navbar navbar-expand-lg navbar-light py-3 trivago-navbar">
  <div class="container container-fluid">
    <a class="navbar-brand" href="/IndexServlet">
      <i class="fas fa-bed"></i>
      Accomodations
    </a>
    <form action="/ReservationServlet" method="POST" class="row row-cols-lg-auto g-3 align-items-center d-flex">
      <div class="col-12">
        <input class="form-control me-2" type="search" placeholder="First Name" name="firstname" required>
        <div class="valid-feedback">
          Looks good!
        </div>
        <div class="invalid-feedback">
          Please give your firstname.
        </div>
      </div>
      <div class="col-12">
        <input class="form-control me-2" type="search" placeholder="Last Name" name="lastname" required>
        <div class="valid-feedback">
          Looks good!
        </div>
        <div class="invalid-feedback">
          Please give your lastname.
        </div>
      </div>
      <div class="col-12">
        <button class="btn btn-outline-primary" type="submit">My Reservations</button>
      </div>
    </form>
  </div>
</nav>