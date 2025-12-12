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
    <title>Create Product</title>
</head>
<body>
<h1>Create Product</h1>

<form action="${pageContext.request.contextPath}/products" method="post">
    <input type="hidden" name="action" value="create" />

    <label>Name:
        <input type="text" name="Name" required />
    </label><br/><br/>

    <label>Description:
        <input type="text" name="Description" required />
    </label><br/><br/>

    <label>Color:
        <input type="text" name="Color" required />
    </label><br/><br/>

    <label>Size:
        <input type="text" name="Size" required />
    </label><br/><br/>

    <label>Price:
        <input type="text" name="Price" required />
    </label><br/><br/>

    <input type="submit" value="Create" />
</form>

<p><a href="${pageContext.request.contextPath}/products?action=read">Back to product list</a></p>

</body>
</html>
