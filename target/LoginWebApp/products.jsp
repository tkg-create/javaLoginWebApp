<%--
  Created by IntelliJ IDEA.
  User: Timmo
  Date: 12/9/2025
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Product Page</title>
</head>
<body>
<h1>Product List</h1>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Color</th>
        <th>Size</th>
        <th>Price</th>
    </tr>

    <c:forEach var="prod" items="${products}">
        <tr>
            <td>${prod.ID}</td>
            <td>${prod.name}</td>
            <td>${prod.description}</td>
            <td>${prod.color}</td>
            <td>${prod.size}</td>
            <td>${prod.price}</td>
        </tr>
    </c:forEach>
</table>

<hr/>

<h2>Add New Product</h2>
<form action="${pageContext.request.contextPath}/products" method="post">
    <label>Name:
        <input type="text" name="Name" />
    </label><br/><br/>

    <label>Description:
        <input type="text" name="Description" />
    </label><br/><br/>

    <label>Color:
        <input type="text" name="Color" />
    </label><br/><br/>

    <label>Size:
        <input type="text" name="Size" />
    </label><br/><br/>

    <label>Price:
        <input type="text" name="Price" />
    </label><br/><br/>

    <input type="submit" value="Save Product" />
</form>

</body>
</html>

