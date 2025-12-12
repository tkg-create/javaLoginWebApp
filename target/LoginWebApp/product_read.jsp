<%--
  Created by IntelliJ IDEA.
  User: Timmo
  Date: 12/12/2025
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Products - Read</title>
</head>
<body>
<h1>Products</h1>

<p><a href="${pageContext.request.contextPath}/product_create.jsp">Create new product</a></p>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Color</th>
        <th>Size</th>
        <th>Price</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="prod" items="${products}">
        <tr>
            <td>${prod.ID}</td>
            <td>${prod.name}</td>
            <td>${prod.description}</td>
            <td>${prod.color}</td>
            <td>${prod.size}</td>
            <td>${prod.price}</td>
            <td>
                <!-- Edit link (loads the pre-filled update page) -->
                <a href="${pageContext.request.contextPath}/products?action=edit&id=${prod.ID}">Edit</a>

                <!-- Delete form (POST) -->
                <form action="${pageContext.request.contextPath}/products" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete" />
                    <input type="hidden" name="ID" value="${prod.ID}" />
                    <input type="submit" value="Delete" onclick="return confirm('Delete product ${prod.ID}?');" />
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<p><a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a></p>

</body>
</html>
