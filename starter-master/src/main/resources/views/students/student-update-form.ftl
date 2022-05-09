<#if student?has_content>
    <h2>Modifier une attribution</h2>
    <fieldset>
        <legend>&eacute;l&egrave;ve</legend>
        <form method="POST" action="/students/${student.getId()}">
            <label for="lastname">nom</label>
            <br>
            <input type="text" id="lastname" name="lastname" value="${student.getLastName()}" required>
            <br>
            <label for="firstname">pr&eacute;nom</label>
            <br>
            <input type="text" id="firstname" name="firstname" value="${student.getFirstName()}" required>
            <br>
            <button type="submit">Modifier</button>
        </form>
    </fieldset>
</#if>