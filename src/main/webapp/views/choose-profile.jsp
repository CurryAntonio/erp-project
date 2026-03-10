<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>ERP - Finaliser Profil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<div class="container" style="max-width: 500px; margin-top: 80px;">
    <div class="form-card">
        <h2>Complétez votre profil</h2>
        <p class="subtitle">Dernière étape pour finaliser votre accès à l'ERP</p>

        <form action="${pageContext.request.contextPath}/complete-profile" method="post">
            <div class="form-group">
                <label for="role">Je suis un :</label>
                <select name="role" id="role" class="form-control role-select" required>
                    <option value="" disabled selected>Choisissez votre fonction...</option>
                    <option value="CLIENT">Client (Particulier)</option>
                    <option value="GESTIONNAIRE">Gestionnaire (Staff)</option>
                    <option value="ADMIN">Administrateur</option>
                </select>
            </div>

            <p style="font-size: 0.8em; color: #95a5a6; margin-bottom: 20px;">
                Note : Votre choix déterminera vos droits d'accès sur la plateforme.
            </p>

            <button type="submit" class="btn btn-primary btn-block">Valider mon inscription</button>
        </form>
    </div>
</div>
</body>
</html>