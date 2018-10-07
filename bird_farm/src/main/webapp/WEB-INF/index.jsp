<%--
  Created by IntelliJ IDEA.
  User: evrom
  Date: 01.10.2018
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>
    <title>getAllBird</title>
    <style>
        .super {
            margin-bottom: 15px;
        }

        .ignore {
            position: absolute;
        }

    </style>
</head>
<body>
<p hidden>${jsonBird}</p>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-9">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">
                        <p>п/н</p>
                    </th>
                    <th scope="col">
                        <p>Тип птицы</p>
                    </th>
                    <th scope="col">
                        <p>колличество, кг</p>
                    </th>
                    <th scope="col">
                        <p>цена за кг</p>
                    </th>
                    <th scope="col">
                        <p>цена за кг при заказе от 10 до 30 кг</p>
                    </th>
                    <th scope="col">
                        <p>цена за кг при заказе от 30 кг</p>
                    </th>
                </tr>
                </thead>
                <c:if test="${!empty listBird}">
                    <c:set var="count" scope="session" value="${0}"/>
                    <c:forEach var="bird" items="${listBird}">
                        <c:set var="count" scope="session" value="${count+1}"/>
                        <tbody>
                        <tr>
                            <th scope="row"><c:out value="${count}"/></th>
                            <td><c:out value="${bird.typeBird}"></c:out></td>
                            <td><c:out value="${bird.totalWeight}"></c:out></td>
                            <td><c:out value="${bird.pricePerKg}"></c:out></td>
                            <td><c:out value="${bird.pricePerKgFrom10to30kg}"></c:out></td>
                            <td><c:out value="${bird.pricePerKgFrom30kg}"></c:out></td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </c:if>

            </table>
        </div>

        <div class="col-md-3">
            <form id="formAdd" action="/addBird" method="post">
                <label for="exampleFormControlSelect1"> type Bird</label>
                <select class="form-control super" id="exampleFormControlSelect1" name="typeBird"
                        onchange="getType()">
                    <option value="duck" selected>duck</option>
                    <option value="chicken">chicken</option>
                    <option value="turkey">turkey</option>
                    <option value="quail">quail</option>
                </select>
                <div class="form-group">
                    <label> Weight <input class="form-control" type="text" name="totalWeight"
                                          placeholder="Weight"></label>
                </div>
                <div class="form-group">
                    <label> price per kg<input class="form-control" type="text" name="pricePerKg"
                                               placeholder="Price per kg"></label>
                </div>
                <div class="form-group">
                    <input class="form-control btn btn-primary" type="submit" value="Add">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script>

    var form = document.forms[0];
    var select = form.elements.typeBird;
    var input = form.elements.pricePerKg;

    var json = (document.body.children[0].textContent).toString().trim();
    var arrParse = JSON.parse(json);

    function getType() {
        for (var i = 0; i < select.options.length; i++) {

            var option = select.options[i];

            for(var j = 0; j<arrParse.length; j++){
                if (option.value === arrParse[j].typeBird && option.selected) {
                    input.value = arrParse[j].pricePerKg
                }
            }
        }
    }

    getType();

</script>
</html>