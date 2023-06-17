<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Currency Converter</title>
</head>
<body>
    <center>
        <br>
        <br>
        <form method="get" action="/convert" name="productForm">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <label>Cryptocurrency</label>
        <select name="cryptoDropDownList" onchange="document.productForm.submit();" id="crypto-dropdown-id">
             <c:forEach items="${cryptoList}" var="cryptoName" varStatus="loop">
                  <option value="${loop.index}">
                      ${cryptoName}
                  </option>
             </c:forEach>
        </select>
        <br>
        <br>
        <label>IP</label>
        <input type="text" name="ip" placeholder="Enter your Ip (Optional)"/>
        <br>
        <br>
        </form>

        <h5>Current unit price is</h5>
        <h5 id ="price">"${price}"</h5>
    </center>
</body>
</html>