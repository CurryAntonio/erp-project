<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Produits</title>
</head>
<body>

<h2>Gestion Produits</h2>

<form method="post" action="${pageContext.request.contextPath}/produits">

    Libellé : <input type="text" name="libelle"><br><br>
    Référence : <input type="text" name="reference"><br><br>
    Prix : <input type="number" step="0.01" name="prix"><br><br>
    Stock : <input type="number" name="stock"><br><br>

    <button type="submit">Ajouter</button>
</form>

<hr>

<table border="1">
<tr>
    <th>ID</th>
    <th>Libellé</th>
    <th>Prix</th>
    <th>Stock</th>
</tr>

<c:forEach items="${produits}" var="p">
<tr>
    <td>${p.id}</td>
    <td>${p.libelle}</td>
    <td>${p.prixUnitaire}</td>
    <td>${p.quantiteStock}</td>
</tr>
</c:forEach>

</table>

<br>
<a href="dashboard">Retour</a>

</body>
</html>