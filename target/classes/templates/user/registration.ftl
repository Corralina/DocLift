<#import "../parts/dom.ftl" as dom>
<@dom.dom>

    <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex  justify-content-center" style="height: 70vh; width: 90%">
        <div class="d-flex flex-column  align-items-center justify-content-center" style="position: relative; width: 80%">
            <div class="mb-3">
                <h1 class="justify-content">Зареєструвати користувача  </h1>
            </div>
            <div class="" style="position: absolute; top: 20px; left: 15px; color: red">
                <#if error??>
                    <p>* ${error?ifExists}</p>
                </#if>
            </div>
            <form action="registration" method="post" class="px-4 py-3" style="width: 100%">
                <div class="form-group mt-4">
                    <label for="exampleDropdownFormEmail1">Логін</label>
                    <input type="text" value="${user?ifExists.username?ifExists}"  name="username" class="form-control" id="exampleDropdownFormEmail1" placeholder="Логін">
                </div>
                <div class="form-group mt-3">
                    <label for="exampleDropdownFormPassword1">Пароль</label>
                    <input type="password" value="${user?ifExists.password?ifExists}" name="password" class="form-control" id="exampleDropdownFormPassword1" placeholder="пароль">
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" class="inpV" value="${information?ifExists.id?ifExists}" name="information">
                <button class="btn btn-primary my_button mt-3" style="float:right;" type="submit" >Зберегти</button>
            </form>
        </div>
    </div>

    <script type="text/javascript" charset="utf8" src="/static/pathCorrect.js"></script>

</@dom.dom>