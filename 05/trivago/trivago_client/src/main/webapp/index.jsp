<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    </main>

    <!-- BOOTSTRAP JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <!-- FONT AWESOME -->
    <script src="https://kit.fontawesome.com/782fba089f.js" crossorigin="anonymous"></script>
</body>
</html>