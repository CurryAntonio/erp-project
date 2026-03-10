<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Stocks - ERP</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
    <div class="container">
        <nav style="display: flex; justify-content: space-between; align-items: center; padding: 10px 0; border-bottom: 2px solid #eee; margin-bottom: 20px;">
            <a href="${pageContext.request.contextPath}/dashboard">🏠 Retour Accueil</a>
            <span>Session : <strong>${sessionScope.user.login}</strong></span>
        </nav>
        <h2>Inventaire des Produits</h2>
        <table>
            <thead>
                <tr><th>Libellé</th><th>Référence</th><th>Prix</th><th>Stock</th></tr>
            </thead>
            <tbody>
                <c:forEach items="${produits}" var="p">
                    <tr>
                        <td>${p.libelle}</td>
                        <td>${p.reference}</td>
                        <td><fmt:formatNumber value="${p.prixUnitaire}" groupingUsed="true"/> Ar</td>
                        <td>
                            <c:choose>
                                <c:when test="${sessionScope.user.role == 'CLIENT'}">Disponible</c:when>
                                <c:otherwise>${p.quantiteStock} unités</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>