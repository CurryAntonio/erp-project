<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>ERP - Administration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<div class="container">
    <nav><a href="${pageContext.request.contextPath}/dashboard">🏠 Dashboard</a></nav>
    <h2>Gestion des Accès Utilisateurs</h2>

    <table>
        <thead>
            <tr>
                <th>Utilisateur</th>
                <th>Rôle Actuel</th>
                <th>Modifier Rôle</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${usersList}" var="u">
                <tr>
                    <td>${u.login}</td>
                    <td><span class="btn-primary" style="padding:2px 8px; border-radius:4px;">${u.role}</span></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/admin/users" method="post" style="display:flex; gap:10px;">
                            <input type="hidden" name="userId" value="${u.id}">
                            <select name="newRole">
                                <option value="CLIENT" ${u.role == 'CLIENT' ? 'selected' : ''}>CLIENT</option>
                                <option value="GESTIONNAIRE" ${u.role == 'GESTIONNAIRE' ? 'selected' : ''}>GESTIONNAIRE</option>
                                <option value="ADMIN" ${u.role == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
                            </select>
                            <button type="submit" class="btn btn-success">Mettre à jour</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>