<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Inscription - ERP</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<div class="container" style="max-width: 600px;">
    <h2>Inscription Professionnelle</h2>
    <form action="${pageContext.request.contextPath}/register" method="post">
        <div class="form-group"><label>Nom</label><input type="text" name="nom" required></div>
        <div class="form-group"><label>Prénom</label><input type="text" name="prenom" required></div>
        <div class="form-group"><label>Email</label><input type="email" name="email" required></div>
        <div class="form-group"><label>Adresse</label><input type="text" name="adresse" required></div>

        <div class="form-group"><label>Login</label><input type="text" name="login" required></div>
        <div class="form-group">
            <label>Rôle</label>
            <select name="role">
                <option value="CLIENT">Client</option>
                <option value="GESTIONNAIRE">Gestionnaire</option>
                <option value="ADMIN">Administrateur</option>
            </select>
        </div>
        <div class="form-group"><label>Mot de passe</label><input type="password" name="password" required></div>

        <button type="submit" class="btn btn-primary btn-block">S'inscrire</button>
    </form>
</div>
</body>
</html>