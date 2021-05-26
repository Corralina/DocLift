<#import "../parts/dom.ftl" as dom>
<@dom.dom>
    <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex  justify-content-center" style="height: 70vh; width: 90%">
        <div class="d-flex flex-column  align-items-center justify-content-center" style="position: relative; width: 80%">
            <div class="mb-3">
                <h1 class="justify-content">Заповнення контактних данних </h1>
            </div>
            <div class="" style="position: absolute; top: 20px; left: 15px; color: red">
                <#if error??>
                    <p>* ${error?ifExists}</p>
                </#if>
            </div>
            <form action="/registration/contact" method="post" class="px-4 py-3" style="width: 100%">

                <div class="form-group mt-4">
                    <label for="exampleDropdownFormEmail1">Пошта користувача</label>
                    <input name="mail" type="text" value="${contact?ifExists.mail?ifExists}" class="form-control" id="exampleDropdownFormEmail1" placeholder="пошта">
                </div>
                <div class="form-group mt-3">
                    <label for="exampleDropdownFormPassword1">Телефон користувача</label>
                    <input name="phone" type="text" class="form-control bfn-phone" data-format="380 (dd) dd dd ddd" value="${contact?ifExists.phone?ifExists}" id="exampleDropdownFormPassword1">
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" class="inpV" value="${individual?ifExists.id?ifExists}" name="individual">
                <button class="btn btn-primary my_button mt-3" style="float:right;" type="submit" >Далі</button>
            </form>
        </div>
    </div>

    <script type="text/javascript" charset="utf8" src="/static/pathCorrect.js"></script>

</@dom.dom>