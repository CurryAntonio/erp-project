<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>ERP - Annuaire des Utilisateurs</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        .badge { padding: 4px 8px; border-radius: 4px; font-size: 0.85em; font-weight: bold; color: white; }
        .bg-admin { background-color: #e74c3c; }
        .bg-gest { background-color: #3498db; }
        .bg-client { background-color: #27ae60; }
    </style>
</head>
<body>
<div class="container">
    <nav>
        <a href="${pageContext.request.contextPath}/dashboard">🏠 Retour au Tableau de bord</a>
    </nav>

    <c:choose>
        <%-- Seuls les membres du personnel peuvent accéder à cette page --%>
        <c:when test="${sessionScope.user.role == 'ADMIN' || sessionScope.user.role == 'GESTIONNAIRE'}">
            <h2>Annuaire des Utilisateurs</h2>
            <table>
                <thead>
                    <tr>
                        <th>Identifiant (Login)</th>
                        <th>Rôle</th>
                        <%-- Seul l'admin voit la colonne d'action --%>
                        <c:if test="${sessionScope.user.role == 'ADMIN'}">
                            <th>Action</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${usersList}" var="u">
                        <%-- Filtrage logique : Admin voit tout, Gestionnaire ne voit que les comptes 'CLIENT' --%>
                        <c:if test="${sessionScope.user.role == 'ADMIN' || (sessionScope.user.role == 'GESTIONNAIRE' && u.role == 'CLIENT')}">
                            <tr>
                                <td>${u.login}</td>
                                <td>
                                    <span class="badge ${u.role == 'ADMIN' ? 'bg-admin' : (u.role == 'GESTIONNAIRE' ? 'bg-gest' : 'bg-client')}">
                                        ${u.role}
                                    </span>
                                </td>
                                <c:if test="${sessionScope.user.role == 'ADMIN'}">
                                    <td>
                                        <a href="${pageContext.request.contextPath}/admin/users" class="btn btn-primary" style="padding: 2px 10px;">Gérer le profil</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="alert alert-error" style="margin-top: 20px; color: #e74c3c;">
                Accès refusé. Cette section est réservée au personnel autorisé.
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>