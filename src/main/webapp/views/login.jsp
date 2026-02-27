<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Connexion ERP</title>
</head>
<body>

<h2>Connexion</h2>

<form method="post" action="${pageContext.request.contextPath}/login">

    <label>Login :</label><br>
    <input type="text" name="login" required><br><br>

    <label>Password :</label><br>
    <input type="password" name="password" required><br><br>

    <button type="submit">Se connecter</button>

</form>

<c:if test="${param.error == 1}">
    <p style="color:red;">Identifiants incorrects</p>
</c:if>

</body>
</html>