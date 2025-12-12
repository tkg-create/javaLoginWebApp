<%--
  Created by IntelliJ IDEA.
  User: Timmo
  Date: 12/12/2025
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Delete Product</title>
</head>
<body>
<h1>Delete Product</h1>

<form action="${pageContext.request.contextPath}/products" method="post">
    <input type="hidden" name="action" value="delete" />
    <label>Product ID to delete:
        <input type="text" name="ID" required />
    </label><br/><br/>
    <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete product ID: ' + this.form.ID.value + '?');" />
</form>

<p><a href="${pageContext.request.contextPath}/products?action=read">Back to product list</a></p>

</body>
</html>
