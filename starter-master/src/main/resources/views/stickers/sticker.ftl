<#ftl encoding="utf-8">

<html lang="fr">
<#include "../bits/head.html">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">

<p>
    Gommette num&eacute;ro : ${sticker.getId()} <br>
    ${sticker.getColor().toString()} ; ${sticker.getDescription().toString()}
</p>

</body>

</html>
