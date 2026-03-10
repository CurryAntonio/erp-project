<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Détail Facture FAC-${facture.id}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<div class="container">
    <nav><a href="factures">⬅ Retour à la liste</a></nav>
    <h2>Détail de la Facture FAC-${facture.id}</h2>
    <div class="card" style="padding: 20px; border: 1px solid #ddd; border-radius: 8px;">
        <p><strong>Client :</strong> ${facture.commande.client.nom} ${facture.commande.client.prenom}</p>
        <p><strong>Date :</strong> ${facture.dateFacturation}</p>

        <table border="1" style="width:100%; border-collapse: collapse; margin-top:20px;">
            <thead>
                <tr style="background:#f4f4f4;">
                    <th>Produit</th><th>Prix Unitaire</th><th>Quantité</th><th>Sous-total</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${facture.commande.lignes}" var="ligne">
                    <tr>
                        <td>${ligne.produit.libelle}</td>
                        <td><fmt:formatNumber value="${ligne.produit.prixUnitaire}" groupingUsed="true"/> Ar</td>
                        <td>${ligne.quantite}</td>
                        <td><fmt:formatNumber value="${ligne.prixVenteTotal}" groupingUsed="true"/> Ar</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <h3 style="text-align:right; margin-top:20px;">
            TOTAL TTC : <fmt:formatNumber value="${facture.montantTotalTTC}" groupingUsed="true"/> Ar
        </h3>
    </div>
    <div style="margin-top:20px;">
        <a href="factures?action=download&id=${facture.id}" class="btn btn-primary">📥 Télécharger en PDF</a>
    </div>
</div>
</body>
</html>