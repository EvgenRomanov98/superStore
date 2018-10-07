<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<c:forEach var="bird" items="${birdList}">
bird <c:out value="${bird.pricePerUnit}"/><p></c:forEach>

</body>
</html>