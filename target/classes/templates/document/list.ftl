<#include "../parts/security.ftl">
<#import "../parts/dom.ftl" as dom>

<@dom.dom>
    <h1 class="justify-content text-success"> ${succes?ifExists} </h1>
    <h1 class="justify-content text-danger"> ${error?ifExists} </h1>
    <div class="mb-3">
        <h1 class="justify-content"> Архів документів </h1>
    </div>
    <div class="shadow-lg mb-5 bg-white rounded p-3 d-flex flex-column">

        <div class="d-flex flex-row justify-content-around">
            <div class="form-group d-flex flex-column my_filter_input" >
                <label for="dateStart">Дата з</label>
                <input name="dateStart" value="${ds?ifExists}"  type="date"  class="form-control" id="dateStart" aria-describedby="inputGroup-sizing-default"/>
            </div>
            <div class="form-group d-flex flex-column my_filter_input" >
                <label for="dateFinish">Дата по</label>
                <input name="dateStop" value="${df?ifExists}"  type="date"  class="form-control" id="dateFinish" aria-describedby="inputGroup-sizing-default"/>
            </div>
            <div class="form-group d-flex flex-column my_filter_input" >
                <label for="status">Статус документу</label>
                <select name="agrees" class="custom-select mr-sm-2" id="status" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                    <option value="all">Усі документи</option>
                    <option value="down">Завантажені документи</option>
                    <option value="wr">Розписані документи</option>
                </select>
            </div>
        </div>

        <div class="d-flex flex-row justify-content-around">
            <div class="form-group d-flex flex-column my_filter_input" >
                <label for="n">Номер</label>
                <input name="number" value="${n?ifExists}"  type="text" class="form-control" id="n" aria-describedby="inputGroup-sizing-default"/>
            </div>
            <div class="form-group d-flex flex-column my_filter_input" >
                <label for="a">Автор</label>
                <input name="autor" value="${a?ifExists}"  type="text" class="form-control" id="a" aria-describedby="inputGroup-sizing-default"/>
            </div>
            <div class="row p-3 mt-2 my_filter_button" >
                <button class="btn btn-primary my_button" id="search" type="submit" >Пошук</button>
            </div>
        </div>
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
    </div>
    <div class="mt-5 shadow-lg mb-5 bg-white rounded p-3">
    <table class="table mt-5 table-bordered table-hover horizontal-align" id="tableId">
        <thead class="table mt-5 table-bordered table-hover horizontal-align" >
        <tr>
            <th>Статус документу</th>
            <th>Номер документу</th>
            <th>Дата створення документу</th>
            <th>Автор документу</th>
            <th>Завантажити документ</th>
            <#if user.isAdmin() || user.isRecorted()>
                <th>Cтворити резолюцію</th>
            </#if>
        </tr>
        </thead>
        <tbody id="tablebody">
        <#list documents as document>
            <tr>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">
                    <#if document.resolution?ifExists>
                        Документ розписано
                    <#else >
                        Документ завантажено
                    </#if>
                    </div>
                </td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${document?ifExists.number?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${document?ifExists.date?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${document?ifExists.author?ifExists.information?ifExists.person?ifExists.initials?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">
                    <#if document.author.username == username>
                        <a href="/pdf/${document?ifExists.name}" target="_blank">Відкрити файл</a>
                    <#else>
                        ---
                    </#if>
                    </div>
                </td>
                <#if user.isAdmin() || user.isRecorted()>
                    <td>
                        <div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">
                            <a class="pathC" href="/resolution/new/${document?ifExists.id}">Розписати</a>
                        </div>
                    </td>
                </#if>
            </tr>
        </#list>
        </tbody>
    </table>
    </div>

    <script type="text/javascript" charset="utf8" src="/static/main.js"></script>

    <script type="text/javascript" charset="utf8" src="/static/document-filter.js"></script>
</@dom.dom>