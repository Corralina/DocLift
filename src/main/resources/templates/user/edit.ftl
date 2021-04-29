<#import "../parts/dom.ftl" as dom>
<@dom.dom>
    <h1 class="justify-content text-success"> ${succes?ifExists} </h1>
    <h1 class="justify-content text-danger"> ${error?ifExists} </h1>
    <div class="mb-3">
        <h1 class="justify-content"> Редагувати ${user?ifExists.information?ifExists.individual?ifExists.initials?ifExists} </h1>
    </div>
    <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex flex-row" style="min-height: 70vh">
        <form action="/user" method="post" class="mb-3 d-flex flex-row" style="width: 100%;" enctype="multipart/form-data">
            <input type="hidden" name="_method" value="patch">
            <div class="d-flex d-flex flex-column p-4 m-2 justify-content-center" style="width: 90%; position: relative">
                <div class="input-group mt-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-default">Прізвище користувача</span>
                    </div>
                    <input name="surname" value="${user?ifExists.information?ifExists.person?ifExists.surname?ifExists}" type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                </div>
                <div class="input-group mt-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-default">Ім'я користувача</span>
                    </div>
                    <input name="name" value="${user?ifExists.information?ifExists.person?ifExists.name?ifExists}" type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                </div>
                <div class="input-group mt-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-default">Батько користувача</span>
                    </div>
                    <input name="middlename" value="${user?ifExists.information?ifExists.person?ifExists.middlename?ifExists}" type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                </div>

                <div class="input-group mt-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-default">Ініціали користувача</span>
                    </div>
                    <input name="initials" value="${user?ifExists.information?ifExists.person?ifExists.initials?ifExists}" type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                </div>
                <div class="input-group mt-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-default">Посада користувача</span>
                    </div>
                    <input name="post" value="${user?ifExists.information?ifExists.person?ifExists.post?ifExists}" type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                </div>


                <div class="input-group mt-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-default">Пошта користувача</span>
                    </div>
                    <input name="mail" value="${user?ifExists.information?ifExists.contact?ifExists.mail?ifExists}" type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                </div>

                <div class="input-group mt-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-default">Телефон користувача</span>
                    </div>
                    <input name="phone" value="${user?ifExists.information?ifExists.contact?ifExists.phone?ifExists}" type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                </div>

                <div class="rd">
                    <label class="m-3">ЕЦП  <input type="file" name="caption" ></label>
                </div>

                <#if user.isLoc()>
                    <div class="border mt-3">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-default">Логін користувача</span>
                            </div>
                            <input name="username" value="${user?ifExists.username?ifExists}" type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                        </div>


                        <div class="input-group mt-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-default">Пароль користувача</span>
                            </div>
                            <input name="password" type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                        </div>
                    </div>
                </#if>
                <div class="row mt-3">
                    <button class="btn btn-success m-3 " type="submit">Зберегти</button>
                </div>
            </div>

            <div class="d-flex flex-column align-items-center justify-content-center p-4 my_res_doc_info_block">
                <div class="mt-3">
                    <h4 class="justify-content"> Права </h4>
                </div>
                <div class="d-flex  d-flex flex-column mt-3">
                    <#list roles as role>
                        <div style="display: inline-block">
                            <input type="checkbox" id="${role}" name="${role}" ${user?ifExists.roles?seqContains(role)?string("checked","")}>
                            <label for="${role}">${role.getNameByRole(role)}</label>
                        </div>
                    </#list>
                </div>
            </div>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <input type="hidden" class="inpV" value="${user?ifExists.id?ifExists}" name="userId">
        </form>
    </div>


    <script type="text/javascript" charset="utf8" src="/static/pathCorrect.js"></script>
</@dom.dom>