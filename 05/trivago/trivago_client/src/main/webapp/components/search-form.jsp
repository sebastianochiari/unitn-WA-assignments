<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="bg-light">
  <div class="container py-5">
    <h4>Find your accommodation</h4>
    <form action="/IndexServlet" method="POST" class="row g-3 align-items-center py-2">
      <div class="col-3">
        <label for="accommodation-type" class="form-label">Accomodation type</label>
        <select id="accommodation-type" class="form-select" name="accommodation-type">
          <option selected>All stays</option>
          <option>Apartment</option>
          <option>Hotel</option>
        </select>
      </div>
      <div class="col-3">
        <label for="start-date" class="form-label">Check in</label>
        <input class="form-control" type="date" id="start-date" name="start-date" required>
      </div>
      <div class="col-3">
        <label for="end-date" class="form-label">Check out</label>
        <input class="form-control" type="date" id="end-date" name="end-date" required>
      </div>
      <div class="col-2">
        <label for="guests" class="form-label">Guests</label>
        <input type="number" class="form-control" id="guests" value="2" name="guests" required>
      </div>
      <div class="col-1">
        <button type="submit" class="btn btn-outline-primary">Search</button>
      </div>
    </form>
  </div>
</div>