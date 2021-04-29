<#import "../parts/dom.ftl" as dom>
<@dom.dom>

    <h1 class="justify-content text-success"> ${succes?ifExists} </h1>
    <h1 class="justify-content text-danger"> ${error?ifExists} </h1>
    <div class="mb-3">
        <h1 class="justify-content"> Список користувачів </h1>
    </div>
    <div class="mt-5 shadow-lg mb-5 bg-white rounded p-3">
        <form action="/userLDAP" method="post" enctype="post" style="min-width: 50vw" >
            <div class="d-flex flex-row justify-content-around">
                <div style="display: inline-block">
                    <input type="checkbox" id="mail" name="mail">
                    <label for="mail">Пошта</label>
                </div>
                <div style="display: inline-block">
                    <input type="checkbox" id="title" name="title">
                    <label for="title">Посада</label>
                </div>
                <div style="display: inline-block">
                    <input type="checkbox" id="name" name="name">
                    <label for="name">ФІО</label>
                </div>
                <#--                <div style="display: inline-block">-->
                <#--                    <input type="checkbox" id="phone" name="phone">-->
                <#--                    <label for="phone">Телефон</label>-->
                <#--                </div>-->
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary my_button mt-3" type="submit" >Оновити</button>
            </div>
        </form>
    </div>
    <div class="mt-5 shadow-lg mb-5 bg-white rounded p-3">
    <table class="table mt-5 table-bordered table-hover horizontal-align" id="tableId" id="tableId">
        <thead class="mt-5 shadow-lg mb-5 bg-white rounded p-3">
        <tr>
            <th>Домен</th>
            <th>Пошта</th>
            <th>Ім'я</th>
            <th>Права</th>
            <th>Редагувати</th>
        </tr>
        </thead>
        <tbody>
        <#list users?ifExists as user>
            <tr>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${user?ifExists.username?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${user?ifExists.information?ifExists.contact?ifExists.mail?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${user?ifExists.information?ifExists.person?ifExists.initials?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">
                        <#list user.getRoles() as role>
                            <div>${role.getNameByRole(role)}</div>
                        </#list>
                    </div></td>
                <td style="background: #f1f1f1"><div class="mt-2  d-flex justify-content-center" ><a class="pathC" href="/user/${user?ifExists.id?ifExists}" title="Edit"><img style="width: 40px" src="/static/settings-line.svg" alt=""></a></div></td>
            </tr>
        </#list>
        </tbody>
    </table>
    </div>

    <script type="text/javascript" charset="utf8" src="/static/main.js"></script>
    <script type="text/javascript" charset="utf8" src="/static/pathCorrect.js"></script>

</@dom.dom>