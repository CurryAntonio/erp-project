<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>ERP - Factures</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<div class="container">
    <nav style="display: flex; justify-content: space-between; align-items: center; padding: 10px 0; border-bottom: 2px solid #eee; margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}/dashboard">🏠 Retour Accueil</a>
        <strong>Journal des ventes</strong>
    </nav>
    <h2>Historique des Factures</h2>
    <table>
        <thead>
            <tr>
                <th>N° Facture</th>
                <th>Date</th>
                <th>Montant TTC</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${factures}" var="f">
                <tr>
                    <td>FAC-${f.id}</td>
                    <td>${f.dateFacturation}</td>
                    <td>
                        <strong><fmt:formatNumber value="${f.montantTotalTTC}" groupingUsed="true"/> Ar</strong>
                    </td>
                    <td>
                        <a href="factures?action=view&id=${f.id}" class="btn">👁 Voir</a>
                        <a href="factures?action=download&id=${f.id}" class="btn btn-primary">📥 PDF</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>