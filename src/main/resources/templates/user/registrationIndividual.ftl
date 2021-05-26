<#import "../parts/dom.ftl" as dom>
<@dom.dom>

    <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex  justify-content-center" style="height: 70vh; width: 90%">
        <div class="d-flex flex-column  align-items-center justify-content-center" style="position: relative; width: 80%">
            <div class="mb-3">
                <h1 class="justify-content">Заповнення профілю </h1>
            </div>
            <div class="" style="position: absolute; top: 20px; left: 15px; color: red">
                <#if error??>
                    <p>* ${error?ifExists}</p>
                </#if>
            </div>
            <form action="/registration/individual" method="post" class="px-4 py-3" style="width: 100%">

                <div class="form-group mt-4">
                    <label for="exampleDropdownFormEmail1">Прізвище користувача</label>
                    <input name="surname" type="text" value="${individual?ifExists.surname?ifExists}" class="form-control" id="exampleDropdownFormEmail1" placeholder="прізвище">
                </div>
                <div class="form-group mt-3">
                    <label for="exampleDropdownFormPassword1">Ім'я користувача</label>
                    <input name="name" type="text" value="${individual?ifExists.name?ifExists}" class="form-control" id="exampleDropdownFormPassword1" placeholder="Iм'я">
                </div>
                <div class="form-group mt-3">
                    <label for="exampleDropdownFormPassword1">По батькові користувача</label>
                    <input name="middlename" type="text" value="${individual?ifExists.middlename?ifExists}" class="form-control" id="exampleDropdownFormPassword1" placeholder="по батькові">
                </div>
                <div class="form-group mt-3">
                    <label for="exampleDropdownFormPassword1">ПІБ користувача (скорочене)</label>
                    <input name="initials" type="text" value="${individual?ifExists.initials?ifExists}" class="form-control" id="exampleDropdownFormPassword1" placeholder="ПІБ">
                </div>
                <div class="form-group mt-3">
                    <label for="exampleDropdownFormPassword1">Посада</label>
                    <input name="post" type="text" value="${individual?ifExists.post?ifExists}" class="form-control" id="exampleDropdownFormPassword1" placeholder="посада">
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary my_button mt-3" style="float:right;" type="submit" >Далі</button>
            </form>
        </div>
    </div>


</@dom.dom>