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
        .nav-button { margin: 1rem 0; }
    </style>
</head>
<body>
<h2>Welcome, <%= session.getAttribute("user") %>!</h2>
<p>You are now logged in.</p>

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
