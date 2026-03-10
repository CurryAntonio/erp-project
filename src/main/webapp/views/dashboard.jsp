<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>ERP - Tableau de bord</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
    <div class="container">
        <nav style="display: flex; justify-content: space-between; align-items: center; padding: 10px 0; border-bottom: 2px solid #eee;">
            <div>
                <a href="${pageContext.request.contextPath}/dashboard">Accueil</a>

                <%-- Seul le staff voit le menu Clients --%>
                <c:if test="${sessionScope.user.role != 'CLIENT'}">
                    <a href="${pageContext.request.contextPath}/clients">Annuaire Clients</a>
                </c:if>

                <a href="${pageContext.request.contextPath}/produits">Stocks</a>
                <a href="${pageContext.request.contextPath}/commande">Mes Commandes</a>
                <a href="${pageContext.request.contextPath}/factures">Factures</a>

                <%-- Seul l'ADMIN peut accéder à la gestion des comptes --%>
                <c:if test="${sessionScope.user.role == 'ADMIN'}">
                    <a href="${pageContext.request.contextPath}/admin/users" style="color: #e74c3c; font-weight: bold;">⚙️ Administration</a>
                </c:if>
            </div>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Déconnexion</a>
        </nav>

        <header style="margin-top: 30px;">
            <h1>Tableau de bord</h1>
            <p>Bienvenue, <strong>${sessionScope.user.login}</strong> | Profil :
               <span class="badge" style="background-color: #3498db; color: white; padding: 2px 8px; border-radius: 4px;">
                   ${sessionScope.user.role}
               </span>
            </p>
        </header>

        <c:if test="${param.success == '1'}">
            <div class="alert alert-success" style="padding: 10px; background: #d4edda; color: #155724; border-radius: 4px;">
                Action effectuée avec succès !
            </div>
        </c:if>

        <div style="display:grid; grid-template-columns: 1fr 1fr; gap:20px; margin-top:30px;">
            <div class="container" style="margin:0; border: 1px solid #ddd; padding: 20px; border-radius: 8px;">
                <h3>Raccourcis rapides</h3>
                <ul style="list-style: none; padding: 0;">
                    <li style="margin-bottom: 10px;">
                        <a href="${pageContext.request.contextPath}/commande">🛒 Passer une nouvelle commande</a>
                    </li>
                    <c:if test="${sessionScope.user.role != 'CLIENT'}">
                        <li>
                            <a href="${pageContext.request.contextPath}/produits">📦 Consulter l'état des stocks</a>
                        </li>
                    </c:if>
                </ul>
            </div>

            <div class="container" style="margin:0; border: 1px solid #ddd; padding: 20px; border-radius: 8px;">
                <h3>État du système</h3>
                <p>Statut : <span style="color:green; font-weight:bold;">Opérationnel</span></p>
                <p>Date actuelle : <%= new java.util.Date().toLocaleString() %></p>
            </div>
        </div>
    </div>
</body>
</html>