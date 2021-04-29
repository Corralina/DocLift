<#macro logaut>
    <div>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-primary my_button" type="submit">Вихід</button>
        </form>
    </div>
</#macro>

