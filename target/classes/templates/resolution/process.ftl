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
                <input name="dateStart" value="${ds?ifExists}"  type="date"  class="form-control" id="dateStart" aria-describedby="inputGroup-sizing-default">
            </div>
            <div class="form-group d-flex flex-column my_filter_input" >
                <label for="dateFinish">Дата по</label>
                <input name="dateStop" value="${df?ifExists}"  type="date"  class="form-control" id="dateFinish" aria-describedby="inputGroup-sizing-default">
            </div>
            <div class="form-group d-flex flex-column my_filter_input" >
                <label for="c">Керівник</label>
                <input name="confirm" value="${c?ifExists}"  type="text" class="form-control" id="c" aria-describedby="inputGroup-sizing-default">
            </div>
        </div>

        <div class="d-flex flex-row justify-content-around">
            <div class="form-group d-flex flex-column my_filter_input" >
                <label for="n">Номер</label>
                <input name="number" value="${n?ifExists}"  type="text" class="form-control" id="n" aria-describedby="inputGroup-sizing-default">
            </div>
            <div class="form-group d-flex flex-column my_filter_input" >
                <label for="a">Автор</label>
                <input name="autor" value="${a?ifExists}"  type="text" class="form-control" id="a" aria-describedby="inputGroup-sizing-default">
            </div>
            <div class="row p-3 mt-2 my_filter_button" >
                <button class="btn btn-primary my_button" id="search" type="submit" >Пошук</button>
            </div>
        </div>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
    </div>

    <div class="mt-5 shadow-lg mb-5 bg-white rounded p-3">
    <table class="table mt-5 " id="tableId">
        <thead class="mt-5 shadow-lg mb-5 bg-white rounded p-3">
        <tr >
            <th>Статус</th>
            <th>Номер документу</th>
            <th>Дата</th>
            <th>Керівник</th>
            <th>Автор</th>
            <th>Завантажити файл</th>
            <th>Show</th>
        </tr>
        </thead>

        <tbody>
        <#list resolutions?ifExists as resolution>
            <tr>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">
                    <#if resolution?ifExists.status?ifExists.finish>
                        Накладено резолюцію
                    </#if>
                    <#if resolution?ifExists.status?ifExists.revers>
                        Повернено до редагування
                    </#if>
                    <#if !resolution?ifExists.status?ifExists.finish && !resolution.status?ifExists.revers>
                        Очікує накладення резолюції
                    </#if>
                    </div>
                </td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row" >${resolution?ifExists.document?ifExists.number?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row" >${resolution?ifExists.date?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row" >${resolution?ifExists.agrees?ifExists.information?ifExists.person?ifExists.initials?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row" >${resolution?ifExists.filling?ifExists.information?ifExists.person?ifExists.initials?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row" ><a href="/pdf/${resolution?ifExists.document?ifExists.name?ifExists}" target="_blank">Відкрити файл</a></div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row" ><a class="pathC" href="/resolution/${resolution?ifExists.id?ifExists}">Перегляд</a></div></td>
            </tr>
        </#list>
        </tbody>
    </table>
    </div>


    <script type="text/javascript" charset="utf8" src="/static/main.js"></script>
    <script type="text/javascript" charset="utf8" src="/static/pathCorrect.js"></script>
    <script type="text/javascript">
        document.getElementById("search").addEventListener("click", function (ev) {
            var c = 0;
            var req = "http://localhost:8080/resolution/process";
            if (document.getElementById("dateStart").value != ""){
                req += "?ds=" + document.getElementById("dateStart").value;
                c = 1;
            }
            if (document.getElementById("dateFinish").value != ""){
                if (c == 1){
                    req += "&";
                }else {
                    req += "?";
                    c = 1;
                }
                req += "df=" + document.getElementById("dateFinish").value;
            }
            if (document.getElementById("n").value != ""){
                if (c == 1){
                    req += "&";
                }else {
                    req += "?";
                    c = 1;
                }
                req += "n=" + document.getElementById("n").value
            }
            if (document.getElementById("a").value != ""){
                if (c == 1){
                    req += "&";
                }else {
                    req += "?";
                    c = 1;
                }
                req += "a=" + document.getElementById("a").value
            }
            if (document.getElementById("c").value != ""){
                if (c == 1){
                    req += "&";
                }else {
                    req += "?";
                    c = 1;
                }
                req += "c=" + document.getElementById("c").value
            }
            document.location.href = req;
        })
    </script>



</@dom.dom>