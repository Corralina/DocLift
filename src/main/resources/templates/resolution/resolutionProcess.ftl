<#import "../parts/dom.ftl" as dom>
<@dom.dom>
    <h1 class="justify-content text-success"> ${succes?ifExists} </h1>
    <h1 class="justify-content text-danger"> ${error?ifExists} </h1>
    <h1 class="justify-content-center text-center"> Резолюція № ${resolution?ifExists.document?ifExists.number?ifExists} </h1>


    <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex flex-row" style="min-height: 50vh">
        <div class="shadow-lg mb-5 bg-white rounded border p-2 d-flex flex-row  justify-content-around my_resolution">

            <div style="width: 100%">
                <p class="text-center font-weight-bold">
                    ${resolution?ifExists.agrees?ifExists.information?ifExists.person?ifExists.post?ifExists}
                </p>
                <p class="text-center font-weight-bold">
                    Сьомого апеляційного
                </p>
                <p class="text-center font-weight-bold">
                    адміністративного суду
                </p>
                <div class="my_resolution_line1"></div>
                <div class="my_resolution_line2"></div>
                <#list performers?ifExists as performer>
                    <p class="text-center font-weight-bold">
                        ${performer?ifExists.user?ifExists.information?ifExists.person?ifExists.initials?ifExists}
                    </p>
                    <p class="text-center font-italic">
                        ${performer?ifExists.coment?ifExists}
                    </p>
                </#list>
                <div class="my_resolution_agree" id="cap">
                    <div class="d-flex flex-row  justify-content-center my_resolution_caption_block">

                    </div>
                    <p class="text-right font-weight-bold font-italic">
                        ${resolution?ifExists.agrees?ifExists.information?ifExists.person?ifExists.initials?ifExists}
                    </p>
                    <p class="text-right font-weight-bold font-italic">
                        ${resolution?ifExists.date?ifExists}
                    </p>
                </div>
            </div>

        </div>

        <div class="d-flex flex-row p-4 pl-3">
            <div class="my_resolution_text d-flex flex-column justify-content-around " >
                <label>${status?ifExists}</label>
                <label>Дата резолюції: ${resolution?ifExists.date?ifExists}</label>
                <label>коментар: ${resolution?ifExists.coment?ifExists}</label>
                <label>Розписав резолюцію: ${resolution?ifExists.filling?ifExists.information?ifExists.person?ifExists.surname?ifExists} ${resolution?ifExists.filling?ifExists.information?ifExists.person?ifExists.name?ifExists} ${resolution?ifExists.filling?ifExists.information?ifExists.person?ifExists.middlename?ifExists}</label>
                <div class="d-flex flex-row">
                    <a class="nav-link" id="add_doc" href="/pdf/${resolution?ifExists.document?ifExists.name?ifExists}" title="Відкрити документ" target="_blank"><img src="/static/openDocument.svg" alt=""></a>
               </div>
            </div>
        </div>
    </div>

    <#if !resolution.status.finish && !resolution.status.revers>
        <#if user.isAdmin() || user.id == resolution.agrees.id>
        <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex flex-row justify-content-around p-4">
            <div>
                <form action="/resolution/visa" method="post">
                    <button class="btn btn-primary my_button mt-2" type="submit">Підтвердити</button>
                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                    <input type="hidden" class="inpV" value="${resolution?ifExists.id?ifExists}" name="resolution">
                </form>
            </div>
            <div>
                <form action="/resolution/revers" method="post">
                    <div class="input-group mt-3">
                        <input type="text" style="width: 310px" class="form-control" name="coment" placeholder="Причина повернення" aria-label="Recipient's username" aria-describedby="button-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-primary my_button" type="submit" id="button-addon2">Повернути</button>
                        </div>
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                        <input type="hidden" class="inpV" value="${resolution?ifExists.id?ifExists}" name="resolution">
                    </div>
                </form>

            </div>
        </div>
        </#if>
    </#if>

    <script type="text/javascript" charset="utf8" src="/static/pathCorrect.js"></script>

</@dom.dom>