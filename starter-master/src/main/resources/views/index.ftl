<#ftl encoding="utf-8">

<html lang="fr">

<body xmlns="http://www.w3.org/1999/html">

<nav>
    <ul>
        <li><a href="/awards">Gommettes attribu&eacute;es</a></li>
        <li><a href="/stickers">Gommettes</a></li>
        <li><a href="/students">El&egrave;ves</a></li>
        <li><a href="/teachers">Enseignants</a></li>
    </ul>
    <ul>
        <li><a href="/login">S'identifier</a></li>
        <#if isAuthorized>
        <li><a href="/signup">Enregistrer un utilisateur</a></li>
        </#if>
    </ul>
</nav>

</body>

</html>
