<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>ERP - Commandes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<div class="container">

    <nav style="display: flex; justify-content: space-between; align-items: center; padding: 10px 0; border-bottom: 2px solid #eee; margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}/dashboard">🏠 Retour Accueil</a>
        <a href="${pageContext.request.contextPath}/logout" style="color:red;">Déconnexion</a>
    </nav>
    <h2>Suivi des Commandes</h2>

    <c:if test="${sessionScope.user.role == 'CLIENT'}">
        <div style="margin-bottom: 20px;">
            <button class="btn btn-success">🛒 Passer une nouvelle commande</button>
        </div>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>Client</th>
                <th>Date</th>
                <th>Total</th>
                <th>État</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${commandes}">
                <tr>
                    <td>${c.client.nom}</td>
                    <td>${c.date}</td>
                    <td>${c.montantTotal} Ar</td>
                    <td>${c.etat}</td>
                    <td>
                        <c:if test="${c.etat == 'EN_ATTENTE' && sessionScope.user.role != 'CLIENT'}">
                            <form action="commande" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="valider">
                                <input type="hidden" name="id" value="${c.id}">
                                <button type="submit" class="btn btn-success">Valider</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>