<#import "../parts/dom.ftl" as dom>
<#include "../parts/security.ftl">

<@dom.dom>
    <h1 class="justify-content text-success"> ${succes?ifExists} </h1>
    <h1 class="justify-content text-danger"> ${error?ifExists} </h1>
    <div class="mb-3">
        <h1 class="justify-content"> Резолюції </h1>
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
                    <label for="c">Керівник</label>
                    <input name="confirm" value="${c?ifExists}"  type="text" class="form-control" id="c" aria-describedby="inputGroup-sizing-default"/>
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
        <thead class="mt-5 shadow-lg mb-5 bg-white rounded p-3">
        <tr>
            <th>Номер документу</th>
            <th>Дата</th>
            <th>Керівник</th>
            <th>Автор</th>
            <th>Завантажити файл</th>
            <th>Show</th>
        </tr>
        </thead>
        <tbody id='tablebody'>
        <#list resolutions?ifExists as resolution>
            <tr>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${resolution?ifExists.document?ifExists.number?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${resolution?ifExists.visa?ifExists.data?ifExists}|</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${resolution?ifExists.agrees?ifExists.information?ifExists.person?ifExists.initials?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${resolution?ifExists.filling?ifExists.information?ifExists.person?ifExists.initials?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row"><a href="/pdf/${resolution?ifExists.document?ifExists.name?ifExists}" target="_blank">Відкрити файл</a></div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row"><a class="pathC" href="/resolution/${resolution?ifExists.id?ifExists}">Перегляд</a></div></td>
            </tr>
        </#list>
        </tbody>
    </table>
    </div>

    <script type="text/javascript" charset="utf8" src="/static/main.js"></script>

    <script type="text/javascript" charset="utf8" src="/static/resolutionSearch.js"></script>
    <script type="text/javascript" charset="utf8" src="/static/pathCorrect.js"></script>

</@dom.dom>