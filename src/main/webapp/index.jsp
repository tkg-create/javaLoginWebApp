<%
    // 'session' is an implicit JSP variable (type HttpSession)
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Home</title>
    <style>
        .nav-button { margin: 1rem 0; display:block; }
    </style>
</head>
<body>
<h2>Welcome, <%= session.getAttribute("user") %>!</h2>
<p>You are now logged in.</p>

<h3>Product Management</h3>

<!-- Create -->
<a class="nav-button" href="${pageContext.request.contextPath}/product_create.jsp">
    <button type="button">Create Product</button>
</a>

<!-- Read -->
<a class="nav-button" href="${pageContext.request.contextPath}/products?action=read">
    <button type="button">View Products</button>
</a>

<!-- Update (goes to edit form) -->
<a class="nav-button" href="${pageContext.request.contextPath}/product_update.jsp">
    <button type="button">Update Product</button>
</a>

<!-- Delete -->
<a class="nav-button" href="${pageContext.request.contextPath}/product_delete.jsp">
    <button type="button">Delete Product</button>
</a>

<hr/>

<p>Use the button below to view the sneaker product page.</p>

<a class="nav-button" href="${pageContext.request.contextPath}/sneaker">
    <button type="button">View Sneaker</button>
</a>

<p>Use the button below to end your session and return to the login screen.</p>

<a class="nav-button" href="${pageContext.request.contextPath}/logout">
    <button type="button">Logout</button>
</a>
</body>
</html>
