<#import "../parts/dom.ftl" as dom>
<@dom.dom>
    <h1 class="justify-content text-success"> ${succes?ifExists} </h1>
    <h1 class="justify-content text-danger"> ${error?ifExists} </h1>
    <h1 class="justify-content-center text-center"> Резолюція № ${resolution.document.number?ifExists} </h1>


    <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex flex-row" style="min-height: 50vh">

        <div class="shadow-lg mb-5 bg-white rounded border p-2 d-flex flex-row  justify-content-around my_resolution">

            <div style="width: 100%">
                <p class="text-center font-weight-bold colorS">
                    ${resolution?ifExists.visa?ifExists.position?ifExists}
                </p>
                <p class="text-center font-weight-bold colorS">
                    Сьомого апеляційного
                </p>
                <p class="text-center font-weight-bold colorS">
                    адміністративного суду
                </p>
                <div class="my_resolution_line1" style="width: 100%; height: 2px"></div>
                <div class="my_resolution_line2" style="width: 100%; height: 5px; margin-top: 2px"></div>
                <#list statics?ifExists as performer>
                    <p class="text-center font-weight-bold">
                        ${performer?ifExists.initials?ifExists}
                    </p>
                    <p class="text-center font-italic">
                        ${performer?ifExists.doer?ifExists}
                    </p>
                </#list>
                <div class="my_resolution_agree" id="cap">
                    <div class="d-flex flex-row  justify-content-center my_resolution_caption_block">
                        <#if resolution?ifExists.agrees?ifExists.information?ifExists.person?ifExists.caption??>
                            <img src="/caption/${resolution?ifExists.agrees?ifExists.information?ifExists.person?ifExists.caption?ifExists}" class="my_resolution_caption_image">
                        </#if>
                    </div>
                    <p class="text-right font-weight-bold font-italic colorS">
                        ${resolution?ifExists.visa?ifExists.agrees?ifExists}
                    </p>
                    <p class="text-right font-weight-bold font-italic colorS">
                        ${resolution?ifExists.date?ifExists}
                    </p>
                </div>
            </div>

        </div>

        <div class="d-flex flex-row p-4 pl-3">
            <div class="my_resolution_text d-flex flex-column justify-content-around " >
                <label>${status?ifExists}</label>
                <label>Дата резолюції: ${resolution?ifExists.visa?ifExists.data?ifExists}</label>
                <label>коментар: ${resolution?ifExists.coment?ifExists}</label>
                <label>Розписав резолюцію: ${resolution?ifExists.filling?ifExists.information?ifExists.person?ifExists.surname?ifExists} ${resolution.filling?ifExists.information?ifExists.person?ifExists.name?ifExists} ${resolution.filling?ifExists.information?ifExists.person?ifExists.middlename?ifExists}</label>
                <#if resolutionFile??>
                    <div class="d-flex flex-row">
                        <a class="nav-link" id="add_doc" href="/pdf/${resolution?ifExists.document?ifExists.name?ifExists}" title="Відкрити документ" target="_blank"><img src="/static/openDocument.svg" alt=""></a>
                        <#--                        <a class="nav-link" id="add_doc" href="/img/${resolution?ifExists.document?ifExists.image?ifExists}" title="Відкрити картинку" target="_blank"><img src="/static/openImage.svg" alt=""></a>-->
                        <a class="nav-link" id="add_doc" href="/resolve/${resolutionFile}" title="Відкрити резолюцію" target="_blank"><img src="/static/openResolve.svg" alt=""></a>
                    </div>
                <#else >
                    <div class="d-flex flex-row">
                        <a class="nav-link" id="add_doc" href="/pdf/${resolution?ifExists.document?ifExists.name?ifExists}" title="Відкрити документ" target="_blank"><img src="/static/openDocument.svg" alt=""></a>
                        <#--                        <a class="nav-link" id="add_doc" href="/img/${resolution?ifExists.document?ifExists.image?ifExists}" title="Відкрити картинку" target="_blank"><img src="/static/openImage.svg" alt=""></a>-->
                    </div>
                    <form action="/resolution/form" method="post" enctype="multipart/form-data">
                        <#if resolution.agrees.information.person.caption?ifExists??>
                            <input type="checkbox" id="cap" name="cap" checked>
                            <label style="font-size: 1rem" for="cap">Присутність підпису</label>
                        </#if>
                        <div class="row mt-3">
                            <button class="btn btn-primary my_button ml-3" type="submit">Сформувати резолюцію</button>
                        </div>
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                        <input type="hidden" class="inpV" value="${resolution.id}" name="resolution">
                    </form>
                </#if>

            </div>
        </div>




    </div>


    <div class="container mt-5 d-flex flex-column">
        <#if user.isAdmin() || user.id == resolution.agrees.id || user.id == resolution.filling.id>
            <table class="table mt-5 table-bordered table-hover horizontal-align">
                <thead class="mt-5 shadow-lg mb-5 bg-white rounded p-3">
                <tr>
                    <th>Дата</th>
                    <th>На підставі</th>
                </tr>
                </thead>
                <tbody>
                <#list revers?ifExists as rev>
                    <tr>
                        <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${rev?ifExists.date?ifExists}</div></td>
                        <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${rev?ifExists.coment?ifExists}</div></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </#if>
    </div>


    <script type="text/javascript" charset="utf8" src="/static/pathCorrect.js"></script>

</@dom.dom>