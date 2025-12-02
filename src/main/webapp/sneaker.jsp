<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<h1>Classic Runner Sneaker</h1>
<div class="product">
    <div>
        <img src="${pageContext.request.contextPath}/images/sneaker.jpg" alt="Classic Runner Sneaker">
    </div>
    <div>
        <p><strong>Description:</strong> Lightweight running sneaker with breathable mesh upper and cushioned sole.</p>
        <p><strong>Price (each):</strong> $
            <%= request.getAttribute("unitPrice") != null ? request.getAttribute("unitPrice") : "120.00" %>
        </p>

        <form action="${pageContext.request.contextPath}/sneaker" method="post">
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" min="1"
                   value="<%= request.getAttribute("quantity") != null ? request.getAttribute("quantity") : "1" %>">
            <button type="submit">Calculate</button>
        </form>

        <div class="summary">
            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
            <div class="error"><%= error %></div>
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

