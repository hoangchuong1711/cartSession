```jsp
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>CD List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
<div class="container">

    <h1>CD list</h1>

    <table>
        <thead>
        <tr>
            <th>Description</th>
            <th>Price</th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <tr>
            <td>86 (the band) - True Life Songs and Pictures</td>
            <td>$14.95</td>
            <td>
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="productCode" value="8601">
                    <button type="submit">Add To Cart</button>
                </form>
            </td>
        </tr>

        <tr>
            <td>Paddlefoot - The first CD</td>
            <td>$12.95</td>
            <td>
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="productCode" value="pf01">
                    <button type="submit">Add To Cart</button>
                </form>
            </td>
        </tr>

        <tr>
            <td>Paddlefoot - The second CD</td>
            <td>$14.95</td>
            <td>
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="productCode" value="pf02">
                    <button type="submit">Add To Cart</button>
                </form>
            </td>
        </tr>

        <tr>
            <td>Joe Rut - Genuine Wood Grained Finish</td>
            <td>$14.95</td>
            <td>
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="productCode" value="jr01">
                    <button type="submit">Add To Cart</button>
                </form>
            </td>
        </tr>
        </tbody>
    </table>

</div>
</body>
</html>
```
