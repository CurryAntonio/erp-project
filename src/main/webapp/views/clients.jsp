<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Clients</title>
</head>
<body>

<h2>Gestion Clients</h2>

<form method="post" action="${pageContext.request.contextPath}/clients">

    Nom : <input type="text" name="nom"><br><br>
    Prénom : <input type="text" name="prenom"><br><br>
    Email : <input type="email" name="email"><br><br>
    Adresse : <input type="text" name="adresse"><br><br>

    <button type="submit">Ajouter</button>
</form>

<hr>

<table border="1">
<tr>
    <th>ID</th>
    <th>Nom</th>
    <th>Prénom</th>
    <th>Email</th>
</tr>

<c:forEach items="${clients}" var="c">
<tr>
    <td>${c.id}</td>
    <td>${c.nom}</td>
    <td>${c.prenom}</td>
    <td>${c.email}</td>
</tr>
</c:forEach>

</table>

<br>
<a href="dashboard">Retour</a>

</body>
</html>