<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sneaker Product</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 800px; margin: 2rem auto; }
        .product { display: flex; gap: 2rem; align-items: flex-start; }
        .product img { max-width: 300px; height: auto; border: 1px solid #ccc; }
        .summary { margin-top: 1rem; padding: 1rem; border: 1px solid #eee; background: #fafafa; }
        .error { color: #b00020; }
        .back-link { margin-top: 1rem; display: inline-block; }
    </style>
</head>
<body>
<h1>Sneaker Product</h1>

<!-- Product ID Search -->
<form action="${pageContext.request.contextPath}/sneaker" method="get" style="margin-bottom:1rem;">
    <label for="productId">Enter Product ID:</label>
    <input type="number" id="productId" name="productId"
           value="<%= request.getAttribute("productId") != null ? request.getAttribute("productId") : "" %>">
    <button type="submit">Load Product</button>
</form>

<!-- Display product or error -->
<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>

<div class="product">
    <div>
        <img src="${pageContext.request.contextPath}/images/sneakers.jpg" alt="Sneaker">
    </div>
    <div>
        <h2>
            <%= request.getAttribute("productName") != null
                    ? request.getAttribute("productName")
                    : "N/A" %>
        </h2>

        <p><strong>Description:</strong>
            <%= request.getAttribute("productDescription") != null
                    ? request.getAttribute("productDescription")
                    : "N/A" %>
        </p>

        <p><strong>Price (each):</strong> $
            <%= request.getAttribute("unitPrice") != null
                    ? request.getAttribute("unitPrice")
                    : "N/A" %>
        </p>

        <!-- Quantity form -->
        <form action="${pageContext.request.contextPath}/sneaker" method="post">
            <input type="hidden" name="unitPrice"
                   value="<%= request.getAttribute("unitPrice") != null ? request.getAttribute("unitPrice") : "N/A" %>">
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" min="1"
                   value="<%= request.getAttribute("quantity") != null ? request.getAttribute("quantity") : "1" %>">
            <button type="submit">Calculate</button>
        </form>

        <!-- Summary -->
        <div class="summary">
            <%
                String err = (String) request.getAttribute("error");
                if (err != null) {
            %>
            <div class="error"><%= err %></div>
            <%
            } else if (request.getAttribute("totalPrice") != null) {
            %>
            <p>Quantity: <strong><%= request.getAttribute("quantity") %></strong></p>
            <p>Unit price: $<strong><%= request.getAttribute("unitPrice") %></strong></p>
            <p>Discount: <strong><%= request.getAttribute("discountPercent") %></strong>%</p>
            <p>Total after discount: $<strong><%= request.getAttribute("totalPrice") %></strong></p>
            <%
            } else {
            %>
            <p>Enter a quantity to see the discounted total.</p>
            <%
                }
            %>
        </div>

        <div class="back-link">
            <a href="${pageContext.request.contextPath}/index.jsp">
                <button type="button">Back to Home</button>
            </a>
        </div>
    </div>
</div>
</body>
</html>
