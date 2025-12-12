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
    <title>Update Product</title>
</head>
<body>
<h1>Update Product</h1>

<c:choose>
    <c:when test="${not empty product}">
        <form action="${pageContext.request.contextPath}/products" method="post">
            <input type="hidden" name="action" value="update" />
            <label>ID:
                <input type="text" name="ID" value="${product.ID}" readonly />
            </label><br/><br/>

            <label>Name:
                <input type="text" name="Name" value="${product.name}" required />
            </label><br/><br/>

            <label>Description:
                <input type="text" name="Description" value="${product.description}" required />
            </label><br/><br/>

            <label>Color:
                <input type="text" name="Color" value="${product.color}" required />
            </label><br/><br/>

            <label>Size:
                <input type="text" name="Size" value="${product.size}" required />
            </label><br/><br/>

            <label>Price:
                <input type="text" name="Price" value="${product.price}" required />
            </label><br/><br/>

            <input type="submit" value="Update" />
        </form>
    </c:when>
    <c:otherwise>
        <p>No product loaded. To edit a product, first go to the product list and click "Edit" on a row, or provide an ID below and click "Load".</p>

        <form action="${pageContext.request.contextPath}/products" method="get">
            <input type="hidden" name="action" value="edit" />
            <label>Product ID:
                <input type="text" name="id" required />
            </label>
            <input type="submit" value="Load" />
        </form>
    </c:otherwise>
</c:choose>

<p><a href="${pageContext.request.contextPath}/products?action=read">Back to product list</a></p>

</body>
</html>
