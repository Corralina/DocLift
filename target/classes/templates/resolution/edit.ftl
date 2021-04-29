<#import "../parts/dom.ftl" as dom>
<@dom.dom>
    <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex flex-row" style="min-height: 70vh">
        <div class="d-flex align-items-center justify-content-center" style="width: 60%; position: relative">
            <div class="" style="position: absolute; top: 20px; left: 15px; color: red">
                <#if error??>
                    <p>* ${error?ifExists}</p>
                </#if>
            </div>
            <form action="/resolution/edit" method="post" enctype="multipart/form-data" class="px-4 py-3">
                <h2>Редагування резолюції</h2>
                <div class="form-group mt-4">
                    <label for="inlineFormCustomSelect">Керівник</label>
                    <select name="agrees" class="custom-select mr-sm-2" id="inlineFormCustomSelect" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                        <#list confirms?ifExists as user>
                            <#if revers?ifExists.resolution?ifExists.agrees?ifExists.username?ifExists == user?ifExists.username?ifExists>
                                <option selected value="${user?ifExists.id?ifExists}">${user?ifExists.information?ifExists.person?ifExists.initials?ifExists}</option>
                            <#else>
                                <option  value="${user?ifExists.id?ifExists}">${user?ifExists.information?ifExists.person?ifExists.initials?ifExists}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
                <div class="form-group mt-4">
                    <label for="exampleDropdownFormEmail1">Коментар до резолюції</label>
                    <input name="coment" value="${revers?ifExists.resolution?ifExists.coment?ifExists}" type="text" class="form-control" id="exampleDropdownFormEmail1" placeholder="коментар">
                </div>
                <div class="d-flex flex-column mb-3 mt-4">
                    <div class="form-group mb-3" id="pro">
                    </div>
                    <div class="col-sm-10">
                        <a id="newF" href="#">додати виконавця</a>
                    </div>
                </div>

                <div class="form-group mt-3">

                    <button class="btn btn-primary my_button mt-3" type="submit">Зберегти</button>
                </div>

                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <input type="hidden" class="inpV" value="${revers?ifExists.id?ifExists}" name="revers">

            </form>
        </div>

        <div class="d-flex flex-row align-items-center justify-content-center p-4 my_res_doc_info_block">
            <div class="my_login_text mt-5 d-flex flex-column justify-content-around my_res_doc_info" >
                <label>Документ № ${revers?ifExists.resolution?ifExists.document?ifExists.number?ifExists}</label>
                <label>Дата документу: ${revers?ifExists.resolution?ifExists.document?ifExists.date?ifExists}</label>
                <label>коментар до документу: ${revers?ifExists.resolution?ifExists.document?ifExists.coment?ifExists}</label>
                <label>коментар до повернення: ${revers?ifExists.coment?ifExists}</label>
                <label>Документ завантажив: ${revers?ifExists.resolution?ifExists.document?ifExists.author?ifExists.information?ifExists.person?ifExists.initials?ifExists}</label>
                <label><a href="/pdf/${revers?ifExists.resolution?ifExists.document?ifExists.name?ifExists}" target="_blank">Відкрити документ</a></label>

            </div>
        </div>

    </div>


    <script type="text/javascript" charset="utf8" src="/static/pathCorrect.js"></script>

<script>
    count = 1;
    allPerform();

    function allPerform(){
        <#list performers as performer>
        var d = document.createElement("div");
        d.setAttribute("id","performer"+count);
        d.setAttribute("class", "input-group mt-5");
        var s = document.createElement("select");
        s.setAttribute("class", "custom-select mr-sm-2");
        s.setAttribute("name", "performs"+count);
        var z = document.createElement("option");
        z.setAttribute("value", "${performer?ifExists.user?ifExists.username?ifExists}");
        var t = document.createTextNode("${performer?ifExists.user?ifExists.information?ifExists.person?ifExists.initials?ifExists}");
        z.appendChild(t);
        s.appendChild(z);


        var inp = document.createElement("input");
        inp.setAttribute("class","form-control");
        inp.setAttribute("name", "note"+count);
        inp.setAttribute("value","${performer?ifExists.coment?ifExists}");

        var dr = document.createElement("a");
        dr.setAttribute("href","#");
        dr.setAttribute("id","performer"+count);
        var tdr = document.createTextNode("видалити");
        dr.appendChild(tdr);

        d.appendChild(s);
        d.appendChild(inp);
        d.appendChild(dr);
        document.getElementById("pro").appendChild(d);
        dr.addEventListener("click",function () {
            document.getElementById(this.valueOf().id).remove();
        });

        count++;
        </#list>
    }

    document.getElementById("newF").addEventListener("click", function () {
        var d = document.createElement("div");
        d.setAttribute("id","performer"+count);
        d.setAttribute("class", "input-group mt-5");
        var s = document.createElement("select");
        s.setAttribute("class", "custom-select mr-sm-2");
        s.setAttribute("name", "performs"+count);
        <#list users as user>
        var z = document.createElement("option");
        z.setAttribute("value", "${user?ifExists.username?ifExists}");
        var t = document.createTextNode("${user?ifExists.information?ifExists.person?ifExists.initials?ifExists}");
        z.appendChild(t);
        s.appendChild(z);
        </#list>
        var inp = document.createElement("input");
        inp.setAttribute("class","form-control");
        inp.setAttribute("name", "note"+count);
        var dr = document.createElement("a");
        dr.setAttribute("href","#");
        dr.setAttribute("id","performer"+count);
        var tdr = document.createTextNode("видалити");
        dr.appendChild(tdr);
        d.appendChild(s);
        d.appendChild(inp);
        d.appendChild(dr);
        document.getElementById("pro").appendChild(d);
        dr.addEventListener("click",function () {
            document.getElementById(this.valueOf().id).remove();
        });
        count++;
    });

    function dropPref(perfId, c) {
        console.log(perfId);

    }


</script>


</@dom.dom>