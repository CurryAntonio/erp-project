<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>ERP - Connexion</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<div class="container" style="max-width: 450px; margin-top: 80px;">
    <div class="form-card" style="padding: 40px; border-radius: 12px; box-shadow: 0 10px 25px rgba(0,0,0,0.1);">
        <h2 style="text-align: center; margin-bottom: 30px; color: #2c3e50;">Connexion ERP</h2>

        <%-- Gestion des alertes --%>
        <c:if test="${param.error == 1}">
            <div class="alert alert-error" style="margin-bottom: 15px; color: #e74c3c;">Login ou mot de passe incorrect.</div>
        </c:if>
        <c:if test="${param.reg == 'success'}">
            <div class="alert alert-success" style="margin-bottom: 15px; color: #27ae60;">Inscription réussie ! Vous pouvez vous connecter.</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label>Nom d'utilisateur</label>
                <input type="text" name="login" required placeholder="Votre login" class="form-control">
            </div>
            <div class="form-group">
                <label>Mot de passe</label>
                <input type="password" name="password" required placeholder="********" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary btn-block" style="width: 100%; margin-top: 20px;">Se connecter</button>
        </form>

        <div style="margin-top: 30px; border-top: 1px solid #eee; padding-top: 20px; text-align: center;">
            <p style="color: #7f8c8d; font-size: 0.9em; margin-bottom: 15px;">Pas encore membre ?</p>
            <%-- Lien vers le Servlet /register configuré dans votre AuthFilter --%>
            <a href="${pageContext.request.contextPath}/register" class="btn btn-success btn-block" style="display: block; padding: 10px; background: #27ae60; color: white; text-decoration: none; border-radius: 5px;">
                Créer un compte professionnel
            </a>
        </div>
    </div>
</div>
</body>
</html>