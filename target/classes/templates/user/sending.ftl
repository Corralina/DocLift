<#import "../parts/dom.ftl" as dom>
<@dom.dom>

    <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex  justify-content-center" style="height: 70vh; width: 90%">
        <div class="d-flex flex-column  align-items-center justify-content-center" style="position: relative; width: 80%">
            <div class="mb-3">
                <h1 class="justify-content">Змінити відповідального за розпис у телеграмі</h1>
                <h1 class="justify-content">Now - ${userSend?ifExists.user?ifExists.information?ifExists.individual?ifExists.initials?ifExists}</h1>
            </div>
            <div class="" style="position: absolute; top: 20px; left: 15px; color: red">
                <#if error??>
                    <p>* ${error?ifExists}</p>
                </#if>
            </div>
            <form action="/userSending" method="post" class="px-4 py-3" style="width: 100%">
                <div class="form-group mt-4">
                    <label for="exampleDropdownFormEmail1">Користувач</label>
                    <select name="userSend" class="custom-select mr-sm-2" id="inlineFormCustomSelect" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                        <#list users?ifExists as user>
                            <option  value="${user?ifExists.id?ifExists}">${user?ifExists.information?ifExists.individual?ifExists.initials?ifExists}</option>
                        </#list>
                    </select>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" class="inpV" value="${userSend?ifExists.id?ifExists}" name="send">
                <button class="btn btn-primary my_button mt-3"  type="submit" >Зберегти</button>
            </form>
        </div>
    </div>

    <script type="text/javascript" charset="utf8" src="/static/pathCorrect.js"></script>
</@dom.dom>