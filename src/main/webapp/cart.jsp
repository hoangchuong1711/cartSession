```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Your Cart</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
<div class="container">

    <h1>Your cart</h1>

    <table>
        <thead>
        <tr>
            <th>Quantity</th>
            <th>Description</th>
            <th>Price</th>
            <th>Amount</th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="item" items="${cart}">
            <tr>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="productCode" value="${item.product.code}">
                        <input type="number" name="quantity" value="${item.quantity}" min="1">
                        <button type="submit">Update</button>
                    </form>
                </td>

                <td>${item.product.description}</td>
                <td>$${item.product.price}</td>
                <td>$${item.total}</td>

                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="productCode" value="${item.product.code}">
                        <button type="submit">Remove Item</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <p>To change the quantity, enter the new quantity and click on the Update button.</p>

    <a href="${pageContext.request.contextPath}/index.jsp">
        <button>Continue Shopping</button>
    </a>

    <br><br>

    <button>Checkout</button>

</div>
</body>
</html>
```
