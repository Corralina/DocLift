<#import "parts/dom.ftl" as dom>

<@dom.dom>
    <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex flex-row" style="height: 70vh">
        <div class="d-flex align-items-center justify-content-center" style="width: 60%; position: relative">
            <div class="" style="position: absolute; top: 20px; left: 15px; color: red">
                <#if error??>
                    <p>* ${error?ifExists}</p>
                </#if>
            </div>
            <form action="/login" class="px-4 py-3" method="post">
                <h2>Авторизація</h2>
                <div class="form-group mt-4">
                    <label for="exampleDropdownFormEmail1">Логін</label>
                    <input type="text"  name="username" class="form-control" id="exampleDropdownFormEmail1" placeholder="Логін">
                </div>
                <div class="form-group mt-3">
                    <label for="exampleDropdownFormPassword1">Пароль</label>
                    <input type="password" name="password" class="form-control" id="exampleDropdownFormPassword1" placeholder="пароль">
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary my_button mt-3" type="submit" value="Sign In">Вхід</button>
            </form>
        </div>
        <div class="d-flex flex-row align-items-center justify-content-center p-4" style="width: 100%; background-image: url(/static/header_savoy.png), linear-gradient(90deg,rgb(39,56,153),rgb(73,109,211));background-position: right; background-repeat: no-repeat">
            <div class="my_login_text mt-5 d-flex flex-column align-items-center justify-content-center" style="color: #d1c3a1; font-size: 3rem">
                <h1 style="font-size: 3rem">Документальний</h1>
                <h1 style="font-size: 3rem">Ліфт</h1>
            </div>
        </div>
    </div>


    <script>
        document.getElementById("navBar").style.display = "none";
    </script>


</@dom.dom>